package com.abhishek.model.services.get_and_update;

import com.abhishek.model.responses.DepartmentTableResponse;

import com.abhishek.model.utils.DBconnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("get_and_update")
public class UpdateDepartmentTableService {
    public UpdateDepartmentTableService() {
        super();
    }

    @GET
    @Produces(value = { "application/json", "application/xml" })
    @Path("updateDept")
    public DepartmentTableResponse updateEmployee(@QueryParam("DepartmentId") String deptId,
                                                  @QueryParam("DepartmentName") String deptName) {
        DepartmentTableResponse departmentTableResponse = new DepartmentTableResponse();
        CallableStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBconnection.getConnect();
            stmt = conn.prepareCall("{CALL Proc_Update_DepartmentTable(?,?,?)}");
            stmt.setString(1, deptId);
            stmt.setString(2, deptName);
            stmt.registerOutParameter(3, Types.VARCHAR);
            stmt.executeQuery();
            departmentTableResponse.setStatus(stmt.getString(3));

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
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
