package com.maglione.stefano.wordsearch;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Primary
public class FilePrinter implements Printer {

    private static final String OUTPUT_FILE_NAME = "output.txt";

    @Override
    public void print(List<String> searchResults) {
        try {
            Path currentPath = Paths.get("");
            String outputPath = currentPath.toAbsolutePath().resolve(OUTPUT_FILE_NAME).toString();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
                writer.write("Company Name Found:");
                writer.newLine();

                Map<String, Integer> companyCounts = new HashMap<>();

                for (String result : searchResults) {
                    String[] parts = result.split(":");
                    String fileName = parts[0].trim();
                    String companyName = parts[1].trim();

                    // Update the count for the company name
                    int count = companyCounts.getOrDefault(companyName, 0);
                    companyCounts.put(companyName, count + 1);

                    String output = "Company Name: " + companyName + " found in file: " + fileName;
                    writer.write(output);
                    writer.newLine();
                }

                writer.newLine();
                writer.write("Company Name Counts:");
                writer.newLine();

                for (Map.Entry<String, Integer> entry : companyCounts.entrySet()) {
                    String companyName = entry.getKey();
                    int count = entry.getValue();
                    String output = "Company Name: " + companyName + " - Count: " + count;
                    writer.write(output);
                    writer.newLine();
                }

                writer.newLine();

                System.out.println("Results written to file: " + outputPath);
            }
        } catch (IOException e) {
            System.out.println("Error occurred while writing results to file: " + e.getMessage());
        }
    }
}
