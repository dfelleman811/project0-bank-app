# project0-bank-app
Console-based Banking Application

This is an application completed for Project 0 of the 2101 Java with ETL training cohort. 

Built with a DAO design patter and JDBC to connect with AWS RDS. 

Entities: 

User - anyone can create a user account that will be stored in the database.

Bank Account - anyone can open multiple accounts and complete transactions. Also stored in database.

Transaction - anytime a user withdraws or deposits money to their Bank Account. Also stored in database.

Features Completed:
1.	A registered user can login with their username and password.
2.	An unregistered user can register by creating a username and password.
3.	A superuser can view, create, update, and delete all users.
4.	A user can view their own existing accounts and balances.
5.	A user can create an account. 
6.	A user can delete an account if it is empty. 
7.	A user can deposit to or withdraw from an account. 
8.	A user can execute multiple deposits or withdrawals in a session. 
9.	A user can log out. 
10.	All user actions provide validation messages through the console.
11.	The application implements a scanner object to collect user input.


12.	The application is built and communicates to the database using the DAO design pattern.
13.	User Ids, Bank Account Numbers, and Transaction Ids are all generated using sequences.
14.	There are three stored PL/SQL procedures: to add a user, bank account, and transaction. 
15.	A user’s transactions are recorded and stored in a transactions table.
16.	A user can view their transaction history.
17.	I constructed Junit tests as I was building the app to test that the DAO/Services were working appropriately.
 
