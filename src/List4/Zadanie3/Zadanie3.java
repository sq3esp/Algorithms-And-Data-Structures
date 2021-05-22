package List4.Zadanie3;

public class Zadanie3 {
	public static void main(String[] args) {

		Stack<Integer> stack = new Stack<>();
		System.out.println("Dodajemy element: 1");
		stack.push(1);
		System.out.println("Dodajemy element: 2");
		stack.push(2);
		System.out.println("Dodajemy element: 3");
		stack.push(3);
		System.out.println("Dodajemy element: 4");
		stack.push(4);

		System.out.println("Podglądamy pierwszy element: " + stack.peek());
		System.out.println("Zdejmujemy kolejny element: " + stack.pop());
		System.out.println("Zdejmujemy kolejny element: " + stack.pop());
		System.out.println("Zdejmujemy kolejny element: " + stack.pop());
		System.out.println("Zdejmujemy kolejny element: " + stack.pop());
		System.out.println("Zdejmujemy pusty element: " + stack.pop()); //można alternatywnie wyrzucać błąd


		System.out.println("\n\n\n\n\n");



		Queue<Integer> queue = new Queue<>();
		System.out.println("Dodajemy element: 1");
		queue.push(1);
		System.out.println("Dodajemy element: 2");
		queue.push(2);
		System.out.println("Dodajemy element: 3");
		queue.push(3);
		System.out.println("Dodajemy element: 4");
		queue.push(4);

		System.out.println("Podglądamy pierwszy element: " + queue.peek());
		System.out.println("Zdejmujemy kolejny element: " + queue.pop());
		System.out.println("Zdejmujemy kolejny element: " + queue.pop());
		System.out.println("Zdejmujemy kolejny element: " + queue.pop());
		System.out.println("Zdejmujemy kolejny element: " + queue.pop());
		System.out.println("Zdejmujemy pusty element: " + queue.pop()); //można alternatywnie wyrzucać błąd
	}
}


