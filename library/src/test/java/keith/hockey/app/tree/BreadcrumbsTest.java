package keith.hockey.app.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.*;

public class BreadcrumbsTest {

    private final Queue<Breadcrumb> store = new ConcurrentLinkedQueue<>();
    private final int maxSize = 10;

    private Breadcrumbs breadcrumbs;

    @Before public void setUp() throws Exception {
        store.clear();
        breadcrumbs = new Breadcrumbs(store, maxSize, 4096);
    }

    @Test public void ignoresMessagesExceedingMaxPayloadSize() {
        String message = "This is a test message";
        breadcrumbs = new Breadcrumbs(store, maxSize, message.length() - 1);
        Breadcrumb breadcrumb = new Breadcrumb(new Date(), message);

        breadcrumbs.add(breadcrumb);

        assertTrue(store.isEmpty());
    }

    @Test public void ensureStoreMaintainsAMaxSize() {
        for (int i = 0; i < 20; i++) {
            breadcrumbs.add(new Breadcrumb("Message:" + i));
        }

        Breadcrumb crouton = new Breadcrumb("My crouton");
        breadcrumbs.add(crouton);

        assertTrue(store.contains(crouton));
        assertEquals(maxSize, store.size());
    }

    @Test public void addingStringCreatesBreadcrumb() {
        String message = "My crouton";
        breadcrumbs.add(message);

        Breadcrumb breadcrumb = store.poll();

        assertEquals(message, breadcrumb.getMessage());
    }

    @Test public void clearsBreadcrumbs() {
        breadcrumbs.add("Crouton crouton");
        assertFalse(store.isEmpty());

        breadcrumbs.clear();
        assertTrue(store.isEmpty());
    }

    @Test public void breadcrumbsPrintsLogs() {
        String time = "1234";
        addBreadcrumbWithTime(time, "Soup, soup, a tasty, soup soup,");
        addBreadcrumbWithTime(time, "spicy carrot and coriander");
        addBreadcrumbWithTime(time, "[Chilli Chowder!]");
        addBreadcrumbWithTime(time, "CRUTON CRUTON!");
        addBreadcrumbWithTime(time, "Crunchy friends in a liquid broth!");
        addBreadcrumbWithTime(time, "I am gazpacho !");
        addBreadcrumbWithTime(time, "I am a summer soup oh!");
        addBreadcrumbWithTime(time, "Miso, Miso,");
        addBreadcrumbWithTime(time, "Fighting in the Dojo,");
        addBreadcrumbWithTime(time, "Oriental prince in the land of SOUP!");

        assertEquals(SOUP_CRIMP, breadcrumbs.toString());
    }

    private void addBreadcrumbWithTime(String time, String breadcrumb) {
        breadcrumbs.add(new Breadcrumb(time, breadcrumb));
    }

    private static final String SOUP_CRIMP = "1234: Soup, soup, a tasty, soup soup,\n1234: spicy " +
        "carrot and coriander\n1234: [Chilli Chowder!]\n1234: CRUTON CRUTON!\n1234: Crunchy friends " +
        "in a liquid broth!\n1234: I am gazpacho !\n1234: I am a summer soup oh!\n1234: Miso, " +
        "Miso,\n1234: Fighting in the Dojo,\n1234: Oriental prince in the land of SOUP!\n";
}
