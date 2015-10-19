
package textgen;

import java.util.AbstractList;

/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */

public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	//* Create a new empty LinkedList 
	public MyLinkedList() {
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null,head);
	}


	public E get(int index){
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("MyLinkedList: Index "+index+" is invalid. List size is "+size);
		}
		return getNode(index).data;
	}
 
  // Assume that index is >0 and <=size, but not strictly less than size
  private LLNode<E> getNode(int index) {
	  // This will get the tail node, so the bounds need to be checked outside of this method.
	  LLNode<E> curr = head.next;
	  int count = 0;
	  while(curr != tail && count < index)
	  {
		  curr = curr.next;
		  count++;
	  }
     return curr;
  }
  
  // returns prev
  public E set(int index, E element) {
	  if (index < 0 || index >= size)
	  {
		  throw new IndexOutOfBoundsException("MyLinkedList: Index "+index+" is invalid. List size is "+size);
	  }
	  if (element == null)
	  {
		  throw new NullPointerException("MyLinkedList: Null elements not permitted in List.");
	  }
	  LLNode<E> curr = getNode(index);
	  E tempData = curr.data;
	  curr.data = element;
    return tempData;
  }
  
  public int size() {
	  return size;
  }

  public void add(int index, E element ) {
	  if (index < 0 || index > size)
	  {
		  throw new IndexOutOfBoundsException("MyLinkedList add: Index "+index+" is invalid. List size is "+size);
	  }
      if(element == null) 
      {
		  throw new NullPointerException("MyLinkedList: Null elements not permitted in List.");
	  }
	  LLNode<E> curr = getNode(index);
	  new LLNode<E>(element, curr.prev);
	  size++;
  }
  public boolean add(E element) {
     this.add(this.size(), element);
     return true;
  }

  public E remove(int index) {
	  if (index < 0 || index >= size)
	  {
		  throw new IndexOutOfBoundsException("MyLinkedList: Index "+index+" is invalid. List size is "+size);
	  }
	  LLNode<E> curr = getNode(index);
	  // fix next elements previous pointer
	  curr.next.prev = curr.prev;

	  // fix prev elements next pointer
	  curr.prev.next = curr.next;

	  size--;
      return curr.data;
  }

}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e, LLNode<E> prevNode) {
		this(e);
		if(prevNode != null)
		{
			this.next = prevNode.next;
			if(this.next!=null) {
			   (this.next).prev = this;
			}
			prevNode.next = this;
			this.prev = prevNode;
		}
	}

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}

