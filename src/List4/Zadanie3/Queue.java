package List4.Zadanie3;

public class Queue<E> {
	LinkedList<E> list = new LinkedList<>();

	public Queue() {
	}

	public void push(E x) {
		list.add(x);
	}

	public E pop() {
		return list.remove(0);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public E peek() {
		return list.peekHead();
	}
}
