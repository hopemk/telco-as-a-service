# Wallet Module for Person - Spring Boot Application

This project implements a wallet management system using Spring Boot, allowing a account to have multiple wallets with different balances.

## Technologies Used

- Spring Boot 3.1.5
- Spring Data JPA
- MySQL Database
- Maven

## Project Structure

The application follows a standard Spring Boot architecture:

```
src/main/java/com/telco/
├── TelcoApplication.java           # Main Spring Boot application class
├── model/                          # Domain models/entities
│   ├── Person.java
│   └── Wallet.java
├── repository/                     # Data access layer
│   ├── PersonRepository.java
│   └── WalletRepository.java
├── service/                        # Business logic layer
│   ├── PersonService.java
│   ├── PersonServiceImpl.java
│   ├── WalletService.java
│   └── WalletServiceImpl.java
├── controller/                     # API endpoints
│   ├── PersonController.java
│   └── WalletController.java
└── exception/                      # Custom exceptions
    ├── ResourceNotFoundException.java
    └── GlobalExceptionHandler.java
```

## Features

### Person Class
- Represents a account who can own multiple wallets
- Each account has a unique ID and a name
- Methods to add, remove, and find wallets
- Method to calculate total balance across all wallets

### Wallet Class
- Represents a wallet that holds a balance
- Each wallet has a unique ID, a name, and a balance
- Methods for depositing and withdrawing funds
- Method for transferring funds between wallets
- Validation to ensure amounts are not negative

## API Endpoints

### Person API

- `GET /api/persons`: Get all persons
- `GET /api/persons/{id}`: Get a account by ID
- `POST /api/persons`: Create a new account
- `PUT /api/persons/{id}`: Update a account
- `DELETE /api/persons/{id}`: Delete a account
- `GET /api/persons/{id}/wallets`: Get all wallets for a account
- `POST /api/persons/{id}/wallets`: Create a new wallet for a account
- `GET /api/persons/{id}/total-balance`: Get total balance for a account

### Wallet API

- `GET /api/wallets`: Get all wallets
- `GET /api/wallets/{id}`: Get a wallet by ID
- `PUT /api/wallets/{id}`: Update a wallet
- `DELETE /api/wallets/{id}`: Delete a wallet
- `POST /api/wallets/{id}/deposit`: Deposit funds to a wallet
- `POST /api/wallets/{id}/withdraw`: Withdraw funds from a wallet
- `POST /api/wallets/{sourceId}/transfer/{destinationId}`: Transfer funds between wallets

### Wallet Type API

- `GET /api/wallet-types`: Get all wallet types
- `GET /api/wallet-types/status/{status}`: Get wallet types by status
- `GET /api/wallet-types/{id}`: Get a wallet type by ID
- `GET /api/wallet-types/name/{name}`: Get a wallet type by name
- `POST /api/wallet-types`: Create a new wallet type
- `PUT /api/wallet-types/{id}`: Update a wallet type
- `DELETE /api/wallet-types/{id}`: Delete a wallet type
- `PUT /api/wallet-types/{id}/activate`: Activate a wallet type
- `PUT /api/wallet-types/{id}/deactivate`: Deactivate a wallet type

## Running the Application

1. Clone the repository
2. Set up a MySQL database:
   - Create a database named `telcodb`
   - Update the database credentials in `application.properties` if needed
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`
5. Access the API at `http://localhost:8080/api`

## License

See the LICENSE file for details.
