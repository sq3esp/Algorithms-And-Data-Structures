package List6;

import java.util.Random;

public class Zadanie1 {

	public static void main(String[] args) {
		int data[]= {8,32,128,512,1024};

		System.out.println("Poprawienie sortowania przez selekcje polega na sprawdzeniu, czy zamieniany element nie jest tym z którym powinien zostać zamieniony, w takim wypadku operacja zamiany jest pomijana");
		for(int x:data) {
			System.out.println("\n\nWyniki dla "+x+" elementów:\n");
			int exampleSorted[] = SortMethods.generateExampleSorted(x);
			int exampleSortedBackwards[] = SortMethods.generateExampleSortedBackwards(x);
			int exampleRandom[] = SortMethods.generateRandom(x);
			int table[];

			System.out.println("\tSortowanie przez selekcję:");
			table = SortMethods.cloneTable(exampleSorted);
			System.out.println("\tSortowanie tablicy posortowanej:           "+SortMethods.selectionSort(table));
			table = SortMethods.cloneTable(exampleSortedBackwards);
			System.out.println("\tSortowanie tablicy posortowanej odwrotnie: "+SortMethods.selectionSort(table));
			table = SortMethods.cloneTable(exampleRandom);
			System.out.println("\tSortowanie tablicy elementów losowych:     "+SortMethods.selectionSort(table)+"\n");

			System.out.println("\tPoprawione sortowanie przez selekcję:");
			table = SortMethods.cloneTable(exampleSorted);
			System.out.println("\tSortowanie tablicy posortowanej:           "+SortMethods.betterSelectionSort(table));
			table = SortMethods.cloneTable(exampleSortedBackwards);
			System.out.println("\tSortowanie tablicy posortowanej odwrotnie: "+SortMethods.betterSelectionSort(table));
			table = SortMethods.cloneTable(exampleRandom);
			System.out.println("\tSortowanie tablicy elementów losowych:     "+SortMethods.betterSelectionSort(table)+"\n");

			System.out.println("\t"+"Sortowanie przez wstawianie:");
			table = SortMethods.cloneTable(exampleSorted);
			System.out.println("\tSortowanie tablicy posortowanej:           "+SortMethods.insertionSort(table));
			table = SortMethods.cloneTable(exampleSortedBackwards);
			System.out.println("\tSortowanie tablicy posortowanej odwrotnie: "+SortMethods.insertionSort(table));
			table = SortMethods.cloneTable(exampleRandom);
			System.out.println("\tSortowanie tablicy elementów losowych:     "+SortMethods.insertionSort(table)+"\n");

			System.out.println("\t"+"Sortowanie bombelkowe:");
			table = SortMethods.cloneTable(exampleSorted);
			System.out.println("\tSortowanie tablicy posortowanej :          "+SortMethods.bubbleSort(table));
			table = SortMethods.cloneTable(exampleSortedBackwards);
			System.out.println("\tSortowanie tablicy posortowanej odwrotnie: "+SortMethods.bubbleSort(table));
			table = SortMethods.cloneTable(exampleRandom);
			System.out.println("\tSortowanie tablicy elementów losowych:     "+SortMethods.bubbleSort(table)+"\n");
		}
	}
}


class SortMethods {
	public static Result selectionSort(int[] arr) {
		Result result = new Result();
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			int min_idx = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[min_idx]) {
					min_idx = j;
				}
				result.addComparisons();

			}
			int temp = arr[min_idx];
			arr[min_idx] = arr[i];
			arr[i] = temp;
			result.addChange();
		}
		return result;
	}


	public static Result betterSelectionSort(int[] arr) {
		Result result = new Result();
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			int min_idx = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[min_idx]) {
					min_idx = j;
				}
				result.addComparisons();

			}
			if(min_idx!=i) {
				int temp = arr[min_idx];
				arr[min_idx] = arr[i];
				arr[i] = temp;
				result.addChange();
			}
			result.addComparisons();
		}
		return result;
	}


	public static Result insertionSort(int[] arr) {
		Result result = new Result();
		int n = arr.length;

		for (int i = 1; i < n; ++i) {
			int key = arr[i];
			result.addRewriting();
			int j = i - 1;
			result.addComparisons();//porównanie które w którymś cyklu spowoduje pominięcie pętli while
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				result.addRewriting();
				result.addComparisons();
				j = j - 1;
			}

			arr[j + 1] = key;
			result.addRewriting();
		}
		return result;
	}

	public static Result bubbleSort(int[] arr) {
		Result result = new Result();
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				result.addComparisons();
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					result.addChange();
				}
			}
		}
		return result;
	}

	public static int[] cloneTable(int[] table){
		int[] result =new int[table.length];

		for(int i=0;i<table.length;i++){
			result[i]=table[i];
		}
		return result;
	}

	public static int[] generateExampleSortedBackwards(int x){
		int[] result = new int[x];

		for(int i=0;i<x;i++){
			result[i]=x-i;
		}
		return result;
	}

	public static int[] generateExampleSorted(int x){
		int[] result = new int[x];

		for(int i=0;i<x;i++){
			result[i]=i+1;
		}
		return result;
	}

	public static int[] generateRandom(int x){
		Random rand = new Random();
		int[] result = new int[x];

		for(int i=0;i<x;i++){
			result[i]=rand.nextInt(x);
		}
		return result;
	}

}

class Result {
	private double changes;
	private int comparisons, rewritings;

	public Result() {
		this.changes = 0;
		this.comparisons = 0;
		this.rewritings = 0;
	}

	public double getChanges() {
		return changes;
	}

	public void setChanges(double changes) {
		this.changes = changes;
	}

	public int getComparisons() {
		return comparisons;
	}

	public void setComparisons(int comparisons) {
		this.comparisons = comparisons;
	}

	public int getRewritings() {
		return rewritings;
	}

	public void setRewritings(int rewritings) {
		this.rewritings = rewritings;
	}

	public void addChange() {
		rewritings += 3;
	}

	public void addRewriting() {
		rewritings++;
	}

	public void addComparisons() {
		comparisons++;
	}

	@Override
	public String toString() {
		return "comparisons=" + comparisons + ", rewritings=" + rewritings;
	}
}




