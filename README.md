# Simple Bank Application

This project is a practice implementation of an ATM bank machine application written in Java.

***Don't care about any of my explanations? Skip down to "Getting started" :)***

**If you have ANY feedback, please feel free to share. I would love feedback as that will make me better in the long run!**

## Objective

The objective and purpose of this project is to practice creating a simple GUI using Java's Swing library 
and to practice implementing some design patterns.

## Backend Implementation

MySQL database was chosen for implementation for storage and retrieval of data within the application. I felt this was appropriate
for the project since it is on the smaller-scale side of things. Using MySQL allowed me to practice relational databases and SQL.
The JDBC driver is used to get the connection to the database.

## Design Patterns used

The following design patterns are used and practiced in this application:

## **Template method**

The template method allowed me to define step algorithms in most of the base classes. 
This allows the subclasses to override the steps without changing the overall algorithm's structure. 
Inheritance allows me to achieve greater flexibility since the subclasses will decide the algorithm's implementation at runtime.

## **Factory method**

The factory method is probably one of the most used design patterns in Java. 
Allowing the subclasses in this project decide which class to instantiate through inheritance allows 
more flexibility in the code by lessening the amount of times I instantiate a new object; I'm able to make changes to
instantiation in the factory class, rather than scrolling through lines of code to fix. 
If there are changes to the code that require you to change where you called "new" on, rather than having ***X*** amount of classes to sift through and fix, 
the factory method gives me one specific class to make this change.
The transaction classes in this project are a good example of using the factory method. A withdrawal or deposit is-a transaction, 
so creating a factory method to instantiate the transaction subclass during runtime depending on what type of transaction is being created allows me to call the factory method and let the compiler deal with which class to instantiate.

## **Data Access Object (DAO)**

The Data Access Object or DAO pattern is used to separate low level data from high level business services.
This design pattern was something that I learned along the way with this project. I'm still not 100% sure if the implementation is correct,
but based on my understanding of the design patter, I believe it was achieved to an extent.

The design pattern is used to hide the complexities in performing CRUD operations. This allows both layers to evolve separately
without knowing anything about each other.

I started with an interface with CRUD methods (create, read, update, delete) and eventually ended up with
an abstract class that implemented these methods. I then used the template method and had the subclasses
decide on how to implement the logic. The subclasses had the complex logic with querying to the database and then either:

A. Returned an object to be used

B. Updated an object

In this project, a "client" object was typically returned and allowed me to pass the object around as needed.
To sum it up- the subclasses contained the complex logic with querying the database while the business logic in the application
just needed to call the methods and let the subclasses handle the logic.

(If I somehow misunderstood the design pattern, please feel free to let me know. I'm still learning :) )

## Getting started

To run the application on your machine:

1. `clone` the repo onto your local drive
2. Install or start up your MySQL local server - your localhost url may be different, if that is the case- you will need to modify the `res_en.properties` file and change the database url to your local url
3. Run the Driver.java file - upon start, the application will create the tables necessary to run the application
4. When the application runs, register an account first by clicking the `Create Account` button so that you can log in and play around with the UI

The UI is very simple as this was my first time using Java's Swing library. There's no fancy transitions or animations as that wasn't the intention of this project.


You will need to "register" for an account by clicking the `Create Account` button and filling out the form. 
Successful registration will submit this information to the database for later retrieval. You can then use this to log into the application.
Your `Client ID` will be displayed to you, or you can simply access the database and look for your Client ID.
