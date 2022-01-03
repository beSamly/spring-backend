package com.backendapi.packet;

import com.backendapi.entity.Employee;
import com.backendapi.repository.EmployeeRepository;

import java.util.List;

public class HandleLogin implements IPacketHandler {

    private EmployeeRepository employeeRepository;

    public HandleLogin(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void Handle() {
        List<Employee> employees = this.employeeRepository.findAll();
        System.out.println(employees);
    }
}
