import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class login {
    public void sqlConnectorLogin(String email, String username, String password){
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pass = "";
        try (
            Connection connect = DriverManager.getConnection(url, user, pass);
            Statement statement = connect.createStatement()) { // connecting to the database

            // checking the database whether email or username inserted by user has existed
            // or not
            ResultSet check = statement.executeQuery("select * from idtest");
            while (check.next()) {
                if (check.getString(2).equals(email) ) {
                    if(check.getString(3).equals(username)){
                        if(check.getString(4).equals(password)){
                            System.out.println("Welcome back "+username+"!");  
                            return;
                        }else{
                            System.out.println("Incorrect password, please try again!");  
                            return;
                        }
                    }else{
                        System.out.println("Username not found. Are you in the right mode?");
                        return;
                    }
                }else{
                    System.out.println("No email found, please register an account if you don't have an account!");
                    return;
                }
            }
            
            

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            // System.out.println("Hello world");
        }
    }
}
