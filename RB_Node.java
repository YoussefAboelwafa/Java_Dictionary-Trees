
class RB_Node<T extends Comparable<T>> {

    /** Possible color for this node */
    public static final int BLACK = 0;
    /** Possible color for this node */
    public static final int RED = 1;
    // the key of each node
    public T key;

    /** Parent of node */
    RB_Node<T> parent;
    /** Left child */
    RB_Node<T> left;
    /** Right child */
    RB_Node<T> right;
    // the number of elements to the left of each node
    public int numLeft = 0;
    // the number of elements to the right of each node
    public int numRight = 0;
    // the color of a node
    public int color;

    RB_Node(){
        color = BLACK;
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
    }

    // Constructor which sets key to the argument.
    RB_Node(T key){
        this();
        this.key = key;
    }
}

