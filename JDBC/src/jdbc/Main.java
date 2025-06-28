package jdbc;
import java .sql.*;
import java.util.*;
public class Main {
	private static final String url="jdbc:mysql://127.0.0.1:3306/jdbc";
	private static final String username="root";
	private static final String password="2003";
	
	public static void main(String[] args) {
		//Drivers Load
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
//-------------------------------------Connection Establishment --------------------------------------------//
//-------------------------------------Statement Interface--------------------------------------------------//
		//------------------------------Data Retrieval------------------------------------------------------------
		try 
		{
			Connection connection=DriverManager.getConnection(url,username,password);
			Statement statement=connection.createStatement();
			String query="select * from students";
			ResultSet resultset=statement.executeQuery(query);
			while(resultset.next())
			{
				int id =resultset.getInt("id");
				String name =resultset.getString("name");
				int age =resultset.getInt("age");
				double marks =resultset.getDouble("marks");
				System.out.println("ID:"+id);
				System.out.println("Name:"+name);
				System.out.println("Age:"+age);
				System.out.println("Marks:"+marks);
			}
		} 
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	 //----------------------------Data Insertion---------------------------------------------------------------
		try 
		{
			Connection connection=DriverManager.getConnection(url,username,password);
			Statement statement=connection.createStatement();
			String query=String.format("Insert into students(name,age,marks)Values('%s',%d,%f)","Nancy",25,85.5); 
			int rowsAffected=statement.executeUpdate(query);
			if(rowsAffected>0)
			{
				System.out.println("Data inserted successfully!");
			}
			else {
				System.out.println("Data not inserted");
			}
		} 
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	//--------------------------------Data Update --------------------------------------------------------------
		try 
		{
			Connection connection=DriverManager.getConnection(url,username,password);
			Statement statement=connection.createStatement();
			String query=String.format("Update students SET marks=%f where id=%d",91.5 ,2); 
			int rowsAffected=statement.executeUpdate(query);
			if(rowsAffected>0)
			{
				System.out.println("Data updated successfully!");
			}
			else {
				System.out.println("Data not updated");
			}
		} 
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	//------------------------------------Data Deletion---------------------------------------------------------
		try 
		{
			Connection connection=DriverManager.getConnection(url,username,password);
			Statement statement=connection.createStatement();
			String query=String.format("Delete from students where id=2"); 
			int rowsAffected=statement.executeUpdate(query);
			if(rowsAffected>0)
			{
				System.out.println("Data deleted successfully!");
			}
			else {
				System.out.println("Data not deleted");
			}
		} 
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
//-------------------------------------PreparedStatement Interface------------------------------------------//
		//-----------------------------Data Insertion----------------------------------------------------------------
		try 
		{
			Connection connection=DriverManager.getConnection(url,username,password);
			String query="Insert into students(name,age,marks)Values(?,?,?)"; 
			PreparedStatement preparedstatement =connection.prepareStatement(query);
			preparedstatement.setString(1,"Nancy Jewel Mcdonie");
			preparedstatement.setInt(2,25);
			preparedstatement.setDouble(3,97.5);
			int rowsAffected=preparedstatement.executeUpdate();
			if(rowsAffected>0)
			{
				System.out.println("Data Inserted successfully!");
			}
			else {
				System.out.println("Data not Inserted");
			}
		} 
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		//-------------------------------------------------Data Update----------------------------------------
		try 
		{
			Connection connection=DriverManager.getConnection(url,username,password);
			String query="update students set marks =? where id=? ";
			
			PreparedStatement preparedstatement =connection.prepareStatement(query);
			preparedstatement.setDouble(1,87.9);
			preparedstatement.setInt(2,3);
			int rowsAffected=preparedstatement.executeUpdate();
			if(rowsAffected>0)
			{
				System.out.println("Data Updated successfully!");
			}
			else {
				System.out.println("Data not Updated");
			}
		} 
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		//----------------------------------------Data Retrieval-----------------------------------------------
		
		try 
		{
			Connection connection=DriverManager.getConnection(url,username,password);
			String query="Select marks from students where id=?";
			PreparedStatement preparedstatement =connection.prepareStatement(query);
			preparedstatement.setInt(1,1);
			ResultSet resultset=preparedstatement.executeQuery();
			if(resultset.next())
			{
				double marks=resultset.getDouble("marks");
				System.out.println("Marks:"+marks);
			}
			else
			{
				System.out.println("Marks not found");
			}
		} 
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		//-----------------------------------------Data Deletion------------------------------------------------
		try 
		{
			Connection connection=DriverManager.getConnection(url,username,password);
			String query="Delete from students where id=? ";
			PreparedStatement preparedstatement =connection.prepareStatement(query);
			preparedstatement.setDouble(1,3);
			int rowsAffected=preparedstatement.executeUpdate();
			if(rowsAffected>0)
			{
				System.out.println("Data Deleted successfully!");
			}
			else {
				System.out.println("Data not Deleted");
			}
		} 
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	//----------------------------------------Batch Processing(Statement Interface)------------------------
		
		 try {
	            Connection connection = DriverManager.getConnection(url, username, password);
	            Scanner scanner = new Scanner(System.in);
	            Statement statement = connection.createStatement();

	            while (true) {
	                System.out.print("Enter name: ");
	                String name = scanner.next();

	                System.out.print("Enter age: ");
	                int age = scanner.nextInt();

	                System.out.print("Enter marks: ");
	                double marks = scanner.nextDouble();

	                String query = String.format("INSERT INTO students(name, age, marks) VALUES ('%s', %d, %f)",name, age, marks);
	                statement.addBatch(query);

	                System.out.print("Enter more data (Y/N)? ");
	                String choice = scanner.next();

	                if (choice.equalsIgnoreCase("N")) {
	                    break;
	                }
	            }

	            int arr[] = statement.executeBatch();
	            for (int i = 0; i < arr.length; i++) {
	                if (arr[i] == 0) {
	                    System.err.println("Query " + i + " not executed successfully");
	                }
	            }
	            connection.close();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	//-----------------------------------Batch Processing(PreparedStatement Interface)------------------------

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Scanner scanner = new Scanner(System.in);
            String query = "Insert into students(name, age, marks) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            while (true) {
                System.out.print("Enter name: ");
                String name = scanner.next();
                System.out.print("Enter age: ");
                int age = scanner.nextInt();
                System.out.print("Enter marks: ");
                double marks = scanner.nextDouble();
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setDouble(3, marks);
                preparedStatement.addBatch();
                System.out.print("Enter more data (Y/N)? ");
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("N")) {
                    break;
                }
            }
            int[] results = preparedStatement.executeBatch();
	    }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
	}
}