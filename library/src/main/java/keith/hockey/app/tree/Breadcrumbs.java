package keith.hockey.app.tree;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Breadcrumbs {

    private static final int MAX_SIZE = 30;
    private static final int MAX_PAYLOAD_SIZE = 4096;

    private final Queue<Breadcrumb> store;
    private final int maxSize;
    private final int maxPayloadSize;

    public Breadcrumbs() {
        this(new ConcurrentLinkedQueue<Breadcrumb>(), MAX_SIZE, MAX_PAYLOAD_SIZE);
    }

    public Breadcrumbs(Queue<Breadcrumb> store, int maxSize, int maxPayloadSize) {
        this.store = store;
        this.maxSize = maxSize;
        this.maxPayloadSize = maxPayloadSize;
    }

    public void add(String message) {
        Breadcrumb breadcrumb = new Breadcrumb(message);
        add(breadcrumb);
    }

    void add(Breadcrumb breadcrumb) {
        if (breadcrumb.payloadSize() > maxPayloadSize) {
            return;
        }
        if (store.size() >= maxSize) {
            // Remove oldest breadcrumb
            store.poll();
        }
        store.add(breadcrumb);
    }

    public void clear() {
        store.clear();
    }

    @Override public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Breadcrumb breadcrumb : store) {
            stringBuilder.append(breadcrumb.toString());
        }
        return stringBuilder.toString();
    }
}
