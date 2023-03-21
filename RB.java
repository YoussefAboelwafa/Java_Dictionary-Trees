import java.awt.*;

public class RB<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;
private int size=0;
    @Override
    public Tree insert(T data) {
        Node<T> node = new Node<>(data);
        root = insert(root, node);
        size++;
        recolorAndRotate(node);
        return this;
    }

    private void recolorAndRotate(Node<T> node) {
        Node<T> parent = node.getParent();
        if (node != root && parent.getColor() == Color.RED) {
            Node<T> grandParent = parent.getParent();
            Node<T> uncle = grandParent.getLeft() == parent ? grandParent.getRight() : grandParent.getLeft();
            if (uncle != null && uncle.getColor() == Color.RED) {
                parent.flipColor();
                uncle.flipColor();
                grandParent.flipColor();
                recolorAndRotate(grandParent);
            } else if (parent.isLeftChild()) {
                handleLeftCase(node, parent, grandParent);
            } else if (!parent.isLeftChild()) {
                handleRightCase(node, parent, grandParent);
            }
        }
        root.setColor(Color.BLACK);
    }

    private void handleLeftCase(Node<T> node, Node<T> parent, Node<T> grandParent) {
        if (!node.isLeftChild()) {
            rotateLeft(parent);
        }
        rotateRight(grandParent);
        parent.flipColor();
        grandParent.flipColor();
        recolorAndRotate(node.isLeftChild() ? parent : grandParent);
    }

    private void handleRightCase(Node<T> node, Node<T> parent, Node<T> grandParent) {
        if (node.isLeftChild()) {
            rotateRight(parent);
        }
        rotateLeft(grandParent);
        parent.flipColor();
        grandParent.flipColor();
        recolorAndRotate(node.isLeftChild() ? grandParent : parent);

    }


    private void rotateLeft(Node<T> node) {
        Node<T> rightChild = node.getRight();
        node.setRight(rightChild.getLeft());
        if (node.getRight() != null) {
            node.getRight().setParent(node);
        }
        rightChild.setLeft(node);
        rightChild.setParent(node.getParent());
        updateChild(node, rightChild);
        node.setParent(rightChild);
    }

    private void rotateRight(Node<T> node) {
        Node<T> leftChild = node.getLeft();
        node.setLeft(leftChild.getRight());
        if (node.getLeft() != null) {
            node.getLeft().setParent(node);
        }
        leftChild.setRight(node);
        leftChild.setParent(node.getParent());
        updateChild(node, leftChild);
        node.setParent(leftChild);
    }

    private void updateChild(Node<T> node, Node<T> leftChild) {
        if (node.getParent() == null) {
            root = leftChild;
        } else if (node.isLeftChild()) {
            node.getParent().setLeft(leftChild);
        } else {
            node.getParent().setRight(leftChild);
        }
    }


    private Node<T> insert(Node<T> node, Node<T> isertedNode) {
        if (node == null) {
            return isertedNode;
        }
        if (isertedNode.getData().compareTo(node.getData()) < 0) {
            node.setLeft(insert(node.getLeft(), isertedNode));
            node.getLeft().setParent(node);
        } else {
            node.setRight(insert(node.getRight(), isertedNode));
            node.getRight().setParent(node);
        }
        updateHeight(node);
        return node;
    }

    @Override
    public void delete(T data) {
        root = delete(data, root);
        size--;
        recolorAndRotateafterdelete(root);
    }

    private void recolorAndRotateafterdelete(Node<T> root) {
        if (root == null) {
            return;
        }
        if (root.getLeft() != null && root.getLeft().getColor() == Color.RED) {
            root.getLeft().flipColor();
        }
        if (root.getRight() != null && root.getRight().getColor() == Color.RED) {
            root.getRight().flipColor();
        }
        if (root.getRight() != null && root.getRight().getColor() == Color.RED && root.getRight().getRight() != null && root.getRight().getRight().getColor() == Color.RED) {
            rotateLeft(root);
            root.flipColor();
            root.getLeft().flipColor();
        }
        if (root.getLeft() != null && root.getLeft().getColor() == Color.RED && root.getLeft().getLeft() != null && root.getLeft().getLeft().getColor() == Color.RED) {
            rotateRight(root);
            root.flipColor();
            root.getRight().flipColor();
        }
        if (root.getLeft() != null && root.getLeft().getColor() == Color.RED && root.getRight() != null && root.getRight().getColor() == Color.RED) {
            root.flipColor();
            root.getLeft().flipColor();
            root.getRight().flipColor();
        }
        recolorAndRotateafterdelete(root.getLeft());
        recolorAndRotateafterdelete(root.getRight());
    }

    private Node<T> delete(T data, Node<T> node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(delete(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(delete(data, node.getRight()));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            node.setData(getMax(node.getLeft()));
            node.setLeft(delete(node.getData(), node.getLeft()));
        }
        updateHeight(node);
        return node;
    }

    private T getMax(Node<T> left) {
        if (left.getRight() == null) {
            return left.getData();
        }
        return getMax(left.getRight());
    }
    private void updateHeight(Node<T> node) {
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
    }
    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    @Override
    public void traverse() {
        if (root == null) {
            return;
        }
        System.out.println("inorder traversal");
        inOrderTraversal(root);
        System.out.println("preoder traversal");
        preOrderTraversal(root);
        System.out.println("postorder traversal");
        postOrderTraversal(root);
    }

    void inOrderTraversal(Node<T> node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.println(node.getData());
            inOrderTraversal(node.getRight());
        }
    }
    void preOrderTraversal(Node<T> node){
        if (node!=null){
            System.out.println(node.getData());
            preOrderTraversal(node.getLeft());
            preOrderTraversal(node.getRight());
        }

    }
    void postOrderTraversal(Node<T> node){
        if (node!=null){
            postOrderTraversal(node.getLeft());
            postOrderTraversal(node.getRight());
            System.out.println(node.getData());
        }

    }


    @Override
    public T getMin() {
        for (Node<T> node = root; node != null; node = node.getLeft()) {
            if (node.getLeft() == null) {
                return node.getData();
            }
        }
        return null;
    }

    @Override
    public T getMax() {
        for (Node<T> node = root; node != null; node = node.getRight()) {
            if (node.getRight() == null) {
                return node.getData();
            }
        }
        return null;
    }

    @Override
    public boolean contains(T data) {
        if (root == null) {
            return false;
        }
        Node<T> node = root;
        while (node != null) {
            if (data.compareTo(node.getData()) < 0) {
                node = node.getLeft();
            } else if (data.compareTo(node.getData()) > 0) {
                node = node.getRight();
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getHeight() {
        return root.getHeight();
    }

}
