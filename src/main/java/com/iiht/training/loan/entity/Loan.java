package com.iiht.training.loan.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;

    @Column(name = "email")
    @NotBlank
    @Email
    private String email;

    @Column(name = "amount")
    @NotNull
    private Double amount;

    @Column(name = "interest")
    @NotNull
    private Long interest;

    @Column(name = "term")
    @NotBlank
    private String term;

    public Loan() {

    }

    public Loan(String firstName, String lastName, String email, double amount, Long interest, String term) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.amount = amount;
        this.interest = interest;
        this.term = term;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getInterest() {
        return interest;
    }

    public void setInterest(Long interest) {
        this.interest = interest;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}

