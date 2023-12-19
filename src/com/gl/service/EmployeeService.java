package com.gl.service;

import com.gl.dao.EmployeeDAO;
import com.gl.model.Employee;

import java.security.PublicKey;
import java.util.ArrayList;

public class EmployeeService {
    EmployeeDAO edao;
    public EmployeeService(){
        edao = new EmployeeDAO();
    }
    public ArrayList<Employee> getEmployee(){
        return edao.getAllEmployee();
    }
    public Employee SerachEmployeeById(String id){
        return edao.getEmployeeById(id);
    }
    public ArrayList<String> AllEmpIds(){
        return edao.AllEmployeeIds();
    }
    public String generateEmployeeID(){
        return edao.getMaxEmployee();
    }
    public boolean InsertData(Employee employee){
        return edao.InsertIntoTable(employee);
    }
    public int DeleteCell(String empid){
        return edao.DeleteData(empid);
    }
    public boolean Update(Employee employee){
        return edao.UpdateData(employee);
    }
}
