package com.example.pr2test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min=2, max = 50, message = "Введите от 2х до 50ти символов")
    private String surname, name;
    private Boolean middle;
    @Digits(integer = 100, fraction = 0, message = "Введите не более 100 знаков")
    private int age;
    @Positive(message = "Проверьте номер группы")
    private int groupName;

    public Reader(String surname, String name, Boolean middle, int age, int groupName) {
        this.surname = surname;
        this.name = name;
        this.middle = middle;
        this.age = age;
        this.groupName = groupName;
    }

    public Reader() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMiddle() {
        return middle;
    }

    public void setMiddle(Boolean middle) {
        this.middle = middle;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGroupName() {
        return groupName;
    }

    public void setGroupName(int groupName) {
        this.groupName = groupName;
    }
}
