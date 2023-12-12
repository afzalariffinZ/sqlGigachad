import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class register {

    // id to connect to database

    public void sqlConnectorRegister(String email, String username, String password) {
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
                if (check.getString(2).equals(email) || check.getString(3).equals(username)) {
                    System.out.println("Email or username has already existed.");
                    return;
                }
            }
            // Inserting the data inserted by user into the database if the email registered
            // is new
            String insertQuery = "INSERT INTO idtest (email, username, password) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connect.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);

                // executing query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Sucessfully registed into the database!");
                } else {
                    System.out.println("Failed to enter the database");
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            // System.out.println("Hello world");
        }

        
    }



}