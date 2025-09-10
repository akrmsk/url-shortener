LinkLeap | A Modern URL Shortener
<div align="center">

</div>

LinkLeap is a full-stack URL shortening application built with a powerful Spring Boot backend and a sleek, modern vanilla JavaScript frontend. It allows users to transform long, unwieldy URLs into short, memorable links that are easy to share, and also generates a QR code for each shortened link.

Features
Shorten Long URLs: Convert any valid URL into a compact, 8-character short link.

Instant Redirection: Use the short link to redirect to the original URL seamlessly.

Modern Frontend: A beautiful, responsive user interface built with HTML, CSS, and vanilla JavaScript.

QR Code Generation: Automatically generates a scannable QR code for every shortened link.

Copy to Clipboard: Easily copy the new short URL with a single click.

Secure Configuration: Uses Spring Profiles to keep database credentials secure and out of version control.

RESTful API: A clean and well-defined API for creating and managing links.

Tech Stack:
Backend
Java 17+: Core programming language.

Spring Boot 3: Framework for building the REST API.

Spring Data JPA (Hibernate): For database interaction and object-relational mapping.

PostgreSQL: Robust, open-source relational database.

Maven: Dependency management and build tool.
<img width="1905" height="873" alt="image" src="https://github.com/user-attachments/assets/a7874c6c-9644-4409-ad68-5e223130459a" />

Frontend:
HTML5: The structure of the web page.

CSS3: Custom styling for a modern, responsive design.

Vanilla JavaScript (ES6+): For DOM manipulation and API communication using the Fetch API.

qrcode.js: Library for generating QR codes on the client side.

Getting Started
To get a local copy up and running, follow these simple steps.

Prerequisites
Make sure you have the following software installed on your machine:

Java (JDK) 17 or newer.

Apache Maven.

PostgreSQL database server.

An IDE like IntelliJ IDEA or VS Code.

Git for version control.

VS Code with the "Live Server" extension for running the frontend.

1. Backend Setup
Clone the repository:

git clone [https://github.com/YOUR_USERNAME/url-shortener.git](https://github.com/YOUR_USERNAME/url-shortener.git)
cd url-shortener

Create the Database:
Open your PostgreSQL client (e.g., psql or pgAdmin) and create a new database.

CREATE DATABASE urlshortener;

Configure Your Secrets:
The project uses Spring Profiles to protect your database credentials.

Navigate to src/main/resources/.

Create a new file named application-local.properties.

Add your database details to this new file. This file is included in .gitignore and will not be committed.

# This is your secret file (application-local.properties)
DB_URL=jdbc:postgresql://localhost:5432/urlshortener
DB_USERNAME=your_postgres_username
DB_PASSWORD=your_postgres_password

Note: Make sure the port and database name match your setup.

Activate the 'local' Profile in your IDE:

For IntelliJ IDEA: Go to Run > Edit Configurations.... In the "Environment variables" field, add SPRING_PROFILES_ACTIVE=local.

For other IDEs/command line: Set the environment variable before running the application.

Run the Backend:
Open the project in your IDE and run the main application class (UrlShortenerApplication.java). The server will start on http://localhost:8080.

2. Frontend Setup
Navigate to the frontend directory:
Open the url-shortener-frontend folder in a separate VS Code window.

Run with Live Server:

Make sure you have the "Live Server" extension installed in VS Code.

Right-click on the index.html file.

Select "Open with Live Server".

Your application is now live! Your browser should open to http://127.0.0.1:5500 (or a similar address), and you can now use the application.

API Usage
The backend exposes a simple REST API.

Create a Short URL
Endpoint: POST /shortenUrl

Description: Takes a long URL and returns the newly created short link.

Request Body:

{
    "long_url": "[https://www.very-long-and-complex-url.com/with/some/path](https://www.very-long-and-complex-url.com/with/some/path)"
}

Success Response (201 Created):

{
    "short_url": "http://localhost:8080/aB3xZ9c1"
}

Redirect to Long URL
Endpoint: GET /{shortCode}

Description: Redirects the user to the original long URL associated with the short code.

Example:

A user navigates to http://localhost:8080/aB3xZ9c1.

The server responds with an HTTP 302 Found redirect to the original URL.
