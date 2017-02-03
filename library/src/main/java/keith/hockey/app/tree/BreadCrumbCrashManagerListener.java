package keith.hockey.app.tree;

import net.hockeyapp.android.CrashManagerListener;

public class BreadCrumbCrashManagerListener extends CrashManagerListener {

    private final Breadcrumbs breadcrumbs;

    public BreadCrumbCrashManagerListener(Breadcrumbs breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }

    @Override public String getDescription() {
        return breadcrumbs.toString();
    }

}
