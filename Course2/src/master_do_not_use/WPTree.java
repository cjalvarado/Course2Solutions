/**
 * 
 */
package master_do_not_use;

//import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author leporter
 *
 */
public class WPTree implements WordPath {

	// this will do all the work of dynamically creating the tree based on two words
	// keep a hashset of seen words
	private WPTreeNode root;
	

	// constructor will build path between the two nodes
	
	public WPTree () {
		this.root = null;
	}
	
	
	
	
	// method to retrieve the path
	public List<String> findPath(String word1, String word2) {
		List<String>  path = null;
	    List<WPTreeNode> queue = new LinkedList<WPTreeNode>();
		boolean found = false;
		HashSet<String> visited = new HashSet<String>();
		NearbyWords nw = new NearbyWords(new DictionaryHashSet("Course2/data/dict.txt"));
		
		// insert first node
		this.root = new WPTreeNode(word1, null);
		queue.add(this.root);
		visited.add(word1);
		
		// BFS while dynamically building the tree
		// pop from the queue, get all words 1 away
		// if not already visited, add those words as children and add to queue
		int count = 0;
		while(!queue.isEmpty() && !found) {
			// remove head
			System.out.println("****** ITERATION "+count+"*********");
			System.out.println(printQueue(queue));
			WPTreeNode curr = queue.remove(0);
			List<String> neighbors = nw.distanceOne(curr.getWord());
			for(String n : neighbors) {
				if(!visited.contains(n)) {
					WPTreeNode child = new WPTreeNode(n, curr);
					visited.add(n);
					queue.add(child);
					if(n.equals(word2)) {
						// Found it!
						path = child.buildPathToRoot();
						found = true;
					}
				}
			}
			count++;
		}
		return path;
	}
	
	private String printQueue(List<WPTreeNode> list) {
		String ret = "[ ";
		
		for (WPTreeNode w : list) {
			ret+= w.getWord()+", ";
		}
		ret+= "]";
		return ret;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String string1 = "the";
		String string2 = "kangaroo";
		WPTree wp = new WPTree();
		List<String> path = wp.findPath(string1, string2);
		if(path == null) {
			System.out.println("No path exists between \""+string1 + "\", \""+string2+"\"");
		}
		else {
			System.out.println("Path exists between \""+string1 + "\", \""+string2 +"\" of length "+path.size());
			System.out.println(path);
		}
	}
}
