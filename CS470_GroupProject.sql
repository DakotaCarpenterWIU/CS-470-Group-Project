-- CS470 GROUP PROJECT
-- Spring 2023

-- Tables

    -- Read & Write (priviledge level)
     -- Owner(owner_ID, owner_password)
    -- Read only
     -- Employee(employee_ID, employee_Fname, employee_Lname, employee_department, employee_email, employee_phone, employee_dob)
     -- Department(department_ID, department_name)
     -- Shift_Type(shift_ID, shift_name, shift_start_time, shift_end_time)
     -- Employee_Shift_Schedule(employee_shift_ID, employee_ID, shift_ID, date)
     -- Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time, total_worked_time)

-- Owner Table
CREATE TABLE Owner (
    owner_ID INT PRIMARY KEY,
    owner_password VARCHAR(255)
);

-- Employee Table
CREATE TABLE Employee (
    employee_ID INT PRIMARY KEY,
    employee_Fname VARCHAR(255),
    employee_Lname VARCHAR(255),
    employee_email VARCHAR(255),
    employee_phone VARCHAR(255),
    employee_dob DATE,
    department_ID INT,
    FOREIGN KEY (department_ID) REFERENCES Department(department_ID)
);

-- Department Table
CREATE TABLE Department (
    department_ID INT PRIMARY KEY,
    department_name VARCHAR(255)
);

-- Shift_Type Table
CREATE TABLE Shift_Type (
    shift_ID INT PRIMARY KEY,
    shift_name VARCHAR(255),
    shift_start_time TIMESTAMP,             -- Only needed the time not timestamp but can't make it work with TIME
    shift_end_time TIMESTAMP                -- I just added the current date so it works with fixed times
);

-- Employee_Shift_Schedule Table
CREATE TABLE Employee_Shift_Schedule (
    employee_shift_ID INT PRIMARY KEY,
    employee_ID INT,
    shift_ID INT,
    shift_schedule_date DATE,
    FOREIGN KEY (employee_ID) REFERENCES Employee(employee_ID),
    FOREIGN KEY (shift_ID) REFERENCES Shift_Type(shift_ID)
);

-- Log_Time Table
CREATE TABLE Log_Time (
    log_ID INT PRIMARY KEY,
    employee_ID INT,
    shift_ID INT,
    log_date DATE,
    login_time TIMESTAMP,                                                       -- as an example without input from user employee                                                 
    logout_time TIMESTAMP,                                                      -- as an example without input from user employee 
    total_worked_time INTERVAL DAY TO SECOND AS (logout_time - login_time),     -- (logout_time - login_time) -> 1 day
    FOREIGN KEY (employee_ID) REFERENCES Employee(employee_ID),
    FOREIGN KEY (shift_ID) REFERENCES Shift_Type(shift_ID)
);





-- Example populate

-- Owner table
INSERT INTO Owner (owner_ID, owner_password) VALUES (0001, 'password1');
INSERT INTO Owner (owner_ID, owner_password) VALUES (0002, 'password2');
INSERT INTO Owner (owner_ID, owner_password) VALUES (0003, 'password3');
INSERT INTO Owner (owner_ID, owner_password) VALUES (0004, 'password4');
INSERT INTO Owner (owner_ID, owner_password) VALUES (0005, 'password5');
INSERT INTO Owner (owner_ID, owner_password) VALUES (0006, 'password6');
INSERT INTO Owner (owner_ID, owner_password) VALUES (0007, 'password7');
INSERT INTO Owner (owner_ID, owner_password) VALUES (0008, 'password8');
INSERT INTO Owner (owner_ID, owner_password) VALUES (0009, 'password9');
INSERT INTO Owner (owner_ID, owner_password) VALUES (0010, 'password10');

SELECT * FROM Owner;

-- Employee table
INSERT INTO Employee (employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID) VALUES (1111, 'John', 'Doe', 'johndoe@example.com', '123-456-7890', TO_DATE('1990-01-01', 'YYYY-MM-DD'), 2210);
INSERT INTO Employee (employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID) VALUES (1112, 'Jane', 'Doe', 'janedoe@example.com', '234-567-8901', TO_DATE('1991-02-02', 'YYYY-MM-DD'), 2229);
INSERT INTO Employee (employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID) VALUES (1113, 'Bob', 'Smith', 'bobsmith@example.com', '345-678-9012', TO_DATE('1992-03-03', 'YYYY-MM-DD'), 2228);
INSERT INTO Employee (employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID) VALUES (1114, 'Alice', 'Johnson', 'alicejohnson@example.com', '456-789-0123', TO_DATE('1993-04-04', 'YYYY-MM-DD'), 2226);
INSERT INTO Employee (employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID) VALUES (1115, 'James', 'Williams', 'jameswilliams@example.com', '567-890-1234', TO_DATE('1994-05-05', 'YYYY-MM-DD'), 2227);
INSERT INTO Employee (employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID) VALUES (1116, 'Emily', 'Brown', 'emilybrown@example.com', '678-901-2345', TO_DATE('1995-06-06', 'YYYY-MM-DD'), 2221);
INSERT INTO Employee (employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID) VALUES (1117, 'David', 'Davis', 'daviddavis@example.com', '789-012-3456', TO_DATE('1996-07-07', 'YYYY-MM-DD'), 2222);
INSERT INTO Employee (employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID) VALUES (1118, 'Sarah', 'Garcia', 'sarahgarcia@example.com', '890-123-4567', TO_DATE('1997-08-08', 'YYYY-MM-DD'), 2223);
INSERT INTO Employee (employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID) VALUES (1119, 'Michael', 'Martinez', 'michaelmartinez@example.com', '901-234-5678', TO_DATE('1998-09-09', 'YYYY-MM-DD'), 2224);
INSERT INTO Employee (employee_ID, employee_Fname, employee_Lname, employee_email, employee_phone, employee_dob, department_ID) VALUES (1110, 'Jessica', 'Lee', 'jessicalee@example.com', '012-345-6789', TO_DATE('1999-10-10', 'YYYY-MM-DD'), 2225);
 
SELECT * FROM Employee;

-- Department table
INSERT INTO Department(department_ID, department_name) VALUES (2221, 'Human Resources');
INSERT INTO Department(department_ID, department_name) VALUES (2222, 'Marketing');
INSERT INTO Department(department_ID, department_name) VALUES (2223, 'Sales');
INSERT INTO Department(department_ID, department_name) VALUES (2224, 'Finance');
INSERT INTO Department(department_ID, department_name) VALUES (2225, 'Information Technology');
INSERT INTO Department(department_ID, department_name) VALUES (2226, 'Operations');
INSERT INTO Department(department_ID, department_name) VALUES (2227, 'Customer Service');
INSERT INTO Department(department_ID, department_name) VALUES (2228, 'Research and Development');
INSERT INTO Department(department_ID, department_name) VALUES (2229, 'Legal');
INSERT INTO Department(department_ID, department_name) VALUES (2210, 'Public Relations');

SELECT * FROM Department;

-- Shift_Type table
INSERT INTO Shift_Type(shift_ID, shift_name, shift_start_time, shift_end_time) VALUES (3331, 'Morning', TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS')); --Current date + fixed time
INSERT INTO Shift_Type(shift_ID, shift_name, shift_start_time, shift_end_time) VALUES (3332, 'Afternoon', TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Shift_Type(shift_ID, shift_name, shift_start_time, shift_end_time) VALUES (3333, 'Night', TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 08:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Shift_Type(shift_ID, shift_name, shift_start_time, shift_end_time) VALUES (3334, 'Weekend Morning', TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Shift_Type(shift_ID, shift_name, shift_start_time, shift_end_time) VALUES (3335, 'Weekend Afternoon', TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Shift_Type(shift_ID, shift_name, shift_start_time, shift_end_time) VALUES (3336, 'Weekend Night', TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 08:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Shift_Type(shift_ID, shift_name, shift_start_time, shift_end_time) VALUES (3337, 'Holiday Morning', TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Shift_Type(shift_ID, shift_name, shift_start_time, shift_end_time) VALUES (3338, 'Holiday Afternoon', TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Shift_Type(shift_ID, shift_name, shift_start_time, shift_end_time) VALUES (3339, 'Holiday Night', TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 08:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Shift_Type(shift_ID, shift_name, shift_start_time, shift_end_time) VALUES (3310, 'On-Call', TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

SELECT * FROM Shift_Type;

-- Employee_Shift_Schedule table
INSERT INTO Employee_Shift_Schedule (employee_shift_ID, employee_ID, shift_ID, shift_schedule_date) VALUES (4441, 1112, 3331, TRUNC(SYSDATE)); --This will insert the current date at midnight
INSERT INTO Employee_Shift_Schedule (employee_shift_ID, employee_ID, shift_ID, shift_schedule_date) VALUES (4442, 1113, 3337, TRUNC(SYSDATE));
INSERT INTO Employee_Shift_Schedule (employee_shift_ID, employee_ID, shift_ID, shift_schedule_date) VALUES (4443, 1114, 3332, TRUNC(SYSDATE));
INSERT INTO Employee_Shift_Schedule (employee_shift_ID, employee_ID, shift_ID, shift_schedule_date) VALUES (4444, 1115, 3310, TRUNC(SYSDATE));
INSERT INTO Employee_Shift_Schedule (employee_shift_ID, employee_ID, shift_ID, shift_schedule_date) VALUES (4445, 1116, 3333, TRUNC(SYSDATE));
INSERT INTO Employee_Shift_Schedule (employee_shift_ID, employee_ID, shift_ID, shift_schedule_date) VALUES (4446, 1117, 3335, TRUNC(SYSDATE));
INSERT INTO Employee_Shift_Schedule (employee_shift_ID, employee_ID, shift_ID, shift_schedule_date) VALUES (4447, 1118, 3334, TRUNC(SYSDATE));
INSERT INTO Employee_Shift_Schedule (employee_shift_ID, employee_ID, shift_ID, shift_schedule_date) VALUES (4448, 1119, 3338, TRUNC(SYSDATE));
INSERT INTO Employee_Shift_Schedule (employee_shift_ID, employee_ID, shift_ID, shift_schedule_date) VALUES (4449, 1110, 3336, TRUNC(SYSDATE));
INSERT INTO Employee_Shift_Schedule (employee_shift_ID, employee_ID, shift_ID, shift_schedule_date) VALUES (4410, 1111, 3339, TRUNC(SYSDATE));

SELECT * FROM Employee_Shift_Schedule;

-- Log_Time table
INSERT INTO Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time) VALUES (6661, 1111, 3331, TRUNC(SYSDATE), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time) VALUES (6662, 1112, 3332, TRUNC(SYSDATE), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 08:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 16:30:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time) VALUES (6663, 1113, 3333, TRUNC(SYSDATE), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 07:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 16:15:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time) VALUES (6664, 1114, 3334, TRUNC(SYSDATE), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 07:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 15:30:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time) VALUES (6665, 1115, 3335, TRUNC(SYSDATE), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 18:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time) VALUES (6666, 1116, 3336, TRUNC(SYSDATE), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 16:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time) VALUES (6667, 1117, 3337, TRUNC(SYSDATE), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 17:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time) VALUES (6668, 1118, 3338, TRUNC(SYSDATE), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 08:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 16:50:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time) VALUES (6669, 1119, 3339, TRUNC(SYSDATE), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 00:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 06:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO Log_Time(log_ID, employee_ID, shift_ID, log_date, login_time, logout_time) VALUES (6610, 1110, 3310, TRUNC(SYSDATE), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 04:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(TO_CHAR(TRUNC(SYSTIMESTAMP), 'YYYY-MM-DD') || ' 12:00:00', 'YYYY-MM-DD HH24:MI:SS'));

SELECT * FROM Log_Time;



-- Autorization priviledges // Roles (Ch4 p52)





-- VIEWS

----VIEW 1: Daily employees status (View for the OWNER)
-- you are checking the interval not the arrival time
CREATE VIEW daily_status AS 
SELECT lt.employee_ID, e.employee_Fname AS First_Name, e.employee_Lname AS Last_Name, 
       CASE WHEN lt.logout_time - lt.login_time <= INTERVAL '8' HOUR THEN 'On time'   -- comparison with Shift_Type (shift_start_time and shift_end_time)
            ELSE 'Late'
       END AS status
FROM Log_Time lt
JOIN Employee e ON e.employee_ID = lt.employee_ID;

SELECT * FROM daily_status;
---DROP VIEW daily_employee_status;

-----------------------------------------------------

-----displays for each employee including dates

CREATE VIEW daily_status_date AS 
SELECT 
  lt.employee_ID, 
  e.employee_Fname AS First_Name, 
  e.employee_Lname AS Last_Name,
  TRUNC(lt.login_time) AS Login_Date, -- add the login date
  CASE 
    WHEN lt.logout_time - lt.login_time <= INTERVAL '8' HOUR THEN 'On time'  
    ELSE 'Late'
  END AS Status
FROM 
  Log_Time lt
JOIN 
  Employee e ON e.employee_ID = lt.employee_ID;


SELECT employee_ID, First_Name, Last_Name, Login_Date, Status
FROM daily_status_date
WHERE employee_id = 1111
ORDER BY employee_ID, Login_Date;

---DROP VIEW daily_status_date;
--------------------------------------

--------------------------------------






----just trying.... but their is data discrepancy between the tables.
CREATE VIEW daily_stats AS
SELECT 
    e.employee_Fname,
    e.employee_Lname,
    ess.employee_ID,
    st.shift_name,
    ess.shift_schedule_date,
    st.shift_start_time,
    lt.login_time,
    
    CASE
        WHEN EXTRACT(SECOND FROM (lt.login_time - st.shift_start_time)) > 0 THEN 'Late'
        WHEN EXTRACT(SECOND FROM (lt.login_time - st.shift_start_time)) < 0 THEN 'Early'
        ELSE 'On time'
    END AS "Punctuality Status"
FROM 
    Employee_Shift_Schedule ess
    JOIN Shift_Type st ON ess.shift_ID = st.shift_ID
    JOIN Log_Time lt ON ess.employee_ID = lt.employee_ID AND ess.shift_ID = lt.shift_ID AND ess.shift_schedule_date = lt.log_date
    JOIN Employee e ON ess.employee_ID = e.employee_ID;

SELECT * FROM daily_stats;

DROP VIEW daily_stats;





---- VIEW 2: Weekly employees total worked hours (View for the OWNER)

-- Do like a yearly view where this view is added every 7 days and the weekly view restarts every 7 days
CREATE VIEW weekly_hours AS 
SELECT 
  lt.employee_ID, e.employee_Fname AS First_Name, e.employee_Lname AS Last_Name,
  TRUNC(lt.log_date, 'IW') as week_start_date,  -- how do we know it is the whole week (7 days)
  SUM(EXTRACT(HOUR FROM (lt.total_worked_time))) as weekly_worked_hours --(lt.logout_time - lt.login_time) = total_worked_time (already created in Log_Time)
FROM 
  Log_Time lt
  JOIN Employee e ON e.employee_ID = lt.employee_ID
GROUP BY 
  lt.employee_ID,
  e.employee_Fname,
  e.employee_Lname, 
  TRUNC(lt.log_date, 'IW');
 
SELECT * FROM weekly_hours;
--DROP VIEW weekly_view;


---------------------------------------
------totals hours displays for weekly

CREATE VIEW weekly_hours_yearly AS 
SELECT 
  lt.employee_ID, 
  e.employee_Fname AS First_Name, 
  e.employee_Lname AS Last_Name,
  TRUNC(lt.log_date, 'IW') as week_start_date,
  SUM(EXTRACT(HOUR FROM (lt.total_worked_time))) as weekly_worked_hours,
  COUNT(*) AS total_weekly_logs, 
  SUM(SUM(EXTRACT(HOUR FROM (lt.total_worked_time)))) OVER (PARTITION BY lt.employee_ID ORDER BY TRUNC(lt.log_date, 'IW')) as yearly_worked_hours -- running total of weekly worked hours
FROM 
  Log_Time lt
  JOIN Employee e ON e.employee_ID = lt.employee_ID
GROUP BY 
  lt.employee_ID,
  e.employee_Fname,
  e.employee_Lname, 
  TRUNC(lt.log_date, 'IW');

SELECT * FROM weekly_hours_yearly;

--DROP VIEW weekly_hours_yearly;

---------------------------------------------------


---- VIEW 3: Weekly SPECIFIC employee total worked hours (View for the EMPLOYEE)

---- Same as weekly view but for a specific employee (this is a view for only employee) HAS TO BE IMPLEMENTED IN JDBC SO EMPLOYEE ID CAN BE INTRODCED
---NOTE: when doing the SQL, add at the end (maybe) a drop command so the view is not already created
CREATE VIEW weekly_employee AS 
SELECT 
  lt.employee_ID, e.employee_Fname AS First_Name, e.employee_Lname AS Last_Name,
  TRUNC(lt.log_date, 'IW') as week_start_date,  -- how do we know it is the whole week (7 days)
  SUM(EXTRACT(HOUR FROM (lt.total_worked_time))) as weekly_worked_hours --(lt.logout_time - lt.login_time) = total_worked_time (already created in Log_Time)
FROM 
  Log_Time lt
  JOIN Employee e ON e.employee_ID = lt.employee_ID
WHERE 
  e.employee_ID = 1111
GROUP BY 
  lt.employee_ID,
  e.employee_Fname,
  e.employee_Lname, 
  TRUNC(lt.log_date, 'IW');

SELECT * FROM weekly_employee;
--DROP VIEW employee_weekly_view;


-----------------------------------------------


----view 4 status for specific employee for whole week

CREATE VIEW weekly_status_for_employee AS 
SELECT 
  e.employee_ID, 
  e.employee_Fname AS First_Name, 
  e.employee_Lname AS Last_Name, 
  TRUNC(lt.login_time, 'IW') AS Week_Start_Date,
  TRUNC(lt.login_time) AS log_date,
  CASE 
    WHEN lt.logout_time - lt.login_time <= INTERVAL '8' HOUR THEN 'On time'  
    ELSE 'Late'
  END AS Status
FROM 
  Log_Time lt
JOIN 
  Employee e ON e.employee_ID = lt.employee_ID
WHERE
  lt.login_time >= TRUNC(SYSDATE, 'IW') - 21  -- replace 21 with the desired number of weeks to query
  AND e.employee_ID = 1115  -- replace <employee_id> with the ID of the specific employee you want to query
ORDER BY 
  log_date;
  
SELECT * FROM weekly_status_for_employee;
--DROP VIEW weekly_status_for_employee;























----VIEW 4: 
--- THIS WOULD HAVE TO BE IMPLEMENTED IN JDBC TO INTRODUCE SPECIFIC EMPLOYEE ID
--- Mix between weekly view and daily employee status for a weekly employee status that owner and employee can see (view that employee will be able to see)
---weekly status for each employee





/*

--- total hours all employees have worked (View for the owner) (MAYBE WE DO NOT NEED THIS VIEW)

CREATE VIEW All_employee_total_hours AS
SELECT SUM(weekly_worked_hours) AS total_hours
FROM weekly_view;

SELECT * FROM All_employee_total_hours;
-- DROP VIEW All_employee_total_hours;




---- consider that every employee can only work for 40hrs max
---- view for showing all weekly pay for each employee
---- ca use it for both employee and owner in jdbc

-- but depending on employee and position the pay will be different (I WOULD NOT IMPLEMENT THIS VIEW)
----- THIS COULD BE SPECIFIED IN JDBC.
WWEKLY PAY FROM 
CREATE VIEW weekly_pay_view AS
SELECT 
  employee_Lname, 
  week_start_date,
  total_hours_worked,
  total_hours_worked * 25 AS total_pay
FROM 
  weekly_view;

SELECT * FROM weekly_pay_view;

*/










