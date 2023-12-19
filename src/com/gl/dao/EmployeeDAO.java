package com.gl.dao;

/*public class EmployeeDAO {
    Connection con;
    ResultSet rs;
    Statement stmt;
    PreparedStatement pstmt;
    Employee employee;
    ArrayList <Employee> employees;
    MyConnection mycon;
    public ArrayList <Employee> getAllEmployees()
    {
//IMPLEMENT HERE
    }
    public Employee getEmployeeById(String empId)
    {
    }
    public boolean deleteEmployeeById(String empId)
    {
    }
    public boolean updateEmployee(Employee empU)
    {
    }
    public boolean insertEmployee(Employee empI)
    {
    }
    public ArrayList <String> getEmployeeIds()
    {
    }
}*/

import com.gl.connections.MyConnection;
import com.gl.model.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {
    Connection con;
    ResultSet rs;
    Statement stmt;
    PreparedStatement pstmt;
     Employee employee;
    ArrayList<Employee> employees;
    MyConnection myCon;

    public EmployeeDAO(){
        myCon = new MyConnection();
    }

    public ArrayList<Employee> getAllEmployee(){
        con = myCon.getMyConnections();
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {
            stmt = con.createStatement();
            String q = "Select * from Employee";
            rs = stmt.executeQuery(q);
            String employeeId;
            String employeeName;
            String employeeAddress;
            String employeePhone;
            int employeeSalary;
            float employeeTax;

            while(rs.next()){
                employeeId = rs.getString(1);
                employeeName  =rs.getString(2);
                employeeAddress  = rs.getString(3);
                employeePhone  = rs.getString(4);
                employeeSalary = rs.getInt(5);
                employeeTax = rs.getFloat(6);
                employee = new Employee(employeeId,employeeName,employeeAddress,employeePhone,employeeSalary,employeeTax);
                employees.add(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;

    }
    public ArrayList<String> AllEmployeeIds(){
        con = myCon.getMyConnections();
        ArrayList<String> list  = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select employeeId from Employee;");
            while(rs.next()){
                String eid = rs.getString(1);
                list.add(eid);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

    }

    public Employee getEmployeeById(String empId)
    {

        try {
            con = myCon.getMyConnections();
            pstmt  = con.prepareStatement("Select * from Employee where employeeId = ?;");
            pstmt.setString(1,empId);
            rs = pstmt.executeQuery();
            String employeeId;
            String employeeName;
            String employeeAddress;
            String employeePhone;
            int employeeSalary;
            float employeeTax;
            rs.next();
            employeeId = rs.getString(1);
            employeeName  =rs.getString(2);
            employeeAddress  = rs.getString(3);
            employeePhone  = rs.getString(4);
            employeeSalary = rs.getInt(5);
            employeeTax = rs.getFloat(6);
            employee = new Employee(employeeId,employeeName,employeeAddress,employeePhone,employeeSalary,employeeTax);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }
//    Step 1: fetch max of employeeId() if suppose it is E014
//    Step2: Split and make it into 2 String “E” & “014”
//    Step3: Convert second part to numeric “014” to 14

public String getMaxEmployee() {
    con = myCon.getMyConnections();
    String maxEmployeeId = "";
    String res="";
    try {
        stmt = con.createStatement();
        String q = "SELECT MAX(employeeId) FROM Employee";
        rs = stmt.executeQuery(q);
        rs.next();
        maxEmployeeId = rs.getString(1);
        String first_part = maxEmployeeId.substring(0,1);
        String secod_part = maxEmployeeId.substring(1);
        int second_value = Integer.parseInt(secod_part)+1;
        String neumeric_part  = String.format("%03d",second_value);
        res = first_part+neumeric_part;

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return res;
}
    public boolean InsertIntoTable(Employee employee){
        con = myCon.getMyConnections();
        boolean flag = false;
        try{
            pstmt = con.prepareStatement("Insert into Employee values(?,?,?,?,?,?);");
            pstmt.setString(1,employee.getEmployeeId());
            pstmt.setString(2,employee.getEmployeeName());
            pstmt.setString(3,employee.getEmployeeAddress());
            pstmt.setString(4,employee.getEmployeePhone());
            pstmt.setInt(5,employee.getEmployeeSalary());
            pstmt.setFloat(6,employee.getEmployeeTax());
            pstmt.execute();
            flag = true;
        } catch (SQLException e) {
            flag = false;
            throw new RuntimeException(e);

        }
        return flag;

    }
    public int DeleteData(String id){
        con = myCon.getMyConnections();
        int n = 0;
            try {
                pstmt = con.prepareStatement("delete  from Employee where employeeId = ?;");
                pstmt.setString(1,id);
                n = pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return n;

    }
    public boolean UpdateData(Employee employee){
        con = myCon.getMyConnections();
        boolean flag = false;
        try {
            pstmt = con.prepareStatement("update Employee set employeeName =?, employeeAddress=?,employeePhone=?,employeeSalary=?,employeeTax = ? where employeeId = ?;");
            String empid = employee.getEmployeeId();
            String empname = employee.getEmployeeName();
            String empaddress = employee.getEmployeeAddress();
            String empphn = employee.getEmployeeAddress();
            int empSal = employee.getEmployeeSalary();
            float employeeTax = employee.getEmployeeSalary();
            pstmt.setString(1,empname);
            pstmt.setString(2,empaddress);
            pstmt.setString(3,empphn);
            pstmt.setInt(4,empSal);
            pstmt.setFloat(5,employeeTax);
            pstmt.setString(6,empid);

            pstmt.execute();
            flag = true;

        } catch (SQLException e) {
            flag = false;
            throw new RuntimeException(e);
        }
        return flag;
    }



//    public static void main(String[] args) {
//        EmployeeDAO daoObj = new EmployeeDAO();
////        System.out.println(daoObj.getAllEmployee());
//        System.out.println(daoObj.getMaxEmployee());
//        System.out.println(daoObj.DeleteData("E002"));

    }


