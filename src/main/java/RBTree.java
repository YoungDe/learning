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
        RBNode t = root;
        if (t == null) {
            root = new RBNode(key, value == null ? key : value, null);
            return;
        }

        //1、插入节点(可以看成是在普通二叉树中插入)
        //   a.找到插入位置
        //   b.将节点插入父节点相应的位置
        int cmp;
        RBNode parent;
        do {
            parent = t;
            cmp = key.compareTo((K) t.getKey());
            if (cmp < 0) {
                //加入的节点需要放在左子树
                t = t.left;
            } else if (cmp > 0) {
                //加入的节点需要放在右子树
                t = t.right;
            } else {
                //加入的节点已经存在，需要进行覆盖操作
                t.setValue(value == null ? key : value);
            }

        } while (t != null);
        //2、插入节点以后进行调整
        //红黑树具备一下性质：
        // 1、节点是红色或黑色；
        // 2、根节点是黑色；
        // 3、不能有连续的两个红色节点。
        // 4、从任一节点到其每个叶子的简单路径都包含相同数量的黑色节点。

        //变色操作：红黑树属于二叉搜索树，插入操作也与二叉搜索树一致，只不过红黑树在插入之后，多了平衡动作（旋转和变色）
        //新插入的节点均为红色，因为红色不会影响路径上黑色节点数量

        RBNode node = new RBNode(key, value == null ? key : value, parent);
        if (cmp < 0) {
            parent.setLeft(node);
        } else if (cmp > 0) {
            parent.setRight(node);
        }
        rebalanced(node);

    }

    private void rebalanced(RBNode node) {
        node.color = RED;

        while (node != null && node != root && node.parent.color == RED) {
            RBNode grandPa = parentOf(parentOf(node));
            RBNode parent = parentOf(node);
            if (parent == leftOf(grandPa)) {
                RBNode uncle = rightOf(node);
                if (colorOf(uncle) == RED) {//父红，叔红
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(grandPa, RED);
                    node = grandPa;
                } else {//叔为黑
                    if (node == rightOf(parent)) {
                        node = parent;
                        leftRotate(node);
                    }
                    setColor(parent, BLACK);
                    setColor(grandPa, RED);
                    rightRotate(grandPa);
                }
            } else {
                RBNode uncle = leftOf(node);
                if (colorOf(uncle) == RED) {
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(grandPa, RED);
                    node = grandPa;
                } else {
                    if (node == leftOf(parent)) {
                        node = parent;
                        rightRotate(node);
                    }
                    setColor(parent, BLACK);
                    setColor(grandPa, RED);
                    rightRotate(grandPa);
                }
            }
        }
    }

    private void setColor(RBNode node, boolean color) {
        if (node != null) {
            node.setColor(color);
        }
    }

    private RBNode parentOf(RBNode node) {
        return node == null ? null : node.parent;
    }

    private RBNode leftOf(RBNode node) {
        return node == null ? null : node.left;
    }

    private RBNode rightOf(RBNode node) {
        return node == null ? null : node.right;
    }

    private boolean colorOf(RBNode node) {
        return node == null ? BLACK : node.color;
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

        public boolean getColor() {
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
