package addressbook;



/**
 * AddressEntry which represents a Contact
 * AddressEntries are immutable. They cannot be changed once they have been created
 * 
 * @author Arturo Muller 
 * @version 1.0 June 13, 2016
 */
public class AddressEntry {
  private final String name;
  private final String postalAddress;
  private final String phoneNumber;
  private final String email;
  private final String note;

  public static class Builder {
    
    // Required parameters
    private final String name;

    // Optional parameters
    private String postalAddress = "";
    private String phoneNumber = "";
    private String email = "";
    private String note = "";

    /**
     * Constructs and initializes an AddressEntry with the mandatory name field
     * @param name name of contact
     */
    public Builder(String name) {
      this.name = name;
    }
    
    /**
     * adds the postalAddress field to an AddressEntry
     * @param postalAddress postalAddress of contact
     * @return Returns a reference to the AddressEntry being constructed.t 
     */
    public Builder postalAddress(String postalAddress) {
      this.postalAddress = postalAddress;
      return this;
    }

    /**
     * adds the phone number field to an AddressEntry
     * @param phoneNumber phoneNumber of contact
     * @return Returns a reference to the AddressEntry being constructed.
     */
    public Builder phoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    /**
     * adds the email field to an AddressEntry
     * @param email email of contact
     * @return Returns a reference to the AddressEntry being constructed.
     */
    public Builder email(String email) {
      this.email = email;
      return this;
    }

    /**
     * adds a note field to an AddressEntry
     * @param email email of contact
     * @return Returns a reference to the AddressEntry being constructed.
     */
    public Builder note(String note) {
      this.note = note;
      return this;
    }

    /**
     * @return Returns a reference to the AddressEntry being constructed. 
     */
    public AddressEntry build() {
      return new AddressEntry(this);
    }
  }

  private AddressEntry(Builder builder) {
    name = builder.name;
    postalAddress = builder.postalAddress;
    phoneNumber = builder.phoneNumber;
    email = builder.email;
    note = builder.note;
  }

  public String getName() {
    return name;
  }

  public String getPostalAddress() {
    return postalAddress;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getNote() {
    return note;
  }

}
