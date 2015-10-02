/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.List;


/**
 * @author leporter
 *
 */
public class NearbyWords {
   Dictionary dict;
   
   public NearbyWords (Dictionary dict) {
      this.dict = dict;
   }
   
   public List<String> distanceOne(String s) {
	   List<String> retList = new ArrayList<String>();
	   insertions(s, retList);
	   changeChar(s, retList);
	   deletions(s, retList);
	   
	   return retList;
   }
   
   public void insertions(String s, List<String> currentList) {
	   //System.out.println("Original Word " + s);
	   for(int index = 0; index <= s.length(); index++){
		   for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
			   StringBuffer sb = new StringBuffer(s);
			   sb.insert(index, (char)charCode);
			   //System.out.println("Inserting: " + sb.toString());
			   if(!currentList.contains(sb.toString()) && dict.isWord(sb.toString())) {
				  // System.out.println("Inserting: " + sb.toString());
				   currentList.add(sb.toString());
			   }
		   }
	   }
   }
   
   public void changeChar(String s, List<String> currentList) {
	  // System.out.println("Change Char: Original Word " + s);
	   for(int index = 0; index < s.length(); index++){
		   for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
			   StringBuffer sb = new StringBuffer(s);
			   sb.setCharAt(index, (char)charCode);
			   //System.out.println("Exploring: " + sb.toString());
			   if(!currentList.contains(sb.toString()) && dict.isWord(sb.toString()) 
					   && !s.equals(sb.toString())) {
				   //System.out.println("Inserting: " + sb.toString());
				   currentList.add(sb.toString());
			   }
		   }
	   }
   }
   public void deletions(String s, List<String> currentList) {
	   //System.out.println("Deletions Char: Original Word " + s);
	   for(int index = 0; index < s.length(); index++){
		       StringBuffer sb = new StringBuffer(s);
			   sb.deleteCharAt(index);
			   //System.out.println("Exploring: " + sb.toString());
			   if(!currentList.contains(sb.toString()) && dict.isWord(sb.toString()) 
					   && !s.equals(sb.toString())) {
				   //System.out.println("Inserting: " + sb.toString());
				   currentList.add(sb.toString());
			   }
		
	   }
   }
   
   
   public static void main(String[] args) {
	   NearbyWords w = new NearbyWords(new DictionaryHashSet("data/dict.txt"));
	   List<String> l = w.distanceOne("i");
	   System.out.println(l);
   }
}
