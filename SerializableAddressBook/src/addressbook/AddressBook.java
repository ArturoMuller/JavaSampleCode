package addressbook;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The AddressBook class implements an application that accesses and creates a list AddressEntries
 * 
 * @author Arturo Muller
 * @version 1.0 June 13, 2016
 */
public class AddressBook {
  private ArrayList<AddressEntry> contacts;

  /**
   * Constructs and initializes an AddressBook of AddressEntries
   */
  public AddressBook() {
    contacts = new ArrayList<AddressEntry>();
  }

  /**
   * Create AddressEntry and insert into AddressBook
   * 
   * @param name name of person
   * @param postalAddress postalAddress of person
   * @param Phone Phone of person
   * @param email email of person
   * @param note notes on person
   */
  public void addEntry(String name, String postalAddress, String Phone, String email, String note) {
    AddressEntry contact = new AddressEntry.Builder(name).postalAddress(postalAddress)
        .phoneNumber(Phone).email(email).note(note).build();
    insertAlphabeticalOrder(contact);
  }

  /**
   * Insert AddressEntry object into AddressBook
   * 
   * @param contact
   */
  public void addEntry(AddressEntry contact) {
    insertAlphabeticalOrder(contact);
  }


  private void insertAlphabeticalOrder(AddressEntry contact) {
    String addingContactsName = contact.getName();
    int initialSize = contacts.size();
    // iterates back to front while moving each contact one place back in the array
    for (int i = initialSize; i >= 0; i--) {
      // when front of array is reached inserts contact and breaks out of loop
      if (i == 0) {
        contacts.add(0, contact);
        break;
      }
      AddressEntry lastContact = contacts.get(i - 1);
      String lastContactsName = lastContact.getName();
      // checks if lastContact is greater than or equal and add insert the new contact right after
      if (addingContactsName.compareTo(lastContactsName) > -1) {
        contacts.add(i, contact);
        break;
      } else {
        contacts.add(i, lastContact);
        contacts.remove(i - 1);
      }
    }
  }

  /**
   * searches AddressBook for contacts matching in name 
   * 
   * @param text text of name of contact being searched use empty string for alls
   * @return ArrayList of contacts that match text in name
   */
  public ArrayList<AddressEntry> searchByName(String text) {
    ArrayList<AddressEntry> textMatches = new ArrayList<AddressEntry>();
    text = text.toLowerCase();
    for (AddressEntry current : contacts) {
      String currentname = current.getName().toLowerCase();
      if (currentname.contains(text)) {
        textMatches.add(current);
      }
    }
    return textMatches;
  }

  /**
   * searches AddressBook for contacts matching in postal address
   * 
   * @param text text of postal address of being searched
   * @return ArrayList of contacts that match text in postal address
   */
  public ArrayList<AddressEntry> searchByPostalAddress(String text) {
    ArrayList<AddressEntry> textMatches = new ArrayList<AddressEntry>();
    text = text.toLowerCase();
    for (AddressEntry current : contacts) {
      String currentname = current.getPostalAddress().toLowerCase();
      if (currentname.contains(text)) {
        textMatches.add(current);
      }
    }
    return textMatches;
  }

  /**
   * searches AddressBook for contacts matching in phone number
   * 
   * @param text text of phone number of being searched
   * @return ArrayList of contacts that match text in phone number
   */
  public ArrayList<AddressEntry> searchByPhoneNumber(String text) {
    ArrayList<AddressEntry> textMatches = new ArrayList<AddressEntry>();
    text = text.toLowerCase();
    for (AddressEntry current : contacts) {
      String currentname = current.getPhoneNumber().toLowerCase();
      if (currentname.contains(text)) {
        textMatches.add(current);
      }
    }
    return textMatches;
  }

  /**
   * searches AddressBook for contacts matching in email
   * 
   * @param text text of email of being searched
   * @return ArrayList of contacts that match text in email
   */
  public ArrayList<AddressEntry> searchByEmail(String text) {
    ArrayList<AddressEntry> textMatches = new ArrayList<AddressEntry>();
    text = text.toLowerCase();
    for (AddressEntry current : contacts) {
      String currentname = current.getEmail().toLowerCase();
      if (currentname.contains(text)) {
        textMatches.add(current);
      }
    }
    return textMatches;
  }

  
  /**
   * searches AddressBook for contacts matching in note
   * 
   * @param text text of note of being searched
   * @return ArrayList of contacts that match text in note
   */
  public ArrayList<AddressEntry> searchByNote(String text) {
    ArrayList<AddressEntry> textMatches = new ArrayList<AddressEntry>();
    text = text.toLowerCase();
    for (AddressEntry current : contacts) {
      String currentname = current.getNote().toLowerCase();
      if (currentname.contains(text)) {
        textMatches.add(current);
      }
    }
    return textMatches;
  }

  /**
   * removes AddressEntry of contact from AddressBook
   * 
   * @param contact contact AddressEntry to be deleted
   */
  public void removeEntry(AddressEntry contact) {
    contacts.remove(contact);
  }

  /**
   * saves the AddressBook in a txt file
   * 
   * @param file file object of file and path to use for creating the txt. Example for windows: new File("C:\\Users\\Arturo\\Desktop\\contacts.txt")
   */
  public void saveAddressBook(File file) {
    try {
      FileWriter fw = new FileWriter(file);
      PrintWriter pw = new PrintWriter(fw);
      // inserts all the contacts with each field on a line by line basis
      for (AddressEntry current : contacts) {
        pw.println(current.getName());
        pw.println(current.getPostalAddress());
        pw.println(current.getPhoneNumber());
        pw.println(current.getEmail());
        pw.println(current.getNote());
      }
      pw.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }


  /**
   * reads the AddressBook in a txt file and adds these addresses to the current AddressBook object
   * 
   * @param file file object of file and path to use for creating the txt. Example for windows: new File("C:\\Users\\Arturo\\Desktop\\contacts.txt")
   */
  public void readAddressBook(File file) {
    try {
      // intializes reader in order to count the number of contacts
      FileReader frLineCount = new FileReader(file);
      BufferedReader brLineCount = new BufferedReader(frLineCount);
      // initializes variable to count the number of lines
      int lines = 0;
      // counts total lines in address book
      while (brLineCount.readLine() != null)
        lines++;
      brLineCount.close();
      // calculates total contacts
      int totalContacts = lines / 5;
      // initializes reader in order to add the contacts to the array
      FileReader frLineRead = new FileReader(file);
      BufferedReader brLineRead = new BufferedReader(frLineRead);
      // inserts each AddressEntry into the array
      for (int i = totalContacts; i > 0; i--) {// for statment to add the exact number of contacts
        this.addEntry(brLineRead.readLine(), brLineRead.readLine(), brLineRead.readLine(),
            brLineRead.readLine(), brLineRead.readLine());
      }
      brLineRead.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
