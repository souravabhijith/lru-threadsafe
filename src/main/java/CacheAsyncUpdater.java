import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class CacheAsyncUpdater {

    ExecutorService executorService = Executors.newFixedThreadPool(1);
    LinkedList linkedList;
    Map<String, Node> map;

    CacheAsyncUpdater(Map<String, Node> map, LinkedList linkedList) {
        this.linkedList = linkedList;
        this.map = map;
    }

    public void addRequest(CacheUpdateRequest cacheUpdateRequest){
        executorService.execute(new CacheAsyncUpdateRunnable(cacheUpdateRequest, linkedList, map));
    }

    public void shutDown() {
        executorService.shutdown();
    }



}
