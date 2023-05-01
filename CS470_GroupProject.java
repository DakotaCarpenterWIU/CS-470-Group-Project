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
                System.out.println("Enter 2 if you are an employee");
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
                        
                                switch (owner_option) {
                                    case 1: //Updates
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
                                        // which table -- MAYBE JUST DO LIKE THREE TABLES -- (Owner, Employee, Department, Shift_Type, Employee_Shift_Schedule, Log_Time)
                                        System.out.println("Enter the table where you want to implement a change: (Owner, Employee, Log_Time)");
                                        String table = keyboard.readLine();
                                        
                                        
                                        switch (up_option) {
                                            case 1: //Update table
                                                
                                                if(table == "Owner"){ //Owner(owner_ID, owner_password)
                                                    System.out.println("Enter Owner ID you want to update:"); 
                                                    int owner_id = Integer.parseInt(keyboard.readLine());
                                                    
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Update Owner's ID");
                                                    System.out.println("Enter 2 to Update Owner's password");
                                                    System.out.println("enter 3 to Quit");

                                                    String input_2 = keyboard.readLine();
                                                    int option_2 = Integer.parseInt(input_2);
 
                                                    if (option_2 == 3) {
                                                        break;
                                                    }
                                                    
                                                    switch (option_2) {
                                                        case 1: // ID
                                                            System.out.println("Enter new Owner ID:");
                                                            String new_OwnerID = keyboard.readLine();
                                                            String sql_new1 = "UPDATE Owner SET owner_ID = " + new_OwnerID + " WHERE owner_ID = " + owner_id;
                                                            stmt.executeUpdate(sql_new1);
                                                            System.out.println("Owner ID updated successfully.");
                                                            break;

                                                        case 2: // password
                                                            System.out.println("Enter new Owner password:");
                                                            String new_OwnerPassword = keyboard.readLine();
                                                            String sql_new2 = "UPDATE Owner SET owner_password = " + new_OwnerPassword + " WHERE owner_ID = " + owner_id;
                                                            stmt.executeUpdate(sql_new2);
                                                            System.out.println("Owner password updated successfully.");
                                                            break;

                                                        default:
                                                            System.out.println("Invalid option.");
                                                            break;
                                                    }
                                                
                                                }else if(table == "Employee"){ //Employee(employee_ID, employee_Fname, employee_Lname, employee_department, employee_email, employee_phone, employee_dob)
                                                    System.out.println("Enter Employee ID you want to update:"); 
                                                    int employee_id = Integer.parseInt(keyboard.readLine());
                                                    
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Update Employee's ID");
                                                    System.out.println("Enter 2 to Update Employee's First name");
                                                    System.out.println("Enter 3 to Update Employee's Last name");
                                                    System.out.println("Enter 4 to Update Employee's Department");
                                                    System.out.println("Enter 5 to Update Employee's Email");
                                                    System.out.println("Enter 6 to Update Employee's Phone");
                                                    System.out.println("Enter 7 to Update Employee's Date of Birth");
                                                    System.out.println("enter 8 to Quit");

                                                    String inputEmp = keyboard.readLine();
                                                    int optionEmp = Integer.parseInt(inputEmp);
 
                                                    if (optionEmp == 8) {
                                                        break;
                                                    }
                                                    
                                                    switch (optionEmp) {
                                                        case 1: //ID
                                                            System.out.println("Enter new Employee ID:");
                                                            String new_EmployeeID = keyboard.readLine();
                                                            String sql_new3 = "UPDATE Employee SET employee_ID = " + new_EmployeeID + " WHERE employee_ID = " + employee_id;
                                                            stmt.executeUpdate(sql_new3);
                                                            System.out.println("Employee ID updated successfully.");
                                                            break;

                                                        case 2: // Fname
                                                            System.out.println("Enter new Employee First name:");
                                                            String new_EmployeeFname = keyboard.readLine();
                                                            String sql_new4 = "UPDATE Employee SET employee_Fname = '" + new_EmployeeFname + "' WHERE employee_ID = " + employee_id;
                                                            stmt.executeUpdate(sql_new4);
                                                            System.out.println("Employee First name updated successfully.");
                                                            break;
                                                            
                                                        case 3: // Lname
                                                            System.out.println("Enter new Employee Last name:");
                                                            String new_EmployeeLname = keyboard.readLine();
                                                            String sql_new5 = "UPDATE Employee SET employee_Fname = '" + new_EmployeeLname + "' WHERE employee_ID = " + employee_id;
                                                            stmt.executeUpdate(sql_new5);
                                                            System.out.println("Employee Last name updated successfully.");
                                                            break;
                                                        
                                                        case 4: // Department
                                                            System.out.println("Enter new Employee Department:");
                                                            String new_EmployeeDepartment = keyboard.readLine();
                                                            String sql_new6 = "UPDATE Employee SET employee_department = '" + new_EmployeeDepartment + "' WHERE employee_ID = " + employee_id;
                                                            stmt.executeUpdate(sql_new6);
                                                            System.out.println("Employee Department updated successfully.");
                                                            break;
                                                        
                                                        case 5: // Email
                                                            System.out.println("Enter new Employee Email:");
                                                            String new_EmployeeEmail = keyboard.readLine();
                                                            String sql_new7 = "UPDATE Employee SET employee_email = '" + new_EmployeeEmail + "' WHERE employee_ID = " + employee_id;
                                                            stmt.executeUpdate(sql_new7);
                                                            System.out.println("Employee Email updated successfully.");
                                                            break;
                                                        
                                                        case 6: // Phone
                                                            System.out.println("Enter new Employee Phone:");
                                                            String new_EmployeePhone = keyboard.readLine();
                                                            String sql_new8 = "UPDATE Employee SET employee_phone = '" + new_EmployeePhone + "' WHERE employee_ID = " + employee_id;
                                                            stmt.executeUpdate(sql_new8);
                                                            System.out.println("Employee Phone updated successfully.");
                                                            break;
                                                        
                                                        case 7: // Date of Birth
                                                            System.out.println("Enter new Employee Date of Birth:");
                                                            String new_EmployeeDoB = keyboard.readLine();
                                                            String sql_new9 = "UPDATE Employee SET employee_dob = '" + new_EmployeeDoB + "' WHERE employee_ID = " + employee_id;
                                                            stmt.executeUpdate(sql_new9);
                                                            System.out.println("Employee Date of Birth updated successfully.");
                                                            break;

                                                        default:
                                                            System.out.println("Invalid option.");
                                                            break;
                                                    }
                                                
                                                }else if(table == "Log_Time"){ //Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time, total_worked_time)
                                                    System.out.println("Enter the Log ID you want to update:"); 
                                                    int log_id = Integer.parseInt(keyboard.readLine());
                                                    
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Update Log's ID");
                                                    System.out.println("Enter 2 to Update Log's employee ID");
                                                    System.out.println("Enter 3 to Update Log's Shift ID");
                                                    System.out.println("Enter 4 to Update Log's Date");
                                                    System.out.println("Enter 5 to Update Log's login time");
                                                    System.out.println("Enter 6 to Update Log's logout time");
                                                    System.out.println("Enter 7 to Update Log's total worked hours");
                                                    System.out.println("enter 8 to Quit");

                                                    String inputLog = keyboard.readLine();
                                                    int optionLog = Integer.parseInt(inputLog);
 
                                                    if (optionLog == 8) {
                                                        break;
                                                    }
                                                    
                                                    switch (optionLog) {
                                                        case 1: //Log ID
                                                            System.out.println("Enter new Log ID:");
                                                            String new_LogID = keyboard.readLine();
                                                            String sql_1 = "UPDATE Log_Time SET log_ID = " + new_LogID + " WHERE log_ID = " + log_id;
                                                            stmt.executeUpdate(sql_1);
                                                            System.out.println("Log_Time log ID updated successfully.");
                                                            break;

                                                        case 2: // employee ID
                                                            System.out.println("Enter new Log_Time Employee ID:");
                                                            String new_LogEmployeeID = keyboard.readLine();
                                                            String sql_2 = "UPDATE Log_Time SET employee_ID = " + new_LogEmployeeID + " WHERE log_ID = " + log_id;
                                                            stmt.executeUpdate(sql_2);
                                                            System.out.println("Log_Time log ID updated successfully.");
                                                            break;
                                                            
                                                        case 3: // Shift ID
                                                            System.out.println("Enter new Log_Time Shift ID:");
                                                            String new_LogShiftID = keyboard.readLine();
                                                            String sql_3 = "UPDATE Log_Time SET shift_ID = '" + new_LogShiftID + "' WHERE log_ID = " + log_id;
                                                            stmt.executeUpdate(sql_3);
                                                            System.out.println("Log_Time Shift ID updated successfully.");
                                                            break;
                                                        
                                                        case 4: // Date
                                                            System.out.println("Enter new Date:");
                                                            String new_LogDate = keyboard.readLine();
                                                            String sql_4 = "UPDATE Log_Time SET log_date = '" + new_LogDate + "' WHERE log_ID = " + log_id;
                                                            stmt.executeUpdate(sql_4);
                                                            System.out.println("Log_Time Date updated successfully.");
                                                            break;
                                                        
                                                        case 5: // login
                                                            System.out.println("Enter new Login time:");
                                                            String new_login = keyboard.readLine();
                                                            String sql_5 = "UPDATE Log_Time SET login_time = '" + new_login + "' WHERE log_ID = " + log_id;
                                                            stmt.executeUpdate(sql_5);
                                                            System.out.println("Log_Time login time updated successfully.");
                                                            break;
                                                        
                                                        case 6: // logout
                                                            System.out.println("Enter new Log_Time Phone:");
                                                            String new_logout = keyboard.readLine();
                                                            String sql_6 = "UPDATE Log_Time SET logout_time = '" + new_logout + "' WHERE log_ID = " + log_id;
                                                            stmt.executeUpdate(sql_6);
                                                            System.out.println("Log_Time logout time updated successfully.");
                                                            break;
                                                        
                                                        case 7: // total worked time
                                                            System.out.println("Enter new total worked hours:");
                                                            String new_TWT = keyboard.readLine();
                                                            String sql_7 = "UPDATE Log_Time SET total_worked_time = '" + new_TWT + "' WHERE log_ID = " + log_id;
                                                            stmt.executeUpdate(sql_7);
                                                            System.out.println("Log_Time total worked time updated successfully.");
                                                            break;

                                                        default:
                                                            System.out.println("Invalid option.");
                                                            break;
                                                    }
                                                
                                                }else
                                                    System.out.println("Invalid table.");
                                                    break;
                                               
                                                
                                            case 2: //Delete from table
                                            
                                                if(table == "Owner"){
                                                
                                                }else if(table == "Employee"){
                                                
                                                }else if(table == "Log_Time"){
                                                
                                                }else
                                                    System.out.println("Invalid table.");
                                                    break;
                                            
                                            case 3: //Insert in table
                                            
                                                if(table == "Owner"){
                                                
                                                }else if(table == "Employee"){
                                                
                                                }else if(table == "Log_Time"){
                                                
                                                }else
                                                    System.out.println("Invalid table.");
                                                    break;
                                            
                                            default:
                                                System.out.println("Invalid option.");
                                                break;
                                    
                                        }   
                                        
                                    
                                    case 2: //see view
                                    //which view
                                    //views -- Which employee do you wanna see? or see all?
                                    //if see all -- view to see all employees with their status of the day
                            
                                    case 3: //grant privileges
                                    
                                    
                            
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
                        
                        String sql_employee = "SELECT employee_ID FROM Employee WHERE employee_ID = " + employee_ID;
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
                        
                        
                            switch (employee_option) {
                                case 1: //daily view

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
                                
                                case 2: //weekly view
                                    String sql_view2 = "SELECT * FROM weekly_view";
                                    ResultSet view2 = stmt.executeQuery(sql_view2);
                                    //print?
                                    break;
                            
                                case 3: //view 3
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
