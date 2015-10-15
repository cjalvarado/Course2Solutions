/**
 * 
 */
package spelling;

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
	private NearbyWords nw; // used to search for nearby Words
	

	// constructor will build path between the two nodes
	
	public WPTree () {
		this.root = null;
		this.nw = new NearbyWords(new DictionaryHashSet("Course2/data/dict.txt"));
	}
	
	
	
	
	// method to retrieve the path
	public List<String> findPath(String word1, String word2) {
		List<String>  path = null;
	    List<WPTreeNode> queue = new LinkedList<WPTreeNode>();
		boolean found = false;
		HashSet<String> visited = new HashSet<String>();
		
		
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
			//System.out.println("****** ITERATION "+count+"*********");
			//System.out.println(printQueue(queue));
			WPTreeNode curr = queue.remove(0);
			List<String> neighbors = nw.distanceOne(curr.getWord(), true);
			for(String n : neighbors) {
				if(!visited.contains(n)) {
					WPTreeNode child = curr.addChild(n);
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
		String string2 = "fighter";
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

class WPTreeNode {
    
    private String word;
    private List<WPTreeNode> children;
    private WPTreeNode parent;
    
    public WPTreeNode(String w, WPTreeNode p) {
        this.word = w;
        this.parent = p;
        this.children = new LinkedList<WPTreeNode>();
    }
    
    // precondition, s is not already a child of parent
    public WPTreeNode addChild(String s){
        WPTreeNode child = new WPTreeNode(s, this);
        this.children.add(child);
        return child;
    }
    
    public List<WPTreeNode> getChildren() {
        return this.children;
    }
    
    public List<String> buildPathToRoot() {
        WPTreeNode curr = this;
        List<String> path = new LinkedList<String>();
        while(curr != null) {
            path.add(0,curr.getWord());
            curr = curr.parent; 
        }
        return path;
    }
    
    public String getWord() {
        return this.word;
    }
    
    public String toString() {
        String ret = "Word: "+word+", parent = ";
        if(this.parent == null) {
           ret+="null.\n";
        }
        else {
           ret += this.parent.getWord()+"\n";
        }
        ret+="[ ";
        for(WPTreeNode curr: children) {
            ret+=curr.getWord() + ", ";
        }
        ret+=(" ]\n");
        return ret;
    }
    

}

