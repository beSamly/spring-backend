package com.backendapi.packet;

import com.backendapi.entity.Employee;
import com.backendapi.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageHandler implements IPacketHandler {

    private EmployeeRepository employeeRepository;

    public MessageHandler(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void handle() {
        List<Employee> employees = this.employeeRepository.findAll();
        System.out.println(employees);
    }
}
