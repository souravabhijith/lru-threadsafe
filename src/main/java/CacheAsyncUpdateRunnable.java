import java.util.Map;

public class CacheAsyncUpdateRunnable implements Runnable {

    CacheUpdateRequest request;
    LinkedList queue;
    Map<String, Node> map;


    CacheAsyncUpdateRunnable(CacheUpdateRequest cacheUpdateRequest, LinkedList queue, Map<String, Node> map) {
        request = cacheUpdateRequest;
        this.queue = queue;
        this.map = map;
    }

    @Override
    public void run() {
        synchronized (queue) {
            System.out.println("Processing " + request.toString());
            String key = request.key;
            String value = request.value;
            if (request.type == RequestType.PUT) {
                if (map.containsKey(key)) {
                    Node node = map.get(key);
                    node.setValue(value);
                    queue.moveToHead(node);
                } else {
                    Node node = queue.add(key, value);
                    map.put(key, node);
                    if (queue.getSize() > map.size()) {
                        String removedKey = queue.removeNodeAtLast();
                        if (removedKey != null) {
                            map.remove(removedKey);
                        }
                    }
                }
            } else {
               queue.moveToHead(map.get(request.key));
            }
        }
    }
}
