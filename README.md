# Simple Bank Application


**If you have ANY feedback, please feel free to share by reporting an issue. I would love feedback as that will make me better in the long run!**

## Objective

The objective of this project is to gain applied practice creating a simple bank application that interacts
with a database to perform CRUD operations. This provided me an opportunity to take a swing at Java's Swing library (no pun intended)
to get a feel for what it's like to design my own UI. This project also allowed me to practice implementing
some known design patterns.

***If you are uninterested about hearing my story behind this project, feel free to skip down to "Getting started"***

If you stumbled upon this repo and have any feedback relating to the codebase, please feel free to share by opening an issue.
I would appreciate the feedback as that will help me improve in the long run! Please see "Opening an issue" for a template.
## Backend Implementation

MySQL database was chosen for implementation for storage and retrieval of data within the application. I felt this was appropriate
for the project since it is on the smaller-scale side of things. Using MySQL allowed me to practice relational databases and SQL syntax.
The JDBC driver is used to get and maintain the connection to the database. 

## PIN Security & Encryption

I didn't want to "finish" this project with exposing user's PINs in the database, so I felt I would give it a try to encrypt and hash the PINs in the database.

The `PINCryptUtils.java` class handles this hashing and encryption using salt values.

## Design Patterns Used

The following design patterns are used and practiced in this application:

### **Template method**

The template method allowed me to define the steps for the algorithms in most of the base classes. 
This allows the subclasses to override the required steps without changing the overall algorithm's structure. 
Inheritance allows me to achieve greater flexibility since the subclasses will decide the algorithm's implementation at runtime.

In the performTransaction() method in the Transaction class `protected abstract ... calculate()` was the part of the algorithm
in performTransaction() that required the subclasses to override to provide the implementation. In this case,
the `calculate` method in `Deposit` is different from the `calculate` method in `Withdrawal`.

Doing the above allowed me to mark the rest of the steps in `performTransaction()` as final and to only let the 2 subclasses provide the implementation
for `calculate`.

### **Factory method**

The factory method is probably one of the most used design patterns in Java. 
Allowing the subclasses in this project decide which class to instantiate through inheritance allows 
more flexibility in the code by lessening the amount of times I instantiate a new object; I'm able to make changes to
instantiation in the factory class, rather than scrolling through lines of code to fix. 
If there are changes to the code that require you to change where you called "new" on, rather than having ***X*** amount of classes to sift through and fix, 
the factory method gives me one specific class to make this change.
The transaction classes in this project are a good example of using the factory method. A withdrawal or deposit is-a transaction, 
so creating a factory method to instantiate the transaction subclass during runtime depending on what type of transaction is being created allows me to call the factory method and let the compiler deal with which class to instantiate.

### **Data Access Object (DAO)**

The Data Access Object or DAO pattern is used to separate low level data from high level business services.
This design pattern was something that I learned along the way with this project. I'm still not 100% sure if the implementation is correct,
but based on my understanding of the design pattern, I believe it was achieved to an extent.

The design pattern is used to hide the complexities in performing CRUD operations. This allows both layers to evolve separately
without knowing anything about each other.

I started with an interface with CRUD methods (create, read, update, delete) and eventually ended up with
an abstract class that implemented these methods. I then used the template method and had the subclasses
decide on how to implement the logic. The subclasses had the complex logic with querying to the database and then either doing:

1. Returning an object to be used

2. Updating an object

In this project, a "client" object was returned and allowed me to pass the object around as needed.
To sum it up- the subclasses contained the complex logic with querying the database while the business logic in the application
just needed to call the methods and let the subclasses handle the logic.

(If I somehow misunderstood the design pattern, please feel free to let me know. I'm still learning :) )

## Getting started

To run the application on your machine:

1. `clone` the repo onto your local drive
2. Install or start up your MySQL local server - your localhost url may be different, if that is the case- you will need to modify the `res_en.properties` file and change the database url to your local url.
    2. I do not think you would need to change the url as they should all be similar `localhost:3306`
    3. If anything, you will need to change the db.url and db.password properties in the `res_en.properties` file to match whatever you have set up.
3. Run the Driver.java file - upon start, the application will create the tables necessary to run the application
4. When the application runs, register an account first by clicking the `Create Account` button so that you can log in and play around with the UI

The UI is very simple as this was my first time using Java's Swing library. There's no fancy transitions or animations as that was not the intention of this project.


You will need to "register" for an account by clicking the `Create Account` button and filling out the form. 
Successful registration will submit this information to the database for later retrieval. You can then use this to log into the application.
Your `Client ID` will be displayed to you, or you can simply access the database and look for your Client ID.

**Warning: I do not keep a copy of the PIN in the database for security reasons. So if you forget the PIN you set, you will need to create a new account.** 
