/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectperformance;

/**
 *
 * @author Michiel
 * Source: https://www.tutorialspoint.com/jdbc/jdbc-select-records.htm
 */
import java.sql.*;
import java.util.ArrayList;

public class Connection {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/tennis";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "mysql";
   
   private ArrayList<Person> people = null;
   
   public Connection() {}
   
   public ArrayList<Person> getData(String table) {
        java.sql.Connection conn = null;
        Statement stmt = null;
        try{
           //STEP 2: Register JDBC driver
           Class.forName("com.mysql.jdbc.Driver");

           //STEP 3: Open a connection
           conn = DriverManager.getConnection(DB_URL, USER, PASS);

           //STEP 4: Execute a query
           stmt = conn.createStatement();

           String sql = "SELECT * FROM " + table;
           ResultSet rs = stmt.executeQuery(sql);
           //STEP 5: Extract data from result set
           people = new ArrayList<>();
           while(rs.next()){
              //Create Person object
              //Retrieve by column name
              Person person = new Person();
              person.setId(rs.getInt("Id"));
              person.setName(rs.getString("Name"));
              person.setLesson(rs.getDate("Lesson"));

              //Add Person to arraylist
              people.add(person);
           }
           rs.close();
        }catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
        }finally{
           //finally block used to close resources
           try{
              if(stmt!=null)
                 conn.close();
           }catch(SQLException se){
           }// do nothing
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }//end finally try
        }//end try
        
        //retun arraylist persons
        return people;
   }//end getData method
   
   public void writeInfopaper(Person person) {
       java.sql.Connection conn = null;
        Statement stmt = null;
        try{
           //STEP 2: Register JDBC driver
           Class.forName("com.mysql.jdbc.Driver");

           //STEP 3: Open a connection
           conn = DriverManager.getConnection(DB_URL, USER, PASS);

           //STEP 4: Create prepared statement
           stmt = conn.createStatement();

           String query = "INSERT into infopaper (LessonInfo)"
                   + " values (?)";
           
           PreparedStatement prepstmt = conn.prepareStatement(query);
           prepstmt.setString(1, person.toString());
           
           //STEP 5: Execute statement
           prepstmt.execute();
        }catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
        }finally{
           //finally block used to close resources
           try{
              if(stmt!=null)
                 conn.close();
           }catch(SQLException se){
           }// do nothing
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }//end finally try
        }//end try
   } //end writeInfopaper   
}//end Connection class
