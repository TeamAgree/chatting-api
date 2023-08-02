package com.agree.chattingapi.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "test")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double value1;
    private double value2;
    private double value3;

    public TestEntity(){}
    public TestEntity(Date date, double value1, double value2, double value3){
        this.date = date;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getValue1() {
        return value1;
    }

    public double getValue2() {
        return value2;
    }

    public double getValue3() {
        return value3;
    }
}
