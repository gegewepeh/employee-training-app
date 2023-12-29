package com.idstar.employeeApp.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "trainings")
@SQLRestriction("deleted_date IS null")
@SQLDelete(sql = "UPDATE trainings SET deleted_date = NOW() WHERE id=?")
public class Training {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "instructor", nullable = false, length = 50)
    @JsonProperty("pengajar")
    private String instructor;

    @Column(name = "title", nullable = false, length = 50)
    @JsonProperty("tema")
    private String title;

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

    @JsonIgnore
    @OneToMany(mappedBy = "training")
    @JsonBackReference
    private Set<EmployeeTraining> employeeTrainings;


    public Training Generate(String instructor, String title) {
        Training training = new Training();
        training.instructor = instructor;
        training.title = title;
        return training;
    }

    public Training Modify(Long id, String instructor, String title) {
        Training training = new Training().Generate(instructor, title);
        training.id = id;
        return training;
    }
}
