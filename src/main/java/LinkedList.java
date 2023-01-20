public class LinkedList implements IList {
    Node head;
    Node tail;
    int size = 0;

    @Override
    public Node add(String key, String value) {
        Node node = new Node(key, value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
            moveToHead(node);
        }
        this.size+=1;
        return node;
    }

    @Override
    public void update(Node node) {
        moveToHead(node);
    }

    @Override
    public void moveToHead(Node node) {
        if (node == null) {
            return;
        }
        if (tail == node) {
            System.out.println("Node val " + node.key);
            node.prev.next = node.next;
            tail = tail.prev;
        } else {
            //H->A->B->C->D
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        node.next = head;
        head.prev = node;
        head = node;
    }

    @Override
    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public String removeNodeAtLast() {
        if (tail == null) {
            return null;
        }
        String key = tail.getValue();
        tail = tail.prev;
        return key;
    }
}
