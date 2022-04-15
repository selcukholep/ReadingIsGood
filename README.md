# ReadingIsGood
ReadingIsGood is an online books retail firm which operates only on the Internet.
Main target of ReadingIsGood is to deliver books from its one centralized warehouse
to their customers within the same day.

## Technology Stack

- Java 17
- Spring Boot
- MongoDB
- Maven
- Docker

## Installation

To start project the following commands needs to be run on project path.
```shell
./start.sh
```

It will take some time. 

All done!

The backend service runs on http://localhost:8080.

After deployment, to show and test API endpoints the followings can be used:

- **Swagger Documentation:** [Open API Endpoint](http://localhost:8080/swagger-ui/index.html)
- **Postman Collection:** [Download](https://raw.githubusercontent.com/selcukholep/ReadingIsGood/main/doc/ReadingIsGood%20-%20API.postman_collection.json)

## Usage

### Login

There is an endpoint(`POST /auth/login`) to login and be authorized user.

This API takes 3 parameters:

| ParameterName | Description                                                                                                                                                                                                                                                   |
|---------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **type**      | Decides authority type, **ADMIN** or **CUSTOMER**. This type allows the project to authorize the request.For example, if the user is an **ADMIN**, he/she cannot create any order. On the other hand the **CUSTOMER**'s cannot create any books or customers. |
| **username**  | The username is a String type which holds emails for **CUSTOMER**s and anything for **ADMIN**s.                                                                                                                                                               |
| **password**  |                                                                                                                                                                                                                                                               |


### Registration

There is a hard-coded **ADMIN** user (No registration endpoint). The credentials of **ADMIN** are given below:

- **username:** admin
- **password:** admin

#### Customer's Registration:

Any authenticated admin user can create a customer (`POST /customer`).
There are only 3 required parameters for persisting a customer; `email`, `name`, `surname`
No `password` required because it is generated automatically.
For example, if customer's email is `username@domain.com`, then the password will be `username`.


### Authentication & Authorization

The most of the endpoints are secured expect `auth/login` and `swagger` endpoints.

> **Authentication:** Bearer <your_token>

Any tokens expire **2 hours** after creation.

> The `ADMIN` user's token generated before, and it is `8f4a0e56-012b-46c6-a29c-052425468f7c`.
> Also, it is reproducible by using `login` endpoint.
> The default admin token have been imported for all Postman requests which can be accessed by only admins.

The sessions are stored in-memory. If the project is restarted, all sessions (except the default administrator session) are dropped.
#### Authorized Endpoints


| Method | Endpoint           | Authority |
|--------|--------------------|-----------|
| ANY    | **/customer/****   | ADMIN     |
| ANY    | **/book/****       | ADMIN     |
| ANY    | **/statistics/**** | ADMIN     |
| POST   | **/order**         | CUSTOMER  |
| GET    | **/order/{id}**    | ADMIN     |
| GET    | **/order/search**  | ADMIN     |

------

## Architecture

In this project multi layered architecture was used.

```.
├── auth                    # Authentication infrastracture.
├── logging                 # Logging infrastracture.
├── controller              # Controller layer.
├── data                    # Business models, DTOs, Repository layer.
└── service                 # Service layer. 
```

### Optimistic Locking

The most important thing for this project is supporting the concurrency and atomicity on updating the book stock.

To overcome this problem, the optimistic locking mechanism was used. Updates were controlled by a `version` and allowed if the `version` has not changed.

> When an order request is received from more than one user at the same time for the same book, only one of them will receive it successfully. Any other requests that get an error are automatically repeated until they succeed or the repeat limit is exceeded.

> The order request is in a transaction so, if any request gets an StockNotEnough exception then the process will be rolled back.

---- 
## Credits

👷 **Selçuk Holep**

- **Linkedin:** [@sholep](https://www.linkedin.com/in/sholep/)

Thank you 😊