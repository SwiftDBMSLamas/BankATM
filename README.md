# Simple Bank Application

This project is a practice implementation of an ATM bank machine application written in Java.

**Note: this project is still in it's finalized stages. Code and UI still need to be cleaned up.**

## Objective

The objective and purpose is to practice creating a simple GUI using Java's Swing library and to practice implementing some design patterns.

## Database used

The MySQL database was chosen for implementation for storage and retrieval of data within the application.
The JDBC driver is used to get the connection to the database.

## Design Patterns used

The following design patterns are used and practiced in this application:

**- Template method**

The template method allowed me to define an algorithm in most of the base classes. This would allow the subclasses to override the steps without changing the overall algorithm's structure. The flexibility of this method makes it much easier to debug the algorithm by looking in the subclasses and and the implemented logic. Inheritance allows the programmer to achieve this flexibility since the subclasses will have overridden the steps, depending on the defects found, the time spent to track down the defect was minimal and the effort was minimal as well.

**- Factory method**

The factory method is probably one of the most used design patterns in Java. Allowing the subclasses in this project decide which class to instantiate through inheritance allows more flexibility in the code. If there are changes to the code that require you to change where you called "new" on, rather than having X amount of classes to sift through and fix, the factory method gives you one specific class to do this change. If you need to change where or what you called "new" on in one place, that allows more maintainable code since there is only **one** Java class to modify. In a way, it's used to hide away the use of saying "new" on an object across your classes.
The transaction classes in this project are a good example of using the factory method. A withdrawal or deposit is-a transaction, so creating a factory method to instantiate the transaction subclass during runtime depending on what type of transaction is being created allows me to call the factory method and let the compiler deal with which class to instantiate.

## Getting started

To run the application on your machine:

1. `clone` the repo onto your local drive
2. Install or start up your MySQL local server - your localhost url may be different, if that is the case- you will need to modify the `res_en.properties` file and change the database url to your local url
3. Run the Driver.java file - upon start, the application will create the tables necessary to run the application
4. When the application runs, register an account first so that you can log in and play around with the UI

The UI is very simple as this was my first time using Java's Swing library, hopefully in the future the UI's will be much better with more practice. :)

Normally your 'bank' would help you register your account for online banking, the application's first iteration does not include this.
You will need to "register" for an account by clicking the 'register' button and plugging in a client card (16 digits) and creating a PIN. Successful registration will submit this information to the database for later retrieval. You can then use this to log into the application.
