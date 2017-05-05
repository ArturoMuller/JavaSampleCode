package addressbookTest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.TemporaryFolder;
import addressbook.*;
import addressbook.AddressBook.ContactAttribute;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AddressBookTest {
	Contact contactA;
	Contact contactB;
	Contact contactC;
	AddressBook bookA;
	AddressBook bookB;

	@Before
	public void setUp() {
		contactA = new Contact.Builder().withName("Arturo Muller").withAddress("79 Clinton St., NY")
				.withEmail("aam760@nyu.edu").withNote("testing purposes").withPhoneNumber("917-1212").build();
		contactB = new Contact.Builder().withName("Bill Gates \n").withAddress("Washington State")
				.withEmail("bill@microsoft.com").withNote("notes").withPhoneNumber("528-1212").build();
		contactC = new Contact.Builder().withName("Clark Kent").withAddress("NY").withEmail("clark@hotmail.com")
				.withNote("Testing purposes").withPhoneNumber("123").build();
		bookA = new AddressBook();
		bookB = new AddressBook();
		bookB.addContact(contactA);
		bookB.addContact(contactB);
		bookB.addContact(contactC);
	}

	@Test
	public void addContactTest() {
		assertTrue(bookA.addContact(contactA));
		ArrayList<Contact> testList = bookA.search(ContactAttribute.NAME, "Arturo");
		Contact testContactA = testList.get(testList.size() - 1);
		assertEquals(contactA, testContactA);
	}

	 /* should return false or NullPointerException because null contact should not be allowed*/
	@Test		
	public void addContactTest_nullContact() {
		assertFalse(bookA.addContact(null));
		ArrayList<Contact> testListA = bookA.search(ContactAttribute.NAME, null);
		assertEquals(testListA.size(), 0);
	}

	@Test
	public void removeContactTest() {
		bookA.addContact(contactA);
		bookA.addContact(contactB);
		ArrayList<Contact> testListA = bookA.search(ContactAttribute.NAME, "");
		assertEquals(testListA.size(), 2);
		assertTrue(bookA.removeContact(contactA));
		ArrayList<Contact> testListB = bookA.search(ContactAttribute.NAME, "Arturo");
		assertEquals(testListB.size(), 0);
		ArrayList<Contact> testListC = bookA.search(ContactAttribute.NAME, "");
		assertEquals(testListC.size(), 1);
	}

	@Test
	public void searchTest_byName() {
		ArrayList<Contact> testList = bookB.search(ContactAttribute.NAME, "bill");
		assertEquals(testList.get(0), contactB);
	}

	@Test
	public void searchTest_byName_CaseInsensitive() {
		Contact James = new Contact.Builder().withName("JAMES").build();
		bookB.addContact(James);
		ArrayList<Contact> testList = bookB.search(ContactAttribute.NAME, "james");
		assertEquals(testList.get(0), James);
	}

	@Test
	public void searchTest_byName_MultipleMatches() {
		Contact Bill = new Contact.Builder().withName("Bill").build();
		bookB.addContact(Bill);
		ArrayList<Contact> testList = bookB.search(ContactAttribute.NAME, "bill");
		assertEquals(testList.size(), 2);
	}

	@Test
	public void searchTest_byPhone_SanitizingNumber() {
		ArrayList<Contact> testListA = bookB.search(ContactAttribute.PHONE, "1-23");
		assertEquals(testListA.get(0), contactC);
		ArrayList<Contact> testListB = bookB.search(ContactAttribute.PHONE, "9171212");
		assertEquals(testListB.get(0), contactA);
	}

	@Test
	public void searchTest_byPhone_MultipleMatches() {
		Contact phone = new Contact.Builder().withPhoneNumber("1232123").build();
		bookB.addContact(phone);
		ArrayList<Contact> testListA = bookB.search(ContactAttribute.PHONE, "1-23");
		assertEquals(testListA.size(), 2);

	}

	@Test
	public void searchTest_byEmail() {
		ArrayList<Contact> testListA = bookB.search(ContactAttribute.EMAIL, "bill");
		assertEquals(testListA.get(0), contactB);
	}

	@Test
	public void searchTest_byEmail_CaseInsensitive() {

		ArrayList<Contact> testListA = bookB.search(ContactAttribute.EMAIL, "BILL@MICROSOFT.COM");
		assertEquals(testListA.get(0), contactB);
	}

	@Test
	public void searchTest_byEmail_MultipleMatches() {
		Contact bill = new Contact.Builder().withEmail("bill@hotmail.com").build();
		bookB.addContact(bill);
		ArrayList<Contact> testListA = bookB.search(ContactAttribute.EMAIL, "bill");
		assertEquals(testListA.size(), 2);
	}

	@Test
	public void searchTest_byAddress() {
		ArrayList<Contact> testListA = bookB.search(ContactAttribute.ADDRESS, "washington");
		assertEquals(testListA.get(0), contactB);
	}

	@Test
	public void searchTest_byAddress_CaseInsensitive() {
		ArrayList<Contact> testListA = bookB.search(ContactAttribute.ADDRESS, "WASHINGTON");
		assertEquals(testListA.get(0), contactB);
	}

	@Test
	public void searchTest_byAddress_MultipleMatches() {
		Contact Washington = new Contact.Builder().withAddress("washington d.c.").build();
		bookB.addContact(Washington);
		ArrayList<Contact> testListA = bookB.search(ContactAttribute.ADDRESS, "washington");
		assertEquals(testListA.size(), 2);
	}

	@Test
	public void searchTest_byNote() {
		ArrayList<Contact> testListA = bookB.search(ContactAttribute.NOTE, "notes");
		assertEquals(testListA.get(0), contactB);
	}

	@Test
	public void searchTest_byNote_CaseInsensitive() {
		ArrayList<Contact> testListA = bookB.search(ContactAttribute.NOTE, "NOTES");
		assertEquals(testListA.get(0), contactB);
	}

	@Test
	public void searchTest_byNote_MultipleMatches() {
		Contact notes = new Contact.Builder().withNote("MoRe NoTeS").build();
		bookB.addContact(notes);
		ArrayList<Contact> testListA = bookB.search(ContactAttribute.NOTE, "notes");
		assertEquals(testListA.size(), 2);
	}

	@Test
	public void saveTest() {
		TemporaryFolder folder = new TemporaryFolder();
		try {
			bookB.save(folder + "temp.json");
		} catch (IOException e) {
			e.getStackTrace();
		}
		File saveAddress = new File(folder + "temp.json");
		assertTrue(saveAddress.canRead());
	}

	@Test
	public void saveTest_empty() {
		TemporaryFolder folder = new TemporaryFolder();
		AddressBook emptyBook = new AddressBook();
		try {
			emptyBook.save(folder + "temp.json");
		} catch (IOException e) {
			e.getStackTrace();
		}
		File saveAddress = new File(folder + "temp.json");
		assertTrue(saveAddress.canRead());
	}

	@Test
	public void ReadTest() {
		TemporaryFolder folder = new TemporaryFolder();
		try {
			bookB.save(folder + "temp.json");
		} catch (IOException e) {
			e.getStackTrace();
		}
		File saveAddress = new File(folder + "temp.json");
		AddressBook bookZ = null;
		try {
			bookZ = new AddressBook(saveAddress.toString());
		} catch (IOException e) {
			e.getStackTrace();
		}
		try {
			ArrayList<Contact> testRead = bookZ.search(ContactAttribute.ADDRESS, "");
			assertEquals(testRead.size(), 3);
			ArrayList<Contact> testExtraLines = bookZ.search(ContactAttribute.ADDRESS, "Washington State");
			assertEquals(testExtraLines.get(0).getName(), "Bill Gates \n");
		} catch (NullPointerException e) {
			e.getStackTrace();
		}
	}

	@Test
	public void ReadTest_empty() {
		TemporaryFolder folder = new TemporaryFolder();
		AddressBook emptyBook = new AddressBook();
		try {
			emptyBook.save(folder + "temp.json");
		} catch (IOException e) {
			e.getStackTrace();
		}
		File saveAddress = new File(folder + "temp.json");
		AddressBook bookZ = null;
		try {
			bookZ = new AddressBook(saveAddress.toString());
		} catch (IOException e) {
			e.getStackTrace();
		}
		try {
			ArrayList<Contact> testRead = bookZ.search(ContactAttribute.ADDRESS, "");
			assertEquals(testRead.size(), 0);
		} catch (NullPointerException e) {
			e.getStackTrace();
		}
	}

	@Test
	public void toStringTest() {
		AddressBook bookC = new AddressBook();
		bookC.addContact(contactA);
		bookC.addContact(contactC);
		assertEquals(bookC.toString(), "Arturo Muller\naam760@nyu.edu\n917-1212\n79 Clinton St., NY\n"
				+ "testing purposes\n\nClark Kent\nclark@hotmail.com\n123\nNY\nTesting purposes\n\n");
	}
}
