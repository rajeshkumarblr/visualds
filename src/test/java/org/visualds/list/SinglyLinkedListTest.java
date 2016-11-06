package org.visualds.list;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by rajesh on 13/8/16.
 */
public class SinglyLinkedListTest {
    SinglyLinkedList<Integer> list;
    @BeforeMethod
    public void setUp() throws Exception {
        list = new SinglyLinkedList();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        Random rand = new Random();
        Integer val = rand.nextInt();
        list.add(val);
        assert list.isFound(val);
    }

    @Test
    public void testAddNegative() throws Exception {
        Random rand = new Random();
        Integer val = rand.nextInt();
        list.add(val);
        assert !list.isFound(val+1);
    }

    @Test
    public void testRemove() throws Exception {
        Random rand = new Random();
        Integer val = rand.nextInt();
        list.add(val);
        assert(list.isFound(val));
        boolean isRemoved = list.remove(val);
        assert isRemoved;
        assert !list.isFound(val);
    }

    @Test
    public void testRemoveNegative() throws Exception {
        Random rand = new Random();
        Integer val = rand.nextInt();
        list.add(val);
        assert(list.isFound(val));
        boolean isRemoved = list.remove(val-1);
        assert !isRemoved;
        assert list.isFound(val);
    }
}