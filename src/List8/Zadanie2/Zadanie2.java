package List8.Zadanie2;

import java.util.Random;

public class Zadanie2 {

	public static void main(String[] args) {
		Test.doTest(10000, 10000);
	}
}


class Searcher {
	public static int[] generateRandom(int x) {
		Random rand = new Random();
		int[] result = new int[x];

		for (int i = 0; i < x; i++) {
			result[i] = rand.nextInt(x);
		}
		return result;
	}


	public static int linearSearch(int[] table, int element, Counter counter) {
		int pos = 0;
		for (int x : table) {
			counter.addComparation();
			if (x == element) {
				counter.convertToHited();
				return pos;
			} else
				pos++;
		}
		counter.convertToMissed();
		return -1;
	}

	public static void sort(int arr[]) {
		int n = arr.length;

		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(arr, n, i);
		}

		for (int i = n - 1; i >= 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			heapify(arr, i, 0);
		}
	}

	private static void heapify(int arr[], int n, int i) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;

		if (l < n && arr[l] > arr[largest])
			largest = l;

		if (r < n && arr[r] > arr[largest])
			largest = r;

		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			heapify(arr, n, largest);
		}
	}

	public static int binarySearch(int[] table, int element, Counter counter) {
		int left = 0;
		int right = table.length - 1;
		int middle;
		while (left <= right) {
			counter.addComparation();
			middle = (left + right) / 2;
			if (table[middle] == element) {
				counter.convertToHited();
				return middle;
			}
			if (table[middle] > element)
				right = middle - 1;
			else
				left = middle + 1;
		}
		counter.convertToMissed();
		return -1;
	}
}


class Counter {
	private long bufor;
	private int chybionych;
	private long operacjiDlaChybionych;
	private int trafionych;
	private long operacjiDlaTrafionych;
	private double sredniaTrafionych;
	private double sredniaChybionych;
	private double sredniaLacznie;

	public Counter() {
		bufor = 0;
		chybionych = 0;
		operacjiDlaChybionych = 0;
		trafionych = 0;
		operacjiDlaTrafionych = 0;
		sredniaChybionych = 0;
		sredniaTrafionych = 0;
	}

	public void addComparation() {
		bufor++;
	}

	public void convertToHited() {
		operacjiDlaTrafionych += bufor;
		trafionych++;
		bufor = 0;
	}

	public void convertToMissed() {
		operacjiDlaChybionych += bufor;
		chybionych++;
		bufor = 0;
	}

	public void prepareResults() {
		sredniaChybionych = (double) operacjiDlaChybionych / chybionych;
		sredniaTrafionych = (double) operacjiDlaTrafionych / trafionych;
		sredniaLacznie = (double) (operacjiDlaChybionych + operacjiDlaTrafionych) / (chybionych + trafionych);
	}

	public int getChybionych() {
		return chybionych;
	}

	public long getOperacjiDlaChybionych() {
		return operacjiDlaChybionych;
	}

	public int getTrafionych() {
		return trafionych;
	}

	public long getOperacjiDlaTrafionych() {
		return operacjiDlaTrafionych;
	}

	public double getSredniaTrafionych() {
		return sredniaTrafionych;
	}

	public double getSredniaChybionych() {
		return sredniaChybionych;
	}

	public double getSredniaLacznie() {
		return sredniaLacznie;
	}
}

class Test {
	public static void doTest(int size, int iterations) {
		Counter counterLinear = new Counter();
		Counter counterBinary = new Counter();
		for (int i = 0; i < iterations; i++) {
			Random rand = new Random();
			int table[] = Searcher.generateRandom(size);
			int element = rand.nextInt(size * 2);
			Searcher.linearSearch(table, element, counterLinear);
			Searcher.sort(table);
			Searcher.binarySearch(table, element, counterBinary);
		}
		System.out.println("Wykonano " + iterations + " operacji na tablicy o " + size + " elementach");
		counterLinear.prepareResults();
		System.out.println("\nLiczba wyszukań trafionych dla wyszukiwania liniowego: " + counterLinear.getTrafionych() + " wykonano " + counterLinear.getOperacjiDlaTrafionych() + " porównań, średnio " + counterLinear.getSredniaTrafionych());
		System.out.println("Liczba wyszukań chybionych dla wyszukiwania liniowego: " + counterLinear.getChybionych() + " wykonano " + counterLinear.getOperacjiDlaChybionych() + " porównań, średnio " + counterLinear.getSredniaChybionych());
		System.out.println("Średnio wykonano " + counterLinear.getSredniaLacznie() + " porównań");

		counterBinary.prepareResults();
		System.out.println("\nLiczba wyszukań trafionych dla wyszukiwania binarnego: " + counterBinary.getTrafionych() + " wykonano " + counterBinary.getOperacjiDlaTrafionych() + " porównań, średnio " + counterBinary.getSredniaTrafionych());
		System.out.println("Liczba wyszukań chybionych dla wyszukiwania binarnego: " + counterBinary.getChybionych() + " wykonano " + counterBinary.getOperacjiDlaChybionych() + " porównań, średnio " + counterBinary.getSredniaChybionych());
		System.out.println("Średnio wykonano " + counterBinary.getSredniaLacznie() + " porównań");
	}
}