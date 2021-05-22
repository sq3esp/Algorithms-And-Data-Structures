package List4.Zadanie4;

public class Zadanie4 {

	public static void main(String[] args) {
		BidirectionalList<Integer> list1 = new BidirectionalList();
		BidirectionalList<Integer> list2 = new BidirectionalList();
		for (int i = 0; i < 5; i++) {
			list1.addEnd(i);
			System.out.println("Dodajemy element " + i + " do listy 1");
		}
		for (int i = 5; i < 10; i++) {
			list2.addEnd(i);
			System.out.println("Dodajemy element " + i + " do listy 2");
		}
		System.out.println("\nWyświetlamy liste 1:");
		list1.print();
		System.out.println("\nWyświetlamy liste 2:");
		list2.print();
		System.out.println("Łączymy obie listy:");
		list1.merge(list2);
		System.out.println("\nWyświetlamy liste 1:");
		list1.print();
		System.out.println("\nCzyślimy listę 1 i dodajemy elementy");
		list1.clear();
		for (int i = 0; i < 5; i++) {
			list1.addEnd(i);
		}
		System.out.println("\nWyświetlamy liste 1:");
		list1.print();
		System.out.println("Łączymy obie listy na elemencie 1:");
		list1.merge(list2, 1);
		System.out.println("\nWyświetlamy liste 1:");
		list1.print();
	}
}


class BidirectionalList<E> {
	Element head;
	Element sentinel;

	BidirectionalList() {
		sentinel = new Element<E>(null);
		head = new Element<E>(null);
		head.setNext(sentinel);
		sentinel.setPrev(head);
	}

	void clear() {
		head.setNext(sentinel);
		sentinel.setPrev(head);
	}

	public int size() {
		Element e = sentinel.getNext();

		int i = 0;
		while (e != sentinel) {
			i++;
			e = e.getNext();
		}
		return i;
	}

	public boolean addEnd(E val) {
		Element newE = new Element(val);

		sentinel.getPrev().setNext(newE);
		newE.setPrev(sentinel.getPrev());
		sentinel.setPrev(newE);
		newE.setNext(sentinel);
		return true;
	}

	private Element getElement(E value) {
		Element e = head.getNext();

		while (e != sentinel && !value.equals(e.getValue())) {
			e = e.getNext();
		}
		if (e == sentinel) return null;
		return e;
	}

	private Element getElement(int index) {
		Element e = head.getNext();
		int i = 0;
		while (e != sentinel && i < index) {
			i++;
			e = e.getNext();
		}
		if (e == sentinel)
			throw new IndexOutOfBoundsException();
		return e;
	}

	public boolean add(int index, E val) {
		Element newE = new Element(val);
		if (index == 0) {
			newE.setNext(head.getNext());
			head.setNext(newE);
		} else {
			Element elem = getElement(index - 1);
			newE.setNext(elem.getNext());
		}
		return true;
	}

	public E set(int index, E val) {
		Element e = getElement(index);
		E result = (E) e.getValue();
		e.setValue(val);
		return result;
	}

	void merge(BidirectionalList list) {
		this.sentinel.getPrev().setNext(list.head.getNext());
		list.head.getNext().setPrev(this.sentinel);
		list.sentinel.getPrev().setNext(sentinel);
	}

	void merge(BidirectionalList list, int e) {
		Element before = getElement(e);
		list.sentinel.getPrev().setNext(before);
		before.getPrev().setNext(list.head.getNext());
	}

	void print() {
		Element e = head.getNext();
		while (e != sentinel) {
			System.out.println(e.getValue());
			e = e.getNext();
		}
	}
}

class Element<E> {
	private Element next;
	private Element prev;
	private E val;

	public Element getNext() {
		return next;
	}

	public void setNext(Element next) {
		this.next = next;
	}

	public Element getPrev() {
		return prev;
	}

	public void setPrev(Element prev) {
		this.prev = prev;
	}

	public E getValue() {
		return val;
	}

	public void setValue(E value) {
		this.val = value;
	}

	public Element(Element next, Element prev, E val) {
		this.next = next;
		this.prev = prev;
		this.val = val;
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
		if (next == null) {
			if (other.next != null) {
				return false;
			}
		} else if (!next.equals(other.next)) {
			return false;
		}
		if (prev == null) {
			if (other.prev != null) {
				return false;
			}
		} else if (!prev.equals(other.prev)) {
			return false;
		}
		if (val == null) {
			if (other.val != null) {
				return false;
			}
		} else if (!val.equals(other.val)) {
			return false;
		}
		return true;
	}

	public Element(E val) {
		this.val = val;
	}

}