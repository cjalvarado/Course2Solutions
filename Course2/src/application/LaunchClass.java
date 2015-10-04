package application;

import java.util.Random;


public class LaunchClass {
	
	// AutoComplete?
	
	public String dictFile = "dict.txt";
	
	public LaunchClass() {
		super();
	}
	
	public document.Document getDocument(String text) {
		return new document.EfficientDocument(text);
	}
	
	public textgen.MarkovTextGenerator getMTG() {
		return new textgen.MarkovTextGeneratorLoL(new Random(42));
	}
	
	public spelling.WordPath getWordPath() {
		//System.out.println("New WP");
		return new spelling.WPTree();
	}
	
    public spelling.AutoComplete getAutoComplete() {
    	return new spelling.AutoCompleteDictionaryTrie();
    }
    
    public spelling.Dictionary getDictionary() {
    	return new spelling.DictionaryHashSet(dictFile);
 
    }
    
    public spelling.SpellingSuggest getSpellingSuggest(spelling.Dictionary dic) {
    	return new spelling.SpellingSuggestNW(new spelling.NearbyWords(dic));
    
    }
}
