package List5;

import java.util.Comparator;

public class Zadanie1 {

	public static void main(String[] args) {
		Osoba[] table = new Osoba[8];
		table[0] = new Osoba("Kowalski", "Adam", 2000);
		table[1] = new Osoba("Kowalski", "Adam", 2010);
		table[2] = new Osoba("Kowalski", "Patryk", 2000);
		table[3] = new Osoba("Kowalski", "Patryk", 2010);
		table[4] = new Osoba("Malinowski", "Adam", 2000);
		table[5] = new Osoba("Malinowski", "Adam", 2010);
		table[6] = new Osoba("Malinowski", "Patryk", 2000);
		table[7] = new Osoba("Malinowski", "Patryk", 2010);


		System.out.println("\nPorównujemy przy użyciu comparatora prostego, wedle nazwiska, sortując przez wstawienie:");
		ArraySorter insertion = new InsertionSort(new CompareBySurname());
		table = insertion.sort(table);
		for (Osoba x : table) {
			System.out.println(x);
		}


		System.out.println("\nPorównujemy przy użyciu comparatora prostego, wedle imienia, sortując bombelkowo:");
		ArraySorter bubble = new BubbleSort(new CompareByName());
		table = bubble.sort(table);
		for (Osoba x : table) {
			System.out.println(x);
		}


		System.out.println("\nPorównujemy przy użyciu comparatora prostego, wedle roku urodzenia, sortując przez wybór:");
		ArraySorter selection = new InsertionSort(new CompareByYear());
		table = selection.sort(table);
		for (Osoba x : table) {
			System.out.println(x);
		}

		System.out.println("\nPorównujemy przy użyciu comparatora prostego, wedle  złożonej metody - rok, imie, nazwisko, sortując przez wybór:");
		selection.changeComparator(new CompareByYear().thenComparing(new CompareByName()).thenComparing(new CompareBySurname()));
		table = selection.sort(table);
		for (Osoba x : table) {
			System.out.println(x);
		}

		System.out.println("\nPorównujemy przy użyciu comparatora prostego, wedle złożonej metody - nazwisko, imie, rok, sortując przez wybór:");
		selection.changeComparator(new CompoundComparatorSurnameNameYear());
		table = selection.sort(table);
		for (Osoba x : table) {
			System.out.println(x);
		}

		System.out.println("\nPorównujemy przy użyciu comparatora prostego, wedle złożonego komparatora - rok, imie, nazwisko, sortując przez wybór:");
		selection.changeComparator(new CompoundComparatorYearNameSurname());
		table = selection.sort(table);
		for (Osoba x : table) {
			System.out.println(x);
		}

	}
}


class BubbleSort implements ArraySorter {
	private Comparator<Osoba> _comparator;

	public BubbleSort(Comparator<Osoba> comparator) {
		_comparator = comparator;
	}

	public Osoba[] sort(Osoba[] table) {
		int n = table.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (_comparator.compare(table[j], table[j + 1]) > 0) {
					Osoba temp = table[j];
					table[j] = table[j + 1];
					table[j + 1] = temp;
				}
		return table;
	}

	@Override
	public void changeComparator(Comparator<Osoba> comparator) {
		_comparator = comparator;
	}
}


class SelectionSort implements ArraySorter {
	private Comparator<Osoba> _comparator;

	public SelectionSort(Comparator<Osoba> comparator) {
		_comparator = comparator;
	}

	public Osoba[] sort(Osoba[] table) {
		int n = table.length;

		for (int i = 0; i < n - 1; i++) {
			int min_idx = i;
			for (int j = i + 1; j < n; j++)
				if (_comparator.compare(table[min_idx], table[j]) > 0)
					min_idx = j;

			Osoba temp = table[min_idx];
			table[min_idx] = table[i];
			table[i] = temp;
		}
		return table;
	}

	@Override
	public void changeComparator(Comparator<Osoba> comparator) {
		_comparator = comparator;
	}
}


class InsertionSort implements ArraySorter {
	private Comparator<Osoba> _comparator;

	public InsertionSort(Comparator<Osoba> comparator) {
		_comparator = comparator;
	}

	public Osoba[] sort(Osoba[] table) {
		int n = table.length;
		for (int i = 1; i < n; ++i) {
			Osoba key = table[i];
			int j = i - 1;
			while (j >= 0 && _comparator.compare(table[j], key) > 0) {
				table[j + 1] = table[j];
				j = j - 1;
			}
			table[j + 1] = key;
		}
		return table;
	}

	@Override
	public void changeComparator(Comparator<Osoba> comparator) {
		_comparator = comparator;
	}
}


interface ArraySorter {
	public Osoba[] sort(Osoba[] table);

	public void changeComparator(Comparator<Osoba> comparator);
}

class Osoba {
	private String nazwisko;
	private String imie;
	private int rok_urodzenia;

	public Osoba(String nazwisko, String imie, int rok_urodzenia) {
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.rok_urodzenia = rok_urodzenia;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public int getRok_urodzenia() {
		return rok_urodzenia;
	}

	public void setRok_urodzenia(int rok_urodzenia) {
		this.rok_urodzenia = rok_urodzenia;
	}


	@Override
	public String toString() {
		return "Osoba{" +
				"nazwisko='" + nazwisko + '\'' +
				", imie='" + imie + '\'' +
				", rok_urodzenia=" + rok_urodzenia +
				'}';
	}
}


class CompareBySurname implements Comparator<Osoba> {
	@Override
	public int compare(Osoba o1, Osoba o2) {
		return o1.getNazwisko().compareTo(o2.getNazwisko());
	}
}

class CompareByName implements Comparator<Osoba> {
	@Override
	public int compare(Osoba o1, Osoba o2) {
		return o1.getImie().compareTo(o2.getImie());
	}
}

class CompareByYear implements Comparator<Osoba> {
	@Override
	public int compare(Osoba o1, Osoba o2) {
		return o1.getRok_urodzenia() - o2.getRok_urodzenia();
	}
}

class CompoundComparatorSurnameNameYear implements Comparator<Osoba> {
	public int compare(Osoba left, Osoba right) {
		int result = 0;
		result = (new CompareBySurname()).compare(left, right);
		if (result != 0) return result;
		result = (new CompareByName()).compare(left, right);
		if (result != 0) return result;
		result = (new CompareByYear()).compare(left, right);
		return result;
	}
}

class CompoundComparatorYearNameSurname implements Comparator<Osoba> {
	public int compare(Osoba left, Osoba right) {
		int result = 0;
		result = (new CompareByYear()).compare(left, right);
		if (result != 0) return result;
		result = (new CompareByName()).compare(left, right);
		if (result != 0) return result;
		result = (new CompareBySurname()).compare(left, right);
		return result;
	}
}
