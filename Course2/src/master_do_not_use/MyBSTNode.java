package master_do_not_use;

public class MyBSTNode {

  private String word;
  private MyBSTNode leftChild;
  private MyBSTNode rightChild;
  private MyBSTNode parent;
  
  public MyBSTNode(MyBSTNode p, String w) {
	this.parent = parent;
    this.word = word;
    this.leftChild = null;
    this.rightChild = null;
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
		if(this.leftChild == null) {
			ret+="null, ";
		}
		else {
			ret+= this.leftChild.getWord()+", ";
		}
		if(this.rightChild == null) {
			ret+="null, ";
		}
		else {
			ret+= this.rightChild.getWord()+", ";
		}
		ret+=(" ]\n");
		return ret;
  }
  
  /** Add this word to the BST.
	* @param word The word to add
	* @return true if the word was added to the dictionary 
	* (it wasn't already there). */
  public boolean addWord(String word) {
	return false;  
  }
}
