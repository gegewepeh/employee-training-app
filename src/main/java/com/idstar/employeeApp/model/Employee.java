package com.idstar.employeeApp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "employees")
@SQLRestriction("deleted_date IS null")
@SQLDelete(sql = "UPDATE employees SET deleted_date = NOW() WHERE id=?")
public class Employee implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    @JsonProperty("nama")
    private String name;

    @Column(name = "dateOfBirth", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "address", columnDefinition = "TEXT")
    @JsonProperty("alamat")
    private String address;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "createdDate", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("created_date")
    private Date createdDate;

    @Column(name = "updatedDate", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("updated_date")
    private Date updatedDate;

    @Column(name = "deletedDate", insertable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("deleted_date")
    private Date deletedDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeDetailId", referencedColumnName = "id")
    @JsonManagedReference
    @JsonProperty("detailKaryawan")
    private EmployeeDetail employeeDetail;

    @JsonIgnore
    @OneToOne(mappedBy = "employee")
    @JsonBackReference
    private BankAccount bankAccount;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    @JsonBackReference
    private Set<EmployeeTraining> employeeTrainings;

    public Employee Generate(String name, Date dob, String address, String status, EmployeeDetail employeeDetail) {
        Employee employee = new Employee();
        employee.name = name;
        employee.dob = dob;
        employee.status = status;
        employee.address = address;
        employee.employeeDetail = employeeDetail;
        return employee;
    }

    public Employee Modify(Long id, String name, Date dob, String address, String status, EmployeeDetail employeeDetail) {
        Employee employee = new Employee().Generate(name, dob, address, status, employeeDetail);
        employee.id = id;
        return employee;
    }
}
