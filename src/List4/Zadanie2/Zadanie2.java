package List4.Zadanie2;

public class Zadanie2 {

	public static void main(String[] args) {
		Game game = new Game();
		System.out.println(game.game(10, 2));
	}
}


class Game {
	private Element<Integer> head;
	private Element<Integer> tail;

	void addEnd(Integer e) {
		Element<Integer> temp = new Element(e);

		if (head == null) {
			head = temp;
		} else {
			tail.next = temp;
		}
		tail = temp;
		tail.next = head;
	}

	int size() {
		Element<Integer> element = head.next;
		int incrementer = 1;
		while (element != head) {
			incrementer++;
			element = element.next;
		}
		return incrementer;
	}

	void clear() {
		head = null;
		tail = null;
	}

	Element deletePos(int pos) {
		if (pos < 0) return null;
		Element<Integer> element = head;
		Element<Integer> temp = tail;

		for (int i = 0; i < pos; i++) {
			temp = element;
			element = element.next;
		}
		if (element.equals(head)) {
			head = element.next;
		}
		if (element.equals(tail)) {
			tail = temp;
		}
		temp.next = element.next;
		element.next = null;
		return element;
	}

	String game(int n, int k) {
		if (n < 0 || k < 1) return "error";
		if (n == 1) return "1";
		String result = "";
		for (int i = 1; i <= n; i++) {
			this.addEnd(i);
		}
		Element<Integer> element = head;
		int pointer = 0;
		while (this.size() > 1) {
			for (int i = 0; i < k - 1; i++) {
				if (element == head) pointer = 0;
				pointer++;
				element = element.next;
			}

			result += element.value + ", ";
			element = element.next;
			this.deletePos(pointer);
		}
		return result + element.value;

	}

}


class Element<E> {
	Element<E> next;
	E value;


	Element(E val) {
		value = val;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Element other = (Element) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Element value= " + value;
	}
}