package master_do_not_use;

import java.util.List;

public class BasicDocument extends Document 
{
	// Will be provided to learners.
	public BasicDocument(String text)
	{
		super(text);
	}
	
	// Learners will write this
	public int getNumWords()
	{
		// 
		List<String> tokens = getTokens("[a-zA-Z]+");
		return tokens.size();
	}
	
	public int getNumSentences()
	{
		// The pattern below will break for floating point numbers, 
		// abbreviations, and other edge cases
		List<String> tokens = getTokens("[^?.!]+");  
		return tokens.size();
	}
	
	public int getNumSyllables()
	{
		List<String> tokens = getTokens("[a-zA-Z]+");
		int totalSyllables = 0;
		for (String word : tokens)
		{
			totalSyllables += countSyllables(word);
		}
		return totalSyllables;
	}
	
	
	public static void main(String[] args)
	{
	    BasicDocument d = new BasicDocument("one (1), two (2), three (3)");
	    List<String> result = d.getTokens("[a-z]+|[()0-9]+");
	    for (String s : result)
	        System.out.println(s);
	    
	    
		/*testCase(new BasicDocument("This is a test.  How many???  Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);
		testCase(new BasicDocument(""), 0, 0, 0);
		testCase(new BasicDocument("sentence, with, lots, of, commas.!  (And some poaren)).  The output is: 7.5."), 15, 11, 4);
		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		*/
	}
	
}
