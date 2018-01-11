/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.dto.DVD;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author matt
 */
public class DVDLibraryDaoInMemImpl implements DVDLibraryDao{
    private Map<Long, DVD> dvdMap = new HashMap<>();
    private static long dvdIdCounter = 0;

    @Override
    public DVD addDVD(DVD dvd) {
        dvd.setDvdId(dvdIdCounter);
        // increment the counter so it is ready for use for the 
        // next dvd
        dvdIdCounter++;
        dvdMap.put(dvd.getDvdId(), dvd);
        return dvd;
    }

    @Override
    public void updateDVD(DVD dvd) {
        dvdMap.put(dvd.getDvdId(), dvd);

    }

    @Override
    public void removeDVD(long dvdId) {
        dvdMap.remove(dvdId);

    }

    @Override
    public List<DVD> getAllDVDs() {
        Collection<DVD> c = dvdMap.values();
        return new ArrayList(c);
    }

    @Override
    public DVD getDVDById(long dvdId) {
        return dvdMap.get(dvdId);

    }

    @Override
    public List<DVD> searchDVDs(Map<SearchTerm, String> criteria) {
        // Get all the search term values from the map
        String titleSearchCriteria =  
            criteria.get(SearchTerm.TITLE);
        String releaseYearSearchCriteria = 
             criteria.get(SearchTerm.RELEASE_YEAR);
        String directorSearchCriteria = 
            criteria.get(SearchTerm.DIRECTOR);
        String ratingSearchCriteria = 
            criteria.get(SearchTerm.RATING);
        
        // Declare all the predicate conditions - remember that
        // Predicate is a functional interface with one method
        // called test(T t) that returns a boolean.  Since
        // Predicate is generic, we get to specify the type of
        // object we want T to be - in our case, we want T to be
        // of type DVD.  That means the Predicates declared 
        // here will have one method: boolean test(DVD c)
        Predicate<DVD> titleMatchPredicate;
        Predicate<DVD> releaseYearMatchPredicate;
        Predicate<DVD> directorMatchPredicate;
        Predicate<DVD> ratingMatchPredicate;

        // Placeholder predicate - always returns true. Used for 
        // search terms that are empty - if the user didn't specify 
        // a value for one of the search terms, we must return true
        // because we are ANDing all the search terms together and 
        // our spec says that we return everything in the DAO when
        // the user leaves all the search terms blank.
        Predicate<DVD> truePredicate = (c) -> {
            return true;
        };
        
        // Assign values to predicates. If a given search term is empty, 
        // just assign the default truePredicate, otherwise assign the 
        // predicate that only returns true when it finds a match for the 
        // given term.
        if (titleSearchCriteria == null || 
            titleSearchCriteria.isEmpty()) {
            titleMatchPredicate = truePredicate;
        } else {
            titleMatchPredicate = 
                (c) -> c.getTitle().equals(titleSearchCriteria);
        }

        if (releaseYearSearchCriteria == null || 
            releaseYearSearchCriteria.isEmpty()) {
            releaseYearMatchPredicate = truePredicate;
        } else {
            releaseYearMatchPredicate = 
                (c) -> c.getReleaseDate().equals(releaseYearSearchCriteria);
        }

        if (directorSearchCriteria == null || 
            directorSearchCriteria.isEmpty()) {
            directorMatchPredicate = truePredicate;
        } else {
            directorMatchPredicate = 
                (c) -> c.getDirector().equals(directorSearchCriteria);
        }

        if (ratingSearchCriteria == null || 
            ratingSearchCriteria.isEmpty()) {
            ratingMatchPredicate = truePredicate;
        } else {
            ratingMatchPredicate = 
                (c) -> c.getRating().equals(ratingSearchCriteria);
        }

        // Return the list of DVDs that match the given criteria. 
        // To do this we just AND all the predicates together in a 
        // filter operation.
        return dvdMap.values().stream()
                .filter(titleMatchPredicate
                        .and(releaseYearMatchPredicate)
                        .and(directorMatchPredicate)
                        .and(ratingMatchPredicate))
                .collect(Collectors.toList());
    }
    
}
