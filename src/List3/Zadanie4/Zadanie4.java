package List3.Zadanie4;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Zadanie4 {
	public static void main(String[] args) {

		Stack<Integer> stack = new Stack<Integer>();
		Calculator calculator = new Calculator();
		System.out.println("Odczytujemy wyrażenie z pliku plik.txt:");
		calculator.read("src/List3/Zadanie4/plik.txt");
		System.out.println("Przetwarzamy postac infiksową na postfiksową:");
		calculator.convert();
		System.out.println("Obliczamy wartość wyrażenia:");
		System.out.println(calculator.calculate());
		System.out.println("Wyświetlamy obie postacie:");
		calculator.show();

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
		E temp[] = (E[]) new Object[table.length * 2];
		for (int i = 0; i < table.length; i++) {
			temp[i] = table[i];
		}
		table = temp;
	}

	private void reduce() {
		E temp[] = (E[]) new Object[table.length / 2];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = table[i];
		}
		table = temp;
	}

	public int size() {
		return index;
	}
}

class EmptyStackException extends Throwable {

	public EmptyStackException() {
	}
}

class Queue<E> {
	private ArrayList list;

	public Queue() {
		list = new ArrayList();
	}

	public void push(E x) {
		list.add(x);
	}

	public E pop() {
		return (E) list.remove(0);
	}

	public E peek() {
		return (E) list.get(0);
	}

	public int size() {
		return list.size();
	}
}

class Calculator {
	private String infix, postfix;

	void read(String file) {
		try (Scanner s = new Scanner(new File(file))) {
			infix = s.nextLine();
		} catch (Exception e) {
			System.out.println("Błąd odczytu");
		}
	}

	int priority(String x) {
		if (x.charAt(0) == '-' || x.charAt(0) == '+') return 0;
		if (x.charAt(0) == '*' || x.charAt(0) == '/') return 1;
		return -1;
	}

	boolean number(String x) {
		try {
			Double.parseDouble(x);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	void convert() {
		Queue<String> queue = new Queue<>();
		Stack<String> stack = new Stack<>();
		String result = "", temp = infix;

		for (String x : temp.split(" ")) {
			queue.push(x);
		}

		try {
			while (queue.size() != 0) {
				String element = queue.pop();
				if (priority(element) >= 0) {
					while (true) {
						if (stack.isEmpty() == true || priority(stack.peek()) < priority(element)) break;
						result += stack.pop();
					}
					stack.push(element);
				} else if (element.equals(")")) {
					String x = stack.pop();
					while (!x.equals("(")) {
						result += x + " ";
						x = stack.pop();
					}
				} else if (element.equals("(")) {
					stack.push(element);
				} else {
					result += element + " ";
				}

			}
			for (int i = 0; i <= stack.size(); i++) {
				result += stack.pop() + " ";
			}
			postfix = result;
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
	}

	public double calculate() {
		Stack<Double> stack = new Stack<>();
		try {
			for (String x : postfix.split(" ")) {
				if (number(x)) {
					stack.push(Double.parseDouble(x));
				} else {
					double a = stack.pop();
					double b = stack.pop();

					if (x.equals("+")) {
						stack.push(a + b);
					}
					if (x.equals("-")) {
						stack.push(a - b);
					}
					if (x.equals("*")) {
						stack.push(a * b);
					}
					if (x.equals("/")) {
						stack.push(a / b);
					}
				}
			}


			return stack.pop();
		} catch (EmptyStackException e) {
			e.printStackTrace();
			return 0;
		}

	}


	public void show() {
		System.out.println("Infiks: " + infix);
		System.out.println("Postfiks: " + postfix);
	}
}

