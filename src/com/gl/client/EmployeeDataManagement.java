package com.gl.client;

import com.gl.model.Employee;
import com.gl.service.EmployeeService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class EmployeeDataManagement {

    Connection con;
    Statement stmt;
    ResultSet rs;
    String reply = "yes";

    Scanner sc = new Scanner(System.in);
    int choice;
    EmployeeService eservice;
    public EmployeeDataManagement(){
        eservice = new EmployeeService();
    }

    public void showMenu(){
        while(reply.equalsIgnoreCase("yes")){
            System.out.println("-----------------------Main Menu-----------------------------");
/*            1.View All Employees
            2.Search Employee By ID
            3.Insert Employee Record
            4.Delete Employee ID
            5.Update Employee
            6.Exit
            Please enter Your Option...*/
            System.out.println("1.View All Employees");
            System.out.println("2.Search Employee By ID");
            System.out.println("3.Insert Employee Record");
            System.out.println("4.Delete Employee ID");
            System.out.println("5.Update Employee");
            System.out.println("6.Exit");
            System.out.println("Enter your choice:  ");
            choice = sc.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Viewing Employees");
                    ArrayList<Employee> elist = eservice.getEmployee();
                    Iterator<Employee> i = elist.iterator();
                    while(i.hasNext()){
                        Employee e = i.next();
                        System.out.println(e);
                    }
                    break;
                case 2:
                    System.out.println("Search Employee By ID");
                    System.out.println("All employee Ids are listed below: ");
                    for(String s:eservice.AllEmpIds()){
                        System.out.println(s);
                    }
                    System.out.println("Enter employee ID whose details you want to fetch");
                    System.out.println();

                    try{
                        String id = sc.next();
                        Employee e = eservice.SerachEmployeeById(id);
                        System.out.println(e);

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    finally {
                        break;

                    }



                case 3:
                    System.out.println("Insert Employee Records");
                    System.out.println("Generating new employee id.....");
                    String empid = eservice.generateEmployeeID();
                    System.out.println("Newly genereaed employee id is: "+empid);
                    System.out.println("Enter name of employee:");
                    String empname = sc.next();
                    System.out.println("Enter the Address of The Employee You wish to Insert");
                    String address =  sc.next();
                    System.out.println("Enter the Phone of The Employee You wish to Insert");
                    String phno = sc.next();
                    System.out.println("Enter the Salary of The Employee You wish to Insert");
                    int salaray  = sc.nextInt();
                    System.out.println("Enter the Tax Eligibility of The Employee You wish to Insert");
                    int tax = sc.nextInt();
                    Employee employee = new Employee(empid,empname,address,phno,salaray,tax);
                    if(eservice.InsertData(employee)){
                        System.out.println("Succesfully inserted");
                    }
                    else{
                        System.out.println("Something went wrong!!");
                    }
                    break;
                case 4:
                    System.out.println("Deleting Employee Id");
                    System.out.println("Select from given employee id to delete its record");
                    for(String s:eservice.AllEmpIds()){
                        System.out.println(s);
                    }
                    System.out.println("Deleting....");
                    String eId = sc.next();
                    if(eservice.DeleteCell(eId)==1){
                        System.out.println("Deleted Succesfully");
                    }else{
                        System.out.println("Something went wrong!!");
                    }
                    break;
                case 5:
                    System.out.println("Updating Employee");
                    System.out.println("All existing employee records are shown below: ");
                    ArrayList<Employee> e = eservice.getEmployee();
                    Iterator<Employee> it = e.iterator();
                    while(it.hasNext()){
                        Employee em = it.next();
                        System.out.println(em);
                    }
                    System.out.println("Select the employee id to update the details");
                    String eid = sc.next();

                    System.out.println("Update the name");
                    String name = sc.next();

                    System.out.println("Enter the New Address of The Employee You wish to Update");
                    String ads = sc.next();

                    System.out.println("Enter the New Phone of The Employee You wish to Update");
                    String ph = sc.next();

                    System.out.println("Enter the New Salary of The Employee You wish to Update");
                    int sal = sc.nextInt();

                    System.out.println("Enter the New Tax Eligibility of The Employee You wish to Update");
                    float taxup = sc.nextFloat();

                    Employee employee1 = new Employee(eid,name,ads,ph,sal,taxup);
                    if(eservice.Update(employee1)){
                        System.out.println("Updated successully");
                    }else{
                        System.out.println("Something went wrong!! Check again");
                    }

                    break;
                case 6:
                    System.out.println("Exiting");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid");
                    break;

            }
            System.out.println("Do you wish to continue? yes or no? ");
            reply = sc.next();
        }
    }

}
