/**
 * Interface for dictionary operations
 *
 * @author Greg Gagne
 */

public interface DictionaryInterface <K, V>
{
    /**
     * Put the key:value mapping in the dictionary
     *
     * @return The value mapped to this key. If the key is
     * mapped to an existing value, the  
     * mapping is updated to the new value.
     */
    public V put(K key, V value);

    /**
     * Get the value mapped to the specified key.
     *
     * @return V the value mapped to the key, or null if no mapping exists.
     */
    public V get(K key);

    /**
     * Determines if the dictionary contains the specified key.
     *
     * @return boolean - True if the dictionary contains the key, false otherwise.
     */
    public boolean contains(K key);

    /**
     * Removes the specified mapping from the dictionary.
     *
     * @return V - The value mapped to the removed key, or null if no mapping exists.
     */
    public V remove(K key);
    
    /**
     * Returns the number of [key:value] mappings
     */
    public int size();

    /**
     * Returns a set of the keys in the dictionary
     */
    public Set<K> keySet();

    /**
     * Returns a set of the values in the dictionary
     */
    public Set<V> valueSet();
}
