
package utility;

import java.sql.*;
import java.util.ArrayList;
import model.Patient;
/**
 *
 * @author EF
 */
public class DatabaseConnector { 
   //CONNECTION STRING
//   private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
   private static final String DB_URL = "jdbc:mysql://localhost:3306/medical_db?useSSL=false";
   private static final String DB_USERNAME = "root";
   private static final String DB_PASSWORD = "1234567";
   
   //CRUD OPERATIONS; 
   
   //CREATE: INSERT
   public static void addPatient(Patient p1){
       //connection
       String query = "INSERT INTO patients (f_name, l_name, email, age) VALUES (?, ?, ?, ?)";
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,p1.getFname());
            stmt.setString(2,p1.getLname());
            stmt.setString(3,p1.getEmail());        
            stmt.setString(4,p1.getAge()); 
            int rows = stmt.executeUpdate();
           System.out.println(rows + " row(s) inserted successfully!");
       conn.close();       
       } catch (SQLException sqlEx) {
           System.out.println("Error occurred!");
           System.out.println(sqlEx);
       } catch (Exception ex){
           System.out.println(ex);
       }

   }
   
   //READ: Getting all the values from the database
    public static ArrayList<Patient> getPatients(){
        ArrayList <Patient> patients = new ArrayList();
        String query = "SELECT * FROM patients";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Patient p1 = new Patient();
                p1.setId(rs.getInt("id"));
                p1.setFname(rs.getString("f_name"));
                p1.setLname(rs.getString("l_name"));
                p1.setEmail(rs.getString("email"));
                p1.setAge(rs.getString("age"));
                patients.add(p1);
            }
            rs.close();
            conn.close();
        } catch (SQLException sqle){
            System.out.println("SQL Exception Occured.");            
            System.out.println(sqle);
        } catch (Exception ex){
            System.out.println(ex);
        }
        return patients;
    }
    
   //UPDATEChanging a value in the database
    public static void updatePatient(Patient oldPatient, Patient updPatient){
            String query = "UPDATE patients SET  f_name=?, l_name=?,  email=?, age=? WHERE id = ? ";
            // Update the query
        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,updPatient.getFname());
            stmt.setString(2,updPatient.getLname());
            stmt.setString(3,updPatient.getEmail());        
            stmt.setString(4,updPatient.getAge()); 
            stmt.setInt(5, updPatient.getId());
            int rows = stmt.executeUpdate();
            System.out.println(rows + " row(s) updated: "+rows);
            conn.close();
        } catch (SQLException sqle){
            System.out.println("SQL Exception Occured.");            
            System.out.println(sqle);
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
    
   //DELETE 
    public static void deletePatient(Patient p1){
        String query = "DELETE FROM patients WHERE id = ? ;";
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, p1.getId());
            int rows = stmt.executeUpdate();
            System.out.println(rows + " row(s) deleted: ");
            conn.close();
        } catch (SQLException sqle){
            System.out.println("SQL Exception Occured.");            
            System.out.println(sqle);
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}
