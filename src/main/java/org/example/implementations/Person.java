package org.example.implementations;

import org.example.annotations.JsonElement;
import org.example.annotations.JsonSerializable;

@JsonSerializable
public class Person {
    @JsonElement(key = "name")
    private String name;
    @JsonElement(key = "surname")
    private String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
