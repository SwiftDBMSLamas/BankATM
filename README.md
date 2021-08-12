# Simple Bank Application

This project is a practice implementation of an ATM bank machine application written in Java.

**Note: this project is still in it's finalized stages. Code and UI still need to be cleaned up.**

## Objective

The objective and purpose is to practice creating a simple GUI using Java's Swing library and to practice implementing some design patterns.

## Database used

The MySQL database was chosen for implementation for storage and retrieval of data within the application.
JDBC was used to get the connection to the database.

## Design Patterns used

The following design patterns are used and practiced in this application:

**- Template method**

The template method allowed me to define an algorithm in most of the base classes. This would allow the subclasses to override the steps without changing the overall algorithm's structure. The flexibility of this method makes it much easier to debug the algorithm by looking in the subclasses and and the implemented logic. Inheritance allows the programmer to achieve this flexibility since the subclasses will have overridden the steps, depending on the defects found, the time spent to track down the defect was minimal and the effort was minimal as well.

**- Factory method**

## Getting started

To run the application on your machine:

`clone` the repo onto your local drive
Install or start up your MySQL local server
Run the Driver.java file - upon start, the application will create the tables necessary to run the application

Since normally your 'bank' would help you register your account for online banking, the application's first iteration does not include this.
You will need to "register" for an account by plugging in a client card (16 digits) and creating a PIN. Successful registration will submit this information to the database for later retrieval.
