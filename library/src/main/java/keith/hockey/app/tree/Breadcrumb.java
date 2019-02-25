package keith.hockey.app.tree;

import java.util.Date;

public final class Breadcrumb {

    private final String timestamp;
    private final String message;

    Breadcrumb(String message) {
        this(new Date(), message);
    }

    Breadcrumb(Date date, String message) {
        this(DateUtils.toISO8601(date), message);
    }

    Breadcrumb(String timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    @Override public String toString() {
        return timestamp + ": " + message;
    }

    int payloadSize() {
        return toString().length();
    }

    String getMessage() {
        return message;
    }
}
