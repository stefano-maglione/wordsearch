package com.maglione.stefano.wordsearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class XmlSearchService {

    private static Logger LOG = LoggerFactory.getLogger(XmlSearchService.class);
    private final Printer printer;

    public XmlSearchService(Printer printer) {
        this.printer = printer;
    }

    public void searchInXmlFiles(List<Path> xmlFiles, Set<String> companyNames) {
        try {
            StopWatch timeMeasure = new StopWatch();
            timeMeasure.start("Task 1");
            int parallelism = Runtime.getRuntime().availableProcessors();
            ExecutorService executor = Executors.newFixedThreadPool(parallelism);

            List<CompletableFuture<List<String>>> futures = new ArrayList<>();

            for (Path xmlFile : xmlFiles) {
                String xmlContent = Files.readString(xmlFile);
                String fileName = xmlFile.getFileName().toString();
                CompletableFuture<List<String>> future = CompletableFuture.supplyAsync(() -> searchInXmlFile(fileName, companyNames, xmlContent), executor);
                futures.add(future);
            }

            CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
            CompletableFuture<List<String>> combinedFuture = allFutures.thenApply(v -> futures.stream()
                    .map(CompletableFuture::join)
                    .flatMap(List::stream)
                    .collect(Collectors.toList()));

            List<String> searchResults = combinedFuture.get();
            timeMeasure.stop();

            printer.print(searchResults);
            System.out.println("Last task time in Sec: " + timeMeasure.getTotalTimeSeconds());
            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Error occurred during XML file search: {}", e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<String> searchInXmlFile(String fileName, Set<String> companyNames, String xmlContent) {

        LOG.info("Processing XML file: {}", fileName);

        return companyNames.stream()
                .filter(companyName -> isCompanyMentioned(xmlContent, companyName))
                .map(companyName -> fileName + ": " + companyName)
                .collect(Collectors.toList());
    }

    public boolean isCompanyMentioned(String xmlContent, String companyName) {
        return xmlContent.toLowerCase().contains(companyName.toLowerCase());
    }
}
