package src.assignment1.addressbook;

import java.io.File;
import java.util.ArrayList;

public class test {
  public static void main(String [] args){
//        String name = new String("sds");
//        
//        Contact carl = new Contact.Builder().setName(name).setPhoneNumber("987").build(); 
//        Contact fredrick = new Contact.Builder().setName("ss").setPhoneNumber("987").build(); 
//        
//        
//        AddressBook people = new AddressBook();
//        people.add(carl);
//        people.add(fredrick);
//        
//        ArrayList<Contact> test = people.search("987");
//        
//        for(Contact e: test){
//        System.out.println(e);
//        }
//        
//        File file = new File("C:\\Users\\Arturo\\Desktop\\SoftwareTestTrash");
//        
//        people.exportContacts("C:\\Users\\Arturo\\Desktop\\SoftwareTestTrash\\contacts.txt");
//        
        
          AddressBook second = new AddressBook();
          second.importContact("C:\\Users\\Arturo\\Desktop\\SoftwareTestTrash\\contacts.txt");
          
          ArrayList<Contact> lesson = second.search("");
          
          for(Contact a: lesson){
            System.out.println(a.getName());
          }
          
//        System.out.println(carl.equals(fredrick));
//       System.out.println("name1 " + carl.getName());
//       System.out.println("name1 " + fredrick.getName());
//        System.out.println(carl.hashCode());
//        System.out.println(fredrick.hashCode());
//        name = new String("dsss");
//        System.out.println("name 2" + carl.getName());
//        carl.getEmailAddress()
//        System.out.println("email 1" + carl.getEmailAddress());
//        
        
  }
}
