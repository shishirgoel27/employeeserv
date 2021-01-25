package com.paypal.bfs.test.employeeserv.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.util.PropertyCopierUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeResource.class)
public class TestEmployeeResourceImpl {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void testCreateEmployee() throws Exception{
        EmployeeEntity employeeEntity = new EmployeeEntity();
        PropertyCopierUtil.copyProperties(getEmployee(), employeeEntity, Date.class);
        Mockito.when(employeeRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn(employeeEntity);
        this.mockMvc.perform(post("/v1/bfs/employees/")
        .content(jsonEmployee())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetEmployee() throws Exception{
        EmployeeEntity employeeEntity = new EmployeeEntity();
        PropertyCopierUtil.copyProperties(getEmployee(), employeeEntity, Date.class);
        Mockito.when(employeeRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(employeeEntity));
        this.mockMvc.perform(get("/v1/bfs/employees/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String jsonEmployee() throws Exception{
        Employee employee = getEmployee();
        ObjectMapper mapper = new ObjectMapper();
        String employeeJson = mapper.writeValueAsString(employee);
        return employeeJson;
    }

    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setDateOfBirth("10/02/2005");
        AddressEntity address = new AddressEntity();
        address.setLine1("street1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipCode("560000");
        employee.setAddress(address);
        return employee;
    }
}
