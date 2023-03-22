import java.awt.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

public class RB <T extends Comparable<T>> implements Tree<T> {
    private int size = 0;
    private class NulNode<T extends Comparable<T>> extends Node<T> {
        public NulNode() {
            super(null);
            setColor(BLACK);
        }
    }
    private Node<T> root;
    @Override
    public Tree insert(T data) {
        Node<T> node = new Node<>(data);
        node.setColor(RED);
        size++;
        root= insert(root, node);
        recolorAndRotate(node);
        return this;
    }

    private void recolorAndRotate(Node<T> node) {
        Node<T> parent = node.getParent(), grandParent;
        if (parent == null) {
            node.setColor(BLACK);
            return;
        }
        if (parent.getColor() == BLACK) {
            return;
        }
        grandParent = parent.getParent();
        if (grandParent == null) {
            parent.setColor(BLACK);
            return;
        }
        Node<T> uncle = grandParent.getLeft() == parent ? grandParent.getRight() : grandParent.getLeft();
        if (uncle != null && uncle.getColor() == RED) {
            parent.flipColor();
            uncle.flipColor();
            grandParent.flipColor();
            recolorAndRotate(grandParent);
        }
        else if(parent.isLeftChild()){
            handleLeftCase(node, parent, grandParent);
        }
        else if (!parent.isLeftChild()){
            handleRightCase(node, parent, grandParent);
        }
    }

    private void handleLeftCase(Node<T> node, Node<T> parent, Node<T> grandParent) {
        if(!node.isLeftChild()){
            rotateLeft(parent);
        }
        rotateRight(grandParent);
        parent.flipColor();
        grandParent.flipColor();
    }

    private void handleRightCase(Node<T> node, Node<T> parent, Node<T> grandParent) {
        if(node.isLeftChild()){
            rotateRight(parent);
        }
        rotateLeft(grandParent);
        parent.flipColor();
        grandParent.flipColor();

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
        if(node.getParent()==null){
            root = leftChild;
        }
        else if(node.isLeftChild()){
            node.getParent().setLeft(leftChild);
        }
        else {
            node.getParent().setRight(leftChild);
        }
        if (leftChild!=null){
            leftChild.setParent(node.getParent());
        }
    }


    private Node<T> insert(Node<T> node, Node<T> isertedNode){
        if (node == null) {
            return isertedNode;
        }
        if (isertedNode.getData().compareTo(node.getData()) < 0) {
            node.setLeft(insert(node.getLeft(), isertedNode));
            node.getLeft().setParent(node);
        } else if (isertedNode.getData().compareTo(node.getData()) > 0){
            node.setRight(insert(node.getRight(), isertedNode));
            node.getRight().setParent(node);
        }
        else {
            size--;
            return node;
        }
        return node;
    }

    @Override
    public void delete(T data) {
        Node<T> node =root,replaced;
        Color color;
        while (node!=null && node.getData().compareTo(data)!=0){
            if (data.compareTo(node.getData())<0){
                node = node.getLeft();
            }
            else {
                node = node.getRight();
            }
        }
        if (node==null){
            return;
        }
        size--;
        if (node.getLeft()==null || node.getRight()==null){
            replaced = deleteNode(node);
            color = node.getColor();
        }
        else {
            Node<T> successor = getMin(node.getRight());
            node.setData(successor.getData());
            replaced = deleteNode(successor);
            color = successor.getColor();
        }

        if (color== BLACK){
            fixDoubleBlack(replaced);
            if(replaced.getClass()==NulNode.class){
                updateChild(replaced, null);
            }
        }


    }

    private void fixDoubleBlack(Node<T> replaced) {
        if(replaced==root){
            return;
        }
        if(replaced.getParent()==null||replaced.getParent().getRight()==null||replaced.getParent().getLeft()==null){replaced=root; return;}
        Node<T> sibling = replaced.isLeftChild()? replaced.getParent().getRight() : replaced.getParent().getLeft();
        if (sibling.getColor()== RED){
            replaced.getParent().flipColor();
            sibling.flipColor();
            if (sibling.isLeftChild()){
                rotateLeft(replaced.getParent());
            }
            else {
                rotateRight(replaced.getParent());
            }
            if(replaced.getParent()==null||replaced.getParent().getRight()==null||replaced.getParent().getLeft()==null){replaced=root; return;}

            sibling = replaced.isLeftChild()? replaced.getParent().getRight() : replaced.getParent().getLeft();
        }
        if((sibling.getLeft()==null||sibling.getLeft().getColor()== BLACK)&&(sibling.getRight()==null||sibling.getRight().getColor()== BLACK)){
            sibling.setColor(RED);
            if (replaced.getParent().getColor()== RED){
                replaced.getParent().setColor(BLACK);
            }
            else {
                fixDoubleBlack(replaced.getParent());
            }
        }
        else {
            if(replaced.isLeftChild() && (sibling.getRight()==null||sibling.getRight().getColor()==BLACK)){
                sibling.getLeft().setColor(BLACK);
                sibling.flipColor();
                rotateRight(sibling);
                sibling = replaced.getParent().getRight();
            }
            else if(!replaced.isLeftChild() && (sibling.getLeft()==null||sibling.getLeft().getColor()== BLACK)){
                sibling.getRight().setColor(BLACK);
                sibling.flipColor();
                rotateLeft(sibling);
                sibling = replaced.getParent().getLeft();
            }
            sibling.setColor(replaced.getParent().getColor());
            replaced.getParent().setColor(BLACK);
            if (replaced.isLeftChild()){
                sibling.getRight().setColor(BLACK);
                rotateLeft(replaced.getParent());
            }
            else {
                sibling.getLeft().setColor(BLACK);
                rotateRight(replaced.getParent());
            }
        }

    }

    Node deleteNode(Node node){
        if(node.getLeft()!=null){
            updateChild(node, node.getLeft());
            return node.getLeft();
        }
        else if(node.getRight()!=null){
            updateChild(node, node.getRight());
            return node.getRight();
        }
        else {
            Node <T> nulNode = node.getColor()== BLACK?new NulNode():null;
            updateChild(node, nulNode);
            return nulNode;
        }
    }
    private Node getMin(Node right) {
        if (right.getLeft()==null){
            return right;
        }
        return getMin(right.getLeft());
    }


    @Override
    public void traverse() {
        if (root == null) {
            return;
        }
        inOrderTraversal(root);
    }
    void inOrderTraversal(Node<T> node){
        if (node!=null){
            inOrderTraversal(node.getLeft());
            System.out.println(node.getData());
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