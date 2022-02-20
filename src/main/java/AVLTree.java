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

        public Node<K, V> getParent() {
            return parent;
        }

        public void setParent(Node<K, V> parent) {
            this.parent = parent;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node<K, V> leftNode) {
            this.leftNode = leftNode;
        }

        public Node<K, V> getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node<K, V> rightNode) {
            this.rightNode = rightNode;
        }
    }

    private Node<K, V> root;

    public Node<K, V> parentOf(Node<K, V> node) {
        return node == null ? null : node.getParent();
    }

    public Node<K, V> leftOf(Node<K, V> node) {
        return node == null ? null : node.getLeftNode();
    }

    public Node<K, V> rightOf(Node<K, V> node) {
        return node == null ? null : node.getRightNode();
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
                t.setValue(node.value);
            }
        } while (t != null);

        node.setParent(parent);
        if (cmp < 0) {
            parent.setLeftNode(node);
        } else {
            parent.setRightNode(node);
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
        return Math.max(node.leftNode == null ? 0 : height(node.leftNode), node.rightNode == null ? 0 : height(node.rightNode)) + 1;
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

            Node<K, V> rightNode = node.getRightNode();
            node.setRightNode(rightNode.getLeftNode());

            if (rightNode.leftNode != null) {
                rightNode.leftNode.parent = node;
            }
            rightNode.parent = node.parent;
            if (node.getParent() == null) {
                root = rightNode;
            } else if (leftOf(parentOf(node)) == node) {
                node.getParent().setLeftNode(rightNode);
            } else {
                node.getParent().setRightNode(rightNode);
            }
            rightNode.setLeftNode(node);
            node.parent = rightNode;
        }
    }

    public void rightRotate(Node<K, V> node) {
        if (node != null) {

            Node<K, V> leftNode = node.getLeftNode();
            node.setLeftNode(leftNode.getRightNode());

            if (leftNode.getRightNode() != null) {
                leftNode.rightNode.parent = node;
            }
            leftNode.parent = node.getParent();
            if (node.getParent() == null) {
                root = leftNode;
            } else if (leftOf(parentOf(node)) == node) {
                node.getParent().setLeftNode(leftNode);
            } else {
                node.getParent().setRightNode(leftNode);
            }

            leftNode.setRightNode(node);
            node.parent = leftNode;

        }
    }


    public static void main(String[] args) {
        int[] arr = {10, 5, 6, 2, 4, 8, 9, 15};
        AVLTree<Integer, String> tree = new AVLTree<>();
        for (int i : arr) {
            tree.add(new Node<>(i, String.valueOf(i)));
        }

    }
}
