import org.junit.Test;
import static org.junit.Assert.*;

public class Junit2<T extends Comparable<T>> {
    Tree<String> d1 = new AVL<String>();
    Tree<T> d2 = new RB<T>();
    @Test
     public void insert_test(){
        d1.insert("youssef");
        assertEquals(1,d1.getSize());
    }

}
