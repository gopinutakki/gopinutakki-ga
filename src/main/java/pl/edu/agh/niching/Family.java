package pl.edu.agh.niching;

/**
 * A "family" of related individuals, consisting of two parent items and their children. 
 * 
 * @param <T> Family members' type
 */
public class Family<T> {
	private T parent1;
	private T parent2;
	private T child1;
	private T child2;
	
	/**
	 * Getter function for parent #1
	 * @return the first parent
	 */
	public T getParent1() {
		return parent1;
	}
	
	/**
	 * Sets the 1st parent
	 * @param parent1 the parent #1 to set
	 */
	public void setParent1(T parent1) {
		this.parent1 = parent1;
	}
	
	/**
	 * Getter function for parent #2
	 * @return the second parent
	 */
	public T getParent2() {
		return parent2;
	}
	
	/**
	 * Sets the 2nd parent
	 * @param parent2 the parent #2 to set
	 */
	public void setParent2(T parent2) {
		this.parent2 = parent2;
	}
	
	/**
	 * Getter function for child #1
	 * @return the 1st child
	 */
	public T getChild1() {
		return child1;
	}
	
	/**
	 * Sets child #1
	 * @param child1 the child #1 to set
	 */
	public void setChild1(T child1) {
		this.child1 = child1;
	}
	
	/**
	 * Getter function for child #2
	 * @return the 2nd child
	 */
	public T getChild2() {
		return child2;
	}
	
	/**
	 * Sets child #2
	 * @param child2 the child #2 to set
	 */
	public void setChild2(T child2) {
		this.child2 = child2;
	}
}
