package controller;

import model.Contact;
import view.ContactView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ContactController {
    private List<Contact> contacts;
    private ContactView view;

    public ContactController(ContactView view) {
        this.contacts = new ArrayList<>();
        this.view = view;
    }

    // Add contact
    public void addContact(Contact contact) {
        contacts.add(contact);
        view.displayMessage("Contact added successfully.");
    }

    // Edit contact
    public void editContact(String name, Contact updatedContact) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contact.setName(updatedContact.getName());
                contact.setPhoneNumber(updatedContact.getPhoneNumber());
                contact.setEmail(updatedContact.getEmail());
                contact.setAddress(updatedContact.getAddress());
                view.displayMessage("Contact updated successfully.");
                return;
            }
        }
        view.displayMessage("Contact not found.");
    }

    // Delete contact
    public void deleteContact(String name) {
        contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));
        view.displayMessage("Contact deleted successfully.");
    }

    // Search contacts
    public void searchContact(String query) {
        List<Contact> results = contacts.stream()
                .filter(c -> c.getName().equalsIgnoreCase(query)
                        || c.getPhoneNumber().equalsIgnoreCase(query)
                        || c.getEmail().equalsIgnoreCase(query))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            view.displayMessage("No contacts found for query: " + query);
        } else {
            view.displayAllContacts(results);
        }
    }

    // Sort contacts
    public void sortContacts(String criteria) {
        switch (criteria.toLowerCase()) {
            case "name":
                contacts.sort(Comparator.comparing(Contact::getName));
                break;
            case "phone":
                contacts.sort(Comparator.comparing(Contact::getPhoneNumber));
                break;
            case "email":
                contacts.sort(Comparator.comparing(Contact::getEmail));
                break;
            default:
                view.displayMessage("Invalid sort criteria.");
                return;
        }
        view.displayMessage("Contacts sorted by " + criteria);
        view.displayAllContacts(contacts);
    }
}
