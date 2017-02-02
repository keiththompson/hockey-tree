package keith.hockey.app.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BreadcrumbsTest {

    private final Queue<Breadcrumb> store = new ConcurrentLinkedQueue<>();
    private final int maxSize = 10;
    private final int maxPayloadSize = 4096;

    private Breadcrumbs breadcrumbs;

    @Before public void setUp() throws Exception {
        store.clear();
        breadcrumbs = new Breadcrumbs(store, maxSize, maxPayloadSize);
    }

    @Test public void ignoresMessagesExceedingMaxPayloadSize() {
        Breadcrumb breadcrumb = mock(Breadcrumb.class);
        when(breadcrumb.payloadSize()).thenReturn(maxPayloadSize + 1);

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

    private static final String SOUP_CRIMP = "1234: Soup, soup, a tasty, soup soup,1234: spicy " +
        "carrot and coriander1234: [Chilli Chowder!]1234: CRUTON CRUTON!1234: Crunchy friends in a " +
        "liquid broth!1234: I am gazpacho !1234: I am a summer soup oh!1234: Miso, Miso,1234: " +
        "Fighting in the Dojo,1234: Oriental prince in the land of SOUP!";
}
