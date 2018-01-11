/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.publishinggood.service;

import com.sg.publishinggood.dao.AuthorBookDao;
import com.sg.publishinggood.dao.AuthorDao;
import com.sg.publishinggood.dao.BookDao;
import com.sg.publishinggood.dto.Author;
import com.sg.publishinggood.dto.AuthorBook;
import com.sg.publishinggood.dto.Book;
import java.util.List;

/**
 *
 * @author matt
 */
public class AuthorService {
    
    AuthorDao authorDao;
    AuthorBookDao authorBookDao; //so we can save
    BookDao bookDao;
    
    //get DTO by DTO
    public List<Author> getAuthorsByBook (Book book, int offset, int limit){ //this reduces headache later
        return authorDao.getAuthorsByBook(book, offset, limit);
    }
    
    //always make the parameter the entire Object first, and if you need the id, just extract it
    //for returning lists, important to use limit offset, numberPerPage
    
    //creating the relationship
    //choice: is this an author-based method or a book-based method? or should we make a new service?
    //content of method doesn't change -- just where it lives
    
    public AuthorBook addAuthorToBook(Author author, Book book){
        AuthorBook authorBook = new AuthorBook();
        authorBook.setAuthor(author);
        authorBook.setBook(book);
        
        return authorBookDao.create(authorBook);
    }
    
    public AuthorBook addAuthorToBook(int authorId, int bookId){
        Author author = authorDao.get(authorId);
        Book book = bookDao.get(bookId);
        
        return addAuthorToBook(author, book);
    }
    
}
