package keith.hockey.app.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BreadcrumbTest {

    private final String timestamp = "2017-02-01'T'14:17:00'Z'";
    private final String message = "I am breadcrumb";

    private Breadcrumb breadcrumb;

    @Before public void setUp() throws Exception {
        breadcrumb = new Breadcrumb(timestamp, message);
    }

    @Test public void breadcrumbString() {
        assertEquals(timestamp + ": " + message, breadcrumb.toString());
    }

    @Test public void breadcrumbPayloadSize() {
        assertEquals(41, breadcrumb.payloadSize());
    }

}
