package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;


public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    
	public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	public void insert(Collection<String> words)
	{
		for (String w : words) {
			addWord(w);
		}
	}
	
	/** Insert a word into the trie */
	public boolean addWord(String word)
	{
		if (word == null) {
			throw new NullPointerException();
		}
		
		TrieNode curr = root;
		TrieNode next;
		boolean inserted = false;
		for (Character c1 : word.toCharArray())
		{
			Character c = Character.toLowerCase(c1.charValue());
		    next = curr.getChild(c);
			if (next == null) {
				inserted = true;
				next = curr.insert(c);
			}
			curr = next;
		}
		if (!curr.endsWord())
		{
			curr.setEndsWord(true);
			inserted = true;
		}
		if (inserted) size++;
		return inserted;
	}
	
	public int size()
	{
	    return size;
	}
	
	// For debugging
	public void printTree()
	{
		printNode(root);
	}
	
	/** Do a pre-order traversal from this node down */
	public void printNode(TrieNode curr)
	{
		if (curr == null) 
			return;
		
		System.out.println(curr.getText());
		
		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			printNode(next);
		}
	}
	
	/** 
	 * Returns up to the n "best" predictions, including the word itself,
	 * in terms of length
	 * If this string is not in the trie, it returns null.
	 * @param text The text to use at the word stem
	 * @param n The maximum number of predictions desired.
	 * @return A list containing the up to n best predictions
	 */
	public List<String> predict(String text, int n)
	{
		// First find the node of the last letter
		TrieNode curr = root;
		TrieNode next = null;
		List<String> toReturn = new LinkedList<String>();
		for (Character c : text.toCharArray())
		{
			next = curr.getChild(c);
			if (next == null)
			{
				return toReturn;
			}
			curr = next;
		}
		// Now build the list of predictions
		LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
		queue.add(curr);
		while (!queue.isEmpty() && n > 0)
		{
			next = queue.removeFirst();
			if (next.endsWord()) {
				toReturn.add(next.getText());
				n--;
			}
			for (Character cnext : next.getValidNextCharacters()) 
			{
				queue.add(next.getChild(cnext));
			}
		}
		return toReturn;
	}
	
	@Override
	public boolean isWord(String s) {
		TrieNode curr = root;
		for(char c : s.toCharArray()) {
			// advance to next char
			curr = curr.getChild(c);
			// if next doesn't exist, return false
			if(curr == null)
				return false;
		}
		// we're at a valid node, but may not be valid
		return curr.endsWord();
	}

	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {
		// First find the node of the last letter
		TrieNode curr = root;
		TrieNode next = null;
		List<String> toReturn = new LinkedList<String>();
		for (Character c : prefix.toCharArray())
		{
			next = curr.getChild(c);
			if (next == null)
			{
				return toReturn;
			}
			curr = next;
		}
		// Now build the list of predictions
		LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
		queue.add(curr);
		int n = 0;
		while (!queue.isEmpty() && n < numCompletions)
		{
			next = queue.removeFirst();
			if (next.endsWord()) {
				toReturn.add(next.getText());
				n++;
			}
			for (Character cnext : next.getValidNextCharacters()) 
			{
				queue.add(next.getChild(cnext));
			}
		}
		return toReturn;
	}
	
	

}

/** 
 * Represents a node in a Trie
 * @author Christine
 *
 */
class TrieNode {
	private HashMap<Character, TrieNode> children; 
	private String text;  // Maybe omit for space
	private boolean isWord;
	
	public TrieNode()
	{
		children = new HashMap<Character, TrieNode>();
		text = "";
		isWord = false;
	}
	
	public TrieNode(String text)
	{
		this();
		this.text = text;
	}
	
	public TrieNode getChild(Character c)
	{
		return children.get(c);
	}
	
	/** Inserts this character at this node.
	 * Returns the newly created node, if c wasn't already
	 * in the trie.  If it was, it does not modify the trie
	 * and returns null.
	 * @param c
	 * @return
	 */
	public TrieNode insert(Character c)
	{
		if (children.containsKey(c)) {
			return null;
		}
		
		TrieNode next = new TrieNode(text + c.toString());
		children.put(c, next);
		return next;
	}
	
    public String getText()
	{
		return text;
	}
	
	public void setEndsWord(boolean b)
	{
		isWord = b;
	}
	
	public boolean endsWord()
	{
		return isWord;
	}
	
	public Set<Character> getValidNextCharacters()
	{
		return children.keySet();
	}
	
	
}
