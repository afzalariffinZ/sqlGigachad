/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

/**
 *
 * @author Afiq Zafry
 */
import java.sql.Connection; // Represents a connection to the database
import java.sql.DriverManager; // Helps in obtaining a connection to the database
import java.sql.PreparedStatement; // Used for prepared statements
import java.sql.Statement; // Used for executing SQL statements
import java.sql.ResultSet; // Represents a table of data resulting from a query
import java.sql.SQLException; // Handles SQL exceptions
import java.util.Scanner;

public class ShoppingCartt {
    public static void main(String[] args) {
       try {
            DisplayCart("adwad");
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void DisplayCart(String username) throws SQLException{
        String uname= "root";
        String password ="";
        String query ="SELECT * FROM shopping_cart where username=Username";
        
//       try{
//           Class.forName("com.mysql.cj.jdbc.Driver");
//       } catch(ClassNotFoundException e){
//           e.printStackTrace();
//       }
       try{
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test69", "root", "");
          Statement statement = con.createStatement();
          ResultSet result= statement.executeQuery(query);
          System.out.println("Your Shopping Cart: ");
          while(result.next()){
               String items="";
               for (int i = 4; i >= 3; i--) {
                  items+= result.getString(i)+" ";
              }
               
               System.out.println(items);
          }
       }catch(SQLException e){
           e.printStackTrace();
       }

    }
    
    public static void AddtoCart(String id, String username ,String item){
        String url = "jdbc:mysql://localhost:3306/test69";
        String user = "root";
        String password = "";
        System.out.print(" Type The Quantity : ");
        Scanner scan = new Scanner(System.in);
        int quantity= scan.nextInt();
        

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String insertQuery = "INSERT INTO shopping_cart (ID,Username,Item_code,Item_quantity) VALUES (?, ?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

            // Set the values for the parameters in the prepared statement
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, item);
            preparedStatement.setInt(4, quantity);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item(s) added to cart successfully.");
            } else {
                System.out.println("Failed to add to cart.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
