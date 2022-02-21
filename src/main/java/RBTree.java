public class RBTree<K extends Comparable<K>, V> {

    private final static boolean BLACK = true;

    private final static boolean RED = false;

    private Node<K, V> root;

    static class Node<K, V> {

        private Node<K, V> parent;

        private Node<K, V> left;

        private Node<K, V> right;

        private K key;

        private V value;

        private boolean color = BLACK;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

    }

    public Node<K, V> leftOf(Node<K, V> node) {
        return node == null ? null : node.left;
    }

    public Node<K, V> rightOf(Node<K, V> node) {
        return node == null ? null : node.right;
    }

    public boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    public void setColor(Node<K, V> node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    public Node<K, V> parentOf(Node<K, V> node) {
        return node == null ? null : node.parent;
    }

    private void leftRotate(Node<K, V> node) {
        if (node != null) {
            Node<K, V> r = node.right;
            node.right = r.left;
            if (r.left != null) {
                r.left.parent = node;
            }

            r.parent = node.parent;
            if (node.parent == null) {
                root = r;
            } else if (leftOf(parentOf(node)) == node) {
                node.parent.left = r;
            } else {
                node.parent.right = r;
            }
            r.left = node;
            node.parent = r;
        }
    }

    private void rightRotate(Node<K, V> node) {
        if (node != null) {
            Node<K, V> l = node.left;
            node.left = l.right;
            if (l.right != null) {
                l.right.parent = node;
            }

            l.parent = node.parent;
            if (node.parent == null) {
                root = l;
            } else if (leftOf(parentOf(node)) == node) {
                node.parent.left = l;
            } else {
                node.parent.right = l;
            }
            l.right = node;
            node.parent = l;
        }
    }

    public Node<K, V> getNode(K key) {
        if (key != null) {
            Node<K, V> p = root;
            while (p != null) {
                int cmp = key.compareTo(p.key);
                if (cmp < 0) {
                    p = p.left;
                } else if (cmp > 0) {
                    p = p.right;
                } else {
                    return p;
                }
            }
            return null;
        }
        return null;
    }

    public Node<K, V> successor(Node<K, V> node) {
        if (node != null) {
            if (node.right != null) {
                Node<K, V> p = node.right;
                while (p != null) {
                    p = p.left;
                }
                return p;
            } else {
                Node<K, V> p = node.parent;
                Node<K, V> ch = node;
                while (p != null && ch == p.right) {
                    ch = p;
                    p = p.parent;
                }
            }
        }
        return null;
    }

    public void insert(K key, V value) {
        if (root == null) {
            root = new Node<K, V>(key, value, null);
            return;
        }

        Node<K, V> p = root;
        Node<K, V> parent;
        int cmp;
        do {
            parent = p;
            cmp = key.compareTo(p.key);
            if (cmp < 0) {
                p = p.left;
            } else if (cmp > 0) {
                p = p.right;
            } else {
                p.value = value;
            }
        } while (p != null);

        Node<K, V> node = new Node<K, V>(key, value, parent);
        if (cmp < 0) {
            parent.left = node;
        } else if (cmp > 0) {
            parent.right = node;
        }
        fixAfterInsert(node);

    }

    public V remove(K key) {
        Node<K, V> node = getNode(key);
        if (node == null) {
            return null;
        }
        V oldValue = node.value;
        delete(node);
        return oldValue;
    }

    private void delete(Node<K, V> node) {
        if (node.left != null && node.right != null) {
            Node<K, V> s = successor(node);
            node.key = s.key;
            node.value = s.value;
            node = s;
        }
        Node<K, V> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (leftOf(parentOf(node)) == node) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            node.left = node.right = node.parent = null;
            if (colorOf(node) == BLACK) {
                fixAfterDeletion(replacement);
            }

        } else if (node.parent == null) {
            // 插入了根节点，然后就删除根节点
            root = null;
        } else {
            if (colorOf(node) == BLACK) {
                fixAfterDeletion(node);
            }
            if (node.parent != null) {
                if (leftOf(parentOf(node)) == node) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
            }
        }

    }

    private void fixAfterDeletion(Node<K, V> node) {
        while (node != root && colorOf(node) == BLACK) {
            if (leftOf(parentOf(node)) == node) {
                // 删除结点是黑色，如果删除结点的兄弟结点是红色,必有两个黑色结点，转换成黑色的情况
                Node<K, V> sibling = rightOf(parentOf(node));
                if (colorOf(sibling) == RED) {
                    setColor(sibling, BLACK);
                    setColor(parentOf(node), RED);
                    leftRotate(parentOf(node));
                    sibling = rightOf(parentOf(node));
                }
                if (colorOf(leftOf(sibling)) == BLACK && colorOf(rightOf(sibling)) == BLACK) {
                    setColor(sibling, RED);
                    node = parentOf(node);
                } else {
                    if (colorOf(leftOf(sibling)) == RED) {
                        setColor(leftOf(sibling), BLACK);
                        setColor(sibling, RED);
                        rightRotate(sibling);
                        sibling = rightOf(parentOf(node));
                    }
                    setColor(sibling, colorOf(parentOf(node)));
                    setColor(parentOf(node), BLACK);
                    setColor(rightOf(sibling), BLACK);
                    leftRotate(parentOf(node));
                    break;
                }
            } else {
                // 删除结点是黑色，如果删除结点的兄弟结点是红色,必有两个黑色结点，转换成黑色的情况
                Node<K, V> sibling = leftOf(parentOf(node));
                if (colorOf(sibling) == RED) {
                    setColor(sibling, BLACK);
                    setColor(parentOf(node), RED);
                    rightRotate(parentOf(node));
                    sibling = leftOf(parentOf(node));
                }
                if (colorOf(leftOf(sibling)) == BLACK && colorOf(rightOf(sibling)) == BLACK) {
                    setColor(sibling, RED);
                    node = parentOf(node);
                } else {
                    if (colorOf(rightOf(sibling)) == RED) {
                        setColor(rightOf(sibling), BLACK);
                        setColor(sibling, RED);
                        leftRotate(sibling);
                        sibling = leftOf(parentOf(node));
                    }
                    setColor(sibling, colorOf(parentOf(node)));
                    setColor(parentOf(node), BLACK);
                    setColor(leftOf(sibling), BLACK);
                    rightRotate(parentOf(node));
                    break;
                }
            }
        }
        node.color = BLACK;

    }

    private void fixAfterInsert(Node<K, V> node) {
        node.color = RED;
        while (node != root && colorOf(node.parent) == RED) {
            // 加入插入的是红色结点，且叔结点为
            Node<K, V> parent = parentOf(node);
            if (leftOf(parentOf(parent)) == parent) {
                Node<K, V> uncle = rightOf(parentOf(parent));
                if (colorOf(uncle) == RED) {
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(parentOf(parent), RED);
                    node = parentOf(parentOf(node));
                } else {
                    // 叔为黑,看差人结点是父结点的左子树还是右子树，不在同一边，先旋转到同一边再再父结点做旋转。同坐右旋。同右左旋。
                    if (colorOf(rightOf(parent)) == RED) {
                        node = parentOf(node);
                        leftRotate(node);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    rightRotate(parentOf(parentOf(node)));
                }
            } else {
                Node<K, V> uncle = leftOf(parentOf(parent));
                if (colorOf(uncle) == RED) {
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(parentOf(parent), RED);
                    node = parentOf(parentOf(node));
                } else {
                    if (colorOf(leftOf(parent)) == RED) {
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

    public static void main(String[] args) {
        int[] arr = { 11, 14, 6, 1, 3, 9 };
        RBTree<Integer, String> tree = new RBTree<>();

        for (int i : arr) {
            tree.insert(i, String.valueOf(i));
        }
        tree.remove(22);

    }

}
