package com.abhishek.model.responses;

import com.abhishek.model.entities.DepartmentTableEntity;

import java.util.ArrayList;

public class DepartmentTableResponse {
    
    private String status;
    private ArrayList<DepartmentTableEntity> allDeptDetails;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setAllDeptDetails(ArrayList<DepartmentTableEntity> allDeptDetails) {
        this.allDeptDetails = allDeptDetails;
    }

    public ArrayList<DepartmentTableEntity> getAllDeptDetails() {
        return allDeptDetails;
    }
}
