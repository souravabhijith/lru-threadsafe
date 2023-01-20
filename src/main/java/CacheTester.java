public class CacheTester {

    public static void main(String[] args) throws Exception {
        LRUCache lruCache = new LRUCache(2);
        System.out.println("Adding A as 1");
        lruCache.put("A", "1");
        System.out.println("Immediately Getting A gives value as " + lruCache.get("A"));
        Thread.sleep(1000);
        System.out.println("Getting after 1 sec A gives value as " + lruCache.get("A"));
        lruCache.put("B", "2");
        lruCache.put("C", "3");
        lruCache.put("D", "4");
        //System.out.println(lruCache.get("B"));
        System.out.println("Shutting down cache");
        Thread.sleep(10000);
        //lruCache.getAsyncUpdater().shutDown();
    }
}
