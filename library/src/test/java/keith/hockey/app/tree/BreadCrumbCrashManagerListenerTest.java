package keith.hockey.app.tree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BreadCrumbCrashManagerListenerTest {

    @Test public void getDescriptionFetchesLogsFromBreadcrumbs() {
        String breadcrumbLogs = "Hello, yes I am logs";
        Breadcrumbs breadcrumbs = mock(Breadcrumbs.class);
        when(breadcrumbs.toString()).thenReturn(breadcrumbLogs);

        BreadCrumbCrashManagerListener breadCrumbCrashManagerListener = new
            BreadCrumbCrashManagerListener(breadcrumbs);

        assertEquals(breadcrumbLogs, breadCrumbCrashManagerListener.getDescription());
    }

}
