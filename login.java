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
            boolean emailExist = false;
            boolean usernameExist = false;
            boolean passwordExist = false;


            ResultSet check = statement.executeQuery("select * from idtest");
            while (check.next()) {
                if (check.getString(2).equals(email) ) {
                    emailExist = true;
                    if(check.getString(3).equals(username)){
                        usernameExist = true;
                        if(check.getString(4).equals(password)){
                            passwordExist = true;
                        }
                    }
                }
            }

            if(emailExist){
                if(usernameExist){
                    if(passwordExist){
                        System.out.println("Welcome back "+username+"!");  
                    }else{
                        System.out.println("Incorrect password, please try again!");  
                    }
                }else{
                    System.out.println("Username not found. Are you in the right mode?");
                }
            }else{
                System.out.println("No email found, please register an account if you don't have an account!");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            // System.out.println("Hello world");
        }
    }
}
