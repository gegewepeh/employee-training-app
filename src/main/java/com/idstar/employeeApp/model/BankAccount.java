package com.idstar.employeeApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "bankAccounts")
@SQLRestriction("deleted_date IS null")
@SQLDelete(sql = "UPDATE bank_accounts SET deleted_date = NOW(), employee_id = NULL WHERE id=?")
public class BankAccount {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    @JsonProperty("nama")
    private String name;

    @Column(name = "type", nullable = false, length = 20)
    @JsonProperty("jenis")
    private String type;

    @Column(name = "account", nullable = false, length = 12)
    @JsonProperty("rekening")
    private String account;

    @Column(name = "createdDate", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @CreationTimestamp
    @JsonProperty("created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updatedDate", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @UpdateTimestamp
    @JsonProperty("updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "deletedDate", insertable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("deleted_date")
    private Date deletedDate;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "employeeId", referencedColumnName = "id")
    @JsonManagedReference
    @JsonProperty("karyawan")
    @JsonIgnoreProperties({"dob", "status", "alamat", "created_date", "updated_date", "deleted_date", "detailKaryawan"})
    private Employee employee;

    public BankAccount Generate(String name, String type, String account, Employee employee) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.name = name;
        bankAccount.type = type;
        bankAccount.account = account;
        bankAccount.employee = employee;
        return bankAccount;
    }

    public BankAccount Modify(Long id, String name, String type, String account, Employee employee) {
        BankAccount bankAccount = new BankAccount().Generate(name, type, account, employee);
        bankAccount.id = id;
        return bankAccount;
    }
}
