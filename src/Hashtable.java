

import java.util.Iterator;
import java.util.function.Consumer;


public class Hashtable<K, V> {
	private Entry<K,V>[] values;
	private int size;

	private int positiveMod(int num,int range) {
		return num % range + (num < 0 ? range-1 : 0);
	}
	
	public Hashtable(int initialCapacity) {
		values = (Entry<K,V>[])new Entry[initialCapacity];
	}
	public Hashtable() { this(42 * 42); }
	
	/**
	 * #3b. Implement this (1 point)
	 * 
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {
		int index = positiveMod(key.hashCode() , values.length);
		Entry<K,V> current = values[index];
		int added = 0;
		if(current == null) {
			values[index] = new Entry<K,V>(key,value);
			added++;
		} else {
			while(current.next != null && !current.key.equals(key)) current = current.next; // walkin
			if(!current.key.equals(key) ) {
				current.next = new Entry<K, V>(key, value);
				added++;
			} else {
				current.data = value;
			}
		}
		size += added;
	}
	
	/**
	 * #3b. Implement this (1 point)
	 * @param key
	 * @return
	 */
	public V get(K key) {
		int index = positiveMod(key.hashCode() , values.length);
		Entry<K,V> current = values[index];
		if(current != null) {
			while(current != null && !current.key.equals(key)) {
				current = current.next;
			}
			if(current == null) return null;
			return current.data;
		}
		return null;
	}

	/**
	 * #3c.  Implement this. (1 point)
	 * 
	 * @param key
	 * @return
	 */
	public V remove(K key) {
		int index = positiveMod(key.hashCode() , values.length);
		Entry<K,V> current  = values[index];
		Entry<K,V> previous = null;

		if(current != null) {
			while(current != null && !current.key.equals(key)) {
				previous = current;
				current = current.next;
			}
			if(current != null) {
				if (previous != null) {
					previous.next = null;
				} else {
					previous.next = current.next;
				}
				return current.data;
			}
		}
		return null;
	}
	
	public int size() {
		return size;
	}
	
	public boolean containsKey(K key) {
		return this.get(key) != null; 
	}

	public Iterator<V> values() {
		return new Iterator<V>() {
			private int count = 0;
			private Entry<K, V> currentEntry;
			{
				while ( ( currentEntry = values[count] ) == null && count < values.length ) {
					count++;
				}
			}
			
			@Override
			public void forEachRemaining(Consumer<? super V> arg0) {
			}

			@Override
			public boolean hasNext() {
				return count < values.length;
			}

			@Override
			public V next() {
				V toReturn = currentEntry.data;
				currentEntry = currentEntry.next;
				while ( currentEntry == null && ++count < values.length && (currentEntry = values[count]) == null );
				return toReturn;
			}

			@Override
			public void remove() {
			}
			
		};
	}

	private static class Entry<K, V> {
		private K key;
		private V data;
		private Entry<K,V> next;
		
		public Entry(K key, V data) {
			this.key = key;
			this.data = data;
		}
		
		public String toString() {
			return "{" + key + "=" + data + "}";
		}
	}
}