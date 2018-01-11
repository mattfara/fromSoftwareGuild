/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.model;

import java.util.Objects;

/**
 *
 * @author matt
 */
public class Genre {
    private int genreId;
    private String GenreName;

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return GenreName;
    }

    public void setGenreName(String GenreName) {
        this.GenreName = GenreName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.genreId;
        hash = 29 * hash + Objects.hashCode(this.GenreName);
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
        final Genre other = (Genre) obj;
        if (this.genreId != other.genreId) {
            return false;
        }
        if (!Objects.equals(this.GenreName, other.GenreName)) {
            return false;
        }
        return true;
    }
    
    
}
