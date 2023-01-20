import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache implements ICache {

    int capacity;
    LinkedList queue = new LinkedList();
    final Map<String, Node> map = new ConcurrentHashMap<>();
    CacheAsyncUpdater asyncUpdater;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        queue.maxSize = capacity;
        this.asyncUpdater = new CacheAsyncUpdater(map, queue);
    }

    CacheAsyncUpdater getAsyncUpdater() {
        return this.asyncUpdater;
    }

    @Override
    public String put(String key, String value) {
        asyncUpdater.addRequest(new CacheUpdateRequest(RequestType.PUT, key, value));
        return null;
    }

    @Override
    public String get(String key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            asyncUpdater.addRequest(new CacheUpdateRequest(RequestType.GET, key, null ));
            return node.getValue();
        } else {
            System.out.println("Element dosent exist " + key);
        }
        return "NOT_EXISTS";
    }

    @Override
    public boolean delete(String key) {
        if (map.containsKey(key)) {
            asyncUpdater.addRequest(new CacheUpdateRequest(RequestType.DELETE, key, null ));
            return true;
        } else {
            System.out.println("Element dosent exist " + key);
        }
        return false;
    }
}
