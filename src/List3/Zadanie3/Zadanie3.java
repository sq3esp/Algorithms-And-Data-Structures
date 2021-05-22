package List3.Zadanie3;

public class Zadanie3 {

	public static void main(String[] args) {

		Stack<Integer> stack = new Stack<Integer>(10);

		try {
		System.out.println("Dodajemy element: 1");
		stack.push(1);
		System.out.println("Dodajemy element: 2");
		stack.push(2);
		System.out.println("Dodajemy element: 3");
		stack.push(3);
		System.out.println("Dodajemy element: 4");
		stack.push(4);
		System.out.println("Dodajemy element: 5");
		stack.push(5);
		System.out.println("Dodajemy element: 6");
		stack.push(6);
		System.out.println("Dodajemy element: 7");
		stack.push(7);
		System.out.println("Dodajemy element: 8");
		stack.push(8);
		System.out.println("Dodajemy element: 9");
		stack.push(9);
		System.out.println("Dodajemy element: 10");
		stack.push(10);
		System.out.println("Dodajemy element: 11");
		stack.push(11);
		System.out.println("Dodajemy element: 12");
		stack.push(12);
		} catch (FullStackException e) {
			System.out.println("/////Stos pełny/////");
		}

		System.out.println("\n");

		try {
			System.out.println("Zdejmujemy element: " + stack.pop());
			System.out.println("Zdejmujemy element: " + stack.pop());
			System.out.println("Zdejmujemy element: " + stack.pop());
			System.out.println("Zdejmujemy element: " + stack.pop());
			System.out.println("Zdejmujemy element: " + stack.pop());
			System.out.println("Zdejmujemy element: " + stack.pop());
			System.out.println("Zdejmujemy element: " + stack.pop());
			System.out.println("Zdejmujemy element: " + stack.pop());
			System.out.println("Zdejmujemy element: " + stack.pop());
			System.out.println("Zdejmujemy element: " + stack.pop());
			System.out.println("Zdejmujemy element: " + stack.pop());
			System.out.println("Zdejmujemy element: " + stack.pop());
			System.out.println("Zdejmujemy element z pustej kolejki: " + stack.pop());

		} catch (EmptyStackException e) {
			System.out.println("Kolejka pusta");
		}
	}
}


class Stack<E> {
	private int index;
	private Queue<E> queue[] = new Queue[2];


	public Stack(int length) {
		queue[0] = new Queue<>(length);
		queue[1] = new Queue<>(length);
		index = 0;
	}

	public void push(E x) throws FullStackException {
		try {
			queue[0].add(x);
		} catch (FullQueueException e) {
			throw new FullStackException();
		}
		while (!queue[1].isEmpty()) {
			try {
				queue[0].add(queue[1].remove());
			} catch (EmptyQueueException e) {
				e.printStackTrace();
			} catch (FullQueueException e) {
				throw new FullStackException();
			}
			swap();
		}
		index++;
	}

	private void swap() {
		Queue<E> temp = queue[0];
		queue[0] = queue[1];
		queue[1] = temp;
	}

	public boolean isEmpty() {
		return index == 0;
	}

	public E pop() throws EmptyStackException {

		if (queue[0].isEmpty()) {
			throw new EmptyStackException();
		}
		try {
			index--;
			return queue[0].remove();
		} catch (EmptyQueueException e) {
			index++;
			throw new EmptyStackException();
		}



	}

	public E peek() throws EmptyStackException {
		if (index > 0) {
			try {
				return queue[0].look();
			} catch (EmptyQueueException e) {
				throw new EmptyStackException();
			}
		}
		throw new EmptyStackException();

	}

}

class EmptyStackException extends Throwable {

	public EmptyStackException() {
	}
}

class FullStackException extends Throwable {

	public FullStackException() {
	}
}


class Queue<E> {
	private E table[];
	private int index = 0;
	private int maxSize;

	public Queue(int lenght) {
		this.table = (E[]) (new Object[lenght]);
		maxSize = table.length;
	}

	public void add(E x) throws FullQueueException {
		if (index < maxSize) {
			table[index] = x;
			index++;
		} else {
			throw new FullQueueException();
		}
	}

	public boolean isFull() {
		return index <= maxSize;
	}

	public E remove() throws EmptyQueueException {
		if (index > 0) {
			E temp = table[0];
			index--;
			for (int i = 0; i < index; i++) {
				table[i] = table[i + 1];
			}
			return temp;
		}
		throw new EmptyQueueException();
	}

	public E look() throws EmptyQueueException {
		if (index > 0) {
			return table[0];
		}
		throw new EmptyQueueException();
	}

	public boolean isEmpty() {
		return index == 0;
	}
}

class FullQueueException extends Throwable {

	public FullQueueException() {
	}
}

class EmptyQueueException extends Throwable {

	public EmptyQueueException() {
	}
}