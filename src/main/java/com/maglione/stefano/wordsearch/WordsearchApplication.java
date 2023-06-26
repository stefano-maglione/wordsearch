package com.maglione.stefano.wordsearch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;


@SpringBootApplication
class WordsearchApplication implements CommandLineRunner {

    private XmlSearchService xmlSearchService;
    private final CsvParserImpl csvParser;

    public WordsearchApplication(XmlSearchService xmlSearchService, CsvParserImpl csvParser) {
        this.xmlSearchService = xmlSearchService;
        this.csvParser = csvParser;
    }

    private static Logger LOG = LoggerFactory
            .getLogger(WordsearchApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WordsearchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("EXECUTING");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the absolute folder path with the xml files: ");
        String xmlFolderPath = scanner.nextLine();

        System.out.print("Enter the company CSV absolute file path: ");
        String csvFilePath = scanner.nextLine();

        // Check if the folder path is a valid directory
        Path xmlFolder = Paths.get(xmlFolderPath);
        if (!Files.isDirectory(xmlFolder)) {
            String errorMessage = "Invalid folder path: " + xmlFolderPath;
            LOG.error(errorMessage);
            return;
        }

        // Check if the CSV file path is a valid file
        Path csvFile = Paths.get(csvFilePath);
        if (!Files.isRegularFile(csvFile)) {
            String errorMessage = "Invalid CSV file path: " + csvFilePath;
            LOG.error(errorMessage);
            return;
        }

        Set<String> companyNames = csvParser.parseCompanyNames(csvFilePath);
        List<Path> xmlFiles = Files.walk(xmlFolder)
                .parallel()
                .filter(path -> path.toString().endsWith(".xml"))
                .collect(Collectors.toList());

        xmlSearchService.searchInXmlFiles(xmlFiles, companyNames);

        LOG.info("APPLICATION FINISHED");
    }

}










