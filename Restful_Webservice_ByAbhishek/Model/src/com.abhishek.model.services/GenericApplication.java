package com.abhishek.model.services;

import com.abhishek.model.services.get.EmployeesTableService;
import com.abhishek.model.services.get.EmployeesTableService;

import com.abhishek.model.services.get_and_update.UpdateDepartmentTableService;
import com.abhishek.model.services.post.FindEmployeeByCountryIdService;
import com.abhishek.model.services.put.FindEmployeeByDeptIdService;

import com.abhishek.model.services.put_and_delete.DeleteDepartmentService;
import com.abhishek.model.services.put_and_insert.InsertNewDepartmentService;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class GenericApplication extends Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();

        // Register root resources.
        classes.add(UpdateDepartmentTableService.class);
        classes.add(EmployeesTableService.class);
        classes.add(InsertNewDepartmentService.class);
        classes.add(DeleteDepartmentService.class);
        classes.add(FindEmployeeByDeptIdService.class);
        classes.add(FindEmployeeByCountryIdService.class);

        // Register provider classes.

        return classes;
    }
}
