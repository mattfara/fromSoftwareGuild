/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author matt
 */
public class Actor {
    private int actorId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate dateOfDeat;

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDateOfDeat() {
        return dateOfDeat;
    }

    public void setDateOfDeat(LocalDate dateOfDeat) {
        this.dateOfDeat = dateOfDeat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.actorId;
        hash = 73 * hash + Objects.hashCode(this.firstName);
        hash = 73 * hash + Objects.hashCode(this.lastName);
        hash = 73 * hash + Objects.hashCode(this.birthDate);
        hash = 73 * hash + Objects.hashCode(this.dateOfDeat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actor other = (Actor) obj;
        if (this.actorId != other.actorId) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.birthDate, other.birthDate)) {
            return false;
        }
        if (!Objects.equals(this.dateOfDeat, other.dateOfDeat)) {
            return false;
        }
        return true;
    }
    
    
    
}
