package List4.Zadanie3;

public class LinkedList<E> {
	Element head = null;

	public LinkedList() {
	}

	private class Element {
		E value;
		Element next;

		public Element(E value) {
			this.value = value;
			this.next = null;
		}

		public Element(E value, Element next) {
			this.value = value;
			this.next = next;
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Element getNext() {
			return next;
		}

		public void setNext(Element next) {
			this.next = next;
		}
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void clear() {
		head = null;
	}

	public void add(E x) {
		Element newElement = new Element(x);
		if (head == null) {
			head = newElement;
			return;
		}
		Element end = head;
		while (end.getNext() != null) {
			end = end.getNext();
		}
		end.setNext(newElement);
	}

	private Element getElement(int index) {
		Element temp = head;
		while (index > 0 && temp != null) {
			index--;
			temp = temp.getNext();
		}
		return temp;
	}

	public E remove(int index) {
		if (head == null) {
			return null;
		}

		if (index == 0) {
			E result = head.getValue();
			head = head.getNext();
			return result;
		}

		Element element = getElement(index - 1);

		if (element == null || element.getNext() == null) {
			return null;
		}
		E result = element.getNext().getValue();
		element.setNext(element.getNext().getNext());
		return result;
	}

	public E remove() {
		if (head == null) {
			return null;
		}
		E result;
		if (head.getNext() == null) {
			result = head.getValue();
			head = null;
			return result;
		}
		Element element = getElement(0);
		while (element.getNext().getNext() != null) {
			element = element.getNext();
		}
		result = element.getNext().getValue();
		element.setNext(null);
		return result;
	}

	public E peekEnd() {
		if (head == null) {
			return null;
		}
		if (head.getNext() == null) {
			return head.getValue();
		}
		Element element = getElement(0);
		while (element.getNext() != null) {
			element = element.getNext();
		}
		return element.getValue();
	}

	public E peekHead() {
		if (head == null) {
			return null;
		}
		return head.getValue();
	}




}
