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
                    if (queue.getSize() > queue.maxSize) {
                        String removedKey = queue.removeNodeAtLast();
                        System.out.println("Removed key " + removedKey);
                        if (removedKey != null) {
                            map.remove(removedKey);
                        }
                    }
                }
            } else if (request.type == RequestType.GET ){
               queue.moveToHead(map.get(request.key));
            } else if (request.type == RequestType.DELETE) {
                queue.remove(map.get(key));
                map.remove(request.key);
            } else if (request.type == RequestType.CLEAR_LAST) {
                String removedKey = queue.removeNodeAtLast();
                System.out.println("Removed " + removedKey);
                map.remove(removedKey);
            }
        }
    }
}
