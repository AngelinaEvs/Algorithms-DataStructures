import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> {
    private Node head;
    private int size;

	private class Node {
		protected T data;
		protected Node next;

        public Node() {
            this.data = null;
            this.next = null;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(Node next) {
            this.data = null;
            this.next = next;
        }

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
	}

    public LinkedList() {
	    Node node = new Node();
        this.head = node;
        this.size = 0;
    }

    public LinkedList(LinkedList<T> linkedList) {
	    if (linkedList == null || linkedList.head == null) throw new NullPointerException();
	    head = new Node(linkedList.head.data);
	    size++;
	    Node node = head;
	    Node linkedListNode = linkedList.head;
	    while (linkedListNode.next != null){
	        node.next = new Node(linkedListNode.next.data);
	        node = node.next;
	        linkedListNode = linkedListNode.next;
	        size++;
        }
    }

    public boolean add(T el) {
        if (el == null) throw new NullPointerException("You entered null");
        Node node = head;
        Node newNode = new Node(el, null);
        if (size == 0) {
	        head = newNode;
	        newNode.data = el;
	        size++;
	        return true;
        }
	    while (node.next != null) {
	        node = node.next;
        }
	    node.next = newNode;
	    size++;
	    return true;
    }

    public void addFirst(T el) {
        Node node = head;
        Node newNode = new Node(el, node);
        head = newNode;
        size++;
    }

    public void addAfter(T el, int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) this.addFirst(el);
        else {
            Node node = head;
            for (int i = 1; i < index; i++) {
                node = node.next;
            }
            Node newNode = new Node(el, node.next);
            node.next = newNode;
            this.add(el);
        }
    }

    public T getFirst() {
        if (size == 0) throw new NoSuchElementException();
        Node node = head;
        return node.data;
    }

    public T getLast() {
        if (size == 0) throw new NoSuchElementException();
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        return node.data;
    }

    public int getSize() {
        return size;
    }

    public T get(int index) {
        if (index <0 || index >= size) throw new IndexOutOfBoundsException();
        Node node = new Node(getFirst(), head.next);
        if (index == 0) return node.data;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    public boolean remove(T el) {
        if (el == null) return false;
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (i == 0 && el.equals(node.data)) {
                head = node.next;
                size--;
                return true;
            }
            if (node.next.data.equals(el)) {
                node.next = node.next.next;
                size--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public boolean remove(int index) {
        if (index <0 || index >= size) throw new IndexOutOfBoundsException();
        Node node = head;
        if (index == 0) {
            head = node.next;
            size--;
            return true;
        }
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }
        node.next = node.next.next;
        size--;
        return true;
    }

    public LinkedList<T> merge(LinkedList<T> list) {
        if (list == null || list.size == 0) {
            return new LinkedList<>(this);
        }
        else {
            LinkedList<T> newList = new LinkedList<>();
            if (head != null) {
                newList.head = new Node(head.data);
                newList.size++;
                Node newListNode = newList.head;
                Node node = head;
                while (node.next != null) {
                    newListNode.next = new Node(node.next.data);
                    newListNode = newListNode.next;
                    node = node.next;
                    newList.size++;
                }
                newListNode.next = new Node(list.head.data);
                newListNode = newListNode.next;
                newList.size++;
                node = list.head;
                while (node.next != null) {
                    newListNode.next = new Node(node.next.data);
                    newListNode = newListNode.next;
                    node = node.next;
                    newList.size++;
                }
                return newList;
            }
            return new LinkedList<>(list);
        }
    }

    public boolean contains(T el) {
        if (head == null) return false;
        if (el == null) return false;
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (el.equals(node.data)) return true;
            node = node.next;
        }
        return false;
    }

    public int indexOf(T el) {
        if (head == null) return -1;
        if (el == null) return -1;
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (el.equals(node.data)) return i;
            node = node.next;
        }
        return -1;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        else return false;
    }

    @Override
    public String toString() {
        Node node = head;
        StringBuilder stringBuilder = new StringBuilder("{");
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(node.data);
            stringBuilder.append(", ");
            node = node.next;
        }
        stringBuilder.append(node.data);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedList<?> that = (LinkedList<?>) o;
        if (this.size != ((LinkedList<?>) o).size) return false;
        Node node = head;
        Node oNode = (Node) that.head;
       for (int i = 0; i < this.size; i++) {
            if (!node.data.equals(oNode.data)) return false;
            node = node.next;
            oNode = oNode.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
	    int res = Objects.hash(head, size);
	    if (size != 0) {
	        T[] elem = (T[]) new Object[size];
	        Node node = head;
	        for (int i = 0; i < size; i++) {
	            elem[i] = node.data;
	            node = node.next;
            }
	        res = 31 * res + Arrays.hashCode(elem);
        }
        return res;
    }
}