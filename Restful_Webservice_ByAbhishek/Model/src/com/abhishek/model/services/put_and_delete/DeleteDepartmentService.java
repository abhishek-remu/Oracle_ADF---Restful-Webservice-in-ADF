package com.abhishek.model.services.put_and_delete;

import com.abhishek.model.responses.DepartmentTableResponse;

import com.abhishek.model.utils.DBconnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("delete")
public class DeleteDepartmentService {
    public DeleteDepartmentService() {
        super();
    }

    @PUT
    @Consumes(value = { "application/x-www-form-urlencoded", "application/json", "multipart/form-data" })
    @Produces(value = { "application/x-www-form-urlencoded", "application/json", "multipart/form-data" })
    @Path("deleteDept")
    public DepartmentTableResponse deleteDepartment(@FormParam("deptId") String deptId){
        DepartmentTableResponse departmentTableResponse= new DepartmentTableResponse();
        CallableStatement stmt= null;
        Connection conn= null;
        try{
             conn = DBconnection.getConnect();
            stmt= conn.prepareCall("{CALL Proc_Delete_Department(?,?)}");
            System.out.println("deptId ");
            stmt.setString(1, deptId);
            stmt.registerOutParameter(2, Types.VARCHAR);
            stmt.executeQuery();
            departmentTableResponse.setStatus(stmt.getString(2));
            
            
            
        }
        catch(Exception e ){
            e.printStackTrace();
            
        }
        finally {
                    try {
                       
                        stmt.close();
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }
                
        return departmentTableResponse;
        
    }
    
    
}
