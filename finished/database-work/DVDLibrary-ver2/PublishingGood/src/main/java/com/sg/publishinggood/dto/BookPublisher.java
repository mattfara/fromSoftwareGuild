/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.dto;

/**
 *
 * @author matt
 */
public class BookPublisher { //making an object out of a relationship -- like a marriage is an object that has two people
    //match database columns
    private Integer id; //better to use the id here instead of composite key, even if using index
                        //better to use Integer instead of int so we can have a null
    private Book book; //introduces type safety into db
    private Publisher publisher; //use Objects

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }


}
