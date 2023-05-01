import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class CS470_GroupProject {
    static Connection conn;
    static Statement stmt;
    static BufferedReader keyboard;
    public static void main(String args[]) throws IOException {
        String username = "ORA_cfg103", password = "CS470_6611";
        String ename;
        int original_empno = 0;
        int empno;
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Registered the driver...");
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle2.wiu.edu:1521/orclpdb1", username, password);
            System.out.println("logged into oracle as " + username);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            while (true) {
                
                //Owner or employee?
                System.out.println("\nPlease select your position/role:");
                System.out.println("Enter 1 if you are an owner");
                System.out.println("Enter 2 if you are an employeee");
                System.out.println("Enter 3 to Quit");
                
                //Read input
                String input = keyboard.readLine();
                int option = Integer.parseInt(input);
                
                //To quit
                if (option == 3) {
                    break;
                }

                switch (option) {
                    case 1: //Owner
                        System.out.println("Enter your nickname");
                        String owner_name = keyboard.readLine();
                        System.out.println("Enter your owner ID");
                        String owner_ID = keyboard.readLine();
                        //if owner ID corresponds to list ask for password if not break.
                        String sqlCheck = "SELECT owner_ID FROM Owner WHERE owner_ID = " + owner_ID;
                        ResultSet check = stmt.executeQuery(sqlCheck);
                        if (!check.next()){//owner_ID is not equal(meaning the string is empty)  
                            System.out.println("Invalid ID.");
                            
                        } else { 
                            
                            System.out.println("Enter your password");
                            String owner_password = keyboard.readLine();
                            String sqlCheck2 = "SELECT owner_password FROM Owner WHERE owner_password = " + owner_password;
                            ResultSet check2 = stmt.executeQuery(sqlCheck2);
                            if (!check2.next()){//owner_password is not equal(meaning the string is empty)
                                System.out.println("Invalid password.");

                            } else { 

                                //After validating owner ID and password
                                System.out.println("Welcome to the Database " + owner_name);
                                System.out.println("\nEnter the option you would like to implement");
                        
                                System.out.println("Enter 1 if you want to update an existing table"); //(UPDATE, DELETE, INSERT)
                                System.out.println("Enter 2 if you want to see a view"); // daily view, weekly view, view3
                                System.out.println("Enter 3 if you want to grant privileges"); //owner privilege...
                                System.out.println("Enter 4 to Quit");
                
                                //Read input
                                String owner_input = keyboard.readLine();
                                int owner_option = Integer.parseInt(owner_input);
                
                                //To quit
                                if (owner_option == 4) {
                                break;
                                }
                        
                                switch (option) {
                                    case 1:
                                    System.out.println("Enter the option of your update");
                                    System.out.println("Enter 1 to UPDATE a table");
                                    System.out.println("Enter 2 to DELETE from a table");
                                    System.out.println("Enter 3 to INSERT in a table");
                                    System.out.println("Enter 4 to Quit");

                                    //Read input
                                    String up_input = keyboard.readLine();
                                    int up_option = Integer.parseInt(up_input);
                
                                    //To quit
                                    if (up_option == 4) {
                                    break;
                                    }
                                    
                                    //...
                                    
                                    case 2:
                                    //which view
                                    //views -- Which employee do you wanna see? or see all?
                                    //if see all -- view to see all employees with their status of the day
                            
                                    case 3:
                                    
                                    //privileges
                            
                                    default:
                                        System.out.println("Invalid option.");
                                        break;
                            
                        
                                }
                            }   
                                                    
                        }
                        
                   case 2: //Employee
                        System.out.println("Enter your first name");
                        String employee_Fname = keyboard.readLine();
                        System.out.println("Enter your ID");
                        String employee_ID = keyboard.readLine();
                        
                        String sql_employee = "SELECT employee_ID FROM Owner WHERE employee_ID = " + employee_ID;
                        ResultSet check_employee = stmt.executeQuery(sql_employee);
                        if (!check_employee.next()){//sql_employee is not equal(meaning the string is empty)
                            System.out.println("Invalid ID.");

                        } else { 
                        
                            //After validating employee ID 
                            System.out.println("Welcome to the Database " + employee_Fname);
                            System.out.println("\nEnter the view you would like to see");
                        
                            System.out.println("Enter 1 if you want to see your Dayly View"); 
                            System.out.println("Enter 2 if you want to see your Weekly View");
                            System.out.println("Enter 3 if you want to see your view3");
                            System.out.println("Enter 4 to Quit");
                        
                            //Read input
                            String employee_input = keyboard.readLine();
                            int employee_option = Integer.parseInt(employee_input);
                
                            //To quit
                            if (employee_option == 4) {
                                break;
                            }
                        
                        
                            switch (option) {
                                case 1:

                                    String sql_view1 = "SELECT * FROM daily_view"; // "WHERE employee_ID = " + employee_ID
                                    ResultSet view1 = stmt.executeQuery(sql_view1);
                                    //print?
                                    /*
                                    while (view1.next()) {
                                        String times = view1.getString("");
                                        System.out.println(times);
                                    }
                                    view1.close();
                                    */
                                    break;                                
                                
                                case 2:
                                    String sql_view2 = "SELECT * FROM weekly_view";
                                    ResultSet view2 = stmt.executeQuery(sql_view2);
                                    //print?
                                    break;
                            
                                case 3:
                                    String sql_view3 = "SELECT * FROM view3";
                                    ResultSet view3 = stmt.executeQuery(sql_view3);
                                    //print?
                                    break;
                            
                                default:
                                    System.out.println("Invalid option.");
                                    break;
                         
                                
                             }
                         }
                         
                    default:
                        System.out.println("Invalid option.");
                        break;
        
                }
                
            }

            stmt.close();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            System.out.println("SQL Exception:" + e.getMessage());
        }
    }
}
