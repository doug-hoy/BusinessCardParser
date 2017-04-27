import java.util.*;

/**
 * Created by dhoy on 4/27/17.
 */
public class BusinessCardParserConfig {
    private List<String> nameFilters = new ArrayList<String>();
    private List<String> phoneFilters = new ArrayList<String>();
    private List<String> emailFilters = new ArrayList<String>();

    public List<String> getNameFilters() {
        return nameFilters;
    }

    public void setNameFilters(List<String> nameFilters) {
        this.nameFilters = nameFilters;
    }

    public List<String> getPhoneFilters() {
        return phoneFilters;
    }

    public void setPhoneFilter(List<String> phoneFilters) {
        this.phoneFilters = phoneFilters;
    }

    public List<String> getEmailFilters() {
        return emailFilters;
    }

    public void setEmailFilters(List<String> emailFilters) {
        this.emailFilters = emailFilters;
    }
}
