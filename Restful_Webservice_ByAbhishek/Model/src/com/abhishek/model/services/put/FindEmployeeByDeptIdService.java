package com.abhishek.model.services.put;

import com.abhishek.model.entities.EmployeeTableEntity;
import com.abhishek.model.responses.EmployeeTableResponse;

import com.abhishek.model.utils.DBconnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Types;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import oracle.jdbc.internal.OracleTypes;

@Path("put")
public class FindEmployeeByDeptIdService {
    public FindEmployeeByDeptIdService() {
        super();
    }

    @PUT
    @Consumes(value = { "application/x-www-form-urlencoded", "application/json", "multipart/form-data" })
    @Produces(value = { "application/x-www-form-urlencoded", "application/json", "multipart/form-data" })
    @Path("findEmployee")
    public EmployeeTableResponse  findEmployee(@FormParam("deptId") String departmentId){
        
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        
        EmployeeTableResponse employeeTableResponse= new EmployeeTableResponse();
        ArrayList<EmployeeTableEntity> allEmpDetailslist=new ArrayList<EmployeeTableEntity>();
        EmployeeTableEntity employeeTableEntity;
        conn = DBconnection.getConnect();
       
        
        try{
            stmt=conn.prepareCall("{CALL Proc_Find_Employee_By_Dept(?,?,?)}");
            stmt.setString(1,departmentId);
            stmt.registerOutParameter(2, OracleTypes.CURSOR );
            stmt.registerOutParameter(3, Types.VARCHAR);
            
            rs=stmt.executeQuery();
            if(stmt.getObject(2)!=null){
                rs=(ResultSet)stmt.getObject(2);
                
                employeeTableEntity = new EmployeeTableEntity();

                while (rs.next()) {
  
                    employeeTableEntity.setEmployeesName(rs.getString(2));
                    employeeTableEntity.setEmail(rs.getString(3));
                    employeeTableEntity.setDepartmentId(Integer.valueOf(rs.getString(4)));
                    employeeTableEntity.setSalary(Integer.valueOf(rs.getString(5)));
                    employeeTableEntity.setEmployeesId(Integer.valueOf(rs.getString(1)));
                     
                     allEmpDetailslist.add(employeeTableEntity);
                  
                }
                employeeTableResponse.setStatus(stmt.getString(3));
                employeeTableResponse.setAllEmpDetails(allEmpDetailslist);
                
                
            }
            else{
                employeeTableResponse.setStatus(stmt.getString(3));
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        finally {
                    try {
                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }
                
        
        return employeeTableResponse;
    }
}
