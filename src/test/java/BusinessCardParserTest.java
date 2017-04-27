import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dhoy on 4/27/17.
 */
public class BusinessCardParserTest {

    @Test
    public void testGetContactInfo1() throws Exception {
        BusinessCardParser parser = new BusinessCardParser();

        String test =
                "ASYMMETRIK LTD\n" +
                "Mike Smith\n" +
                "Senior Software Engineer\n" +
                "(410)555-1234\n" +
                "msmith@asymmetrik.com";

        ContactInfo info = parser.getContactInfo(test);
        Assert.assertEquals("Mike Smith", info.getName());
        Assert.assertEquals("4105551234", info.getPhoneNumber());
        Assert.assertEquals("msmith@asymmetrik.com", info.getEmailAddress());

        System.out.println();
        System.out.println(info.getName());
        System.out.println(info.getPhoneNumber());
        System.out.println(info.getEmailAddress());
    }

    @Test
    public void testGetContactInfo2() throws Exception {
        BusinessCardParser parser = new BusinessCardParser();

        String test =
                "Foobar Technologies\n" +
                "Analytic Developer\n" +
                "Lisa Haung\n" +
                "1234 Sentry Road\n" +
                "Columbia, MD 12345\n" +
                "Phone: 410-555-1234\n" +
                "Fax: 410-555-4321\n" +
                "lisa.haung@foobartech.com";

        ContactInfo info = parser.getContactInfo(test);
        Assert.assertEquals("Lisa Haung", info.getName());
        Assert.assertEquals("4105551234", info.getPhoneNumber());
        Assert.assertEquals("lisa.haung@foobartech.com", info.getEmailAddress());

        System.out.println();
        System.out.println(info.getName());
        System.out.println(info.getPhoneNumber());
        System.out.println(info.getEmailAddress());
    }

    @Test
    public void testGetContactInfo3() throws Exception {
        BusinessCardParser parser = new BusinessCardParser();

        String test =
                "Arthur Wilson\n" +
                "Software Engineer\n" +
                "Decision & Security Technologies\n" +
                "ABC Technologies\n" +
                "123 North 11th Street\n" +
                "Suite 229\n" +
                "Arlington, VA 22209\n" +
                "Tel: +1 (703) 555-1259\n" +
                "Fax: +1 (703) 555-1200\n" +
                "awilson@abctech.com";

        ContactInfo info = parser.getContactInfo(test);
        Assert.assertEquals("Arthur Wilson", info.getName());
        Assert.assertEquals("17035551259", info.getPhoneNumber());
        Assert.assertEquals("awilson@abctech.com", info.getEmailAddress());

        System.out.println();
        System.out.println(info.getName());
        System.out.println(info.getPhoneNumber());
        System.out.println(info.getEmailAddress());
    }
}