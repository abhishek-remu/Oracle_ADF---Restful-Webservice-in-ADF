package com.abhishek.model.services.get;

import com.abhishek.model.entities.EmployeeTableEntity;
import com.abhishek.model.responses.EmployeeTableResponse;

import com.abhishek.model.utils.DBconnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import oracle.jdbc.OracleTypes;


@Path("get")
public class EmployeesTableService {
    public EmployeesTableService() {
        super();
    }


    @GET
    @Produces("application/json")
    @Path("getEmpDetails")
    public EmployeeTableResponse  empDetailService() {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        EmployeeTableResponse employeeTableResponse = new EmployeeTableResponse();
        ArrayList<EmployeeTableEntity> allEmpDetailslist=new ArrayList<EmployeeTableEntity>();
        EmployeeTableEntity employeeTableEntity;
        conn = DBconnection.getConnect();
        System.out.println("conn  " + conn);


        try {
            stmt = conn.prepareCall("{CALL PROC_EMPLOYEESTABLE(?,?)}"); //Proc_EmployeesTable
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.registerOutParameter(2, Types.VARCHAR);

           
            rs = stmt.executeQuery();
          
          
            if (stmt.getObject(1) != null) {
                rs = (ResultSet) stmt.getObject(1);

                employeeTableEntity = new EmployeeTableEntity();

                while (rs.next()) {

                    
                  
                   
                   
                    employeeTableEntity.setEmployeesName(rs.getString(2));
                    employeeTableEntity.setEmail(rs.getString(3));
                    employeeTableEntity.setDepartmentId(Integer.valueOf(rs.getString(4)));
                    employeeTableEntity.setSalary(Integer.valueOf(rs.getString(5)));
                    employeeTableEntity.setEmployeesId(Integer.valueOf(rs.getString(1)));
                     
                    
                    allEmpDetailslist.add(employeeTableEntity);
                  
                }
                employeeTableResponse.setAllEmpDetails(allEmpDetailslist);
                employeeTableResponse.setStatus(stmt.getString(2));

            } else {

                employeeTableResponse.setStatus(stmt.getString(2));

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
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
