package hospitalmanagementsystem;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbOperation {

    String url = "jdbc:mysql://localhost:3306/hospital_management_system";
    String localHostUsername = "supun";
    String localHostPassword = "7876351a";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    boolean addEmployee(employeeDeatails ed) {//add Employee employee table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//make database connection
            String query = "INSERT INTO `employee`(`First_name`, `Last_name`, `Name_with_initials`, `state`, `Gender`, `Age`, `Address`, `E-mail`, `Mobile-no`, `Fixed-no`, `Nic`, `Password`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, ed.getFirst_name());
            pst.setString(2, ed.getLast_name());
            pst.setString(3, ed.getName_with_initials());
            pst.setString(4, ed.getState());
            pst.setString(5, ed.getGender());
            pst.setInt(6, ed.getAge());
            pst.setString(7, ed.getAddress());
            pst.setString(8, ed.getEmail());
            pst.setInt(9, ed.getMobile_no());
            pst.setInt(10, ed.getFixed_no());
            pst.setString(11, ed.getNic());
            pst.setString(12, ed.getPassword());

            pst.executeUpdate();
            return true;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return false;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    boolean add_patient(patientDeatails pd) {//add patient patient table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//make database connection
            String query = "INSERT INTO `patient` ( `full_name`, `Address`, `nic`, `sex`) VALUES (?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            //add data to database 
            pst.setString(1, pd.getFullname());
            pst.setString(2, pd.getAddress());
            pst.setString(3, pd.getNic());
            pst.setString(4, pd.getGender());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return false;
        } finally {//colse database connection 
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    boolean add_phone_number_table(phoneNo ph) {//add data phone_numbers table in database
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//make database connection
            String query = "INSERT INTO `phone_numbers`(`Admission id`, `Mobile no`, `fix no`, `Other`) VALUES (?,?,?,?)";//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            // add data 
            pst.setInt(1, ph.getAdmissionId());
            pst.setString(2, ph.getMobileNo());
            pst.setString(3, ph.getFixNo());
            pst.setString(4, ph.getOther());
            pst.executeUpdate();//update to database
            return true;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return false;
        } finally {//close database connection  
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    boolean add_external_physician(External_physician ep) {//add data external_physician table in the database
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection
            String query = "INSERT INTO `external_physician`(`admission_id`, `nic`, `first_name`, `last_name`, `mobile_no`) VALUES (?,?,?,?,?)";//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            //add data
            pst.setInt(1, ep.getAdmission_id());
            pst.setString(2, ep.getNic());
            pst.setString(3, ep.getFirst_name());
            pst.setString(4, ep.getLast_name());
            pst.setString(5, ep.getMobile_no());
            pst.executeUpdate();//update data to database
            return true;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return false;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    int get_admission_id_when_given_nic(String nic) {// get admission id when given nic frompatient table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection 
            String query = "SELECT `Admission_id` FROM `patient` WHERE nic=" + "'" + nic + "'";//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();//run the query
            rs.next();
            return Integer.parseInt(rs.getString(1));//return admission id 
        } catch (Exception e) {/////if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return -1;//an error occur 
        } finally {//close database connection 
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }

    }

    int checkEmployeeId(String employee_id) {
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//creat database connection 
            String query = "SELECT `employee_id` FROM employee";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (employee_id.equals(rs.getString(1))) {
                    return (0); //id is  exists
                }
            }
            return 1;//username does not exists
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return 2;//an error occur 
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }

    }

    int checkPasswordForParticularUsername(String password, String employee_id) {//check particular Username has correct mark
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//creat database connection 
            String query = "SELECT `Password` FROM `employee` WHERE employee_id=" + employee_id;
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            rs.next();

            if (password.equals(rs.getString(1))) {
                return 0;//pasword is match
            }

            return 1;//password does not match
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.println(e);
            return 2;//an error occur 
        } finally {//close database connection 
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }

    }

    int check_Whether_Given_Employee_ID_is_equal_given_state(String employee_id, String state) {//check Whether Given Employee ID is equal given state
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection
            String query = "SELECT `state` FROM `employee` WHERE employee_id=" + employee_id;//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();//run query
            rs.next();

            if (state.equals(rs.getString(1))) {
                return 0;//if Given Employee ID is equal given state
            }

            return 1;// if notGiven Employee ID is equal given state
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return 2;
        } finally {//closse database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    String[] get_patient_personol_details(String employee_id) {//get patient personol details
        String s[] = null;
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create connection
            String query = "SELECT `admission_id`,`age`, `diseases`, `physical_disabilities`, `allergic_medicines`, `previous_operation_history` FROM `personal_details` WHERE admission_id=" + employee_id;//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();//run query
            s = new String[6];//put data in to this array
            rs.next();

            s[0] = rs.getString(1);
            s[1] = rs.getString(2);
            s[2] = rs.getString(3);
            s[3] = rs.getString(4);
            s[4] = rs.getString(5);
            s[5] = rs.getString(6);
            return s;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  

            System.out.print(e);
            return s;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    int update_patient_personal_deatails(int admission_id, int age, String diseases, String physical_disabilities, String allergic_medicines, String previous_operation_history) {//updatate personal_details table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection 

            String query = "UPDATE `personal_details` SET `age`=?,`diseases`=?,`physical_disabilities`=?,`allergic_medicines`=?,`previous_operation_history`=? WHERE admission_id=" + admission_id;

            pst = (PreparedStatement) con.prepareStatement(query);
            //add data
            pst.setInt(1, age);
            pst.setString(2, diseases);
            pst.setString(3, physical_disabilities);
            pst.setString(4, allergic_medicines);
            pst.setString(5, previous_operation_history);
            pst.executeUpdate();

            return 0;

        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return 1;

        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    int add_personal_deatails(int admission_id, int age, String diseases, String physical_disabilities, String allergic_medicines, String previous_operation_history) {// add personal detaila to personal details table

        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection 

            String query = "INSERT INTO `personal_details`(`admission_id`, `age`, `diseases`, `physical_disabilities`, `allergic_medicines`, `previous_operation_history`) VALUES (?,?,?,?,?,?)";////sql query

            pst = (PreparedStatement) con.prepareStatement(query);
            //add data
            pst.setInt(1, admission_id);
            pst.setInt(2, age);
            pst.setString(3, diseases);
            pst.setString(4, physical_disabilities);
            pst.setString(5, allergic_medicines);
            pst.setString(6, previous_operation_history);
            pst.executeUpdate();

            return 0;

        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return 1;

        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    int checkPatientId_given_table(String Admission_id, String tableName) {// check patient id given table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection 
            String query = "SELECT `Admission_id` FROM " + tableName;
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {//using while loop check admission id
                if (Admission_id.equals(rs.getString(1))) {
                    return (0); //id is  exists
                }
            }
            return 1;//username does not exists
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return 2;//an error occur 
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }

    }

    int count_number_Rows_table(String table_name) {//count number of rows given table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection 
            String query = "SELECT COUNT(*) FROM   " + table_name;//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();//run query
            rs.next();

            return rs.getInt(1);//return count

        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return -1;

        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    int add_data_treatment_table(String table[][]) {//add datatreatment table
        if (count_number_Rows_table("treatment") == 0) {//if no of rows=0 in treatment table
            try {
                con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection 

                for (int i = 0; i < table.length; i++) {
                    String query = "INSERT INTO `treatment`( `treatment_id`, `medicine`, `period`, `How Much`) VALUES (?,?,?,?)";//sql query

                    pst = (PreparedStatement) con.prepareStatement(query);
                    //add data
                    pst.setInt(1, 1);
                    pst.setString(2, table[i][0]);
                    pst.setString(3, table[i][1]);
                    pst.setString(4, table[i][2]);

                    pst.executeUpdate();//update query to the database
                }
                return 0;

            } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
                System.out.print(e);
                return 1;

            } finally {//close database connection
                try {
                    if (pst != null) {
                        pst.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (Exception e) {
                }
            }
        } else {//if no of rows is not equal to 0 in treatment table
            try {
                con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection 
                //to get 
                String query1 = "SELECT `medicine_list _id` FROM `examined`";//select medicine_list _id from examined table 

                pst = (PreparedStatement) con.prepareStatement(query1);

                rs = pst.executeQuery();//run query

                rs.last();//navigate  last row

                for (int i = 0; i < table.length; i++) {
                    System.out.println("f");
                    String query = "INSERT INTO `treatment`( `treatment_id`, `medicine`, `period`, `How Much`) VALUES (?,?,?,?)";//sql query

                    pst = (PreparedStatement) con.prepareStatement(query);
                    // System.out.println("h" + "    " + rs.getInt(1));
                    //add data to the query
                    pst.setInt(1, rs.getInt(1) + 1);
                    pst.setString(2, table[i][0]);
                    pst.setString(3, table[i][1]);
                    pst.setString(4, table[i][2]);
                    //System.out.println("i");
                    pst.executeUpdate();//run query
                    //System.out.println("j");
                }
                return 0;

            } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
                System.out.println("k");
                System.out.print(e);
                return 1;

            } finally {//close database connection
                try {
                    if (pst != null) {
                        pst.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (Exception e) {
                }
            }

        }

    }

    void add_data_examined_table(int admission_id, int consultant_id, String Date, String Diseases) {//add data to the examined table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection 

            String query1 = "SELECT `treatment_id` FROM treatment";//get treatement id 
            pst = (PreparedStatement) con.prepareStatement(query1);
            rs = pst.executeQuery();//run query
            rs.last();
            int medicine_list_id = rs.getInt(1);//list id give veriable 
            //System.out.println(medicine_list_id + "AAA");
            String query = "INSERT INTO `examined`(`admission_id`, `Consultant _id`, `Diseases`, `medicine_list _id`, `Date`) VALUES (?,?,?,?,?)";//sql query

            pst = (PreparedStatement) con.prepareStatement(query);
            //add data to the query
            pst.setInt(1, admission_id);
            pst.setInt(2, consultant_id);
            pst.setString(3, Diseases);
            pst.setInt(4, medicine_list_id);
            pst.setString(5, Date);
            pst.executeUpdate();//run the query

            //   return 0;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            // return 1;

        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    int update_patient_Table(String column_name, int value, int Admission_id) {//update patient table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection

            String query = "UPDATE `patient` SET `" + column_name + "`=? WHERE Admission_id=" + Admission_id;//sql query

            pst = (PreparedStatement) con.prepareStatement(query);

            pst.setInt(1, value);

            pst.executeUpdate();//run query

            return 0;

        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return 1;

        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    boolean add_test(String test, String result, int Admission_id) {//addd data to test table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection

            String query = "INSERT INTO `test`( `Admission_id`, `test_name`, `test_result`) VALUES (?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            //add date to query
            pst.setInt(1, Admission_id);
            pst.setString(2, test);
            pst.setString(3, result);
            pst.executeUpdate();//run query
            return true;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return false;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    String[][] get_data_from_test_table(int Admission_id) {// get data test table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection
            String query = "SELECT `test_name`, `test_result` FROM `test` WHERE Admission_id=" + Admission_id;//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            int length = 0;
            rs = pst.executeQuery();//run query
            while (rs.next()) {//get number of rows 
                length++;
            }
            rs.beforeFirst();
            String s[][] = new String[length][2];//store data string array
            int i = 0;
            while (rs.next()) {//store data
                if ((rs.getString(1) != "" && rs.getString(2) != "") || (rs.getString(1) != "" && rs.getString(2) == "")) {
                    s[i][0] = rs.getString(1);
                    s[i][1] = rs.getString(2);
                }
                i++;
            }
            return s;//username does not exists
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return null;//an error occur 
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }
    }

    boolean add_data_attendance(Addattendence at) {//add data to attendance table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);
            String query = "INSERT INTO `attendance`(`employee_id`, `date`, `start_time`, `end_time`) VALUES (?,?,?,?)";//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            //add data to sql query
            pst.setInt(1, at.getEmployeeId());
            pst.setString(2, at.getDate());
            pst.setString(3, at.getStartTime());
            pst.setString(4, at.getEndTime());
            pst.executeUpdate();//run query
            return true;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return false;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    boolean update_password_employee(String employeeId, String newPassword) {
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);
            String query = "UPDATE `employee` SET `Password`='" + newPassword + "' WHERE employee_id=" + employeeId;
            pst = (PreparedStatement) con.prepareStatement(query);
            // pst.setString(1, newPassword);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return false;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    employeeDeatails view_employee_deatails(String id) {//get data from employee table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection
            String query = "SELECT * FROM `employee` WHERE employee_id=" + id;//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            rs.next();
            employeeDeatails empDeat = new employeeDeatails();//create employeeDeatails object
            //add data to the object
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            String name_initials = rs.getString(4);
            String state = rs.getString(5);
            String gender = rs.getString(6);
            int age = rs.getInt(7);
            String addres = rs.getString(8);
            String email = rs.getString(9);
            int mobileNo = rs.getInt(10);
            int fixedNo = rs.getInt(11);
            String nic = rs.getString(12);
            String password = rs.getString(13);
            empDeat.setFirst_name(firstName);
            empDeat.setLast_name(lastName);
            empDeat.setName_with_initials(name_initials);
            empDeat.setState(state);
            empDeat.setGender(gender);
            empDeat.setAge(age);
            empDeat.setAddress(addres);
            empDeat.setEmail(email);
            empDeat.setMobile_no(mobileNo);
            empDeat.setFixed_no(fixedNo);
            empDeat.setNic(nic);
            empDeat.setPassword(password);

            return empDeat;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return null;//an error occur 
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }

    }

    boolean update_Employee_details(employeeDeatails em, String empid) {//update  employee table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);
            String query = "UPDATE `employee` SET `First_name`=?,`Last_name`=?,`Name_with_initials`=?,`state`=?,`Gender`=?,`Age`=?,`Address`=?,`E-mail`=?,`Mobile-no`=?,`Fixed-no`=?,`Nic`=?,`Password`=? WHERE employee_id=" + empid;

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, em.getFirst_name());
            pst.setString(2, em.getLast_name());
            pst.setString(3, em.getName_with_initials());
            pst.setString(4, em.getState());
            pst.setString(5, em.getGender());
            pst.setInt(6, em.getAge());
            pst.setString(7, em.getAddress());
            pst.setString(8, em.getEmail());
            pst.setInt(9, em.getMobile_no());
            pst.setInt(10, em.getFixed_no());
            pst.setString(11, em.getNic());
            pst.setString(12, em.getPassword());

            pst.executeUpdate();//run query

            return true;

        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return false;

        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    String[][] get_attendance(String emp_id, String year1, String year2, String month1, String month2) {//get attendence from attendance table

        String s[][] = null;
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection
            String query = "select * from attendance where Date >= '" + year1 + "/" + month1 + "/01' and Date < '" + year2 + "/" + month2 + "/01'and employee_id=" + emp_id;//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();//run query and get data to  variable 
            int count = 0;
            while (rs.next()) {//count no of rows
                count++;
            }
            rs.beforeFirst();
            s = new String[count][4];//get data to string array
            int i = 0;
            while (rs.next()) {
                //put data in array
                s[i][0] = rs.getString(1);
                System.out.println(s[i][0]);
                s[i][1] = rs.getString(2);
                System.out.println(s[i][1]);
                s[i][2] = rs.getString(3);
                System.out.println(s[i][2]);
                s[i][3] = rs.getString(4);
                i++;
            }
            return s;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  

            System.out.print(e);
            return s;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    boolean update_salary(salaryclass slrclss) {//update salary table 
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection
            String query = "INSERT INTO `salary`(`employee_id`, `year`, `month`, `payment_one_ot_hour`, `ot_hours`, `payment_ot`, `basic`, `full_salary`) VALUES (?,?,?,?,?,?,?,?)";//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            //add data to the query
            pst.setInt(1, slrclss.getEmployee_id());
            pst.setInt(2, slrclss.getyear());
            pst.setString(3, slrclss.getMonth());
            pst.setInt(4, slrclss.getPayment_one_ot_hour());
            pst.setInt(5, slrclss.getOt_hours());
            pst.setInt(6, slrclss.getPayment_ot());
            pst.setInt(7, slrclss.getBasic());
            pst.setInt(8, slrclss.getFull_salary());

            pst.executeUpdate();//run the query
            return true;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return false;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    int delete_Employee(String Emp_id) {//delete selected employee
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection 
            String query = "DELETE FROM `employee` WHERE employee_id=" + Emp_id;//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();//run the query

            return 0;
        } catch (Exception e) {/////if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return 1;//an error occur 
        } finally {//close database connection 
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    String[][] select_treatment_make_salary(String EmpID) {
        String s[][] = null;
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection
            String query = "SELECT * FROM `treatment` WHERE treatment_id IN(SELECT DISTINCT `medicine_list _id` FROM `examined` WHERE admission_id=" + EmpID + ")";//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();//run query and get data to  variable 
            int count = 0;
            while (rs.next()) {//count no of rows
                count++;
            }
            rs.beforeFirst();
            s = new String[count][4];//get data to string array
            int i = 0;
            while (rs.next()) {
                //put data in array
                s[i][0] = rs.getString(1);
                System.out.println(s[i][0]);
                s[i][1] = rs.getString(2);
                System.out.println(s[i][1]);
                s[i][2] = rs.getString(3);
                System.out.println(s[i][2]);
                s[i][3] = rs.getString(4);
                i++;
            }
            return s;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  

            System.out.print(e);
            return s;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }
    }

    String[][] select_Testing_data_from_patient(String pID) {
        String s[][] = null;
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection
            String query = "SELECT test_name,test_result FROM `test` WHERE Admission_id=" + pID;//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();//run query and get data to  variable 
            int count = 0;
            while (rs.next()) {//count no of rows
                count++;
            }
            rs.beforeFirst();

            s = new String[count][2];//get data to string array
            int i = 0;

            while (rs.next()) {

                //put data in array
                s[i][0] = rs.getString(1);
                System.out.println(s[i][0]);
                s[i][1] = rs.getString(2);
                System.out.println(s[i][1]);

                i++;
            }
            return s;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  

            System.out.print(e);
            return s;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }
    }

    boolean insert_data_patientbill_tabe(patientBill pb) {//add data patientbill table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//make database connection
            String query = "INSERT INTO `patientbill`(`admission_id`,`testing`, `treatment`, `other`, `total`) VALUES (?,?,?,?,?)";//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, pb.getAdmition_id());
            pst.setInt(2, pb.getTest());
            pst.setInt(3, pb.getTreatment());
            pst.setInt(4, pb.getOther());
            pst.setInt(5, pb.getTotal());

            pst.executeUpdate();
            return true;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return false;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    int get_registation_id(String nic) {//get registation_id of  employee from employee table
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection
            String query = "SELECT `employee_id` FROM `employee` WHERE Nic='" + nic + "'";//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();//run query and get data to  variable 
            int count = 0;

            rs.beforeFirst();

            rs.next();
            
            return (rs.getInt(1));

        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  

            System.out.print(e);
            return -1;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }
    }

    boolean insert_Date_Consultant_table(int Consultant_id, String Specialied, int ward_id) {//add data to consultant tabel
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//make database connection
            String query = "INSERT INTO `consultant`(`Consultant_id`, `Specialied`, `ward_id`) VALUES (?,?,?)";//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, Consultant_id);
            pst.setString(2, Specialied);
            pst.setInt(3, ward_id);

            pst.executeUpdate();
            return true;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return false;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    boolean insert_data_nurse_table(int epmID,int wardID){//add data to nurse table
    try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//make database connection
            String query = "INSERT INTO `nurse`(`employee_id`, `ward_id`) VALUES (?,?)";//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, epmID);
            pst.setInt(2, wardID);
            

            pst.executeUpdate();
            return true;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return false;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }}
    int delete_patient(String pID){
    try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create database connection 
            String query = "DELETE FROM `patient` WHERE Admission_id=" + pID;//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();//run the query

            return 0;
        } catch (Exception e) {/////if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return 1;//an error occur 
        } finally {//close database connection 
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    boolean add_data_dischargepatient_table(String nic,String fullname,String address){//add data dischargepatient table 
    try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//make database connection
            String query = "INSERT INTO `dischargepatient`(`nic`, `fullname`, `address`) VALUES (?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, nic);
            pst.setString(2, fullname);
            pst.setString(3,address);
            

            pst.executeUpdate();
            return true;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  
            System.out.print(e);
            return false;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    String[] get_patient_details(String p_id) {//get patient personol details
        String s[] = null;
        try {
            con = (Connection) DriverManager.getConnection(url, localHostUsername, localHostPassword);//create connection
            String query = "SELECT `full_name`, `Address`, `nic` FROM `patient` WHERE Admission_id=" + p_id;//sql query
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();//run query
            s = new String[3];//put data in to this array
            rs.next();

            s[0] = rs.getString(1);
            s[1] = rs.getString(2);
            s[2] = rs.getString(3);
            
            return s;
        } catch (Exception e) {//if occur any problem while runing upper part of th code print the error massage  

            System.out.print(e);
            return s;
        } finally {//close database connection
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

}
