package List4.Zadanie3;

public class Stack<E> {
	LinkedList<E> list = new LinkedList<>();

	public Stack() {
	}

	public void push(E x) {
		list.add(x);
	}

	public E pop() {
		return list.remove();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public E peek() {
		return list.peekEnd();
	}
}
