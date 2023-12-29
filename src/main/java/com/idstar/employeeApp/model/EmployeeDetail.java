package com.idstar.employeeApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "employeeDetails")
@SQLRestriction("deleted_date IS null")
@SQLDelete(sql = "UPDATE employee_details SET deleted_date = NOW() WHERE id=?")
public class EmployeeDetail implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nik", nullable = false, length = 16)
    private String nik;

    @Column(name = "npwp", nullable = false, length = 15)
    private String npwp;

    @Column(name = "createdDate", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @CreationTimestamp
    @JsonProperty("created_date")
    private Date createdDate;

    @Column(name = "updatedDate", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @UpdateTimestamp
    @JsonProperty("updated_date")
    private Date updatedDate;

    @Column(name = "deletedDate", insertable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("deleted_date")
    private Date deletedDate;

    @OneToOne(mappedBy = "employeeDetail")
    @JsonBackReference
    private Employee employee;

    public EmployeeDetail Generate(String nik, String npwp) {
        EmployeeDetail employeeDetail = new EmployeeDetail();
        employeeDetail.nik = nik;
        employeeDetail.npwp = npwp;
        return employeeDetail;
    }

    public EmployeeDetail Modify(Long id, String nik, String npwp) {
        EmployeeDetail employeeDetail = new EmployeeDetail().Generate(nik, npwp);
        employeeDetail.id = id;
        return employeeDetail;
    }
}
