/**
 * 
 */
package master_do_not_use;

import java.util.List;

/**
 * @author Christine
 *
 */
public interface AutoComplete {
	public List<String> predictCompletions(String prefix, int numCompletions);
}
