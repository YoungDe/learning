public class Node {

    private int value;

    private Node leftNode;

    private Node rightNode;

    public Node(int value) {
        this.value = value;
    }

    public void leftRotate() {
        Node newNode = new Node(value);
        newNode.setLeftNode(leftNode);
        newNode.setRightNode(rightNode.getLeftNode());
        setValue(rightNode.getValue());
        setRightNode(rightNode.getRightNode());
        setLeftNode(newNode);
    }

    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.setRightNode(rightNode);
        newNode.setLeftNode(leftNode.getRightNode());
        setValue(leftNode.getValue());
        setRightNode(newNode);
        setLeftNode(leftNode.getLeftNode());
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.getValue() < value) {
            if (leftNode == null) {
                leftNode = node;
            } else {
                leftNode.add(node);
            }
        }

        if (node.getValue() > value) {
            if (rightNode == null) {
                rightNode = node;
            } else {
                rightNode.add(node);
            }
        }

        if (leftHeight() - rightHeight() > 1) {
            if (leftNode != null && leftNode.rightHeight() > leftNode.leftHeight()) {
                leftNode.leftRotate();
            }
            rightRotate();
            return;
        }

        if (rightHeight() - leftHeight() > 1) {
            if (rightNode != null && rightNode.leftHeight() > rightNode.rightHeight()) {
                rightNode.rightRotate();
            }
            leftRotate();
        }
    }

    public int leftHeight() {
        if (leftNode == null) {
            return 0;
        } else {
            return leftNode.height();
        }
    }

    public int rightHeight() {
        if (rightNode == null) {
            return 0;
        } else {
            return rightNode.height();
        }
    }

    public int height() {
        return Math.max(leftNode == null ? 0 : leftNode.height(), rightNode == null ? 0 : rightNode.height()) + 1;
    }


    /**
     * 二叉树删除节点的时候有三种情况
     * 1、删除叶子节点
     * 1、找到要删除的节点
     * 2、直接删除节点，把父节点的next指向空（判断next是左右的那个指针）
     * 2、删除只有一棵子树的节点
     * 3、删除有两棵子树的节点
     *
     * @param value
     * @return
     */

    //找到目标节点
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {
            if (this.leftNode == null) {
                return null;
            }
            return this.leftNode.search(value);
        } else {
            if (this.rightNode == null) {
                return null;
            }
            return this.rightNode.search(value);
        }
    }

    //找到目标节点的父节点
    public Node searchParent(int value) {
        if ((this.leftNode != null && this.leftNode.value == value) || (this.rightNode != null && this.rightNode.value == value)) {
            return this;
        } else {
            if (value < this.value && this.leftNode != null) {
                return this.leftNode.searchParent(value);
            } else if (value >= this.value && this.rightNode != null) {
                return this.rightNode.searchParent(value);
            } else {
                System.out.println("该节点没有父节点");
                return null;
            }
        }
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public void DLR() {
        System.out.println("node value is " + value);
        if (leftNode != null) {
            leftNode.DLR();
        }
        if (rightNode != null) {
            rightNode.DLR();
        }
    }
}
