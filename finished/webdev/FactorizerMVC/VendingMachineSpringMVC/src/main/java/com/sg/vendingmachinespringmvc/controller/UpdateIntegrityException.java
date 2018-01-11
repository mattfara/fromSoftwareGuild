/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

/**
 *
 * @author matt
 */
public class UpdateIntegrityException extends Exception {
    public UpdateIntegrityException(String message) {
        super(message);
    }
} //haven't added most CRUD methods yet -- if there is time, will need a version of this too:

/*
@RequestMapping(value = "/contact/{id}", method = RequestMethod.PUT)
@ResponseStatus(HttpStatus.NO_CONTENT)
public void updateContact(@PathVariable("id") long id, 
                          @Valid @RequestBody Contact contact) throws UpdateIntegrityException {
        
    if (id != contact.getContactId()) {
        throw 
          new UpdateIntegrityException("Contact Id on URL must match Contact Id in submitted data.");
    }
    dao.updateContact(contact);
}
*/