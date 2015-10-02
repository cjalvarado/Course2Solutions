package master_do_not_use;

//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WPTreeNode {
	
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
	
	public static void main(String[] args) {
		WPTreeNode root = new WPTreeNode("Hi",null);
		WPTreeNode c1 = root.addChild("His");
		root.addChild("i");
		System.out.println(root);
		System.out.println(c1);
	}

}
