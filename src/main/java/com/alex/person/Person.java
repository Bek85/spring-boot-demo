package com.alex.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;


public class Person {

    private final Integer id;
    private final String  name;
    private final Integer age;
    private final Gender gender;
    @JsonIgnore private final String password;

    public Person(Integer id, String name, Integer age, Gender gender, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(age, person.age) && gender == person.gender && Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, gender, password);
    }



    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }
}