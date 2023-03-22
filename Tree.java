public interface Tree<T extends Comparable <T> > {
    boolean insert(T data);
    boolean delete(T data);
    void traverse();
    T getMin();
    T getMax();
    boolean contains(T data);
    boolean isEmpty();
    int getSize();
    int getHeight();
}
