package com.abhishek.model.services.put_and_insert;


import com.abhishek.model.responses.DepartmentTableResponse;

import com.abhishek.model.utils.DBconnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("put_insert")
public class InsertNewDepartmentService {
    public InsertNewDepartmentService() {
        super();
    }

    @PUT
    @Consumes(value = { "application/x-www-form-urlencoded", "application/json", "multipart/form-data" })
    @Produces(value = { "application/x-www-form-urlencoded", "application/json", "multipart/form-data" })
    @Path("insertDept")
    public DepartmentTableResponse insertNewDept(@FormParam("deptId") String deptId,
                                                 @FormParam("deptName") String deptName,
                                                 @FormParam("mngrId") String mngrId, @FormParam("locId") String locId) {
        DepartmentTableResponse departmentTableResponse = new DepartmentTableResponse();

        Connection conn = DBconnection.getConnect();
        CallableStatement stmt=null;
        try {
            stmt = conn.prepareCall("{CALL PROC_INSERT_New_DEPARTMENT(?,?,?,?,?)}");
            stmt.setString(1, deptId);
            stmt.setString(2, deptName);
            stmt.setString(3, mngrId);
            stmt.setString(4, locId);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.executeQuery();
            departmentTableResponse.setStatus(stmt.getString(5));
        } catch (Exception e) {
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
