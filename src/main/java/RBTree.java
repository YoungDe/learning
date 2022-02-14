import java.util.TreeMap;

public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private RBNode<K, V> root;

    private void leftRotate(RBNode<K, V> node) {
        if (node != null) {
            RBNode<K, V> rightNode = node.right;
            node.right = rightNode.left;
            if (rightNode.left != null) {
                rightNode.left.parent = node;
            }
            rightNode.parent = node.parent;
            if (node.parent == null) {
                root = rightNode;
            } else if (node.parent.left == node) {
                node.parent.left = rightNode;
            } else {
                node.parent.right = rightNode;
            }

            rightNode.left = node;
            node.parent = rightNode;
        }
    }

    private void rightRotate(RBNode<K, V> node) {
        if (node != null) {
            RBNode<K, V> leftNode = node.left;
            node.left = leftNode.right;
            if (leftNode.right != null) {
                leftNode.right.parent = node;
            }
            leftNode.parent = node.parent;
            if (node.parent == null) {
                root = leftNode;
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
        RBNode<K, V> t = root;
        if (t == null) {
            root = new RBNode<>(key, value, null);
            return;
        }

        //1、插入节点(可以看成是在普通二叉树中插入)
        //   a.找到插入位置
        //   b.将节点插入父节点相应的位置
        int cmp;
        RBNode<K, V> parent;
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
                t.setValue(value);
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

        RBNode<K, V> node = new RBNode<>(key, value, parent);
        if (cmp < 0) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
        rebalanced(node);

    }

    public V remove(K k) {
        RBNode<K, V> t = getNode(k);
        if (t == null) {
            return null;
        }
        V oldValue = t.getValue();
        deleteNode(t);
        return oldValue;

    }

    private void deleteNode(RBNode<K, V> t) {
        if (t.left != null && t.right != null) {
            //用后继节点交换现有节点
            RBNode<K, V> p = successor(t);
            t.key = p.key;
            t.value = p.value;
            t = p;
        }

        RBNode<K, V> replacement = (t.left != null ? t.left : t.right);
        if (replacement != null) {
            //删除节点有一个子节点时的情况；
            replacement.parent = t.parent;
            if (t.parent == null) {
                root = replacement;
            } else if (t == leftOf(parentOf(t))) {
                t.parent.left = replacement;
            } else {
                t.parent.right = replacement;
            }

            t.left = t.right = t.parent = null;
            if (t.color == BLACK) {
                fixAfterDeletion(replacement);
            }
        } else if (t.parent == null) {
            root = null;
        } else {
            if (colorOf(t) == BLACK) {
                fixAfterDeletion(t);
            }

            if (t.parent != null) {
                if (t == leftOf(parentOf(t))) {
                    t.parent.left = null;
                } else if (t == rightOf(parentOf(t))) {
                    t.parent.right = null;
                }
            }
        }

    }

    private void fixAfterDeletion(RBNode<K, V> replacement) {

    }

    //获取后继节点
    private RBNode<K, V> successor(RBNode<K, V> t) {
        if (t == null) {
            return null;
        } else if (t.right != null) {
            RBNode<K, V> p = t.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else {
            RBNode<K, V> p = t.parent;
            RBNode<K, V> ch = t;
            while (p != null && ch == p.left) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    //获取前继节点
    private RBNode<K, V> predecessor(RBNode<K, V> t) {
        if (t == null) {
            return null;
        } else if (t.left != null) {
            RBNode<K, V> p = t.left;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        } else {
            RBNode<K, V> p = t.parent;
            RBNode<K, V> ch = t;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    public RBNode<K, V> getNode(K k) {
        if (k == null) {
            return null;
        }
        RBNode<K, V> t = root;
        while (t != null) {
            int cmp = k.compareTo(t.getKey());
            if (cmp < 0) {
                t = t.left;
            } else if (cmp > 0) {
                t = t.right;
            } else {
                return t;
            }
        }
        return null;
    }

    private void rebalanced(RBNode<K, V> node) {
        node.color = RED;

        while (node != null && node != root && node.parent.color == RED) {
            RBNode<K, V> grandPa = parentOf(parentOf(node));
            RBNode<K, V> parent = parentOf(node);
            if (parent == leftOf(grandPa)) {
                RBNode<K, V> uncle = rightOf(parentOf(parentOf(node)));
                if (colorOf(uncle) == RED) {//父红，叔红
                    setColor(uncle, BLACK);
                    setColor(rightOf(parentOf(parentOf(node))), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    node = grandPa;
                } else {//叔为黑
                    if (node == rightOf(parent)) {
                        node = parentOf(node);
                        leftRotate(node);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    rightRotate(parentOf(parentOf(node)));
                }
            } else {
                RBNode<K, V> uncle = leftOf(parentOf(parentOf(node)));
                if (colorOf(uncle) == RED) {
                    setColor(parentOf(node), BLACK);
                    setColor(uncle, BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    node = grandPa;
                } else {
                    if (node == leftOf(parent)) {
                        node = parentOf(node);
                        rightRotate(node);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    leftRotate(parentOf(parentOf(node)));
                }
            }
        }
        root.color = BLACK;
    }

    private void setColor(RBNode<K, V> node, boolean color) {
        if (node != null) {
            node.setColor(color);
        }
    }

    private RBNode<K, V> parentOf(RBNode<K, V> node) {
        return node == null ? null : node.parent;
    }

    private RBNode<K, V> leftOf(RBNode<K, V> node) {
        return node == null ? null : node.left;
    }

    private RBNode<K, V> rightOf(RBNode<K, V> node) {
        return node == null ? null : node.right;
    }

    private boolean colorOf(RBNode<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    static class RBNode<K extends Comparable<K>, V> {
        private RBNode<K, V> parent;
        private RBNode<K, V> left;
        private RBNode<K, V> right;
        private boolean color;
        private K key;
        private V value;

        public RBNode() {
        }

        public RBNode(K key, V value, RBNode<K, V> parent) {
            this.parent = parent;
            this.key = key;
            this.value = value;
        }

        public RBNode<K, V> getParent() {
            return parent;
        }

        public void setParent(RBNode<K, V> parent) {
            this.parent = parent;
        }

        public RBNode<K, V> getLeft() {
            return left;
        }

        public void setLeft(RBNode<K, V> left) {
            this.left = left;
        }

        public RBNode<K, V> getRight() {
            return right;
        }

        public void setRight(RBNode<K, V> right) {
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

    public static void main(String[] args) {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.insert(1, "1");
        tree.insert(2, "2");
        tree.insert(3, "3");
        tree.insert(4, "4");
        tree.insert(5, "5");
        tree.insert(6, "6");
        tree.insert(7, "7");
    }
}
