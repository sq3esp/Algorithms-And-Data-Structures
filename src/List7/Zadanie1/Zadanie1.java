package List7.Zadanie1;

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

			System.out.println("\tMerge sort:");
			table = SortMethods.cloneTable(exampleSorted);
			System.out.println("\tSortowanie tablicy posortowanej:           "+SortMethods.mergeSort(table));
			table = SortMethods.cloneTable(exampleSortedBackwards);
			System.out.println("\tSortowanie tablicy posortowanej odwrotnie: "+SortMethods.mergeSort(table));
			table = SortMethods.cloneTable(exampleRandom);
			System.out.println("\tSortowanie tablicy elementów losowych:     "+SortMethods.mergeSort(table)+"\n");


			System.out.println("\t"+"Quick sort:");
			table = SortMethods.cloneTable(exampleSorted);
			System.out.println("\tSortowanie tablicy posortowanej:           "+SortMethods.quickSort(table));
			table = SortMethods.cloneTable(exampleSortedBackwards);
			System.out.println("\tSortowanie tablicy posortowanej odwrotnie: "+SortMethods.quickSort(table));
			table = SortMethods.cloneTable(exampleRandom);
			System.out.println("\tSortowanie tablicy elementów losowych:     "+SortMethods.quickSort(table)+"\n");

		}
	}
}


class SortMethods {
	public static Result mergeSort(int[] a){
		Result result= new Result();
		mergeSortInside(a, a.length, result);
		return result;
	}

	private static Result mergeSortInside(int[] a, int n, Result result) {
		if (n < 2) {
			return null;
		}
		int mid = n / 2;
		int[] l = new int[mid];
		int[] r = new int[n - mid];

		for (int i = 0; i < mid; i++) {
			result.addRewriting();
			l[i] = a[i];
		}
		for (int i = mid; i < n; i++) {
			result.addRewriting();
			r[i - mid] = a[i];
		}
		mergeSortInside(l, mid, result);
		mergeSortInside(r, n - mid, result);

		merge(a, l, r, mid, n - mid, result);
		return result;
	}

	public static void merge(int[] a, int[] l, int[] r, int left, int right, Result result) {
		int i = 0, j = 0, k = 0;
		while (i < left && j < right) {
			result.addComparisons();

			if (l[i] <= r[j]) {
				a[k++] = l[i++];
				result.addRewriting();
			}
			else {
				a[k++] = r[j++];
				result.addRewriting();
			}
		}
		while (i < left) {
			result.addRewriting();
			a[k++] = l[i++];
		}
		while (j < right) {
			result.addRewriting();
			a[k++] = r[j++];
		}
	}


	public static Result quickSort(int[] a){
		Result result= new Result();
		quickSortInternal(a, 0, a.length-1, result);
		return result;
	}

	public static void quickSortInternal(int arr[], int begin, int end, Result result) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end, result);

			quickSortInternal(arr, begin, partitionIndex-1, result);
			quickSortInternal(arr, partitionIndex+1, end, result);
		}
	}

	private static int partition(int arr[], int begin, int end, Result result) {
		Random rand = new Random();
		int pivot = arr[begin + rand.nextInt(end - begin)];
		result.addRewriting();
		int i = (begin-1);

		for (int j = begin; j < end; j++) {
			result.addComparisons();
			if (arr[j] <= pivot) {
				i++;

				int swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
				result.addChange();
			}
		}

		int swapTemp = arr[i+1];
		arr[i+1] = arr[end];
		arr[end] = swapTemp;
		result.addChange();
		return i+1;
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




