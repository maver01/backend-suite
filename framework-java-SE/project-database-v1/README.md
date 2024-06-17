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
8. Create a user (mstestv1) and create a password (test1234) they can use to access database:
   `CREATE USER 'mstestv1'@'localhost' IDENTIFIED BY 'test1234';`
9. List users:
   `SELECT user, host FROM mysql.user;`
10. Exit:
    `exit`

Access as User:

1. Access MySQL as root:
   `sudo mysql -u root -p`
2. Access MYSQL as a specific user:
   `sudo mysql -u mstestv1 -p`

Grant privileges:

1. Give a certain user (mstestv1) all permissions (from root, assuming testv1 is name of database):
   `GRANT ALL PRIVILEGES ON testv1.* TO 'mstestv1'@'localhost';`
2. Saving changes.
   `FLUSH PRIVILEGES;`

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

## Perform CRUD operations on MySQL database in localhost

Assuming the following configuration exists in local machine:

- database type: MySQL
- host: localhost
- user: mstestv1
- password: test1234
- User permissions: All
- database: testv1
- Table: Person (testv1.Person)
- table schema: | Id | name | last_name |

Then the class JDBC can be run to perform basic operations on this table:

1. Add mysql dependency to the pom.xlm file:

```
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
```

2. Create project:
   `mvn clean install`
3. Run class:
   `mvn exec:java -Dexec.mainClass=com.mycompany.app.JDBCExample -Dexec.cleanupDaemonThreads=false`
   Use the cleanupDaemonThreads (optional) to solve a known issue with the class.
