package keith.hockey.app.tree;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import timber.log.Timber;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class BreadcrumbTreeTest {

    @Mock Breadcrumbs breadcrumbs;

    private BreadcrumbTree breadcrumbTree;

    @Before public void setUp() throws Exception {
        initMocks(this);
        breadcrumbTree = new BreadcrumbTree(Log.INFO, breadcrumbs);
        Timber.plant(breadcrumbTree);
    }

    @Test public void logsMessagesAndFiltersAppropriately() {
        Timber.v("verbose");
        Timber.d("debug");
        Timber.e("error");
        Timber.i("info");
        Timber.w("warn");

        verify(breadcrumbs).add("error");
        verify(breadcrumbs).add("info");
        verify(breadcrumbs).add("warn");
        verify(breadcrumbs, never()).add("debug");
        verify(breadcrumbs, never()).add("verbose");
    }
}
