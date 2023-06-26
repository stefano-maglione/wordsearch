
# Word Search Application

The Word Search Application is a Java-based application built with Spring Boot that allows users to search for specific company names within a collection of XML files. The application reads XML files and a CSV file containing company data, performs the search operation, and produces the search results.

## Technical Overview

### 1. Spring Boot
The Word Search Application is built using the Spring Boot framework, which provides a convenient and powerful way to develop Java-based applications. Spring Boot simplifies the configuration and setup of the application, allowing for rapid development and deployment.

### 2. Asynchronous Processing with CompletableFuture
To achieve parallel processing and improve the overall performance, CompletableFuture from the Java Concurrency API is used. CompletableFuture allows executing tasks asynchronously and combining their results.

By leveraging CompletableFuture, the XML search service can process multiple XML files concurrently, which maximizes CPU utilization and reduces the overall execution time.

### 3. ExecutorService for Thread Pool Management
To manage the concurrency level and handle thread execution, an ExecutorService is utilized. The ExecutorService implementation, Executors.newFixedThreadPool(), is chosen to create a thread pool with a fixed number of threads. The number of threads is determined based on the available processors.

Using an ExecutorService enables efficient utilization of system resources and optimal parallelism for the XML search process.

### 4. Stream API for Data Processing
The Stream API from Java 8+ is utilized for processing and manipulating data during the XML search process. Streams provide a concise and expressive way to perform operations such as filtering, mapping, and collecting data.

By leveraging the Stream API, the XML search service can easily filter XML files based on conditions, map search results to desired output format, and collect the results efficiently.

### 5. Exception Handling and Logging
Proper exception handling and logging are implemented to ensure error-free execution and facilitate debugging. Exceptions during XML file processing, file reading, or other operations are caught, logged, and handled appropriately.

The SLF4J logging framework is used for logging, allowing flexible configuration and integration with various logging implementations.

###  6. Printer Interface for Output
A Printer interface is introduced to decouple the printing functionality from the XML search service. This allows flexibility in choosing different output formats or destinations for the search results.

The Printer interface provides a contract for printing the search results, and the implementation can be easily swapped or extended as needed.

### 7. Data Structures
By utilizing the Set and HashMap data structures in the Word Search Application, the application benefits from efficient storage, retrieval, and manipulation of company names and their counts. The Set ensures uniqueness and fast lookup of company names, while the HashMap enables efficient mapping and retrieval of counts based on the company names. Together, these data structures contribute to the effectiveness and performance of the Word Search Application.






### 
These technical choices aim to provide an efficient, scalable, and maintainable XML search service that can process large volumes of XML files while mitigating memory issues and maximizing performance.








## SOLID Approach in the XML Search Service Code
The XML search service code follows the SOLID principles, which are design principles that promote maintainable and extensible software architectures. Here's how the SOLID principles are applied in the XML search service implementation:

### 1. Single Responsibility Principle (SRP)
The XML search service class (XmlSearchService) adheres to the SRP by having a single responsibility: searching for company names in XML files. It encapsulates the logic related to searching, processing, and extracting search results from XML files. This separation of concerns improves code readability, maintainability, and testability.

### 2. Open/Closed Principle (OCP)
The XML search service code complies with the OCP by being open for extension but closed for modification. The service accepts a printer dependency through dependency injection (constructor injection). This allows different printer implementations to be easily plugged into the service without modifying the existing code. This flexibility enables future enhancements or customization of the printing behavior without impacting the core search functionality.

### 3. Liskov Substitution Principle (LSP)
The LSP is honored in the XML search service code by adhering to the Liskov substitution principle. The service defines the Printer interface, and any implementation of this interface can be used interchangeably in the XmlSearchService class. This promotes substitutability and allows different printer implementations to be used without affecting the correctness or behavior of the XML search service.

### 4. Interface Segregation Principle (ISP)
The XML search service demonstrates the ISP by defining the Printer interface with a single method print(). This interface is focused on a specific responsibility, which is printing search results. By having a fine-grained interface, the implementing classes are not forced to depend on methods they don't need. This promotes loose coupling and improves the maintainability and modularity of the code.

### 5. Dependency Inversion Principle (DIP)
The XML search service follows the DIP by relying on abstractions rather than concrete implementations. The service depends on the Printer interface, which is an abstraction, rather than a specific printer implementation. This inversion of control allows for flexibility and decoupling between the XML search service and the printer implementations. It enables easier testing, promotes modularity, and facilitates future changes or additions to the printer implementations.

By adhering to the SOLID principles, the XML search service code achieves high cohesion, loose coupling, extensibility, and maintainability. It enables easy modification, enhancement, and customization while ensuring that changes to one part of the system have minimal impact on other parts. The SOLID principles contribute to a robust and scalable design that can evolve over time with changing requirements.




## Example Usage of the Word Search Application:
Let's assume you have the following file structure:

```
- wordsearch
  - src
    - main
      - java
        - com.maglione.stefano.wordsearch
          - WordsearchApplication.java
          - XmlSearchService.java
          - CsvParserImpl.java
      - resources
  - target

```

1. Ensure that you have Java and Maven installed on your system.

2. Open a terminal or command prompt and navigate to the root directory of the wordsearch project.

3. Build the project using Maven by executing the following command:


```
mvn clean install

```

4. Once the build is successful, navigate to the target directory:

```
cd target

```

5. Run the Word Search Application by executing the following command:

```
java -jar wordsearch-1.0.0.jar

```
Note: Replace wordsearch-1.0.0.jar with the actual name of the generated JAR file.

6. The application will start and prompt you to enter the absolute folder path containing the XML files.
```
Enter the absolute folder path with the xml files:

```
Provide the absolute path to the folder containing your XML files and press Enter.

7. Next, you will be asked to enter the absolute file path of the CSV file containing the company names.

```
Enter the company CSV absolute file path:

```
Provide the absolute path to the CSV file and press Enter.


8. The application will validate the input folder and CSV file. If they are valid, it will proceed to search for company names in the XML files.

9. After the search is complete, the application will display the search results and the total time taken for the task.

# Search Results

The FilePrinter instance will write the search results and company name counts to the "output.txt" file in the current directory.

Each company name is written together with the file where it is found.

A simple statistic is reported indicating how many times a company name is found.






