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
                //System.out.println("Enter 3 to Terminate");
                System.out.println("Enter 3 to Terminate");
                
                //Read input
                String input = keyboard.readLine();
                int option = Integer.parseInt(input);
                
                //To quit
                /*
                if (option == 4) {
                    break;
                */
                                
                //To terminate
                if (option == 3) {
                    Runtime.getRuntime().halt(0);
                }

                switch (option) {
                    case 1: //Owner
                        
                        System.out.println("Enter your nickname:");
                        String owner_name = keyboard.readLine();
                        System.out.println("Enter your owner ID: (format: 0000)");
                        int owner_ID = Integer.parseInt(keyboard.readLine());
                        //if owner ID corresponds to list ask for password if not break.
                        String sqlCheck = "SELECT owner_ID FROM Owner WHERE owner_ID = " + owner_ID;
                        ResultSet check = stmt.executeQuery(sqlCheck);
                        if (!check.next()){//owner_ID is not equal(meaning the string is empty)  
                            System.out.println("Invalid ID.");
                            break;
                        } else { 
                            
                            System.out.println("Enter your password:");
                            String owner_password = keyboard.readLine();
                            String sqlCheck2 = "SELECT owner_password FROM Owner WHERE owner_password = '" + owner_password + "'";
                            ResultSet check2 = stmt.executeQuery(sqlCheck2);
                            if (!check2.next()){//owner_password is not equal(meaning the string is empty)
                                System.out.println("Invalid password.");
                                break;
                            } else { 

                                //After validating owner ID and password
                                System.out.println("Welcome to the Database " + owner_name);
                                System.out.println("\nEnter the option you would like to implement:");
                        
                                System.out.println("Enter 1 if you want to introduce updates to an existing table"); //(UPDATE, DELETE, INSERT)
                                System.out.println("Enter 2 if you want to see a view"); // Owner = 3 views (daily_status, weekly_hours, employee_weekly_status)
                                System.out.println("Enter 3 if you want to grant privileges"); //owner privilege...
                                System.out.println("Enter 4 if you want to revoke privileges");
                                System.out.println("Enter 5 to Terminate");
                
                                //Read input
                                String owner_input = keyboard.readLine();
                                int owner_option = Integer.parseInt(owner_input);
                
                                //To terminate
                                if (owner_option == 5) {
                                    Runtime.getRuntime().halt(0);
                                }
                        
                                switch (owner_option) {
                                    case 1: //Updates
                                        System.out.println("Enter the appropiate option:");
                                        System.out.println("Enter 1 to UPDATE a table");
                                        System.out.println("Enter 2 to DELETE from a table");
                                        System.out.println("Enter 3 to INSERT in a table");
                                        System.out.println("Enter 4 to Terminate");

                                        //Read input
                                        String up_input = keyboard.readLine();
                                        int up_option = Integer.parseInt(up_input);
                
                                        //To terminate
                                        if (up_option == 4) {
                                            Runtime.getRuntime().halt(0);
                                        }
                                        // which table -- JUST DO LIKE THREE TABLES -- (Owner, Employee, Department, Shift_Type, Employee_Shift_Schedule, Log_Time)
                                        System.out.println("Enter the table where you want to implement a change: (Owner, Employee, Log_Time)");
                                        String table = keyboard.readLine();
                                        
                                        
                                        switch (up_option) {
                                            case 1: //Update table
                                                
                                                if(table.trim().equalsIgnoreCase("Owner")){ //Owner(owner_ID, owner_password)
                                                    
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Update Owner's ID");
                                                    System.out.println("Enter 2 to Update Owner's password");
                                                    System.out.println("Enter 3 to Terminate");

                                                    int option_update = Integer.parseInt(keyboard.readLine());
                                           
                                                    if (option_update == 3) {
                                                        Runtime.getRuntime().halt(0);
                                                    }
                                                    if (option_update != 1 && option_update != 2 && option_update != 3) {
                                                        System.out.println("Invalid option.");
                                                            Runtime.getRuntime().halt(0);
                                                    }
                                                    
                                                    System.out.println("Enter Owner ID you want to update: (format: 0000)"); 
                                                    int owner_id = Integer.parseInt(keyboard.readLine());
                                                    
                                                    switch (option_update) {
                                                        case 1: // ID
                                                            System.out.println("Enter new Owner ID: (format: 0000)");
                                                            int new_OwnerID = Integer.parseInt(keyboard.readLine()); 
                                                            String sql_new1 = "UPDATE Owner SET owner_ID = " + new_OwnerID + " WHERE owner_ID = " + owner_id;
                                                            stmt.executeQuery(sql_new1);
                                                            System.out.println("Owner ID updated successfully.");
                                                            Runtime.getRuntime().halt(0);

                                                        case 2: // password
                                                            System.out.println("Enter new Owner password:");
                                                            String new_OwnerPassword = keyboard.readLine();
                                                            String sql_new2 = "UPDATE Owner SET owner_password = '" + new_OwnerPassword + "' WHERE owner_ID = " + owner_id;
                                                            stmt.executeQuery(sql_new2);
                                                            System.out.println("Owner password updated successfully.");
                                                            Runtime.getRuntime().halt(0);

                                                    }
                                                
                                                }else if(table.trim().equalsIgnoreCase("Employee")){ //Employee(employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID)
                                             
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Update Employee's ID");
                                                    System.out.println("Enter 2 to Update Employee's First name");
                                                    System.out.println("Enter 3 to Update Employee's Last name");
                                                    System.out.println("Enter 4 to Update Employee's Email");
                                                    System.out.println("Enter 5 to Update Employee's Phone");
                                                    System.out.println("Enter 6 to Update Employee's Date of Birth");
                                                    System.out.println("Enter 7 to Update Employee's Department ID");
                                                    System.out.println("Enter 8 to Terminate");

                                                    int optionEmp = Integer.parseInt(keyboard.readLine());
                                                    
                                                    if (optionEmp == 8) {
                                                        Runtime.getRuntime().halt(0);
                                                    }
                                                    if (optionEmp != 1 && optionEmp != 2 && optionEmp != 3 && optionEmp != 4 && optionEmp != 5 && optionEmp != 6 && optionEmp != 7 && optionEmp != 8) {
                                                        System.out.println("Invalid option.");
                                                            Runtime.getRuntime().halt(0);
                                                    }
                                                    System.out.println("Enter Employee ID you want to update: (format: 0000)"); 
                                                    int employee_id = Integer.parseInt(keyboard.readLine());
                                                    
                                                    switch (optionEmp) {
                                                        case 1: //ID
                                                            System.out.println("Enter new Employee ID: (format: 0000)");
                                                            int new_EmployeeID = Integer.parseInt(keyboard.readLine());
                                                            String sql_new3 = "UPDATE Employee SET employee_ID = " + new_EmployeeID + " WHERE employee_ID = " + employee_id;
                                                            stmt.executeQuery(sql_new3);
                                                            System.out.println("Employee ID updated successfully.");
                                                            Runtime.getRuntime().halt(0);

                                                        case 2: // Fname
                                                            System.out.println("Enter new Employee First name:");
                                                            String new_EmployeeFname = keyboard.readLine();
                                                            String sql_new4 = "UPDATE Employee SET employee_Fname = '" + new_EmployeeFname + "' WHERE employee_ID = " + employee_id;
                                                            stmt.executeQuery(sql_new4);
                                                            System.out.println("Employee First name updated successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                            
                                                        case 3: // Lname
                                                            System.out.println("Enter new Employee Last name:");
                                                            String new_EmployeeLname = keyboard.readLine();
                                                            String sql_new5 = "UPDATE Employee SET employee_Fname = '" + new_EmployeeLname + "' WHERE employee_ID = " + employee_id;
                                                            stmt.executeQuery(sql_new5);
                                                            System.out.println("Employee Last name updated successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                        
                                                        case 4: // Email
                                                            System.out.println("Enter new Employee Email:");
                                                            String new_EmployeeEmail = keyboard.readLine();
                                                            String sql_new7 = "UPDATE Employee SET employee_email = '" + new_EmployeeEmail + "' WHERE employee_ID = " + employee_id;
                                                            stmt.executeQuery(sql_new7);
                                                            System.out.println("Employee Email updated successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                        
                                                        case 5: // Phone
                                                            System.out.println("Enter new Employee Phone: (format: 000-000-0000)");
                                                            String new_EmployeePhone = keyboard.readLine();
                                                            String sql_new8 = "UPDATE Employee SET employee_phone = '" + new_EmployeePhone + "' WHERE employee_ID = " + employee_id;
                                                            stmt.executeQuery(sql_new8);
                                                            System.out.println("Employee Phone updated successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                        
                                                        case 6: // Date of Birth
                                                            System.out.println("Enter new Employee Date of Birth: (format: YYYY-MM-DD)"); //TO_DATE('1999-10-10', 'YYYY-MM-DD')
                                                            String new_EmployeeDoB = keyboard.readLine();
                                                            String sql_new9 = "UPDATE Employee SET employee_dob = TO_DATE('" + new_EmployeeDoB + "', 'YYYY-MM-DD') WHERE employee_ID = " + employee_id;
                                                            //UPDATE Employee SET employee_dob = TO_DATE('1999-10-11', 'YYYY-MM-DD') WHERE employee_ID = 1110;
                                                            stmt.executeQuery(sql_new9);
                                                            System.out.println("Employee Date of Birth updated successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                            
                                                        case 7: // Department ID
                                                            System.out.println("Enter new Employee Department ID: (format: 0000)");
                                                            int new_EmployeeDepartment = Integer.parseInt(keyboard.readLine());
                                                            String sql_new6 = "UPDATE Employee SET employee_department = " + new_EmployeeDepartment + " WHERE employee_ID = " + employee_id;
                                                            stmt.executeQuery(sql_new6);
                                                            System.out.println("Employee Department ID updated successfully.");
                                                            Runtime.getRuntime().halt(0);

                                                    }
                                                
                                                }else if(table.trim().equalsIgnoreCase("Log_Time")){ //Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time, total_worked_time)
                               
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Update Log's ID");
                                                    System.out.println("Enter 2 to Update Log's employee ID");
                                                    System.out.println("Enter 3 to Update Log's Shift ID");
                                                    //System.out.println("Enter 4 to Update Log's Date"); //The date in Log_Time is set as the current date, so it cannot be updated
                                                    System.out.println("Enter 4 to Update Log's login time");
                                                    System.out.println("Enter 5 to Update Log's logout time");
                                                    //System.out.println("Enter 7 to Update Log's total worked hours"); //Total worked time is an automatic calculation so it cannot be updated:
                                                    System.out.println("Enter 6 to Terminate");

                                                    int optionLog = Integer.parseInt(keyboard.readLine());
                                                   
                                                    if (optionLog == 6) {
                                                        Runtime.getRuntime().halt(0);
                                                    }
                                                    if (optionLog != 1 && optionLog != 2 && optionLog != 3 && optionLog != 4 && optionLog != 5 && optionLog != 6) {
                                                        System.out.println("Invalid option.");
                                                            Runtime.getRuntime().halt(0);
                                                    }
                                                    System.out.println("Enter the Log ID you want to update: (format: 0000)"); 
                                                    int log_id = Integer.parseInt(keyboard.readLine());
                                                    
                                                    switch (optionLog) {
                                                        case 1: //Log ID
                                                            System.out.println("Enter new Log ID: (format: 0000)");
                                                            int new_LogID = Integer.parseInt(keyboard.readLine());
                                                            String sql_1 = "UPDATE Log_Time SET log_ID = " + new_LogID + " WHERE log_ID = " + log_id;
                                                            stmt.executeQuery(sql_1);
                                                            System.out.println("Log_Time log ID updated successfully.");
                                                            Runtime.getRuntime().halt(0);

                                                        case 2: // employee ID
                                                            System.out.println("Enter new Log_Time Employee ID: (format: 0000)");
                                                            int new_LogEmployeeID = Integer.parseInt(keyboard.readLine());
                                                            String sql_2 = "UPDATE Log_Time SET employee_ID = " + new_LogEmployeeID + " WHERE log_ID = " + log_id;
                                                            stmt.executeQuery(sql_2);
                                                            System.out.println("Log_Time log ID updated successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                            
                                                        case 3: // Shift ID
                                                            System.out.println("Enter new Log_Time Shift ID: (format: 0000)");
                                                            int new_LogShiftID = Integer.parseInt(keyboard.readLine());
                                                            String sql_3 = "UPDATE Log_Time SET shift_ID = " + new_LogShiftID + " WHERE log_ID = " + log_id;
                                                            stmt.executeQuery(sql_3);
                                                            System.out.println("Log_Time Shift ID updated successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                        
                                                        case 4: // login
                                                            System.out.println("Enter new Login time: (format: 00:00:00)");
                                                            String new_login = keyboard.readLine();
                                                            String sql_5 = "UPDATE Log_Time SET login_time = TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || '" + new_login + "', 'YYYY-MM-DD HH24:MI:SS') WHERE log_ID = " + log_id; 
                                                            //UPDATE Log_Time SET login_time = TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 05:00:00', 'YYYY-MM-DD HH24:MI:SS') WHERE log_ID = 6610;
                                                            stmt.executeQuery(sql_5);
                                                            System.out.println("Log_Time login time updated successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                        
                                                        case 5: // logout
                                                            System.out.println("Enter new Log_Time Phone: (format: 00:00:00)");
                                                            String new_logout = keyboard.readLine();
                                                            String sql_6 = "UPDATE Log_Time SET logout_time = TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || '" + new_logout + "', 'YYYY-MM-DD HH24:MI:SS') WHERE log_ID = " + log_id;
                                                            stmt.executeQuery(sql_6);
                                                            System.out.println("Log_Time logout time updated successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                 
                                                    }
                                                
                                                }else
                                                    System.out.println("Invalid table.");
                                                    Runtime.getRuntime().halt(0);
                                               
                                                
                                            case 2: //Delete from table
                                            
                                                if(table.trim().equalsIgnoreCase("Owner")){ //Owner(owner_ID, owner_password)                                                    
                                                    
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Delete according to Owner's ID");
                                                    System.out.println("Enter 2 to Delete according to Owner's password");
                                                    System.out.println("Enter 3 to Terminate");

                                                    int option_delete = Integer.parseInt(keyboard.readLine());
                          
                                                    if (option_delete == 3) {
                                                        Runtime.getRuntime().halt(0);
                                                    }
                                                  
                                                    switch (option_delete) { //DELETE FROM table WHERE ...
                                                        case 1: // ID
                                                            System.out.println("Enter Owner ID from where you want to delete: (format: 0000)"); 
                                                            int owner_id2 = Integer.parseInt(keyboard.readLine());
                                                            String sql_old1 = "DELETE FROM Owner WHERE owner_ID = " + owner_id2;
                                                            stmt.executeQuery(sql_old1);
                                                            System.out.println("Row/s according to Owner ID deleted successfully.");
                                                            Runtime.getRuntime().halt(0);

                                                        case 2: // password
                                                            System.out.println("Enter Owner password from where you want to delete:");
                                                            String old_OwnerPassword = keyboard.readLine();
                                                            String sql_old2 = "DELETE FROM Owner WHERE owner_password = '" + old_OwnerPassword + "'";
                                                            stmt.executeQuery(sql_old2);
                                                            System.out.println("Row/s according to Owner password deleted successfully.");
                                                            Runtime.getRuntime().halt(0);

                                                        default:
                                                            System.out.println("Invalid option.");
                                                            Runtime.getRuntime().halt(0);
                                                    }
                                                 
                                                }else if(table.trim().equalsIgnoreCase("Employee")){ //Employee(employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID)                                                   
                                                    
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Delete according to Employee's ID");
                                                    //System.out.println("Enter 2 to Delete according to Employee's First name"); does not make sense to delete from first or last name (can be the same and not same person)
                                                    //System.out.println("Enter 3 to Delete according to Employee's Last name"); 
                                                    System.out.println("Enter 2 to Delete according to Employee's Email");
                                                    System.out.println("Enter 3 to Delete according to Employee's Phone");
                                                    System.out.println("Enter 4 to Delete according to Employee's Date of Birth");
                                                    System.out.println("Enter 5 to Delete according to Employee's Department ID");
                                                    System.out.println("Enter 6 to Terminate");

                                                    int optionEmp2 = Integer.parseInt(keyboard.readLine());
                                          
                                                    if (optionEmp2 == 6) {
                                                        Runtime.getRuntime().halt(0);
                                                    }
                                                    
                                                    switch (optionEmp2) {
                                                        case 1: //ID
                                                            System.out.println("Enter Employee ID from where you want to delete: (format: 0000)");
                                                            int old_EmployeeID = Integer.parseInt(keyboard.readLine());
                                                            String sql_old3 = "DELETE FROM Employee WHERE employee_ID = " + old_EmployeeID;
                                                            stmt.executeQuery(sql_old3);
                                                            System.out.println("Row/s according to Employee ID deleted successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                        
                                                        case 2: // Email
                                                            System.out.println("Enter Employee Email from where you want to delete:");
                                                            String old_EmployeeEmail = keyboard.readLine();
                                                            String sql_old7 = "DELETE FROM Employee WHERE employee_email = '" + old_EmployeeEmail + "'";
                                                            stmt.executeQuery(sql_old7);
                                                            System.out.println("Row/s according to Employee Email deleted successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                        
                                                        case 3: // Phone
                                                            System.out.println("Enter Employee Phone from where you want to delete: (format: 000-000-0000)");
                                                            String old_EmployeePhone = keyboard.readLine();
                                                            String sql_old8 = "DELETE FROM Employee WHERE employee_phone = '" + old_EmployeePhone + "'";
                                                            stmt.executeQuery(sql_old8);
                                                            System.out.println("Row/s according to Employee Phone deleted successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                        
                                                        case 4: // Date of Birth
                                                            System.out.println("Enter Employee Date of Birth from where you want to delete: (format: YYYY-MM-DD)"); //TO_DATE('1999-10-10', 'YYYY-MM-DD')
                                                            String old_EmployeeDoB = keyboard.readLine();
                                                            String sql_old9 = "DELETE FROM Employee WHERE employee_dob = TO_DATE('" + old_EmployeeDoB + "', 'YYYY-MM-DD')";
                                                            stmt.executeQuery(sql_old9);
                                                            System.out.println("Row/s according to Employee Date of Birth deleted successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                            
                                                        case 5: // Department ID
                                                            System.out.println("Enter Employee Department from where you want to delete: (format: 0000)");
                                                            int old_EmployeeDepartmentID = Integer.parseInt(keyboard.readLine());
                                                            String sql_old6 = "DELETE FROM Employee WHERE employee_departmentID = " + old_EmployeeDepartmentID;
                                                            stmt.executeQuery(sql_old6);
                                                            System.out.println("Row/s according to Employee Department ID deleted successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                          
                                                        default:
                                                            System.out.println("Invalid option.");
                                                            Runtime.getRuntime().halt(0);
                                                    }
                                                
                                                }else if(table.trim().equalsIgnoreCase("Log_Time")){ //Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time, total_worked_time
                                                    
                                                    System.out.println("\nPlease select an option:");
                                                    System.out.println("Enter 1 to Update Log's ID");
                                                    System.out.println("Enter 2 to Update Log's employee ID");
                                                    System.out.println("Enter 3 to Update Log's Shift ID");
                                                    //System.out.println("Enter 4 to Update Log's Date"); //current date
                                                    //System.out.println("Enter 5 to Update Log's login time"); //does not make sense to delete from login, logout, and total hours 
                                                    //System.out.println("Enter 6 to Update Log's logout time");
                                                    //System.out.println("Enter 7 to Update Log's total worked hours");
                                                    System.out.println("Enter 4 to Terminate");

                                                    int optionLog = Integer.parseInt(keyboard.readLine());
                                                    
                                                    if (optionLog == 4) {
                                                        Runtime.getRuntime().halt(0);
                                                    }
                                                    
                                                    switch (optionLog) {
                                                        case 1: //Log ID
                                                            System.out.println("Enter Log ID from where you want to delete: (format: 0000)");
                                                            int old_LogID = Integer.parseInt(keyboard.readLine());
                                                            String oldsql_1 = "DELETE FROM Log_Time WHERE log_ID = " + old_LogID;
                                                            stmt.executeQuery(oldsql_1);
                                                            System.out.println("Row/s according to Log_Time log ID deleted successfully.");
                                                            Runtime.getRuntime().halt(0);

                                                        case 2: // employee ID
                                                            System.out.println("Enter Log_Time Employee ID from where you want to delete: (format: 0000)");
                                                            int old_LogEmployeeID = Integer.parseInt(keyboard.readLine());
                                                            String oldsql_2 = "DELETE FROM Log_Time WHERE employee_ID = " + old_LogEmployeeID;
                                                            stmt.executeQuery(oldsql_2);
                                                            System.out.println("Row/s according to Log_Time log ID deleted successfully.");
                                                            Runtime.getRuntime().halt(0);
                                                            
                                                        case 3: // Shift ID
                                                            System.out.println("Enter Log_Time Shift ID from where you want to delete: (format: 0000)");
                                                            int old_LogShiftID = Integer.parseInt(keyboard.readLine());
                                                            String oldsql_3 = "DELETE FROM Log_Time WHERE shift_ID = " + old_LogShiftID;
                                                            stmt.executeQuery(oldsql_3);
                                                            System.out.println("Row/s according to Log_Time Shift ID deleted successfully.");
                                                            Runtime.getRuntime().halt(0);
                                             
                                                        default:
                                                            System.out.println("Invalid option.");
                                                            Runtime.getRuntime().halt(0);
                                                    }
                                                
                                                }else
                                                    System.out.println("Invalid table.");
                                                    Runtime.getRuntime().halt(0);
                                            
                                            case 3: //Insert in table -- insert into TABLE values ('X', 'X', 'X', X);

                                                if(table.trim().equalsIgnoreCase("Owner")){ //INSERT INTO Owner (owner_ID, owner_password) VALUES (0001, 'password1');
                                                    System.out.println("To insert a new row in the Owner table:");
                                                    System.out.println("Choose a new ID: (format: 0000)");
                                                    int newOwnerID = Integer.parseInt(keyboard.readLine());
                                                    System.out.println("Choose a new password:");
                                                    String newOwnerPassword = keyboard.readLine();
                                                   
                                                    String new_OwnerRow = "INSERT INTO Owner (owner_ID, owner_password) VALUES (" + newOwnerID + ", '" + newOwnerPassword + "')";
                                                    stmt.executeQuery(new_OwnerRow);
                                                    
                                                    System.out.println("Row has been added successfully.");
                                                    Runtime.getRuntime().halt(0);
                                                
                                                }else if(table.trim().equalsIgnoreCase("Employee")){ //INSERT INTO Employee (employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID) VALUES (1111, 'John', 'Doe', 'johndoe@example.com', '123-456-7890', TO_DATE('1990-01-01', 'YYYY-MM-DD'), 2210);
                                                    System.out.println("To insert a new row in the Employee table:");
                                                    System.out.println("Choose a new ID: (format: 0000)");
                                                    int newEmployeeID = Integer.parseInt(keyboard.readLine());
                                                    System.out.println("Choose a new First name:");
                                                    String newEmployeeFname = keyboard.readLine();
                                                    System.out.println("Choose a new Last name:");
                                                    String newEmployeeLname = keyboard.readLine();
                                                    System.out.println("Choose a new email:");
                                                    String newEmployeeEmail = keyboard.readLine();
                                                    System.out.println("Choose a new phone number: (format: 000-000-0000)");
                                                    String newEmployeePhone = keyboard.readLine();
                                                    System.out.println("Choose a new date of birth: (format: YYYY-MM-DD)");
                                                    String newEmployeeDoB = keyboard.readLine();
                                                    System.out.println("Choose a new department ID: (format: 0000)");
                                                    int newDepartmentID = Integer.parseInt(keyboard.readLine());
                                                   
                                                    String new_EmployeeRow = "INSERT INTO Employee (employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID) VALUES (" + newEmployeeID + ", '" + newEmployeeFname + "', '" + newEmployeeLname + "', '" + newEmployeeEmail + "', '" + newEmployeePhone + "', TO_DATE('" + newEmployeeDoB + "', 'YYYY-MM-DD'), " + newDepartmentID + ")";
                                                    stmt.executeQuery(new_EmployeeRow);
                                                    
                                                    System.out.println("Row has been added successfully.");
                                                    Runtime.getRuntime().halt(0);
                                                
                                                }else if(table.trim().equalsIgnoreCase("Log_Time")){ //INSERT INTO Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time) VALUES (6661, 1111, 3331, TRUNC(SYSDATE), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS'));
                                                    System.out.println("To insert a new row in the Log_Time table:");
                                                    System.out.println("Choose a new ID: (format: 0000)");
                                                    int newLogID = Integer.parseInt(keyboard.readLine());
                                                    System.out.println("Choose a new employee ID: (format: 0000)");
                                                    int newEmpID = Integer.parseInt(keyboard.readLine());
                                                    System.out.println("Choose a new shift ID: (format: 0000)");
                                                    int newShifID = Integer.parseInt(keyboard.readLine());
                                                    System.out.println("Choose a new login time: (format: 00:00:00)");
                                                    String newLogin = keyboard.readLine();
                                                    System.out.println("Choose a new logout time: (format: 00:00:00)");
                                                    String newLogout = keyboard.readLine();
                                                  
                                                    String new_LogRow = "INSERT INTO Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time) VALUES (" + newLogID + ", " + newEmpID + ", " + newShifID + ", TRUNC(SYSDATE), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' " + newLogin + "', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' " + newLogout + "', 'YYYY-MM-DD HH24:MI:SS'))";
                                                    stmt.executeQuery(new_LogRow);
                                                    
                                                    System.out.println("Row has been added successfully.");
                                                    Runtime.getRuntime().halt(0);
                                                
                                                }else
                                                    System.out.println("Invalid table.");
                                                    Runtime.getRuntime().halt(0);
                                            
                                            default:
                                                System.out.println("Invalid option.");
                                                Runtime.getRuntime().halt(0);
                                    
                                        }   
                                        
//UPDATE VIEWS                                    
                                    case 2: //see view
                              
                                        System.out.println("\nEnter the view you would like to see:"); //which view
                        
                                        System.out.println("Enter 1 if you want to see the owner daily status"); //Owner has access to 3 views (daily_status, weekly_hours, employee_weekly_status)
                                        System.out.println("Enter 2 if you want to see the owner weekly hours");
                                        System.out.println("Enter 3 if you want to see the owner weekly status");
                                        System.out.println("Enter 4 to Terminate");
                        
                                        //Read input
                                        int owner_view_option = Integer.parseInt(keyboard.readLine());
                       
                                        //To terminate
                                        if (owner_view_option == 4) {
                                            Runtime.getRuntime().halt(0);
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
                                                                       " TRUNC(lt.log_date, 'IW') as week_start_date, " + 
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
                                                System.out.println("\nEnter the employee's ID you want to see the view of: (format: 0000)");
                                                int which_emp = Integer.parseInt(keyboard.readLine());
                                               
                                                //Erase/Drop previous view
                                                String sql_drop3 = "DROP VIEW employee_weekly_status";
                                                stmt.executeQuery(sql_drop3);
                                                
                                                //VIEW NOT CREATED YET, THIS IS AN EXAMPLE TO PASTE ON TOP -- IN THE WHERE CLAUSE ASKED ID
                                                String sql_view3 = "CREATE VIEW employee_weekly_status AS " +
                                                                   " SELECT lt.employee_ID, e.employee_Fname AS First_Name, e.employee_Lname AS Last_Name, " + 
                                                                       " TRUNC(lt.log_date, 'IW') as week_start_date, " + 
                                                                       " SUM(EXTRACT(HOUR FROM (lt.total_worked_time))) as weekly_worked_hours " + 
                                                                   " FROM Log_Time lt JOIN Employee e ON e.employee_ID = lt.employee_ID" + 
                                                                   " GROUP BY lt.employee_ID, e.employee_Fname, e.employee_Lname, TRUNC(lt.log_date, 'IW')";
                                                stmt.executeQuery(sql_view3);
                                                
                                                String runview3 = "SELECT * FROM employee_weekly_status"; //if able to just run view: "SELECT * FROM employee_weekly_status WHERE e.employee_ID = " + employee_ID;
                                                stmt.executeQuery(runview3); //ResultSet view3 =
                                                //print?
                                                break;
                            
                                            default:
                                                System.out.println("Invalid option.");
                                                Runtime.getRuntime().halt(0);
                           
                                        }
                            
                                    case 3: //grant privileges
                                        //Enter Id instead of Username so it is a unique values
                                        System.out.println("Enter the ID of the the user you would like to grant privileges to: (format: 0000)");

                                        // User that you are granting privileges to
                                        int grant_privileges_userID = Integer.parseInt(keyboard.readLine());

                                        // System.out.println("Enter the table you would like to give authorization to");
                                        System.out.println("Enter the option for the table you want to grant privileges for");
                                        System.out.println("Enter 1 if you want to grant privileges to the Owner Table");
                                        System.out.println("Enter 2 if you want to grant privileges to the Employee Table");
                                        System.out.println("Enter 3 if you want to grant privileges to the Department Table");
                                        System.out.println("Enter 4 if you want to grant privileges to the Shift_Type Table");
                                        System.out.println("Enter 5 if you want to grant privileges to the Employee_Shift_Schedule Table");
                                        System.out.println("Enter 6 if you want to grant privileges to the Log_Time Table");
                                        System.out.println("Enter 7 to Terminate");

                                        // Read input
                                        int grant_table_privilege = Integer.parseInt(keyboard.readLine());

                                        // Table permissions are being granted for
                                        String grant_privileges_table = "";

                                        switch (grant_table_privilege) {
                                            case 1:
                                                grant_privileges_table = "Owner";
                                                break;
                                            case 2:
                                                grant_privileges_table = "Employee";
                                                break;
                                            case 3:
                                                grant_privileges_table = "Department";
                                                break;
                                            case 4:
                                                grant_privileges_table = "Shift_Type";
                                                break;
                                            case 5:
                                                grant_privileges_table = "Employee_Shift_Schedule";
                                                break;
                                            case 6:
                                                grant_privileges_table = "Log_Time";
                                                break;
                                            case 7:
                                                Runtime.getRuntime().halt(0);
                                        }

                                        System.out.println("Enter the option for the privilege you would like to grant");
                                        System.out.println("Enter 1 if you want to grant SELECT privileges");
                                        System.out.println("Enter 2 if you want to grant INSERT privileges");
                                        System.out.println("Enter 3 if you want to grant UPDATE privileges");
                                        System.out.println("Enter 4 if you want to grant DELETE privileges");
                                        System.out.println("Enter 5 if you want to grant ALL privileges");
                                        System.out.println("Enter 6 to Terminate");

                                        //Read input
                                        int grant_privileges_option = Integer.parseInt(keyboard.readLine());

                                        switch (grant_privileges_option) {
                                            case 1:
                                                String sql_privilege1 = "GRANT SELECT ON " + grant_privileges_table + " to " + grant_privileges_userID;
                                                stmt.executeQuery(sql_privilege1);
                                                break;
                                            case 2:
                                                String sql_privilege2 = "GRANT INSERT ON " + grant_privileges_table + " to " + grant_privileges_userID;
                                                stmt.executeQuery(sql_privilege2);
                                                break;
                                            case 3:
                                                String sql_privilege3 = "GRANT UPDATE ON " + grant_privileges_table + " to " + grant_privileges_userID;
                                                stmt.executeQuery(sql_privilege3);
                                                break;
                                            case 4:
                                                String sql_privilege4 = "GRANT DELETE ON " + grant_privileges_table + " to " + grant_privileges_userID;
                                                stmt.executeQuery(sql_privilege4);
                                                break;
                                            case 5:
                                                String sql_privilege5 = "GRANT ALL PRIVILEGES ON " + grant_privileges_table + " to " + grant_privileges_userID;
                                                stmt.executeQuery(sql_privilege5);
                                                break;
                                            case 6:
                                                Runtime.getRuntime().halt(0);
                                        }
                                        
                                    case 4: // revoke privileges
                                        System.out.println("Enter the username for the user you would like to revoke privileges from: ");

                                        // User that you are revoking privileges from
                                        String revoke_privileges_user = keyboard.readLine(); //shouldn't this be User ID to be unique?

                                        //System.out.println("Enter the table you would like to revoke authorization to");
                                        System.out.println("Enter the option for the table you want to revoke privileges for");
                                        System.out.println("Enter 1 if you want to revoke privileges to the Owner Table");
                                        System.out.println("Enter 2 if you want to revoke privileges to the Employee Table");
                                        System.out.println("Enter 3 if you want to revoke privileges to the Department Table");
                                        System.out.println("Enter 4 if you want to revoke privileges to the Shift_Type Table");
                                        System.out.println("Enter 5 if you want to revoke privileges to the Employee_Shift_Schedule Table");
                                        System.out.println("Enter 6 if you want to revoke privileges to the Log_Time Table");
                                        System.out.println("Enter 7 to Quit");

                                        // Read input
                                        int revoke_table_privilege = Integer.parseInt(keyboard.readLine());

                                        // Table permissions are being revoked for
                                        String revoke_privileges_table = "";

                                        switch (revoke_table_privilege) {
                                            case 1:
                                                revoke_privileges_table = "Owner";
                                                break;
                                            case 2:
                                                revoke_privileges_table = "Employee";
                                                break;
                                            case 3:
                                                revoke_privileges_table = "Department";
                                                break;
                                            case 4:
                                                revoke_privileges_table = "Shift_Type";
                                                break;
                                            case 5:
                                                revoke_privileges_table = "Employee_Shift_Schedule";
                                                break;
                                            case 6:
                                                revoke_privileges_table = "Log_Time";
                                                break;
                                            case 7:
                                                Runtime.getRuntime().halt(0);
                                            default:
                                                break;
                                        }

                                        System.out.println("Enter the option for the privilege you would like to revoke");
                                        System.out.println("Enter 1 if you want to revoke SELECT privileges");
                                        System.out.println("Enter 2 if you want to revoke INSERT privileges");
                                        System.out.println("Enter 3 if you want to revoke UPDATE privileges");
                                        System.out.println("Enter 4 if you want to revoke DELETE privileges");
                                        System.out.println("Enter 5 if you want to revoke ALL privileges");
                                        System.out.println("Enter 6 to Quit");

                                        //Read input
                                        int revoke_privileges_option = Integer.parseInt(keyboard.readLine());

                                        switch (revoke_privileges_option) {
                                            case 1:
                                                String sql_privilege1 = "REVOKE SELECT ON " + revoke_privileges_table + " from " + revoke_privileges_user;
                                                stmt.executeQuery(sql_privilege1);
                                                break;
                                            case 2:
                                                String sql_privilege2 = "REVOKE INSERT ON " + revoke_privileges_table + " from " + revoke_privileges_user;
                                                stmt.executeQuery(sql_privilege2);
                                                break;
                                            case 3:
                                                String sql_privilege3 = "REVOKE UPDATE ON " + revoke_privileges_table + " from " + revoke_privileges_user;
                                                stmt.executeQuery(sql_privilege3);
                                                break;
                                            case 4:
                                                String sql_privilege4 = "REVOKE DELETE ON " + revoke_privileges_table + " from " + revoke_privileges_user;
                                                stmt.executeQuery(sql_privilege4);
                                                break;
                                            case 5:
                                                String sql_privilege5 = "REVOKE ALL PRIVILEGES ON " + revoke_privileges_table + " from " + revoke_privileges_user;
                                                stmt.executeQuery(sql_privilege5);
                                                break;
                                            case 6:
                                                Runtime.getRuntime().halt(0);
                                            default:
                                                break;
                                        }                     
                            
                                    default:
                                        System.out.println("Invalid option.");
                                        Runtime.getRuntime().halt(0);
                            
                                }
                            
                            }   
                                                    
                        }
                   
                                 
                    case 2: //Employee
                        
                        System.out.println("Enter your first name");
                        String employee_Fname = keyboard.readLine();
                        System.out.println("Enter your ID (format: 0000)");
                        int employee_ID = Integer.parseInt(keyboard.readLine());
                        
                        String sql_employee = "SELECT employee_ID FROM Employee WHERE employee_ID = " + employee_ID;
                        ResultSet check_employee = stmt.executeQuery(sql_employee);
                        if (!check_employee.next()){//sql_employee is not equal(meaning the string is empty)
                            System.out.println("Invalid ID.");
                            break;
                        } else { 
                        
                            //After validating employee ID 
                            System.out.println("Welcome to the Database " + employee_Fname);
                            System.out.println("\nEnter the view you would like to see");
                        
                            System.out.println("Enter 1 if you want to see your hours per week View"); //Employee has access to 2 views
                            System.out.println("Enter 2 if you want to see your status per week View");
                            System.out.println("Enter 3 to Terminate");
                        
                            //Read input
                            int employee_option = Integer.parseInt(keyboard.readLine());
                           
                            //To terminate
                            if (employee_option == 3) {
                                Runtime.getRuntime().halt(0);
                            }
                        
//UPDATE VIEWS                      
                            switch (employee_option) {
                                case 1: //employee_weekly_hours view

                                    //Need employee ID -- employee_ID (asked at the beginning)
                                            
                                    //Erase/Drop previous view
                                    String sql_drop4 = "DROP VIEW employee_weekly_hours";
                                    stmt.executeQuery(sql_drop4);
                                    
                                    String sql_view4 = "CREATE VIEW employee_weekly_hours AS " +
                                                       " SELECT lt.employee_ID, e.employee_Fname AS First_Name, e.employee_Lname AS Last_Name, " + 
                                                           " TRUNC(lt.log_date, 'IW') as week_start_date, " + 
                                                           " SUM(EXTRACT(HOUR FROM (lt.total_worked_time))) as weekly_worked_hours " + 
                                                       " FROM Log_Time lt JOIN Employee e ON e.employee_ID = lt.employee_ID" + 
                                                       " WHERE e.employee_ID = " + employee_ID +
                                                       " GROUP BY lt.employee_ID, e.employee_Fname, e.employee_Lname, TRUNC(lt.log_date, 'IW')";
                                    stmt.executeQuery(sql_view4);
                                                
                                    String runview4 = "SELECT * FROM employee_weekly_hours"; //if able to just run view: "SELECT * FROM employee_weekly_status WHERE e.employee_ID = " + employee_ID;
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
                                                           " TRUNC(lt.log_date, 'IW') as week_start_date, " + 
                                                           " SUM(EXTRACT(HOUR FROM (lt.total_worked_time))) as weekly_worked_hours " + 
                                                       " FROM Log_Time lt JOIN Employee e ON e.employee_ID = lt.employee_ID" + 
                                                       " WHERE e.employee_ID = " + employee_ID +
                                                       " GROUP BY lt.employee_ID, e.employee_Fname, e.employee_Lname, TRUNC(lt.log_date, 'IW')";
                                    stmt.executeQuery(sql_view5);
                                                
                                    String runview5 = "SELECT * FROM employee_weekly_status"; //if able to just run view: "SELECT * FROM employee_weekly_status WHERE e.employee_ID = " + employee_ID;
                                    stmt.executeQuery(runview5); //ResultSet view4 = 
                                    //print?
                                    break;
                            
                                default:
                                    System.out.println("Invalid option.");
                                    Runtime.getRuntime().halt(0);
                           
                            }
                        }
                         
                               
                    default:
                        System.out.println("Invalid option.");
                        Runtime.getRuntime().halt(0);
        
                }
                
            }

            //stmt.close();
            //conn.commit();
            //conn.close();

        } catch (SQLException e) {
            System.out.println("SQL Exception:" + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    conn.commit();
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception:" + e.getMessage());
            }
        }
    }
}
