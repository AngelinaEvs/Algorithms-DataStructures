import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class AVLTree<T extends Comparable> {
    private Node root;
    private int size;

    private class Node {
        private T data;
        private Node left;
        private Node right;
        private int h;

        private Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.h = 1;
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

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        @Override
        public String toString() {
            return (String) data;
        }
    }

    public AVLTree() {
        root = null;
        size = 0;
    }

    public void add(T data) {
        root = add(root, data);
    }

    private Node add(Node node, T data) {
        if (node == null) {
            size++;
            return (new Node(data));
        }
        if (data.compareTo(node.getData()) > 0) node.setRight(add(node.getRight(), data));
        else node.setLeft(add(node.getLeft(), data));
        node.setH(Integer.max(height(node.getLeft()), height(node.getRight())) + 1);
        int balance = balance(node);
        if (balance == -2 && (balance(node.getRight()) == -1 || balance(node.getRight()) == 0)) return smallLeftRotation(node);
        if (balance == -2 && balance(node.getRight()) == 1 && (balance(node.getRight().getLeft()) >= -1 && balance(node.getRight().getLeft()) <= 1)) {
            return bigLeftRotate(node);
        }
        if (balance == -2 && (balance(node.getLeft()) == -1 || balance(node.getLeft()) == 0)) return smallRightRotation(node);
        if (balance == -2 && balance(node.getLeft()) == 1 && (balance(node.getLeft().getRight()) >= -1 && balance(node.getLeft().getRight()) <= 1)) {
            return bigRightRotate(node);
        }
        return node;
    }

    private int balance(Node node) {
        if (node == null) return 0;
        return height(node.getLeft()) - height(node.getRight());
    }

    private int height(Node node) {
        if (node == null) return 0;
        return node.getH();
    }

    private Node smallLeftRotation(Node node) {
        Node otherNode = node.getRight();
        node.setRight(otherNode.getLeft());
        otherNode.setLeft(node);
        node.setH(Integer.max(height(node.getLeft()), height(node.getRight())) + 1);
        otherNode.setH(Integer.max(height(otherNode.getLeft()), height(otherNode.getRight())) + 1);
        return otherNode;
    }

    private Node bigLeftRotate(Node node) {
        node.setRight(smallRightRotation(node.getRight()));
        return smallLeftRotation(node);
    }

    private Node bigRightRotate(Node node) {
        node.setLeft(smallLeftRotation(node.getLeft()));
        return smallRightRotation(node);
    }

    private Node smallRightRotation(Node node) {
        Node otherNode = node.getLeft();
        node.setLeft(otherNode.getRight());
        otherNode.setRight(node);
        node.setH(Integer.max(height(node.getLeft()), height(node.getRight())) + 1);
        otherNode.setH(Integer.max(height(otherNode.getLeft()), height(otherNode.getRight())) + 1);
        return otherNode;
    }

    public void delete(T data) {
        root = delete(root, data);
    }

    private Node delete(Node node, T data) {
        if (node == null) throw new NullPointerException("You try to delete null");
        if (data.compareTo(node.getData()) > 0) node.setRight(delete(node.getRight(), data));
        else if (data.compareTo(node.getData()) < 0) node.setLeft(delete(node.getLeft(), data));
        else {
            if (node.getLeft() != null || node.getRight() != null) {
                Node n = minValueOfNode(node.getRight());
                node.setData(n.getData());
                node.setRight(delete(node.getRight(), n.getData()));
            }
            else if (node.getLeft() == null && node.getRight() == null) node = null;
            else {
                Node del;
                if (node.getLeft() == null) del = node.getRight();
                else del = node.getLeft();
                node = del;
            }
        }
        if (node == null) return null;
        node.setH(Integer.max(height(node.getLeft()), height(node.getRight())) + 1);
        int balance = balance(node);
        if (balance == -2 && (balance(node.getRight()) == -1 || balance(node.getRight()) == 0)) return smallLeftRotation(node);
        if (balance == -2 && balance(node.getRight()) == 1 && (balance(node.getRight().getLeft()) >= -1 && balance(node.getRight().getLeft()) <= 1)) {
            return bigLeftRotate(node);
        }
        if (balance == -2 && (balance(node.getLeft()) == -1 || balance(node.getLeft()) == 0)) return smallRightRotation(node);
        if (balance == -2 && balance(node.getLeft()) == 1 && (balance(node.getLeft().getRight()) >= -1 && balance(node.getLeft().getRight()) <= 1)) {
            return bigRightRotate(node);
        }
        return node;
    }

    public T minValueOfNode() {
        return minValueOfNode(root).getData();
    }

    public Node minValueOfNode(Node node) {
        Node n = node;
        while (n.getLeft() != null) {
            n = n.getLeft();
        }
        return n;
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
        return "AVLTree{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AVLTree<?> avlTree = (AVLTree<?>) o;
        return size == avlTree.size &&
                Objects.equals(root, avlTree.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, size);
    }
}
