package List2.Zadanie2;

public class Zadanie2 {

	public static void main(String[] args) {

		//podpunkt a
		//tworzymy iterator int w zakresie [0;10);

		NumberIterator iterator = new NumberIterator(0, 10);

		//wyświetlamy wszystkie dostępne liczby

		System.out.println("Wynik wyświetlania liczb w zakresie [0;10)");

		while (!iterator.isDone()) {
			System.out.println(iterator.currentItem());
			iterator.next();
		}

		//potpunkt b
		//wyświetlamy liczby pierwsze w przedziale [0;100)
		System.out.println("\n\nWyświetlamy liczby pierwsze w zakresie [0;100) przy użyciu iteratora filtrującego");

		FilterIterator filterIterator = new FilterIterator(new NumberIterator(0, 100), new Predicate() {
			public boolean accept(Object arg) {
				{
					if ((int) arg < 2) {
						return false;
					}
					for (int i = 2; i <= (int) arg / 2; i++) {
						if ((int) arg % i == 0) {
							return false;
						}
					}
					return true;
				}
			}
		});

		while (!filterIterator.isDone()) {
			System.out.println(filterIterator.currentItem());
			filterIterator.findNextValid();
		}
	}
}


interface Iterator<T> {
	void first();

	void next();

	boolean isDone();

	int currentItem();
}


class NumberIterator implements Iterator {
	private int start;
	private int end;
	private int current;

	@Override
	public void first() {
		current = start;
	}

	@Override
	public void next() {
		if (!isDone()) {
			current++;
		}
	}

	@Override
	public boolean isDone() {
		return current == end;
	}

	@Override
	public int currentItem() {
		return (Integer) current;
	}

	public NumberIterator(int start, int end) {
		this.start = start;
		this.end = end;
		first();
	}
}


interface Predicate<T> {
	boolean accept(T arg);
}

class FilterIterator implements Iterator {
	private Iterator iterator;
	private Predicate predicate;
	private int current;
	private boolean hasNext;

	public FilterIterator(Iterator iterator, Predicate predicate) {
		this.iterator = iterator;
		this.predicate = predicate;
		this.findNextValid();
		current = iterator.currentItem();

	}

	public int currentItem() {
		return current;
	}

	public void first() {
		iterator.first();
		current = iterator.currentItem();
	}

	public void next() {
		iterator.next();
		current = iterator.currentItem();
	}

	public boolean isDone() {
		return iterator.isDone();
	}

	public boolean hasNext() {
		return hasNext;
	}

	public void findNextValid() {
		while (!iterator.isDone()) {
			iterator.next();
			current = iterator.currentItem();
			if (predicate.accept(current)) {
				return;
			}

		}
		hasNext = false;
	}


}