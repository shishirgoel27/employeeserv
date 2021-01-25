package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.exception.CreateEmployeeException;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.util.PropertyCopierUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {


        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(Integer.parseInt(id));
        if(employeeEntity.isPresent()) {
            Employee employee = new Employee();
            try {
                PropertyCopierUtil.copyProperties(employeeEntity.get(), employee, String.class);
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }else {
            throw new EmployeeNotFoundException(String.format("No employee found with id - %s", id));
        }
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        try {
            EmployeeEntity employeeEntity = new EmployeeEntity();
            PropertyCopierUtil.copyProperties(employee,employeeEntity, Date.class);
            final EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
            Employee savedEmployee = new Employee();
            PropertyCopierUtil.copyProperties(savedEmployeeEntity, savedEmployee, String.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
        }catch(DataAccessException e) {
            throw new CreateEmployeeException(e);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}
