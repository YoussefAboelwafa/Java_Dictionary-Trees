public interface Tree<T extends Comparable <T> > {
    Tree<T> insert(T data);
    void delete(T data);
    Object traverse();
    T getMin();
    T getMax();
    boolean contains(T data);
    boolean isEmpty();
    int getSize();
    int getHeight();
}
