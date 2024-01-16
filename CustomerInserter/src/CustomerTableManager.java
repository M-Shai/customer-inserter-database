import java.sql.*;

/**
   The CustomerTableManager class provides methods for 
   inserting a record into the Customer table of the 
   CoffeeDB database for the Customer Inserter 
   programming challenge.
*/

public class CustomerTableManager
{
	// Create a named constant for the URL.
	// NOTE: This value is specific for Derby
	public final String DB_URL =  "jdbc:derby:CoffeeDB";
   
	// Field for the database connection
	private Connection conn;

	/**
      Constructor
	 */
   
	public CustomerTableManager() throws SQLException
	{
		// Create a connection to the database.
		conn = DriverManager.getConnection(DB_URL);
	}
   
	public void insert(String custNum, String name,
                       String address, String city,
                       String state, String zip) 
                       throws SQLException
	{
		String query = "INSERT INTO Customer VALUES" +
                       "(" + 
                       "'" + custNum + 
                       "', " +
                       "'" + name + 
                       "', " +
                       "'" + address + 
                       "', " +
                       "'" + city + 
                       "', " +
                       "'" + state + 
                       "', " +
                       "'" + zip + "'" +
                       ")";
		System.out.println("DEBUG---------------------");
		System.out.println(query);
		System.out.println("END-DEBUG---------------------");
                     
		// Create a Statement object.
		Statement stmt = conn.createStatement();
               
		// Send the statement to the DBMS.
		stmt.executeUpdate(query);
         
		// Close the statement.
		conn.close();
		stmt.close();
	}
}
