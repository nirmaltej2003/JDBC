# JDBC Overview

**JDBC (Java Database Connectivity)** is an API in Java that allows Java applications to interact with databases. It provides methods to query and update data in a database using SQL.

## Key Features

- **Database Independent:** Works with any database that provides a JDBC driver.
- **Easy to Use:** Provides standard methods for connecting, querying, and updating databases.
- **Supports SQL:** Execute SQL queries directly from Java programs.
- **Secure:** Supports prepared statements to prevent SQL injection.
- **Scalable:** Can be used in both small and large applications.

## JDBC Architecture

1. **JDBC API:** Provides interfaces and classes for database operations.
2. **JDBC Driver:** Translates JDBC calls into database-specific calls.
3. **Database:** The backend database like MySQL, Oracle, SQL Server, PostgreSQL, etc.

## JDBC Components

- **DriverManager:** Manages a list of database drivers.
- **Connection:** Represents a connection to a specific database.
- **Statement:** Used to execute SQL queries.
  - `Statement` – For general queries.
  - `PreparedStatement` – For precompiled queries (faster and secure).
  - `CallableStatement` – For executing stored procedures.
- **ResultSet:** Stores the result returned by SQL queries.
- **SQLException:** Handles database errors.

## Basic JDBC Workflow

1. **Load the JDBC Driver**  
2. **Establish Connection** using `DriverManager.getConnection()`  
3. **Create Statement** object  
4. **Execute SQL Query** using `executeQuery()` or `executeUpdate()`  
5. **Process ResultSet**  
6. **Close Connection**  

## Example

```java
import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb", "root", "password");

            // Create statement
            Statement stmt = con.createStatement();

            // Execute query
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            // Process result
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }

            // Close connection
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
