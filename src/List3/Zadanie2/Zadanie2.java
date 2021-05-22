package List3.Zadanie2;

public class Zadanie2 {

	public static void main(String[] args) {

		Stack<Integer> stack = new Stack<Integer>();

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
	private E table[];
	private int index;

	public Stack() {
		this.table = (E[]) (new Object[4]);
		index = 0;
	}

	public void push(E x) {
		if (table.length * 3 / 4 <= index + 1) {
			expand();
		}
		table[index] = x;
		index++;
	}

	public boolean isEmpty() {
		return index == 0;
	}

	public E pop() throws EmptyStackException {
		if (index <= table.length / 4 && table.length > 4) {
			reduce();
		}
		if (index > 0) {
			index--;
			return table[index];
		}
		throw new EmptyStackException();
	}

	public E peek() throws EmptyStackException {
		if (index > 0) {
			return table[index - 1];
		}
		throw new EmptyStackException();
	}

	private void expand() {
		System.out.println("/////Powiększam tablicę///// Nowy rozmiar:" + table.length * 2);//dodano w celu ułatwienia pokazania pracu kodu
		E temp[] = (E[]) new Object[table.length * 2];
		for (int i = 0; i < table.length; i++) {
			temp[i] = table[i];
		}
		table = temp;
	}

	private void reduce() {
		System.out.println("/////Zmniejszam tablicę///// Nowy rozmiar:" + table.length / 2);//dodano w celu ułatwienia pokazania pracu kodu
		E temp[] = (E[]) new Object[table.length / 2];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = table[i];
		}
		table = temp;
	}

}

class EmptyStackException extends Throwable {

	public EmptyStackException() {
	}
}