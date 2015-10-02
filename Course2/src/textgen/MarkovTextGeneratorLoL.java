package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	private String starter;
	
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList = new LinkedList<ListNode>();
		train(sourceText);
	}
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		String splitOn = "[\\s]+";
		String[] words = sourceText.split(splitOn);
		String prev = starter;
		for (String w : words) 
		{
			ListNode toModify = null;
			// Find the previous word is in the list
			ListIterator<ListNode> it = wordList.listIterator();
			while (it.hasNext())
			{
				ListNode n = it.next();
				if (n.getWord().equals(prev)) {
					toModify = n;
					break;
				}
			}
			if (toModify == null) 
			{
				toModify = new ListNode(prev);
				wordList.add(toModify);
			}
			
			toModify.addNextWord(w);
			
			prev = w;
		}
	}
	
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	private ListNode findNode(String key)
	{
		for (ListNode n : wordList)
		{
			if (n.getWord().equals(key)) {
				return n;
			}
		}
		return null;
	}
	
	@Override
	public String generateText(int numWords) {
		if (wordList.size() == 0) {
			throw new IndexOutOfBoundsException();
		}
		String theText = "";
		String current = starter;
		for (int i = 0; i < numWords; i++)
		{
			ListNode currNode = findNode(current);
			if (currNode == null) {
				currNode = findNode(starter);
			}
			String nextWord = currNode.getRandomNextWord(rnGenerator);
			theText += nextWord + " ";
			current = nextWord;
		}
		return theText;
	}
	
	public static void main(String[] args)
	{
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list */
class ListNode
{
	private String word;
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		if (nextWords.size() == 0) {
			throw new IndexOutOfBoundsException();
		}
		int index = generator.nextInt(nextWords.size());
		return nextWords.get(index);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


