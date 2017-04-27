import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dhoy on 4/27/17.
 */
public class BusinessCardParser {
    public static final String JSON_PATH = "src/main/resources/BusinessCardParser.config";

    private Pattern phonePattern = Pattern.compile("\\d{3}\\D*\\d{3}\\D*\\d{4}");
    private Pattern emailPattern = Pattern.compile("(.+)@(.+)");

    private BusinessCardParserConfig businessCardParserConfig = null;

    /*
    * BusinessCardParser
    *
    * Creates BusinessCardParser and loads config file
    *
    * */
    BusinessCardParser () {
        businessCardParserConfig = getBusinessCardParserConfig();
    }

    /*
    * getBusinessCardParserConfig
    *
    * Loads JSON config file which contains filters for name, phone, and email lines
    *
    * */
    private BusinessCardParserConfig getBusinessCardParserConfig() {
        BusinessCardParserConfig config = null;
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(JSON_PATH));
            config = gson.fromJson(reader, BusinessCardParserConfig.class);
        }
        catch (Exception e) {
            config = new BusinessCardParserConfig();
        }
        return config;
    }

    /*
    * getContactInfo
    *
    * Parses business card string and returns contact info
    * */
    ContactInfo getContactInfo(String document) {
        List<String> nameLines = new ArrayList<String>();
        List<String> phoneLines = new ArrayList<String>();
        List<String> emailLines = new ArrayList<String>();

        BufferedReader reader = new BufferedReader(new StringReader(document));
        String line;
        try {
            // Loop through lines
            while((line = reader.readLine()) != null) {
                // Split lines into buckets
                Matcher emailMatcher = emailPattern.matcher(line);
                Matcher phoneMatcher = phonePattern.matcher(line);

                if (emailMatcher.find()) {
                    emailLines.add(line);
                }
                else if (phoneMatcher.find()) {
                    phoneLines.add(line);
                }
                else {
                    nameLines.add(line);
                }
            }
        } catch (IOException e) {
        }

        // Get email
        Email email = getEmail(emailLines);

        // Get name
        String name = getName(nameLines, email);

        // Get phone number
        String phone = getPhone(phoneLines);

        // Return contact info
        return new ContactInfo(name, phone, email.getEmailAddress());
    }

    /*
    * getName
    *
    * Finds name line from candidates and returns name
    *
    * */
    private String getName(List<String> nameLines, Email email) {
        String name = null;

        // Get name line filters
        String[] nameFilters = businessCardParserConfig.getNameFilters().toArray(new String[0]);

        // Loop through name line candidates
        for (String line : nameLines) {
            // Exclude lines the match name filters
            if (StringUtils.indexOfAny(line, nameFilters) == -1) {
                // Split into words
                for(String word : line.split("\\s+")) {
                    // Look for word in username
                    if (email.getUsername().toLowerCase().contains(word.toLowerCase())) {
                        // Get name
                        name = line;
                    }
                }
            }
        }

        return name;
    }

    /*
    * getPhone
    *
    * Finds phone line from candidates and returns phone number
    *
    * */
    private String getPhone(List<String> phoneLines) {
        String phone = null;

        // Get phone line filters
        String[] phoneFilters = businessCardParserConfig.getPhoneFilters().toArray(new String[0]);

        // Loop through phone line candidates
        for(String line : phoneLines) {
            // Exclude lines the match phone filters
            if (StringUtils.indexOfAny(line, phoneFilters) == -1) {
                // Get phone number
                phone = line.replaceAll("\\D+","");
            }
        }
        return phone;
    }

    /*
    * getEmail
    *
    * Finds email line from candidates and returns email info
    *
    * */
    private Email getEmail(List<String> emailLines) {
        String email;

        // Get email line filters
        String[] emailFilters = businessCardParserConfig.getEmailFilters().toArray(new String[0]);

        String username = null;
        String domain = null;
        // Loop through email line candidates
        for(String line : emailLines) {
            // Exclude lines the match email filters
            if (StringUtils.indexOfAny(line, emailFilters) == -1) {
                // Look for email pattern
                Matcher matcher = emailPattern.matcher(line);
                if (matcher.find()) {
                    // Get email
                    email = matcher.group(0);
                    // Get username
                    username = matcher.group(1);
                    // Get domain
                    domain = matcher.group(2);
                    // Return first valid email
                    return new Email(email, username, domain);
                }
            }
        }
        return new Email();
    }
}
