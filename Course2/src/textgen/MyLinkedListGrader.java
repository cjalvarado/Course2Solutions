package textgen;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class MyLinkedListGrader {
	
	PrintWriter out;
	
	public void printListForwards(MyLinkedList<Integer> lst)
	{
		LLNode<Integer> curr;
		if (lst.head.data == null)
			curr = lst.head.next;
		else
			curr = lst.head;
		
		while (curr != null && curr.data != null)
		{
			printNum(curr.data);
			curr = curr.next;
		}
	}
	
	public void printListBackwards(MyLinkedList<Integer> lst) {
		LLNode<Integer> curr;
		if (lst.tail.data == null)
			curr = lst.tail.prev;
		else
			curr = lst.tail;
		while (curr != null && curr.data != null)
		{
			printNum(curr.data);
			curr = curr.prev;
		}
	}
	
	public void printNum(int num) {
		out.print("" + num + " ");
	}
	
	public void doTest()
	{
		try {
			out = new PrintWriter("grader_output/module3.part1.out", "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		MyLinkedList<Integer> lst = new MyLinkedList<Integer>();
		int nums[] = {5, 4, 3, 2, 1};
		
		for (int i : nums) {
			lst.add(0, i);
		}
		
		lst.add(2, 6);
		
		printListForwards(lst);
		printListBackwards(lst);
		printNum(lst.size());
		
		lst.remove(2);
		printListForwards(lst);
		printListBackwards(lst);
		printNum(lst.size());
		
		lst = new MyLinkedList<Integer>();
		lst.add(0, 1);
		lst.remove(0);
		lst.add(0, 1);
		printListForwards(lst);
		printNum(lst.size());
		
		try
		{
			lst.get(-1);
			printNum(0);
		}
		catch (IndexOutOfBoundsException e)
		{
			printNum(1);
		}
		
		try
		{
			lst.get(2);
			printNum(0);
		}
		catch (IndexOutOfBoundsException e)
		{
			printNum(1);
		}
		
		try
		{
			lst.set(-1, 2);
			printNum(0);
		}
		catch (IndexOutOfBoundsException e)
		{
			printNum(1);
		}
		
		try
		{
			lst.set(2, 2);
			printNum(0);
		}
		catch (IndexOutOfBoundsException e)
		{
			printNum(1);
		}
		
		try
		{
			lst.set(0, null);
			printNum(0);
		}
		catch (NullPointerException e)
		{
			printNum(1);
		}
		
		try
		{
			lst.remove(-1);
			printNum(0);
		}
		catch (IndexOutOfBoundsException e)
		{
			printNum(1);
		}
		
		try
		{
			lst.remove(2);
			printNum(0);
		}
		catch (IndexOutOfBoundsException e)
		{
			printNum(1);
		}
		out.flush();
	}
	
	public static void main(String args[])
	{
		MyLinkedListGrader grader = new MyLinkedListGrader();
		grader.doTest();
	}
	
	
}
