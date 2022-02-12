public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private RBNode root;

    private void leftRotate(RBNode node) {
        if (node != null) {
            RBNode rightNode = node.right;
            node.right = rightNode.left;
            if (rightNode.left != null) {
                rightNode.left.parent = node;
            }
            rightNode.parent = node.parent;
            if (node.parent == null) {
                root = node;
            } else if (node.parent.left == node) {
                node.parent.left = rightNode;
            } else {
                node.parent.right = rightNode;
            }

            rightNode.left = node;
            node.parent = rightNode;
        }
    }

    private void rightRotate(RBNode node) {
        if (node != null) {
            RBNode leftNode = node.left;
            node.left = leftNode.right;
            if (leftNode.right != null) {
                leftNode.right.parent = node;
            }
            leftNode.parent = node.parent;
            if (node.parent == null) {
                root = node;
            } else if (node.parent.left == node) {
                node.parent.left = leftNode;
            } else {
                node.parent.right = leftNode;
            }

            leftNode.right = node;
            node.parent = leftNode;
        }
    }

    public void insert(K key, V value) {
        RBNode rootNode = root;
        if (rootNode == null) {
            root = new RBNode(key, value == null ? key : value, null);
            return;
        }
        //1、插入节点(可以看成是在普通二叉树中插入)
        //   a.找到插入位置
        //   b.将节点插入父节点相应的位置
        //2、插入节点以后进行调整

        int cmp;
        RBNode parent;
        do {
            parent = rootNode;
            key.compareTo((K) rootNode.getKey());
        } while (rootNode != null);

    }


    static class RBNode<K extends Comparable<K>, V> {
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private K key;
        private V value;

        public RBNode() {
        }

        public RBNode(K key, V value, RBNode parent) {
            this.parent = parent;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
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
    }
}
