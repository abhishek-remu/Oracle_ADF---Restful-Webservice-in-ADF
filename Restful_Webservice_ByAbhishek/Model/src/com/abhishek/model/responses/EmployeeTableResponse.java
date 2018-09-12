package com.abhishek.model.responses;

import com.abhishek.model.entities.EmployeeTableEntity;

import java.util.ArrayList;

public class EmployeeTableResponse {
    private String status;
    private ArrayList<EmployeeTableEntity> allEmpDetails;

    public void setAllEmpDetails(ArrayList<EmployeeTableEntity> allEmpDetails) {
        this.allEmpDetails = allEmpDetails;
    }

    public ArrayList<EmployeeTableEntity> getAllEmpDetails() {
        return allEmpDetails;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
