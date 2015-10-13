/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class NearbyWords implements SpellingSuggest {
    private static final int THRESHOLD = 1000; 
	
	Dictionary dict;
   
   public NearbyWords (Dictionary dict) {
      this.dict = dict;
   }
   
   public List<String> distanceOne(String s, boolean wordsOnly) {
	   List<String> retList = new ArrayList<String>();
	   insertions(s, retList, wordsOnly);
	   substitutions(s, retList, wordsOnly);
	   deletions(s, retList, wordsOnly);
	   return retList;
   }
   
   public void insertions(String s, List<String> currentList, boolean wordsOnly) {
	   //System.out.println("Original Word " + s);
	   for(int index = 0; index <= s.length(); index++){
		   for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
			   StringBuffer sb = new StringBuffer(s);
			   sb.insert(index, (char)charCode);
			   //System.out.println("Inserting: " + sb.toString());
			   if(!currentList.contains(sb.toString()) && (!wordsOnly||dict.isWord(sb.toString()))) {
				  // System.out.println("Inserting: " + sb.toString());
				   currentList.add(sb.toString());
			   }
		   }
	   }
   }
   
   public void substitutions(String s, List<String> currentList, boolean wordsOnly) {
	  // System.out.println("Change Char: Original Word " + s);
	   for(int index = 0; index < s.length(); index++){
		   for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
			   StringBuffer sb = new StringBuffer(s);
			   sb.setCharAt(index, (char)charCode);
			   //System.out.println("Exploring: " + sb.toString());
			   if(!currentList.contains(sb.toString()) && (!wordsOnly||dict.isWord(sb.toString()))
					   && !s.equals(sb.toString())) {
				   //System.out.println("Inserting: " + sb.toString());
				   currentList.add(sb.toString());
			   }
		   }
	   }
   }
   public void deletions(String s, List<String> currentList, boolean wordsOnly) {
	   //System.out.println("Deletions Char: Original Word " + s);
	   for(int index = 0; index < s.length(); index++){
		       StringBuffer sb = new StringBuffer(s);
			   sb.deleteCharAt(index);
			   //System.out.println("Exploring: " + sb.toString());
			   if(!currentList.contains(sb.toString()) && (!wordsOnly||dict.isWord(sb.toString()))
					   && !s.equals(sb.toString())) {
				   //System.out.println("Inserting: " + sb.toString());
				   currentList.add(sb.toString());
			   }
		
	   }
   }
   
	@Override
	public List<String> suggestions(String word, int numSuggestions) {
		// this method creates and calls your NearbyWords class for
		// words nearby
		List<String> queue = new LinkedList<String>();
		HashSet<String> visited = new HashSet<String>();
		List<String> retList = new LinkedList<String>();
		
		// insert first node
		queue.add(word);
		visited.add(word);
		
		// If you want to add the original word before starting, uncomment
		// the line below
    	// retList.add(curr);
		
		// perform BFS while dynamically building the tree
		// pop from the queue, get all String permutations 1 away
		// if not already visited, add those Strings to the queue
		// if the String is a word in the dictionary, add it to the return list
		
		// use count for debugging and to stop what could be infinite exploration
		int count = 0;  
		while(!queue.isEmpty() && retList.size() < numSuggestions){ //&& count < THRESHOLD) {
			/*  // Uncomment below for DEBUGGING SUPPORT, beware - large output
			  System.out.println("****** ITERATION "+count+"*********");
		      System.out.println(queue);
		    */
			// remove head
			String curr = queue.remove(0);
			// find neighboring Strings (not necessarily words)
			List<String> neighbors = distanceOne(curr, false);
			for(String n : neighbors) {
				if(!visited.contains(n)) {
					// mark visited
					visited.add(n);
					// add to the exploration queue
					queue.add(n);
					// if a word (and we need more suggestions), add to suggestion list
					if(retList.size() < numSuggestions && dict.isWord(n)) {
						retList.add(n);
					}
				}
			}
			count++;
		}
		return retList;
	}	
   
   
   public static void main(String[] args) {
	   String word = "i";
	   NearbyWords w = new NearbyWords(new DictionaryHashSet("Course2/data/dict.txt"));
	   List<String> l = w.distanceOne(word, true);
	   System.out.println("One away Strings for for \""+word+"\" are:");
	   System.out.println(l+"\n");
	   
	   word = "kangaro";
	   //word = "tailo";
	   List<String> suggest = w.suggestions(word, 10);
	   System.out.println("Spelling Suggestions for \""+word+"\" are:");
	   System.out.println(suggest);
   }
   
   
}
