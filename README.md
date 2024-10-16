# Spring POS API

A Point of Sale (POS) RESTful API developed using Spring. It includes CRUD operations and transaction management for Customer, Item, Order, and OrderDetail entities. The project is seamlessly integrated with Hibernate for ORM and JPA for repository management. 

## Features

- CRUD operations for Customer, Item, Order, and OrderDetail entities.
- Transaction management.
- Validation using Hibernate Validator.
- Logging using Logback.
- Custom exceptions and error messages.
- DTO and Entity mapping using ModelMapper.
- Utility for Base64 image conversion.
- Configuration files for application setup.

## Tech Stack

- **Java 17**
- **Spring Framework**
- **Hibernate ORM**
- **Spring Data JPA**
- **ModelMapper**
- **Logback**
- **MySQL**

## Project Structure

- **Entities**: Customer, Item, Order, OrderDetail
- **DTOs**: CustomerDTO, ItemDTO, OrderDTO
- **Repositories**: CustomerRepository, ItemRepository, OrderRepository, OrderDetailRepository
- **Services**: CustomerService, ItemService, OrderService
- **Controllers**: CustomerController, ItemController, OrderController
- **Utilities**: Mapping, AppUtil
- **Exceptions**: Custom exceptions for handling specific error cases
- **Configuration**: WebAppConfig, WebAppRootConfig

## Validation

Validation is implemented using Hibernate Validator annotations in the DTO classes to ensure data integrity and correctness.

## Logging

Logging is configured using Logback. Logs are written to both the console and a file.

## Custom Exceptions

Custom exceptions are created to handle specific error scenarios, providing meaningful error messages to the client.

## Utilities

- **Mapping**: Utility class for converting between DTOs and entities using ModelMapper.
- **AppUtil**: Utility class for converting MultipartFile to Base64 string for image storage.

## Configuration

Configuration files are used to set up the application context, data source, JPA, and transaction management.

## Postman Documentation

For detailed API documentation and testing, please refer to the [Postman Documentation](https://documenter.getpostman.com/view/36681432/2sAXxTcWWN).

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Getting Started

### Prerequisites

- Java 17
- MySQL
- Maven

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/gayanukabulegoda/Spring-POS-API.git
    ```
2. Navigate to the project directory:
    ```sh
    cd Spring-POS-API
    ```
3. Update the MySQL database configuration in `WebAppRootConfig.java`:
    ```java
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dmds = new DriverManagerDataSource();
        dmds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dmds.setUrl("jdbc:mysql://localhost:3306/spring_pos_api?createDatabaseIfNotExist=true");
        dmds.setUsername("root");
        dmds.setPassword("your_password");
        return dmds;
    }
    ```
4. Build the project using Maven:
    ```sh
    mvn clean install
    ```
5. Deploy the WAR file to your preferred servlet container (e.g., Tomcat).

### Usage

- Access the API endpoints using your preferred API client (e.g., Postman).
- Refer to the Postman documentation for detailed API usage.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

##
<div align="center">
<a href="https://github.com/gayanukabulegoda" target="_blank"><img src = "https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"></a>
<a href="https://git-scm.com/" target="_blank"><img src = "https://img.shields.io/badge/Git-100000?style=for-the-badge&logo=git&logoColor=white"></a>
<a href="https://spring.io/projects/spring-framework" target="_blank"><img src = "https://img.shields.io/badge/Spring_Framework-100000?style=for-the-badge&logo=spring&logoColor=white"></a>
<a href="https://spring.io/projects/spring-data-jpa" target="_blank"><img src = "https://img.shields.io/badge/Spring_Data_JPA-100000?style=for-the-badge&logo=spring&logoColor=white"></a>
<a href="https://hibernate.org/orm/" target="_blank"><img src = "https://img.shields.io/badge/Hibernate-100000?style=for-the-badge&logo=Hibernate&logoColor=white"></a>
<a href="https://tomcat.apache.org/" target="_blank"><img src = "https://img.shields.io/badge/Tomcat-100000?style=for-the-badge&logo=apachetomcat&logoColor=white"></a>
<a href="https://logback.qos.ch/documentation.html" target="_blank"><img src = "https://img.shields.io/badge/Logback-100000?style=for-the-badge&logo=ko-fi&logoColor=white"></a>
<a href="https://maven.apache.org/download.cgi" target="_blank"><img src = "https://img.shields.io/badge/Maven-100000?style=for-the-badge&logo=apachemaven&logoColor=white"></a>
<a href="https://www.mysql.com/downloads/" target="_blank"><img src = "https://img.shields.io/badge/Mysql-100000?style=for-the-badge&logo=mysql&logoColor=white"></a>
<a href="https://www.postman.com/downloads/" target="_blank"><img src = "https://img.shields.io/badge/Postman-100000?style=for-the-badge&logo=Postman&logoColor=white"></a>
</div> <br>
<p align="center">
  &copy; 2024 Gayanuka Bulegoda
</p>
