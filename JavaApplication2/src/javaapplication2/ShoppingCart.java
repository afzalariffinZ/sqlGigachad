/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;



/**
 *
 * @author sawaz
 */
import java.sql.Connection; // Represents a connection to the database
import java.sql.DriverManager; // Helps in obtaining a connection to the database
import java.sql.PreparedStatement; // Used for prepared statements
import java.sql.Statement; // Used for executing SQL statements
import java.sql.ResultSet; // Represents a table of data resulting from a query
import java.sql.SQLException; // Handles SQL exceptions
import java.util.Scanner;

public class ShoppingCart {
    
    static String url = "jdbc:mysql://localhost:3306/test69";
    static String user = "root";
    static String pass = "";
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
       try {
            
            AddtoCart("afzal","ikan");
            DisplayCart("afzal");
            addExistingItem("afzal","ikan");
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }
    
    public static void addExistingItem(String username, String Item_code){
        
        try{
          Connection connect = DriverManager.getConnection(url,user,pass);
          Statement statement = connect.createStatement();
          String query ="SELECT * FROM shopping_cart where username=Username";
          ResultSet result= statement.executeQuery(query);
          while (result.next()) {
                if (result.getString(1).equals(username) && result.getString(2) == Item_code) {
                    System.out.print("Enter the quantity that you wish to add: ");
                    int x = result.getInt(3)+scan.nextInt();
                    String insertQuery = "INSERT INTO shopping_cart (Item_quantity) VALUES (?)";
                    PreparedStatement preparedStatement = connect.prepareStatement(insertQuery);
                    preparedStatement.setInt(3, x);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Quantity(s) updated to cart successfully.");
                    } else {
                        System.out.println("Failed to update to cart.");
                    }
                    
                    return;
                }
            }
       }catch(SQLException e){
           e.printStackTrace();
       }
        
    }
    public static void DisplayCart(String username) throws SQLException{
        
        
       try{
          Connection connect = DriverManager.getConnection(url,user,pass);
          Statement statement = connect.createStatement();
          String query ="SELECT * FROM shopping_cart where username=Username";
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
    
    public static void AddtoCart(String username ,String item){


        System.out.print(" Type The Quantity : ");
        
        int quantity= scan.nextInt();
        

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            
            
            
            String insertQuery = "INSERT INTO shopping_cart (Username,Item_code,Item_quantity) VALUES ( ?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

            // Set the values for the parameters in the prepared statement
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, item);
            preparedStatement.setInt(3, quantity);

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

