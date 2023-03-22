import org.junit.Test;
import static org.junit.Assert.*;

public class Junit <T extends Comparable<T>> {
    Dictionary d1 = new Dictionary(1); // AVL
    Dictionary d2 = new Dictionary(2); // Red black
    int test1_size=84402;
    int test2_size=10000;
    int test10_size=466472;

    // AVL TESTS

    @Test
     public void single_insert_avl_test(){
        d1.insert("apple");
        assertEquals(1,d1.getSize());
        assertEquals(1,d1.getHeight());
        assertTrue(d1.search("apple"));
    }
    @Test
    public void single_delete_avl_test(){
        d1.insert("apple");
        assertEquals(1,d1.getSize());
        assertEquals(1,d1.getHeight());
        d1.delete("apple");
        assertEquals(0,d1.getSize());
        assertEquals(0,d1.getHeight());
        assertFalse(d1.search("apple"));
    }
    @Test
    public void batch_insert_avl_test(){
        d1.batch_insert("test1.txt");
        assertEquals(test1_size,d1.getSize());
        assertEquals(19,d1.getHeight());
        d1.batch_insert("test1.txt"); // insert again but still the same
        assertEquals(test1_size,d1.getSize());
        assertEquals(19,d1.getHeight());
    }
    @Test
    public void batch_insert_empty_avl_test(){
        d1.batch_insert("empty.txt");
        assertEquals(0,d1.getSize());
        assertEquals(0,d1.getHeight());
    }
    @Test
    public void batch_delete_avl_test(){
        d1.batch_insert("test1.txt");
        d1.batch_delete("test1.txt");
        assertEquals(0,d1.getSize());
        d1.batch_insert("test2.txt");
        d1.batch_delete("test2.txt");
        assertEquals(0,d1.getSize());
    }
    @Test
    public void insert_bigdata_avl_test(){
        d1.batch_insert("test10.txt");
        assertEquals(test10_size,d1.getSize());
    }



    // RED BLACK TESTS

    @Test
    public void single_insert_rb_test(){
        d2.insert("apple");
        assertEquals(1,d2.getSize());
        assertEquals(1,d2.getHeight());
        assertTrue(d2.search("apple"));
    }
    @Test
    public void single_delete_rb_test(){
        d2.insert("apple");
        assertEquals(1,d2.getSize());
        assertEquals(1,d2.getHeight());
        d2.delete("apple");
        assertEquals(0,d2.getSize());
        assertEquals(0,d2.getHeight());
        assertFalse(d2.search("apple"));
    }
    @Test
    public void batch_insert_rb_test(){
        d2.batch_insert("test1.txt");
        assertEquals(test1_size,d2.getSize());
        assertEquals(19,d2.getHeight());
        d2.batch_insert("test1.txt"); // insert again but still the same
        assertEquals(test1_size,d2.getSize());
        assertEquals(19,d2.getHeight());
    }
    @Test
    public void batch_insert_empty_rb_test(){
        d2.batch_insert("empty.txt");
        assertEquals(0,d2.getSize());
        assertEquals(0,d2.getHeight());
    }
    @Test
    public void batch_delete_rb_test(){
        d2.batch_insert("test1.txt");
        d2.batch_delete("test1.txt");
        assertEquals(0,d2.getSize());
        d2.batch_insert("test2.txt");
        d2.batch_delete("test2.txt");
        assertEquals(0,d2.getSize());
    }
    @Test
    public void insert_bigdata_rb_test(){
        d2.batch_insert("test10.txt");
        assertEquals(test10_size,d2.getSize());
    }
    @Test
    public void search_avl_time(){
        d1.batch_insert("test10.txt");
        long start1 = System.nanoTime();
        System.out.println(d1.search("nonatomic"));
        long end1 = System.nanoTime();
        System.out.println("Time taken to search in AVL tree = "+(end1-start1)+" nanoseconds");
    }
    @Test
    public void search_rb_time(){
        d2.batch_insert("test10.txt");
        long start2 = System.nanoTime();
        System.out.println(d2.search("nonatomic"));
        long end2 = System.nanoTime();
        System.out.println("Time taken to search in RED BLACK tree = "+(end2-start2)+" nanoseconds");
    }
}
