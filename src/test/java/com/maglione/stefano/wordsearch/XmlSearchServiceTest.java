package com.maglione.stefano.wordsearch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class XmlSearchServiceTest {


    @Mock
    private Printer printer;


    @Test
    void searchInXmlFile_ShouldReturnMatchingCompanyNames() {
        // Arrange
        String fileName = "sample.xml";
        Set<String> companyNames = new HashSet<>(Arrays.asList("company1", "company2", "company3"));
        String xmlContent = "<root>Some XML content mentioning company1 and company2</root>";

        XmlSearchService xmlSearchService = new XmlSearchService(printer);

        // Act
        List<String> result = xmlSearchService.searchInXmlFile(fileName, companyNames, xmlContent);

        // Assert
        assertEquals(2, result.size());
        assertEquals("sample.xml: company1", result.get(0));
        assertEquals("sample.xml: company2", result.get(1));
    }

    @Test
    void searchInXmlFile_ShouldReturnEmptyList_WhenNoMatchingCompanyNames() {
        // Arrange
        String fileName = "sample.xml";
        Set<String> companyNames = new HashSet<>(Arrays.asList("company1", "company2", "company3"));
        String xmlContent = "<root>Some XML content without matching company names</root>";

        XmlSearchService xmlSearchService = new XmlSearchService(printer);

        // Act
        List<String> result = xmlSearchService.searchInXmlFile(fileName, companyNames, xmlContent);

        // Assert
        assertEquals(0, result.size());
    }
}
