package spelling;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class SpellingSuggestNW implements SpellingSuggest {

	private NearbyWords nw;
	
	public SpellingSuggestNW(NearbyWords w) {
	  this.nw = w;
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
		
		// BFS while dynamically building the tree
		// pop from the queue, get all words 1 away
		// if not already visited, add those words as children and add to queue
		int count = 0;
		while(!queue.isEmpty() && retList.size() < numSuggestions) {
			// remove head
			System.out.println("****** ITERATION "+count+"*********");
			System.out.println(queue);
			String curr = queue.remove(0);
			
			List<String> neighbors = nw.distanceOne(curr);
			for(String n : neighbors) {
				if(!visited.contains(n)) {
					visited.add(n);
					queue.add(n);
					if(retList.size() < numSuggestions) {
						retList.add(n);
					}
				}
			}
			count++;
		}
		return retList;
	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String word = "the";
		NearbyWords near = new NearbyWords(new DictionaryHashSet("data/dict.txt"));
		SpellingSuggestNW mysuggest = new SpellingSuggestNW(near);
		List<String> list = mysuggest.suggestions(word, 10);
		System.out.println("Spelling Suggestions for \""+word+"\" are:");
		System.out.println(list);
	}

}
