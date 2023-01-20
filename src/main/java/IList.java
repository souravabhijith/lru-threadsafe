public interface IList {

    Node add(String key, String value);
    void update(Node node);
    void moveToHead(Node node);
    void remove(Node node);
    int getSize();
    String removeNodeAtLast();
}
