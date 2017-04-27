/**
 * Created by dhoy on 4/27/17.
 */
public class ContactInfo {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    ContactInfo (String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    String getName() {
        return name;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    String getEmailAddress() {
        return emailAddress;
    }
}
