package master_do_not_use;

import java.util.List;


public class EfficientDocument extends Document {

	private int numWords;
	private int numSentences;
	private int numSyllables;
	
	public EfficientDocument(String text)
	{
		super(text);
		processText();
	}
	
	
	/** Return true if this string is a word (as opposed to punctuation */
	// We will give them this.
	private boolean isWord(String tok)
	{
		// This is way too slow:
		/*Pattern word = Pattern.compile("[\\S&&\\P{Punct}]+");
		Matcher m = word.matcher(tok);
		return m.find();*/

		// Even tok.matches is too slow
		//return tok.matches("[a-zA-Z]+");

		// Only this is fast enough.
		return !(tok.indexOf("!") >=0 || tok.indexOf(".") >=0 || tok.indexOf("?")>=0);
	}
	
	
	private void processText()
	{
		// Provide this first line in the starter code.  
		// Words are only strings of letters.  No numbers.
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
		numWords = 0;
		numSentences = 0;
		numSyllables = 0;
		for (String tok : tokens)
		{
			//System.out.println(tok);
			// If it's a sentence ending punctuation, increment number of sentences
			if (!isWord(tok)) {
				numSentences++;
			}
			else // if (isWord(tok)) {
			{	
				numWords++;
				numSyllables += countSyllables(tok);
			}
		}
	}
	
	
	@Override
	public int getNumWords() {
		return numWords;
	}

	@Override
	public int getNumSentences() {
		return numSentences;
	}

	@Override
	public int getNumSyllables() {
		return numSyllables;
	}
	
	public static void main(String[] args)
	{
		testCase(new EfficientDocument("This is. A, test...."), 4, 4, 2);
		
	}
	

}
