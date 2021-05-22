package List4.Zadanie1;

public class Zadanie1 {

	public static void main(String[] args) {
		CyclicList<Integer> list = new CyclicList();
		System.out.println("Dodajemy element 10");
		list.addEnd(10);
		for (int i = 1; i < 10; i++) {
			list.addEnd(i);
			System.out.println("Dodajemy element " + i);
		}
		System.out.println("Sprawdzamy rozmiar listy: " + list.size());
		System.out.println("Wstawiamy elemetn 20 na pozycji 5");
		list.insert(5, 20);
		System.out.println("Sprawdzamy rozmiar listy: " + list.size());
		System.out.println("Sprawdzamy element stojący na pozycji 5: " + list.get(5));
		System.out.println("Usuwamy element z pozycji 5: " + list.deletePos(5));
		System.out.println("Sprawdzamy element stojący na pozycji 5: " + list.get(5));
		System.out.println("Czy lista zawiera 5? " + list.contains(5));
		System.out.println("Usuwany element o wartości 5: " + list.delete(9));
		System.out.println("Sprawdzamy element stojący na pozycji 5: " + list.get(5));
		System.out.println("Ustawiamy element na pozycji 5 na wartość 5, stara wartość: " + list.set(5, 5));
		System.out.println("Sprawdzamy rozmiar listy: " + list.size());
		System.out.println("Usuwamy nowy element o wartości 9: " + list.deleteEl(new Element(9)));
		System.out.println("\nWyświetlamy listę: ");
		list.wyswietlListe();

	}
}


class CyclicList<E> {
	private Element<E> head;
	private Element<E> tail;

	void addEnd(E e) {
		Element<E> temp = new Element(e);

		if (head == null) {
			head = temp;
		} else {
			tail.next = temp;
		}
		tail = temp;
		tail.next = head;
	}

	void insert(int pos, E e) {
		Element<E> temp1 = head;
		Element<E> temp2 = tail;

		if (pos < 0) return;

		for (int i = 0; i < pos; i++) {
			temp2 = temp1;
			if (temp1.next == null) return;
			temp1 = temp1.next;
		}

		Element temp = new Element(e);

		temp.next = temp1;
		if (pos == 0) {
			head = temp;
		}
		temp2.next = temp;
	}

	E get(int pos) {
		if (pos < 0) return null;
		Element<E> result = head;

		for (int i = 0; i < pos; i++) {
			result = result.next;
			if (result == null) return null;
		}
		return result.value;
	}

	E set(int pos, E e) {
		if (pos < 0) return null;
		Element<E> element = head;

		for (int i = 0; i < pos; i++) {
			element = element.next;
			if (element == null) return null;
		}
		E temp = element.value;

		element.value = e;
		return temp;
	}

	boolean contains(E e) {
		if (head.value.equals(e)) return true;
		Element<E> element = head.next;

		while (element != head) {
			if (element.value.equals(e)) return true;
			element = element.next;
		}
		return false;
	}

	int size() {
		Element<E> element = head.next;
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
		Element<E> element = head;
		Element<E> temp = tail;

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

	boolean delete(E e) {
		Element<E> temp = head;
		Element<E> end = tail;

		do {
			if (temp.value.equals(e)) {
				if (temp.equals(head)) {
					head = temp.next;
				}
				if (temp.equals(tail)) {
					tail = end;
				}

				end.next = temp.next;
				temp.next = null;
				return true;
			}
			end = temp;
			temp = temp.next;
		} while (temp != head);
		return false;
	}

	E deleteEl(Element e) {
		Element<E> temp = head;
		Element<E> end = tail;

		do {
			if (temp.equals(e)) {
				if (temp.equals(head)) {
					head = temp.next;
				}
				if (temp.equals(tail)) {
					tail = end;
				}
				end.next = temp.next;
				temp.next = null;
				head = tail.next;
				return temp.value;
			}
			end = temp;
			temp = temp.next;
		} while (temp != head);
		return null;
	}

	void wyswietlListe() {
		Element<E> temp = head;
		System.out.println(temp.value);
		temp = temp.next;
		while (temp != head) {
			System.out.println(temp.value);
			temp = temp.next;
		}

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