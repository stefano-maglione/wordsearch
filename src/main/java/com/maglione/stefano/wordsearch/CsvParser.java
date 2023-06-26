package com.maglione.stefano.wordsearch;

import java.io.IOException;
import java.util.Set;

public interface CsvParser {
    Set<String> parseCompanyNames(String csvFilePath) throws IOException;
}

