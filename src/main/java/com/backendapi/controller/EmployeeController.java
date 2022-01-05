package com.backendapi.controller;

import com.backendapi.entity.Employee;
import com.backendapi.exception.MyCustomException;
import com.backendapi.repository.EmployeeRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/employees")
    List<Employee> all() {

        throw new RuntimeException("what the fuck is this");
//        return this.repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return this.repository.save(newEmployee);
    }

    // Single item

    @GetMapping("/employees/{id}")
    Employee getOne(@PathVariable Long id) throws MyCustomException {

        Employee employee = this.repository.findById(id).orElse(null);

        if(employee == null){
            throw new MyCustomException("awef");
        }

        return employee;
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return this.repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        this.repository.deleteById(id);
    }
}