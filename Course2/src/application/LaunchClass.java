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
		return new textgen.MarkovTextGeneratorLoL(new Random());
	}
	
	public spelling.WordPath getWordPath() {
		//System.out.println("New WP");
		return new spelling.WPTree();
	}
	
    public spelling.AutoComplete getAutoComplete() {
        spelling.AutoCompleteDictionaryTrie tr = new spelling.AutoCompleteDictionaryTrie();
        spelling.DictionaryLoader.loadDictionary(tr, dictFile);
        return tr;
    }
    
    public spelling.Dictionary getDictionary() {
        spelling.Dictionary d = new spelling.DictionaryHashSet(dictFile);
        spelling.DictionaryLoader.loadDictionary(d, dictFile);
    	return d;
    }
    
    public spelling.SpellingSuggest getSpellingSuggest(spelling.Dictionary dic) {
    	return new spelling.SpellingSuggestNW(new spelling.NearbyWords(dic));
    
    }
}
