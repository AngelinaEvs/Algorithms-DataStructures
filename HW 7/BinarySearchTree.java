import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class BinarySearchTree<T extends Comparable> {
    private Node root;
    private int size;

    private class Node {
        private Node parent;
        private T data;
        private Node left;
        private Node right;

        private Node(Node parent, T data) {
            this.parent = parent;
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return (String) data;
        }
    }

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public void add(T data) {
        if (data != null) {
            if (root != null) {
                add(root, data);
            }
            else {
                root = new Node(null, data);
                size++;
            }
        } else {
            throw new NullPointerException("You  entered the null data");
        }
    }

    private void add(Node node, T data) {
        if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new Node(node, data));
                size++;
            } else add(node.getRight(), data);
        } else {
            if (node.getLeft() == null) {
                node.setLeft(new Node(node,  data));
                size++;
            } else add(node.getLeft(), data);
        }
    }

    public int size() {
        return size;
    }

    public void dfs() {
        dfs(root);
    }

    private void dfs(Node node) {
        if (node != null) {
            dfs(node.getLeft());
            System.out.print(node.getData().toString() + " ");
            dfs(node.getRight());
        }
    }

    public void bfs() {
        Collection<Node> nodes = new ArrayList<>();
        nodes.add(root);
        childrenBFS(nodes);
    }

    private void childrenBFS(Collection<Node> nodes) {
        Collection<Node> children = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.getData().toString() + " ");
                if (node.getLeft() != null) children.add(node.getLeft());
                if (node.getRight() != null) children.add(node.getRight());
            }
        }
        if (children.size() > 0) childrenBFS(children);
    }

    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinarySearchTree<?> that = (BinarySearchTree<?>) o;
        return size == that.size &&
                Objects.equals(root, that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, size);
    }
}
