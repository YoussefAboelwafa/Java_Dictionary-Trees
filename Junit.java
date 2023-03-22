import org.junit.Test;
import static org.junit.Assert.*;

public class Junit<T extends Comparable<T>> {
    Dictionary d1 = new Dictionary(1); // AVL
    Dictionary d2 = new Dictionary(2); // Red black
    @Test
     public void single_insert_test(){
        d1.insert("apple");
        assertEquals(1,d1.getSize());
        assertEquals(1,d1.getHeight());
        assertTrue(d1.search("apple"));
    }
    @Test
    public void single_delete_test(){
        d1.insert("apple");
        assertEquals(1,d1.getSize());
        assertEquals(1,d1.getHeight());
        d1.delete("apple");
        assertEquals(0,d1.getSize());
        assertEquals(0,d1.getHeight());
        assertFalse(d1.search("apple"));
    }
    @Test
    public void batch_insert_test(){
        d1.batch_insert("test1.txt");
        assertEquals(84402,d1.getSize());
        assertEquals(19,d1.getHeight());
        d1.batch_insert("test1.txt"); // insert again but still the same
        assertEquals(84402,d1.getSize());
        assertEquals(19,d1.getHeight());
    }
    @Test
    public void batch_insert_empty_test(){
        d1.batch_insert("empty.txt");
        assertEquals(0,d1.getSize());
        assertEquals(0,d1.getHeight());
    }
    @Test
    public void batch_delete_test(){
        d1.batch_insert("test1.txt");
        d1.batch_delete("test1.txt");
        assertEquals(0,d1.getSize());
        d1.batch_insert("test2.txt");
        d1.batch_delete("test2.txt");
        assertEquals(0,d1.getSize());
    }

}
