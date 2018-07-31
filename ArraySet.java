import java.util.Iterator;

/*
 * Preston McIllece and Sean Yan's Lab11
 * 
 * This program creates methods for an ArraySet, an implementation of a Set using generics 
 * and iterators.
 * 
 */



//generics
public class ArraySet<T> implements Set<T> {
	public static final int DEFAULT_SIZE = 10;
	public static final int CAPACITY_MULTIPLIER = 2;
	private int capacity;
	private T[] stuff;
	private int numberOfElements;
	
	//default constructor
	public ArraySet() {
		this(DEFAULT_SIZE);
	}

	//constructor that sets the capacity of the set
	public ArraySet(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity must be >= 0");
		}
		this.capacity = capacity;
		stuff = (T[])new Object[capacity];
	}
	
	//add method, adds to the end of the array
	@Override
	public void add(T element) {
		ensureCapacity();
		boolean check = false;
		for (int i = 0; i < numberOfElements; i++) {
			if (element == stuff[i]) 
				check = true;
		}
		if (check == false) {
		stuff[numberOfElements] = element;
		numberOfElements++;
		}
	}

	//adds all of the elements of the given array at the end
	@Override
	public void addAll(T[] elements) {
		for (int i = 0; i < elements.length; i++) 
				add(elements[i]);
	}

	//checks if the ArraySet contains the given element
	@Override
	public boolean contains(T element) {
		boolean check = false;	
		for (int i = 0; i < stuff.length; i++) {
			if (element == stuff[i])
				check = true;
		}
		return check;
		
	}

	//returns the number of elements in the ArraySet
	@Override
	public int getSize() {
		return numberOfElements;
	}

	//takes the given element out of the ArraySet and shifts all other elements down 1
	@Override
	public void remove(T element) {	
		int index = indexOf(element);
		
		if (index > -1) {
			numberOfElements--;
			stuff[index] = stuff[numberOfElements];
		}

		return;
	}

	//combines 2 Sets
	@Override
	public Set union(Set anotherSet) {
		Set s3 = new ArraySet();
		s3 = anotherSet.difference(this);
		
		for (int i = 0; i < numberOfElements; i++)
		{
			s3.add(stuff[i]);
		}
		
		return s3;
	}

	//returns the elements that are contained in both Sets
	@Override
	public Set intersection(Set anotherSet) {
		Set s3 = new ArraySet();	
		
		for (int i = 0; i < numberOfElements; i++)
		{
			if (anotherSet.contains(stuff[i]))
				s3.add(stuff[i]);
		}
		
		return s3;
	}

	//returns the elements that are in current ArraySet and NOT in the given Set
	@Override
	public Set difference(Set anotherSet) {
		Set s3 = new ArraySet();
		
		for (int i = 0; i < numberOfElements; i++)
		{
			if (anotherSet.contains(stuff[i]) == false)
				s3.add(stuff[i]);
				
		}
		
		return s3;
	}

	/**
	 * This ensures the array has sufficient capacity to store an additional element.
	 * 
	 * If the bag is full, a new array is created that is CAPACITY_MULTIPLIER times
	 * larger than the original array. All existing elements are copied to the
	 * new array.
	 */
	private void ensureCapacity() {
		if (numberOfElements == capacity) {
			T[] newArray = (T[]) new Object[(numberOfElements+1) * CAPACITY_MULTIPLIER];
			System.arraycopy(stuff,0,newArray,0,numberOfElements);
			stuff = newArray;
		}
	}
	
	//returns the index of a given element
	private int indexOf(T element) {
		int index = -1;
		for (int i = 0; i < numberOfElements; i++) {
			if (stuff[i].equals(element)) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	//iterator method that creates a new array set iterator
	public Iterator<T> iterator() {
		return new ArraySetIterator();
	}
	
	//nested class
	private class ArraySetIterator implements Iterator<T>
	{
		private int index = 0;
		
		/**
		 * Determines if there are more elements
		 * in the iteration.
		 * 
		 * @return true if there are more elements, false otherwise.
		 */
		public boolean hasNext() {
			if (index < numberOfElements)
				return true;
			else
				return false;
		}

		/**
		 * Returns the next element in the iteration.
		 * 
		 * @throws java.util.NoSuchElementException if there are no more elements in the iteration.
		 */
		public T next() {
			if (hasNext()) {
				T nextItem = stuff[index];
				index++;
				
				return nextItem;
			}
			else
				throw new java.util.NoSuchElementException("No items remaining in the iteration.");
			
		}

		/**
		 * The remove() operation is not supported.
		 * @throws UnsupportedOperationException if involed.
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}
