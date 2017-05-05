package addressbookTest;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import addressbook.AddressBook;
import addressbook.Contact;
import addressbook.AddressBook.ContactAttribute;

public class ContactTest {
	String objName;
	String objPhoneNumber;
	String objEmail;
	String objAddress;
	String objNote;
	String strName;
	String strPhoneNumber;
	String strEmail;
	String strAddress;
	String strNote;

	@Before
	public void setUp() {
		objName = new String("Arturo Muller");
		objPhoneNumber = new String("917-856-1256");
		objEmail = new String("aam760@nyu.edu");
		objAddress = new String("79 clinton st, new york");
		objNote = new String("This is for testing");
		strName = "Arturo Muller";
		strPhoneNumber = "917-856-1256";
		strEmail = "aam760@nyu.edu";
		strAddress = "79 clinton st, new york";
		strNote = "This is for testing";
	}

	@Test(expected = IllegalArgumentException.class)
	public void WithNameTest_twoNames() {
		Contact testContact = new Contact.Builder().withName(strName).withName(objName).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void WithPhoneNumberTest_twoPhones() {
		Contact testContact = new Contact.Builder().withPhoneNumber(objPhoneNumber).withPhoneNumber(strPhoneNumber)
				.build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void WithEmailTest_twoEmails() {
		Contact testContact = new Contact.Builder().withEmail(objEmail).withEmail(strEmail).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void WithAddressTest_twoAdresses() {
		Contact testContact = new Contact.Builder().withAddress(strAddress).withAddress(objAddress).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void WithNoteTest_twoNotes() {
		Contact testContact = new Contact.Builder().withNote(strNote).withNote(objNote).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void buildTest_noAttributes() {
		Contact testContact = new Contact.Builder().build();
	}

	/*
	 * attempt to build a contact with empty string i.e. this should
	 * bring up the exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void buildTest_emptyString() {
		Contact testContact = new Contact.Builder().withName("").build();
	}

	/*
	 * attempt to build a contact with null Attribute i.e. this should
	 * bring up the exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void buildTest_nullAttribute() {
		Contact testContact = new Contact.Builder().withName(null).build();
	}

	@Test
	public void buildTest_succesfullBuild() {
		Contact testContact = new Contact.Builder().withName(objName).withAddress(objAddress).withEmail(objEmail)
				.withNote(objNote).withPhoneNumber(objPhoneNumber).build();
		assertEquals(testContact.getName(), strName);
		assertEquals(testContact.getAddress(), strAddress);
		assertEquals(testContact.getPhoneNumber(), strPhoneNumber);
		assertEquals(testContact.getNote(), strNote);
		assertEquals(testContact.getEmailAddress(), strEmail);
	}

	@Test
	public void toStringTest() {
		Contact contactA = new Contact.Builder().withName(objName).withAddress(objAddress).withEmail(objEmail)
				.withNote(objNote).withPhoneNumber(objPhoneNumber).build();
		Contact contactB = new Contact.Builder().withName(objName).withAddress(objAddress).withEmail(objEmail)
				.withNote(objNote).withPhoneNumber(objPhoneNumber).build();
		Contact contactC = new Contact.Builder().withName(objName).withAddress(objAddress).withEmail(objEmail)
				.withNote("sds").withPhoneNumber(objPhoneNumber).build();
		assertEquals(contactA.toString(), contactB.toString());
		assertNotEquals(contactA.toString(), contactC.toString());
	}

	@Test
	public void matchTest() {
		Contact contactA = new Contact.Builder().withName("Arturo Muller").withAddress("79 Clinton St., NY")
				.withEmail("aam760@nyu.edu").withNote("testing purposes").withPhoneNumber("917-1212").build();
		Contact contactB = new Contact.Builder().withName("Bill Gates \n").withAddress("Washington State")
				.withEmail("bill@microsoft.com").withNote("notes").withPhoneNumber("528-1212").build();
		Contact contactC = new Contact.Builder().withName("Clark Kent").withAddress("NY").withEmail("clark@hotmail.com")
				.withNote("Testing purposes").withPhoneNumber("123").build();
		AddressBook bookA = new AddressBook();
		bookA.addContact(contactA);
		bookA.addContact(contactB);
		bookA.addContact(contactC);
		// Name
		ArrayList<Contact> testListA = bookA.search(ContactAttribute.NAME, "bill");
		assertEquals(testListA.get(0), contactB);
		// Phone
		ArrayList<Contact> testListB = bookA.search(ContactAttribute.PHONE, "1-23");
		assertEquals(testListB.get(0), contactC);
		ArrayList<Contact> testListC = bookA.search(ContactAttribute.PHONE, "9171212");
		assertEquals(testListC.get(0), contactA);
		// Email
		ArrayList<Contact> testListD = bookA.search(ContactAttribute.EMAIL, "bill");
		assertEquals(testListD.get(0), contactB);
		// Address
		ArrayList<Contact> testListE = bookA.search(ContactAttribute.ADDRESS, "washington");
		assertEquals(testListE.get(0), contactB);
		// Note
		ArrayList<Contact> testListF = bookA.search(ContactAttribute.NOTE, "notes");
		assertEquals(testListF.get(0), contactB);
	}

	@Test
	public void matchTest_NoMatch() {
		Contact contactA = new Contact.Builder().withName("Arturo Muller").withAddress("79 Clinton St., NY")
				.withEmail("aam760@nyu.edu").withNote("testing purposes").withPhoneNumber("917-1212").build();
		Contact contactB = new Contact.Builder().withName("Bill Gates \n").withAddress("Washington State")
				.withEmail("bill@microsoft.com").withNote("notes").withPhoneNumber("528-1212").build();
		Contact contactC = new Contact.Builder().withName("Clark Kent").withAddress("NY").withEmail("clark@hotmail.com")
				.withNote("Testing purposes").withPhoneNumber("123").build();
		AddressBook bookA = new AddressBook();
		bookA.addContact(contactA);
		bookA.addContact(contactB);
		bookA.addContact(contactC);
		// Name
		ArrayList<Contact> testListA = bookA.search(ContactAttribute.NAME, "Jones");
		assertEquals(testListA.size(), 0);
		// Phone
		ArrayList<Contact> testListB = bookA.search(ContactAttribute.PHONE, "7878");
		assertEquals(testListB.size(), 0);
		ArrayList<Contact> testListC = bookA.search(ContactAttribute.PHONE, "51515");
		assertEquals(testListC.size(), 0);
		// Email
		ArrayList<Contact> testListD = bookA.search(ContactAttribute.EMAIL, "op234@de.com");
		assertEquals(testListD.size(), 0);
		// Address
		ArrayList<Contact> testListE = bookA.search(ContactAttribute.ADDRESS, "Kentucky");
		assertEquals(testListE.size(), 0);
		// Note
		ArrayList<Contact> testListF = bookA.search(ContactAttribute.NOTE, "No Match");
		assertEquals(testListF.size(), 0);
	}

	/*
	 * Contact class method match cannot search within null attributes of
	 * contact it would be useful to have this added
	 */
	@Test
	public void matchTest_OneNull() {
		Contact contactA = new Contact.Builder().withAddress("79 Clinton St., NY").withEmail("aam760@nyu.edu")
				.withNote("testing purposes").withPhoneNumber("917-1212").build();
		Contact contactB = new Contact.Builder().withName("Bill Gates \n").withEmail("bill@microsoft.com")
				.withNote("notes").withPhoneNumber("528-1212").build();
		Contact contactC = new Contact.Builder().withName("Clark Kent").withAddress("NY").withNote("Testing purposes")
				.withPhoneNumber("123").build();
		Contact contactD = new Contact.Builder().withName("Clark Brown").withAddress("Downtown")
				.withEmail("clarkb@hotmail.com").build();
		AddressBook bookA = new AddressBook();
		bookA.addContact(contactA);
		bookA.addContact(contactB);
		bookA.addContact(contactC);
		bookA.addContact(contactD);
		// Name
		ArrayList<Contact> testListA = bookA.search(ContactAttribute.NAME, null);
		assertEquals(testListA.size(), 1);
		// Phone
		ArrayList<Contact> testListB = bookA.search(ContactAttribute.PHONE, null);
		assertEquals(testListB.size(), 1);
		// Email
		ArrayList<Contact> testListD = bookA.search(ContactAttribute.EMAIL, null);
		assertEquals(testListD.size(), 1);
		// Address
		ArrayList<Contact> testListE = bookA.search(ContactAttribute.ADDRESS, null);
		assertEquals(testListE.size(), 1);
		// Note
		ArrayList<Contact> testListF = bookA.search(ContactAttribute.NOTE, null);
		assertEquals(testListF.size(), 1);
	}

}
