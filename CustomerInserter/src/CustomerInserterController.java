
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
CustomerInserterCotroller class
Handles add button event
Contains all necessary import statements and private variables
Gets all resources and initializes the variables
*/
public class CustomerInserterController
{
	public final String DB_URL =  "jdbc:derby:CoffeeDB";

	CustomerTableManager custTabMan;

	@FXML // fx:id="rootNode"
    private AnchorPane rootNode; // Value injected by FXMLLoader

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="custCity"
    private TextField custCity; // Value injected by FXMLLoader


    @FXML // fx:id="exitItem"
    private MenuItem exitItem; // Value injected by FXMLLoader

    @FXML // fx:id="custNumber"
    private TextField custNumber; // Value injected by FXMLLoader

    @FXML // fx:id="custAddress"
    private TextField custAddress; // Value injected by FXMLLoader

    @FXML // fx:id="clearItem"
    private MenuItem clearItem; // Value injected by FXMLLoader

    @FXML // fx:id="custState"
    private TextField custState; // Value injected by FXMLLoader

    @FXML // fx:id="custZipCode"
    private TextField custZipCode; // Value injected by FXMLLoader

    @FXML // fx:id="custName"
    private TextField custName; // Value injected by FXMLLoader

    @FXML // fx:id="addBtn"
    private Button addBtn; // Value injected by FXMLLoader

    /*@FXML // fx:id="exitItem"
    private MenuItem exitItem; // Value injected by FXMLLoader*/

    @FXML //initialization method
    void initialize()
    {
        assert custCity != null : "fx:id=\"custCity\" was not injected: check"
        		+ " your FXML file 'CustomerInserter.fxml'.";
        assert exitItem != null : "fx:id=\"exitItem\" was not injected: check"
        		+ " your FXML file 'CustomerInserter.fxml'.";
        assert custNumber != null : "fx:id=\"custNumber\" was not injected: "
        		+ "check your FXML file 'CustomerInserter.fxml'.";
        assert custAddress != null : "fx:id=\"custAddress\" was not injected:"
        		+ " check your FXML file 'CustomerInserter.fxml'.";
        assert custState != null : "fx:id=\"custState\" was not injected: "
        		+ "check your FXML file 'CustomerInserter.fxml'.";
        assert clearItem != null : "fx:id=\"clearItem\" was not injected: "
        		+ "check your FXML file 'CustomerInserter.fxml'.";
        assert custZipCode != null : "fx:id=\"custZipCode\" was not injected:"
        		+ " check your FXML file 'CustomerInserter.fxml'.";
        assert custName != null : "fx:id=\"custName\" was not injected: check"
        		+ " your FXML file 'CustomerInserter.fxml'.";
        assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your"
        		+ " FXML file 'CustomerInserter.fxml'.";
        assert exitItem != null : "fx:id=\"exitItem\" was not injected: check"
        		+ " your FXML file 'CustomerInserter.fxml'.";
        assert rootNode != null : "fx:id=\"rootNode\" was not injected: check"
        	+ " your FXML file 'CustomerInserter.fxml'.";

		//CreateCoffeeDB coffeeDB = new CreateCoffeeDB();
    }
    /**
     addBtn button listener
     When button is clicked, gets the customer data from the TextFields
     Checks to see if the customer data is in the database
     Inserts new customer data into database
     Displays data base data
     */
    public void addBtn()
    {
		//Statement variable
		Statement stmt;
		//query statement, selects all data from CUSTOMER table
		String query1 = "SELECT * FROM CUSTOMER";
		//try block
		try
		{
			//query statement, selects the row with the customer number
			String query2 = "SELECT * FROM CUSTOMER WHERE CustomerNumber = '"
					+ custNumber.getText() + "'";
			//connection to db is made
			Connection conn = DriverManager.getConnection(DB_URL);
			//connection object is created
			stmt = conn.createStatement();
			//creates a result set and stores the data returned by
			//query2 statement
			ResultSet result = stmt.executeQuery(query2);

			//if the customer's number is unique
			if(!result.next())
			{
				System.out.println("Customer number is unique");
				//CustomerTableManager object
				custTabMan = new CustomerTableManager();
				//insert new customer data in the db
				custTabMan.insert(custNumber.getText(), custName.getText(),
						custAddress.getText(), custCity.getText(),
						custState.getText(),custZipCode.getText());
				//clear all text fields
				clearItem();
			}

			//if customer number is already in the db
			else
			{
				System.out.println("Error duplicate customer number");
				clearItem();
			}
			//gets the all data from the db
			result = stmt.executeQuery(query1);

			System.out.println("Customer Number:	Name:			     "
					+ "Address:			City:		   State:	Zip:\n");
			//displays data
			while(result.next())
			{
				System.out.println(result.getString("CustomerNumber")
						+ "		" + result.getString("Name")
						+ "    " + result.getString("Address") + "	"
						+ result.getString("City")
						+ "	   " + result.getString("State") + "		"
						+  result.getString("Zip") );
			}
			//close connection
			conn.close();

		}
		//catch block
		catch (SQLException e)
		{
			e.printStackTrace();
		}


    }
	/**
	clearItem, clears all the text fields
	*/
    public void clearItem()
    {
    	custNumber.setText("");
    	custName.setText("");
    	custAddress.setText("");
    	custCity.setText("");
    	custState.setText("");
    	custZipCode.setText("");
    }

	/**
	exitItem, exits the application
	*/
    public void exitItem()
    {
    	System.exit(0);
   	}
}