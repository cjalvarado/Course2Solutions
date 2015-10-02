package master_do_not_use;

import java.util.List;

public interface SpellingSuggest {

	public List<String> suggestions(String word, int numSuggestions);
	
}
