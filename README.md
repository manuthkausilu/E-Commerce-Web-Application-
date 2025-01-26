# E-commerce-Web-app

## Introduction

The **E-Commerce Web App** is a full-featured online shopping platform that allows users to browse products, add them to a shopping cart, and make purchases. The app provides functionalities for user authentication, product management, order management.

## Features

- **User Authentication:** Secure user login and registration.
- **Product Listing:** Browse products by category, with search functionality.
- **Shopping Cart:** Add, update, or remove items from the cart.
- **Order Management:** Manage and track orders.
- **Admin Panel:** Admin functionalities for managing products, categories, and orders.

## Tech Stack

- **Frontend:** JSP, HTML, CSS, Bootstrap
- **Backend:** Java Servlets, Hibernate (ORM)
- **Database:** MySQL
- **Build Tool:** Maven
- **Server:** Apache Tomcat

## Architecture

The application follows the Model-View-Controller (MVC) design pattern:

- **Model:** Hibernate entities representing the application's data.
- **View:** JSP pages for the presentation layer.
- **Controller:** Servlets managing the interaction between the view and the model.

## Installation

### Prerequisites

- Java 11 or higher
- Apache Tomcat 9 or higher
- MySQL 5.7 or higher
- Maven 3.6 or higher

### Steps

1. **Clone the repository:**

   ```bash
   git clone https://github.com/manuthkausilu/E-Commerce-Web-Application-.git
   cd E-Commerce-Web-app
   ```

2. **Configure MySQL:**

   Update the `hibernate.cfg.xml` file with your MySQL database credentials.

3. **Build the project:**

   ```bash
   mvn clean install
   ```

4. **Deploy on Tomcat:**

   - Copy the generated WAR file from the `target/` directory to the `webapps/` directory of your Tomcat server.
   - Start the Tomcat server.

## Database Setup

1. **Create MySQL Database:**

   ```sql
   CREATE DATABASE Ecommerce;
   ```
   
2. **Configure Hibernate:**

   Update `hibernate.cfg.xml` with your database connection details (username, password, URL).

## Usage

- **Admin Panel:** Accessible at `/admin.jsp`. Allows managing products, categories, and orders.
- **User Section:** Register, login, browse products, add to cart, and place orders.


## Contact

**Manuth Kausilu**   
[LinkedIn](https://www.linkedin.com/in/manuth-kausilu-732535316/) 
