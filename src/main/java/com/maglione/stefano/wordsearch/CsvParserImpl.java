package com.maglione.stefano.wordsearch;


import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CsvParserImpl implements CsvParser {
    public Set<String> parseCompanyNames(String csvFilePath) throws IOException {
        return Files.lines(Paths.get(csvFilePath))
                .skip(1) // Skip header line
                .map(line -> line.split(";")[1])
                .map(word -> word.replaceAll("[^a-zA-Z0-9\\s]", "").trim())
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toSet());
    }
}
