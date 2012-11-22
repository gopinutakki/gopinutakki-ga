package pl.edu.agh.niching;

public class Family<T> {
	T Parent1;
	T Parent2;
	T Child1;
	T Child2;
	public T getParent1() {
		return Parent1;
	}
	public void setParent1(T parent1) {
		Parent1 = parent1;
	}
}
