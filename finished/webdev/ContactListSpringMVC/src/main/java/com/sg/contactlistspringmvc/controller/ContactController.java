/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringmvc.controller;

import com.sg.contactlistspringmvc.dao.ContactListDao;
import com.sg.contactlistspringmvc.model.Contact;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author matt
 */
@Controller
public class ContactController {
    
    ContactListDao dao;
    
    //injection
    @Inject //use the bean in the spring-persistence.xml -- will get fucked up if there is more than one version
    public ContactController(ContactListDao dao){
        this.dao = dao;
    }
    
    
    @RequestMapping(value="/displayContactsPage", method=RequestMethod.GET)
    public String displayContactsPage(Model model){ //model comes from Spring. like a map. easy to read. transport backend data to front end
        //get all contacts
        List<Contact> allContacts = dao.getAllContacts();
        model.addAttribute("contactList",allContacts);
        return "contacts";
    }
    
    @RequestMapping(value="/createContact", method=RequestMethod.POST) //dispatcher is a servlet....
    public String createContact(HttpServletRequest request){
        //create new instance of contact
        Contact newContact = new Contact();
        
        //set properties based on form
        newContact.setFirstName(request.getParameter("firstName"));
        newContact.setLastName(request.getParameter("lastName"));
        newContact.setCompany(request.getParameter("company"));
        newContact.setPhone(request.getParameter("phone"));
        newContact.setEmail(request.getParameter("email"));
        //save contact -- persist
        dao.addContact(newContact);
        //redirect toward contact page
        return "redirect:displayContactsPage"; //this one takes you to this page in particular, see the first method displayContactsPage value
                                                //catches on RequestMapping
    }
    @RequestMapping(value="/displayContactDetails", method=RequestMethod.GET) //click a link, it's a get
    public String displayContactInfo(HttpServletRequest request, Model model){
        String contactIdParameter = request.getParameter("contactId");
        int contactId = Integer.parseInt(contactIdParameter);
        
        Contact contact = dao.getContactById(contactId);

        //what is addAllAttributes?
        model.addAttribute("selectedContact", contact); //key:value
        
        return "contactDetails";
    }
    
    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public String deleteContact(HttpServletRequest request) {
        String contactIdParameter = request.getParameter("contactId");
        long contactId = Long.parseLong(contactIdParameter);
        dao.removeContact(contactId);
        return "redirect:displayContactsPage";
    }
    
    //the following two methods are required for the editing
    //first display all the contact into in a jsp form
    //then put all that form info into a Contact object
    @RequestMapping(value = "/displayEditContactForm", method = RequestMethod.GET)
    public String displayEditContactForm(HttpServletRequest request, Model model) {
        String contactIdParameter = request.getParameter("contactId");
        long contactId = Long.parseLong(contactIdParameter);
        Contact contact = dao.getContactById(contactId);
        model.addAttribute("contact", contact);
        return "editContactForm";
    }
    //@ModelAttribute is what pulls all the form data into a filled Object
    @RequestMapping(value = "/editContact", method = RequestMethod.POST)
    public String editContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult result) {

        if (result.hasErrors()) { //hasErrors method called on BindingResult object
            return "editContactForm";
        }

        dao.updateContact(contact);

        return "redirect:displayContactsPage";
    }    
}   

/*new things on this page
    redirect
    RequestMapping(value="/urlToBeCalledFromJspForThisMethod"
    Model model
    model.addAttribute
    @Valid
    BindingResult result
    
    
    questions -- why not use this sf magic in the add a contact method too?

*/
    