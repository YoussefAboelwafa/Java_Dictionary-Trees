import lombok.Data;
import lombok.NonNull;

import java.awt.*;

@Data
public class Node<T extends Comparable<T>> {
    @NonNull
    private T data;
    private int height = 1;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;
    private Color color = Color.RED;

    public boolean isLeftChild() {
        return parent != null && parent.getLeft() == this;
    }

    public void flipColor() {
        setColor(getColor() == Color.RED ? Color.BLACK : Color.RED);
    }
}