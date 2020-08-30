package com.sample;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @HasUserPermission(permissions = {"view_employee"})
    public String getEmployee(String employeeName) {
        return employeeName;
    }
}
