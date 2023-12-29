package com.idstar.employeeApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Embeddable
@Table(name = "employeeTrainings")
@SQLRestriction("deleted_date IS null")
@SQLDelete(sql = "UPDATE employee_trainings SET deleted_date = NOW() WHERE id=?")
public class EmployeeTraining {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonProperty("tanggal")
    private Date date;

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

    @ManyToOne
    @JoinColumn(name = "employeeId")
    @JsonProperty("karyawan")
    @JsonManagedReference
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "trainingId")
    @JsonManagedReference
    Training training;

    public EmployeeTraining Generate(Date date, Employee employee, Training training) {
        EmployeeTraining employeeTraining = new EmployeeTraining();
        employeeTraining.date = date;
        employeeTraining.employee = employee;
        employeeTraining.training = training;
        return employeeTraining;
    }

    public EmployeeTraining Modify(Long id, Date date, Employee employee, Training training) {
        EmployeeTraining employeeTraining = new EmployeeTraining().Generate(date, employee, training);
        employeeTraining.id = id;
        return employeeTraining;
    }
}
