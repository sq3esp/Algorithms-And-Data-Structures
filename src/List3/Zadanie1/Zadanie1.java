package List3.Zadanie1;

public class Zadanie1 {

	public static void main(String[] args) {

		Queue<Integer> queue = new Queue<Integer>(10);


		try {
			System.out.println("Dodajemy 10 elementów(rozmiar kolejki 10):");
			for (int i = 10; i < 20; i++) {
				queue.add(i);
				System.out.println("Element: " + i);
			}
			System.out.println("Sprawdzamy, czy kolejka jest pełna: " + queue.isFull());
			System.out.println("Przepełniamy kolejkę:");
			queue.add(851);
		} catch (FullQueueException e) {
			System.out.println("\n/////Kolejka pełna//////\n");
		}


		try {
			System.out.println("Zdejmujemy po kolei 10 elmentów");
			for (int i = 0; i < 10; i++) {
				System.out.println(queue.remove());
			}
			System.out.println("Dodajemy jeden element(5)");
			queue.add(5);
			System.out.println("Podglądamy pierwszy element bez zdjęcia: " + queue.look());
			System.out.println("Zdejmujemy element: ");
			System.out.println(queue.remove());
			System.out.println("Zdejmujemy element z pustej kolejki");
			queue.remove();
		} catch (EmptyQueueException e) {
			System.out.println("\n/////Kolejka pusta//////\n");
		} catch (FullQueueException e) {
			System.out.println("\n/////Kolejka pełna//////\n");
		}
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
}

class FullQueueException extends Throwable {

	public FullQueueException() {
	}
}

class EmptyQueueException extends Throwable {

	public EmptyQueueException() {
	}
}