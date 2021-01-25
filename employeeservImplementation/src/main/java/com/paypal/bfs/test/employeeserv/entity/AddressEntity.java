package com.paypal.bfs.test.employeeserv.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="address")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty(value = "id")
    private Integer id;

    @Column(name = "line1", nullable = false)
    @JsonProperty(value = "line1")
    private String line1;

    @Column(name = "line2", nullable = true)
    @JsonProperty(value = "line2", required = false)
    private String line2;

    @Column(name = "city", nullable = false)
    @JsonProperty(value = "city")
    private String city;

    @Column(name = "state", nullable = false)
    @JsonProperty(value = "state")
    private String state;

    @Column(name = "country", nullable = false)
    @JsonProperty(value = "country")
    private String country;

    @Column(name = "zip_code", nullable = false)
    @JsonProperty(value = "zip_code")
    private String zipCode;


}
