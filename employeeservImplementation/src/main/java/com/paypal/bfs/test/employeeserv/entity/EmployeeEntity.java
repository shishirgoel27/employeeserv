package com.paypal.bfs.test.employeeserv.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="employee" ,uniqueConstraints = @UniqueConstraint(columnNames = {"first_name", "last_name", "date_of_birth"}))
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false,name = "first_name")
    private String firstName;

    @Column(nullable = false,name = "last_name")
    private String lastName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false, name="date_of_birth")
    private Date dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "address_id")
    private AddressEntity address;

}
