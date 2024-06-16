This project contains scripts that use JDBC to perform basic interactions with a MySQL database, installed locally.

## PART 1: Quick setup MySQL in Ubuntu:

Use this tutorial to install and setup MySQL database in Ubuntu local machine: https://likegeeks.com/mysql-on-linux-beginners-tutorial/

Setup MySQL:

1. Install MySQL database in Ubuntu machine:
   `sudo apt install mysql-server`
2. Check installation:
   `mysql --version`
3. Start the database (db):
   `sudo systemctl start mysql`
4. Check that the db is running:
   `sudo systemctl status mysql`
5. Check which port the instance is runnin on:
   `sudo netstat -tlpn | grep mysql`
6. Define root user (who manages all databases)
   `sudo mysql_secure_installation`
7. Access MySQL shell as the root user:
   `sudo mysql -u root -p`
   Input password created in previous point, or press ENTER if none was created.
8. Create a user and create a password they can use to access database:
   `CREATE USER 'user-name-v1'@'localhost' IDENTIFIED BY 'test1234';`
9. Exit:
   `exit`

Create Users:

1. Access MySQL as root:
   `sudo mysql -u user-name-v1 -p`
2. Access MYSQL as a specific user:
   `sudo mysql -u root -p`

Grant privileges:

1. Give a certain user permissions (from root, assuming test is name of db):
   `GRANT ALL PRIVILEGES ON test.* TO 'user'@'localhost';`

Hello World example with a database, CRUD operations. This database is called testv1, with one table called Person.

1. Create databse:
   `CREATE DATABASE testv1;`
2. Show databases:
   `SHOW DATABASES;`
3. Use database:
   `USE testv1`
4. Create a table:
   `CREATE TABLE Person (Id int(10) NOT NULL, name varchar(10), last_name varchar(10));`
5. Insert a value inside the table:
   `INSERT INTO Person (Id, name, last_name) VALUES (1,'Richard','Winters');`
6. Visualize table:
   `SELECT * FROM Person`
