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
	

	
	@Override
	public boolean isWord(String s) {
		TrieNode curr = root;
		for(char cWithCase : s.toCharArray()) {
			Character c = Character.toLowerCase(cWithCase);
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
		Character c = null;
		for (Character cWithCase : prefix.toCharArray())
		{
			c = Character.toLowerCase(cWithCase);
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

