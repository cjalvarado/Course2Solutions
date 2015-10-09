/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		list1.add(0,a); // restore it back to where it was
		// TODO: Add more tests here
		// remove middle
		shortList.add("C");
		String b = shortList.remove(1);
		assertEquals("Remove: check shortlist contents ", "A", shortList.get(0));
		assertEquals("Remove: check shortlist contents ", "C", shortList.get(1));	
		shortList.add(1,b); // restore it back to where it was
		shortList.remove(2);
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		shortList.add("C");
		assertEquals("AddEnd: check shortlist contents ", "A", shortList.get(0));
		assertEquals("AddEnd: check shortlist contents ", "B", shortList.get(1));
		assertEquals("AddEnd: check shortlist contents ", "C", shortList.get(2));
		shortList.remove(2); // remove C
		
		try {
			shortList.remove(-1);
			fail("Remove Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.remove(2);
			fail("Remove Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Short List size", 2, shortList.size());
		assertEquals("list1 size",3,list1.size());
		assertEquals("Long list size", LONG_LIST_LENGTH, longerList.size());
		
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		shortList.add(1,"C");
		assertEquals("AddAtIndex: check shortlist contents ", "A", shortList.get(0));
		assertEquals("AddAtIndex: check shortlist contents ", "C", shortList.get(1));
		assertEquals("AddAtIndex: check shortlist contents ", "B", shortList.get(2));
		shortList.remove(1);
		shortList.add(0, "C");
		assertEquals("AddAtIndex: check shortlist contents ", "C", shortList.get(0));
		assertEquals("AddAtIndex: check shortlist contents ", "A", shortList.get(1));
		assertEquals("AddAtIndex: check shortlist contents ", "B", shortList.get(2));
		shortList.remove(0);
		// add to invalid location
		try {
			shortList.add(-1,"C");
			fail("AddAtIndex Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.add(3,"C");
			fail("AddAtIndex Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		// TODO: implement this test
		assertEquals("set: initial size off ",2,shortList.size());
		try {
			shortList.set(-1,"C");
			fail("Set Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.set(3,"C");
			fail("Set Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			shortList.set(0,null);
			fail("Set null pointer insert");
		}
		catch (NullPointerException e) {
			
		}
		shortList.set(0, "C");
		assertEquals("set: check shortlist contents ", "C", shortList.get(0));
		assertEquals("set: check shortlist contents ", "B", shortList.get(1));
		assertEquals("set: check size",2,shortList.size());
		shortList.set(0, "A");  // restore	    
	}
	
	
	
}
