package view;

import model.Contact;
import java.util.List;

public class ContactView {

    public void displayContact(Contact contact) {
        System.out.println(contact);
        System.out.println("----------------------------");
    }

    public void displayAllContacts(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }
        for (Contact contact : contacts) {
            displayContact(contact);
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
