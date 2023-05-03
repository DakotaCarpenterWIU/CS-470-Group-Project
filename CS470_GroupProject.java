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
                                System.out.println("Enter 2 if you want to see a view"); // Owner = 3 views (daily_status, weekly_hours, employee_weekly_status)
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
                                                    
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Update Owner's ID");
                                                    System.out.println("Enter 2 to Update Owner's password");
                                                    System.out.println("enter 3 to Quit");

                                                    String input_update = keyboard.readLine();
                                                    int option_update = Integer.parseInt(input_update);
 
                                                    if (option_update == 3) {
                                                        break;
                                                    }
                                                    System.out.println("Enter Owner ID you want to update:"); 
                                                    int owner_id = Integer.parseInt(keyboard.readLine());
                                                    
                                                    switch (option_update) {
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
                                                    System.out.println("Enter Employee ID you want to update:"); 
                                                    int employee_id = Integer.parseInt(keyboard.readLine());
                                                    
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
                                                            System.out.println("Enter new Employee Phone: (format: 000-000-0000)");
                                                            String new_EmployeePhone = keyboard.readLine();
                                                            String sql_new8 = "UPDATE Employee SET employee_phone = '" + new_EmployeePhone + "' WHERE employee_ID = " + employee_id;
                                                            stmt.executeUpdate(sql_new8);
                                                            System.out.println("Employee Phone updated successfully.");
                                                            break;
                                                        
                                                        case 7: // Date of Birth
                                                            System.out.println("Enter new Employee Date of Birth: (format: YYYY-MM-DD)"); //TO_DATE('1999-10-10', 'YYYY-MM-DD')
                                                            String new_EmployeeDoB = keyboard.readLine();
                                                            String sql_new9 = "UPDATE Employee SET employee_dob = TO_DATE('" + new_EmployeeDoB + "', 'YYYY-MM-DD') WHERE employee_ID = " + employee_id;
                                                            //UPDATE Employee SET employee_dob = TO_DATE('1999-10-11', 'YYYY-MM-DD') WHERE employee_ID = 1110;
                                                            stmt.executeUpdate(sql_new9);
                                                            System.out.println("Employee Date of Birth updated successfully.");
                                                            break;

                                                        default:
                                                            System.out.println("Invalid option.");
                                                            break;
                                                    }
                                                
                                                }else if(table == "Log_Time"){ //Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time, total_worked_time)
                               
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Update Log's ID");
                                                    System.out.println("Enter 2 to Update Log's employee ID");
                                                    System.out.println("Enter 3 to Update Log's Shift ID");
                                                    //System.out.println("Enter 4 to Update Log's Date"); //The date in Log_Time is set as the current date, so it cannot be updated
                                                    System.out.println("Enter 4 to Update Log's login time");
                                                    System.out.println("Enter 5 to Update Log's logout time");
                                                    //System.out.println("Enter 7 to Update Log's total worked hours"); //Total worked time is an automatic calculation so it cannot be updated:
                                                    System.out.println("enter 6 to Quit");

                                                    String inputLog = keyboard.readLine();
                                                    int optionLog = Integer.parseInt(inputLog);
 
                                                    if (optionLog == 6) {
                                                        break;
                                                    }
                                                    System.out.println("Enter the Log ID you want to update:"); 
                                                    int log_id = Integer.parseInt(keyboard.readLine());
                                                    
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
                                                            String sql_3 = "UPDATE Log_Time SET shift_ID = " + new_LogShiftID + " WHERE log_ID = " + log_id;
                                                            stmt.executeUpdate(sql_3);
                                                            System.out.println("Log_Time Shift ID updated successfully.");
                                                            break;
                                                        
                                                        case 4: // login
                                                            System.out.println("Enter new Login time: (format: 00:00:00)");
                                                            String new_login = keyboard.readLine();
                                                            String sql_5 = "UPDATE Log_Time SET login_time = TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || '" + new_login + "', 'YYYY-MM-DD HH24:MI:SS') WHERE log_ID = " + log_id; 
                                                            //UPDATE Log_Time SET login_time = TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 05:00:00', 'YYYY-MM-DD HH24:MI:SS') WHERE log_ID = 6610;
                                                            stmt.executeUpdate(sql_5);
                                                            System.out.println("Log_Time login time updated successfully.");
                                                            break;
                                                        
                                                        case 5: // logout
                                                            System.out.println("Enter new Log_Time Phone: (format: 00:00:00)");
                                                            String new_logout = keyboard.readLine();
                                                            String sql_6 = "UPDATE Log_Time SET logout_time = TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || '" + new_logout + "', 'YYYY-MM-DD HH24:MI:SS') WHERE log_ID = " + log_id;
                                                            stmt.executeUpdate(sql_6);
                                                            System.out.println("Log_Time logout time updated successfully.");
                                                            break;
                                                 
                                                        default:
                                                            System.out.println("Invalid option.");
                                                            break;
                                                    }
                                                
                                                }else
                                                    System.out.println("Invalid table.");
                                                    break;
                                               
                                                
                                            case 2: //Delete from table
                                            
                                                if(table == "Owner"){ //Owner(owner_ID, owner_password)                                                    
                                                    
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Delete according to Owner's ID");
                                                    System.out.println("Enter 2 to Delete according to Owner's password");
                                                    System.out.println("enter 3 to Quit");

                                                    String input_delete = keyboard.readLine();
                                                    int option_delete = Integer.parseInt(input_delete);
 
                                                    if (option_delete == 3) {
                                                        break;
                                                    }
                                                  
                                                    switch (option_delete) { //DELETE FROM table WHERE ...
                                                        case 1: // ID
                                                            System.out.println("Enter Owner ID from where you want to delete:"); 
                                                            int owner_id2 = Integer.parseInt(keyboard.readLine());
                                                            String sql_old1 = "DELETE FROM Owner WHERE owner_ID = " + owner_id2;
                                                            stmt.executeUpdate(sql_old1);
                                                            System.out.println("Row/s according to Owner ID deleted successfully.");
                                                            break;

                                                        case 2: // password
                                                            System.out.println("Enter Owner password from where you want to delete:");
                                                            String old_OwnerPassword = keyboard.readLine();
                                                            String sql_old2 = "DELETE FROM Owner WHERE owner_password = " + old_OwnerPassword;
                                                            stmt.executeUpdate(sql_old2);
                                                            System.out.println("Row/s according to Owner password deleted successfully.");
                                                            break;

                                                        default:
                                                            System.out.println("Invalid option.");
                                                            break;
                                                    }
                                                 
                                                }else if(table == "Employee"){ //Employee(employee_ID, employee_Fname, employee_Lname, employee_department, employee_email, employee_phone, employee_dob)                                                   
                                                    
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Delete according to Employee's ID");
                                                    //System.out.println("Enter 2 to Delete according to Employee's First name"); does not make sense to delete from first or last name (can be the same and not same person)
                                                    //System.out.println("Enter 3 to Delete according to Employee's Last name");
                                                    System.out.println("Enter 2 to Delete according to Employee's Department");
                                                    System.out.println("Enter 3 to Delete according to Employee's Email");
                                                    System.out.println("Enter 4 to Delete according to Employee's Phone");
                                                    System.out.println("Enter 5 to Delete according to Employee's Date of Birth");
                                                    System.out.println("enter 6 to Quit");

                                                    String inputEmp2 = keyboard.readLine();
                                                    int optionEmp2 = Integer.parseInt(inputEmp2);
 
                                                    if (optionEmp2 == 6) {
                                                        break;
                                                    }
                                                    
                                                    switch (optionEmp2) {
                                                        case 1: //ID
                                                            System.out.println("Enter Employee ID from where you want to delete:");
                                                            String old_EmployeeID = keyboard.readLine();
                                                            String sql_old3 = "DELETE FROM Employee WHERE employee_ID = " + old_EmployeeID;
                                                            stmt.executeUpdate(sql_old3);
                                                            System.out.println("Row/s according to Employee ID deleted successfully.");
                                                            break;
                                                        
                                                        case 2: // Department
                                                            System.out.println("Enter Employee Department from where you want to delete:");
                                                            String old_EmployeeDepartment = keyboard.readLine();
                                                            String sql_old6 = "DELETE FROM Employee WHERE employee_department = '" + old_EmployeeDepartment + " '";
                                                            stmt.executeUpdate(sql_old6);
                                                            System.out.println("Row/s according to Employee Department deleted successfully.");
                                                            break;
                                                        
                                                        case 3: // Email
                                                            System.out.println("Enter Employee Email from where you want to delete:");
                                                            String old_EmployeeEmail = keyboard.readLine();
                                                            String sql_old7 = "DELETE FROM Employee WHERE employee_email = '" + old_EmployeeEmail + " '";
                                                            stmt.executeUpdate(sql_old7);
                                                            System.out.println("Row/s according to Employee Email deleted successfully.");
                                                            break;
                                                        
                                                        case 4: // Phone
                                                            System.out.println("Enter Employee Phone from where you want to delete: (format: 000-000-0000)");
                                                            String old_EmployeePhone = keyboard.readLine();
                                                            String sql_old8 = "DELETE FROM Employee WHERE employee_phone = '" + old_EmployeePhone + " '";
                                                            stmt.executeUpdate(sql_old8);
                                                            System.out.println("Row/s according to Employee Phone deleted successfully.");
                                                            break;
                                                        
                                                        case 5: // Date of Birth
                                                            System.out.println("Enter Employee Date of Birth from where you want to delete: (format: YYYY-MM-DD)"); //TO_DATE('1999-10-10', 'YYYY-MM-DD')
                                                            String old_EmployeeDoB = keyboard.readLine();
                                                            String sql_old9 = "DELETE FROM Employee WHERE employee_dob = TO_DATE('" + old_EmployeeDoB + "', 'YYYY-MM-DD')";
                                                            stmt.executeUpdate(sql_old9);
                                                            System.out.println("Row/s according to Employee Date of Birth deleted successfully.");
                                                            break;
                                                            
                                                            // should we add a free option? Like the user can enter their choice of "WHERE (+condition)"

                                                        default:
                                                            System.out.println("Invalid option.");
                                                            break;
                                                    }
                                                
                                                }else if(table == "Log_Time"){ //Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time, total_worked_time
                                                    
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Update Log's ID");
                                                    System.out.println("Enter 2 to Update Log's employee ID");
                                                    System.out.println("Enter 3 to Update Log's Shift ID");
                                                    //System.out.println("Enter 4 to Update Log's Date"); //current date
                                                    //System.out.println("Enter 5 to Update Log's login time"); //does not make sense to delete from login, logout, and total hours 
                                                    //System.out.println("Enter 6 to Update Log's logout time");
                                                    //System.out.println("Enter 7 to Update Log's total worked hours");
                                                    System.out.println("enter 4 to Quit");

                                                    String inputLog = keyboard.readLine();
                                                    int optionLog = Integer.parseInt(inputLog);
 
                                                    if (optionLog == 4) {
                                                        break;
                                                    }
                                                    
                                                    switch (optionLog) {
                                                        case 1: //Log ID
                                                            System.out.println("Enter Log ID from where you want to delete:");
                                                            String old_LogID = keyboard.readLine();
                                                            String oldsql_1 = "DELETE FROM Log_Time WHERE log_ID = " + old_LogID;
                                                            stmt.executeUpdate(oldsql_1);
                                                            System.out.println("Row/s according to Log_Time log ID deleted successfully.");
                                                            break;

                                                        case 2: // employee ID
                                                            System.out.println("Enter Log_Time Employee ID from where you want to delete:");
                                                            String old_LogEmployeeID = keyboard.readLine();
                                                            String oldsql_2 = "DELETE FROM Log_Time WHERE employee_ID = " + old_LogEmployeeID;
                                                            stmt.executeUpdate(oldsql_2);
                                                            System.out.println("Row/s according to Log_Time log ID deleted successfully.");
                                                            break;
                                                            
                                                        case 3: // Shift ID
                                                            System.out.println("Enter Log_Time Shift ID from where you want to delete::");
                                                            String old_LogShiftID = keyboard.readLine();
                                                            String oldsql_3 = "DELETE FROM Log_Time WHERE shift_ID = " + old_LogShiftID;
                                                            stmt.executeUpdate(oldsql_3);
                                                            System.out.println("Row/s according to Log_Time Shift ID deleted successfully.");
                                                            break;
                                                            
                                                            // should we add a free option? Like the user can enter their choice of "WHERE (+condition)"
                    
                                                      default:
                                                            System.out.println("Invalid option.");
                                                            break;
                                                    }
                                                
                                                }else
                                                    System.out.println("Invalid table.");
                                                    break;
//STILL NEED TO WORK HERE                                             
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
                              
                                        System.out.println("\nEnter the view you would like to see"); //which view
                        
                                        System.out.println("Enter 1 if you want to see the employees daily status"); //Owner has access to 3 views (daily_status, weekly_hours, employee_weekly_status)
                                        System.out.println("Enter 2 if you want to see the employees weekly hours");
                                        System.out.println("Enter 3 if you want to see the employees weekly status");
                                        System.out.println("Enter 4 to Quit");
                        
                                        //Read input
                                        String owner_view_input = keyboard.readLine();
                                        int owner_view_option = Integer.parseInt(owner_view_input);
                
                                        //To quit
                                        if (owner_view_option == 4) {
                                            break;
                                        }
                        
                                      
                                        switch (owner_view_option) {
                                            case 1: //daily_status view
                                                
                                                //Erase/Drop previous view
                                                String sql_drop1 = "DROP VIEW daily_status";
                                                stmt.executeQuery(sql_drop1);
                                                
                                                String sql_view1 = "CREATE VIEW daily_status AS " +
                                                                   " SELECT lt.employee_ID, e.employee_Fname AS First_Name, e.employee_Lname AS Last_Name, " + 
                                                                       " CASE WHEN lt.logout_time - lt.login_time <= INTERVAL '8' HOUR THEN 'On time' " + 
                                                                           " ELSE 'Late' " + 
                                                                       " END AS status " + 
                                                                   " FROM Log_Time lt JOIN Employee e ON e.employee_ID = lt.employee_ID";
                                                stmt.executeQuery(sql_view1); 
                                                
                                                String runView1 = "SELECT * FROM daily_status"; //Does this show the view in SQL Developer?
                                                stmt.executeQuery(runView1); //ResultSet view1 = 
                                                //print?
                                                /*
                                                while (view1.next()) {
                                                String times = view1.getString("");
                                                System.out.println(times);
                                                }
                                                view1.close();
                                                */
                                                break;                                
                                
                                            case 2: //weekly_hours view
                                            
                                                //Erase/Drop previous view
                                                String sql_drop2 = "DROP VIEW weekly_hours";
                                                stmt.executeQuery(sql_drop2);
                                                
                                                String sql_view2 = "CREATE VIEW weekly_hours AS " +
                                                                   " SELECT lt.employee_ID, e.employee_Fname AS First_Name, e.employee_Lname AS Last_Name, " + 
                                                                       " TRUNC(lt.log_date, 'IW') as week_start_date,  -- how do we know it is the whole week (7 days) " + 
                                                                       " SUM(EXTRACT(HOUR FROM (lt.total_worked_time))) as weekly_worked_hours " + 
                                                                   " FROM Log_Time lt JOIN Employee e ON e.employee_ID = lt.employee_ID" + 
                                                                   " GROUP BY lt.employee_ID, e.employee_Fname, e.employee_Lname, TRUNC(lt.log_date, 'IW')";
                                                stmt.executeQuery(sql_view2);
                                                
                                                String runview2 = "SELECT * FROM weekly_hours";
                                                stmt.executeQuery(runview2); //ResultSet view2 = 
                                                //print?
                                                break;
                                                
                                            case 3: //employee_weekly_status view
                                                //Need employee ID Owner wants to access
                                                System.out.println("\nEnter the employee's ID you want to see the view of");
                                                String which_emp = keyboard.readLine();
                                                int emplo_id = Integer.parseInt(which_emp);
                                            
                                                //Erase/Drop previous view
                                                String sql_drop3 = "DROP VIEW employee_weekly_status";
                                                stmt.executeQuery(sql_drop3);
                                                
                                                //VIEW NOT CREATED YET, THIS IS AN EXAMPLE TO PASTE ON TOP -- IN THE WHERE CLAUSE ASKED ID
                                                String sql_view3 = "CREATE VIEW employee_weekly_status AS " +
                                                                   " SELECT lt.employee_ID, e.employee_Fname AS First_Name, e.employee_Lname AS Last_Name, " + 
                                                                       " TRUNC(lt.log_date, 'IW') as week_start_date,  -- how do we know it is the whole week (7 days) " + 
                                                                       " SUM(EXTRACT(HOUR FROM (lt.total_worked_time))) as weekly_worked_hours " + 
                                                                   " FROM Log_Time lt JOIN Employee e ON e.employee_ID = lt.employee_ID" + 
                                                                   " GROUP BY lt.employee_ID, e.employee_Fname, e.employee_Lname, TRUNC(lt.log_date, 'IW')";
                                                stmt.executeQuery(sql_view3);
                                                
                                                String runview3 = "SELECT * FROM employee_weekly_status";
                                                stmt.executeQuery(runview3); //ResultSet view3 =
                                                //print?
                                                break;
                            
                                            default:
                                                System.out.println("Invalid option.");
                                                break;
                           
                                        }
                            
                                    case 3: //grant privileges
                                    
//STILL NEED TO WORK HERE                                 
                            
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
                        
                            System.out.println("Enter 1 if you want to see your hours per week View"); //Employee has access to 2 views
                            System.out.println("Enter 2 if you want to see your status per week View");
                            System.out.println("Enter 3 to Quit");
                        
                            //Read input
                            String employee_input = keyboard.readLine();
                            int employee_option = Integer.parseInt(employee_input);
                
                            //To quit
                            if (employee_option == 3) {
                                break;
                            }
                        
                     
                            switch (employee_option) {
                                case 1: //employee_weekly_hours view

                                    //Need employee ID -- employee_ID (asked at the beginning
                                            
                                    //Erase/Drop previous view
                                    String sql_drop4 = "DROP VIEW employee_weekly_hours";
                                    stmt.executeQuery(sql_drop4);
                                    
                                    String sql_view4 = "CREATE VIEW employee_weekly_hours AS " +
                                                       " SELECT lt.employee_ID, e.employee_Fname AS First_Name, e.employee_Lname AS Last_Name, " + 
                                                       " TRUNC(lt.log_date, 'IW') as week_start_date,  -- how do we know it is the whole week (7 days) " + 
                                                       " SUM(EXTRACT(HOUR FROM (lt.total_worked_time))) as weekly_worked_hours " + 
                                                       " FROM Log_Time lt JOIN Employee e ON e.employee_ID = lt.employee_ID" + 
                                                       " WHERE e.employee_ID = " + employee_ID +
                                                       " GROUP BY lt.employee_ID, e.employee_Fname, e.employee_Lname, TRUNC(lt.log_date, 'IW')";
                                    stmt.executeQuery(sql_view4);
                                                
                                    String runview4 = "SELECT * FROM employee_weekly_hours";
                                    stmt.executeQuery(runview4); //ResultSet view4 = 
                                    //print?
                                    break;                                
                                
                                case 2: //employee_weekly_status view
                                    //Need employee ID -- employee_ID (asked at the beginning
                                            
                                    //Erase/Drop previous view
                                    String sql_drop5 = "DROP VIEW employee_weekly_status";
                                    stmt.executeQuery(sql_drop5);
                                    
                                    //VIEW NOT CREATED YET, THIS IS AN EXAMPLE TO PASTE ON TOP -- IN THE WHERE CLAUSE ASKED ID
                                    String sql_view5 = "CREATE VIEW employee_weekly_status AS " +
                                                       " SELECT lt.employee_ID, e.employee_Fname AS First_Name, e.employee_Lname AS Last_Name, " + 
                                                       " TRUNC(lt.log_date, 'IW') as week_start_date,  -- how do we know it is the whole week (7 days) " + 
                                                       " SUM(EXTRACT(HOUR FROM (lt.total_worked_time))) as weekly_worked_hours " + 
                                                       " FROM Log_Time lt JOIN Employee e ON e.employee_ID = lt.employee_ID" + 
                                                       " WHERE e.employee_ID = " + employee_ID +
                                                       " GROUP BY lt.employee_ID, e.employee_Fname, e.employee_Lname, TRUNC(lt.log_date, 'IW')";
                                    stmt.executeQuery(sql_view5);
                                                
                                    String runview5 = "SELECT * FROM employee_weekly_status";
                                    stmt.executeQuery(runview5); //ResultSet view4 = 
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
