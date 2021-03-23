package com.inconcept.task.persistence.entity;


import com.inconcept.task.persistence.entity.enums.UserEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String firstName;

    @Column(name = "Surname", nullable = false)
    private String lastName;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Hash_Pass", nullable = false)
    private String password;

    @Column(name = "Status")
    private String status;

    @Column(name = "Age")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "Location_Id",referencedColumnName = "Id")
    private LocationEntity location;

    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private UserEnum userEnum;

    @OneToMany(mappedBy = "user", targetEntity = RateEntity.class, fetch = FetchType.LAZY)
    private List<RateEntity> listUserRates;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserEnum getUserEnum() {
        return userEnum;
    }

    public void setUserEnum(UserEnum userEnum) {
        this.userEnum = userEnum;
    }

    public List<RateEntity> getListUserRates() {
        return listUserRates;
    }

    public void setListUserRates(List<RateEntity> listUserRates) {
        this.listUserRates = listUserRates;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }
}
