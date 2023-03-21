
public class AVL<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;
    private int size = 0;

    @Override
    public Tree<T> insert(T data) {
        root = insert(data, root);
        size++;
        return this;
    }

    private Node<T> insert(T data, Node<T> node) {
        if (node == null) {
            Node<T> newNode = new Node<>(data);
            return newNode;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(insert(data, node.getLeft()));
        } else {
            node.setRight(insert(data, node.getRight()));
        }
        updateHeight(node);
        return balance(node);
    }

    private Node<T> balance(Node<T> node) {
        if (balanceFactor(node) > 1) {
            if (balanceFactor(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        } else if (balanceFactor(node) < -1) {
            if (balanceFactor(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> newRoot = node.getRight();
        node.setRight(newRoot.getLeft());
        newRoot.setLeft(node);
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> newRoot = node.getLeft();
        node.setLeft(newRoot.getRight());
        newRoot.setRight(node);
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    private void updateHeight(Node<T> node) {
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
    }

    private int balanceFactor(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }


    @Override
    public void delete(T data) {

        root = remove(data, root);
        size--;
    }

    private Node<T> remove(T data, Node<T> node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(data, node.getRight()));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            node.setData(getMax(node.getLeft()));
            node.setLeft(remove(node.getData(), node.getLeft()));
        }
        updateHeight(node);
        return balance(node);
    }

    private T getMax(Node<T> left) {
        if (left.getRight() == null) {
            return left.getData();
        }
        return getMax(left.getRight());
    }

    @Override
    public void traverse() {
        if (root == null) {
            return;
        }
        inOrderTraversal(root);

    }

    private void inOrderTraversal(Node<T> node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.println("\u001B[34m"+node.getData()+"\u001B[0m");
            inOrderTraversal(node.getRight());
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

