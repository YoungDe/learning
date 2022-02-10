public class Tree {

    private Node root;

    public void add(Node node) {
        if (root == null) {
            this.root = node;
        } else {
            root.add(node);
        }
    }

    public void DLR() {
        if (root != null) {
            root.DLR();
        } else {
            System.out.println("无法遍历空树");
        }
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public void delete(int value) {
        if (root == null) {
            return;
        } else {
            Node target = search(value);
            if (target == null) {
                return;
            }

            //如果只有一个根节点，直接删除这个节点
            if (root.getLeftNode() == null && root.getRightNode() == null) {
                root = null;
            }

            //查找目标节点的父节点
            Node targetParent = searchParent(value);
            //仅有一个节点的情况
            if (target.getRightNode() == null && target.getRightNode() == null) {
                if (targetParent.getLeftNode() != null && targetParent.getLeftNode().getValue() == value) {
                    targetParent.setLeftNode(null);
                }
                if (targetParent.getRightNode() != null && targetParent.getRightNode().getValue() == value) {
                    targetParent.setRightNode(null);
                }

            } else if (target.getLeftNode() != null && target.getRightNode() != null) {
                int minValue = deleteMin(target.getRightNode());
                target.setValue(minValue);
            } else {
                //目标节点有左子树
                if (target.getLeftNode() != null) {
                    if (targetParent != null) {
                        targetParent.setLeftNode(target.getLeftNode());
                    } else {
                        targetParent.setRightNode(target.getLeftNode());
                    }
                } else {
                    if (targetParent != null) {
                        if (targetParent.getLeftNode().getValue() == value) {
                            targetParent.setLeftNode(target.getRightNode());
                        } else {
                            targetParent.setRightNode(target.getRightNode());
                        }
                    } else {
                        root = target.getRightNode();
                    }
                }
            }
        }
    }

    public int deleteMin(Node node) {
        Node targetNode = node;
        while (targetNode.getLeftNode() != null) {
            targetNode = targetNode.getLeftNode();
            //targetNode 指向最小值
        }
        delete(targetNode.getValue());
        return targetNode.getValue();
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 6};
        Tree tree = new Tree();
        for (int i : arr) {
            tree.add(new Node(i));
        }
        tree.DLR();
//        tree.delete(6);
//        tree.DLR();
    }
}
