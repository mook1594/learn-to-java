package effective.java.chapter5.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> {
	private E[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	@SuppressWarnings("unchecked")
	public Stack() {
		// elements = new E[DEFAULT_INITIAL_CAPACITY]; <- 컴파일 에러
		elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(E e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public E pop() {
		if(size == 0)
			throw new EmptyStackException();

		@SuppressWarnings("unchecked")
		E result = (E) elements[--size];
		elements[size] = null;
		return result;
	}

	private void ensureCapacity() {
		if(elements.length == size){
			elements = Arrays.copyOf(elements, (2 * size) + 1);
		}
	}
}
