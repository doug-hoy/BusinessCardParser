/**
 * Created by dhoy on 4/26/17.
 */
public class Email {
    private String emailAddress;
    private String username;
    private String domain;

    Email()
    {
        this.emailAddress = "";
        this.username = "";
        this.domain = "";
    }

    Email (String emailAddress, String username, String domain) {
        this.emailAddress = emailAddress;
        this.username = username;
        this.domain = domain;
    }

    String getEmailAddress() {
        return emailAddress;
    }

    String getUsername() {
        return username;
    }

    String getDomain() {
        return domain;
    }
}
