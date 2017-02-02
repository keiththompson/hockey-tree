package keith.hockey.app.tree;

import timber.log.Timber;

public class BreadcrumbTree extends Timber.Tree {

    private final int priority;
    private final Breadcrumbs breadcrumbs;

    public BreadcrumbTree(int priority, Breadcrumbs breadcrumbs) {
        this.priority = priority;
        this.breadcrumbs = breadcrumbs;
    }

    @Override protected void log(int priority, String tag, String message, Throwable t) {
        breadcrumbs.add(message);
    }

    @Override protected boolean isLoggable(String tag, int priority) {
        return priority >= this.priority;
    }
}
