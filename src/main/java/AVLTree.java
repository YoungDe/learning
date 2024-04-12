public class AVLTree<K extends Comparable<K>, V> {

    static class Node<K extends Comparable<K>, V> {

        private Node<K, V> parent;

        private K key;

        private V value;

        private Node<K, V> leftNode;

        private Node<K, V> rightNode;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V> root;

    public Node<K, V> parentOf(Node<K, V> node) {
        return node == null ? null : node.parent;
    }

    public Node<K, V> leftOf(Node<K, V> node) {
        return node == null ? null : node.leftNode;
    }

    public Node<K, V> rightOf(Node<K, V> node) {
        return node == null ? null : node.rightNode;
    }

    public void add(Node<K, V> node) {
        int cmp;
        Node<K, V> t = root;
        if (t == null) {
            root = node;
            return;
        }
        Node<K, V> parent;
        do {
            parent = t;
            cmp = node.key.compareTo(t.key);
            if (cmp < 0) {
                t = t.leftNode;
            } else if (cmp > 0) {
                t = t.rightNode;
            } else {
                t.value = node.value;
            }
        } while (t != null);

        node.parent = parent;
        if (cmp < 0) {
            parent.leftNode = node;
        } else {
            parent.rightNode = node;
        }

        while (node != null) {
            node = parentOf(node);
            rebalanced(node);
        }

    }

    private void rebalanced(Node<K, V> node) {
        if (node != null) {
            if (leftHeight(node) - rightHeight(node) > 1) {
                if (rightHeight(node.leftNode) > leftHeight(node.leftNode))
                    leftRotate(node.leftNode);
                rightRotate(node);
            }
            if (rightHeight(node) - leftHeight(node) > 1) {
                if (leftHeight(node.rightNode) > rightHeight(node.rightNode))
                    rightRotate(node.rightNode);
                leftRotate(node);
            }
        }
    }

    public int leftHeight(Node<K, V> node) {
        if (node.leftNode == null) {
            return 0;
        } else {
            return height(node.leftNode);
        }
    }

    private int height(Node<K, V> node) {
        return Math.max(node.leftNode == null ? 0 : height(node.leftNode),
                node.rightNode == null ? 0 : height(node.rightNode)) + 1;
    }

    public int rightHeight(Node<K, V> node) {
        if (node.rightNode == null) {
            return 0;
        } else {
            return height(node.rightNode);
        }
    }

    public void leftRotate(Node<K, V> node) {
        if (node != null) {

            Node<K, V> rightNode = node.rightNode;
            node.rightNode = rightNode.leftNode;

            if (rightNode.leftNode != null) {
                rightNode.leftNode.parent = node;
            }
            rightNode.parent = node.parent;
            if (node.parent == null) {
                root = rightNode;
            } else if (leftOf(parentOf(node)) == node) {
                node.parent.leftNode = rightNode;
            } else {
                node.parent.rightNode = rightNode;
            }
            rightNode.leftNode = node;
            node.parent = rightNode;
        }
    }

    public void rightRotate(Node<K, V> node) {
        if (node != null) {

            Node<K, V> leftNode = node.leftNode;
            node.leftNode = leftNode.rightNode;

            if (leftNode.rightNode != null) {
                leftNode.rightNode.parent = node;
            }
            leftNode.parent = node.parent;
            if (node.parent == null) {
                root = leftNode;
            } else if (leftOf(parentOf(node)) == node) {
                node.parent.leftNode = leftNode;
            } else {
                node.parent.rightNode = leftNode;
            }

            leftNode.rightNode = node;
            node.parent = leftNode;

        }
    }

    public static void main(String[] args) {
        int[] arr = { 10, 5, 6, 2, 4, 8, 9, 15 };
        AVLTree<Integer, String> tree = new AVLTree<>();
        for (int i : arr) {
            tree.add(new Node<>(i, String.valueOf(i)));
        }

    }
}
