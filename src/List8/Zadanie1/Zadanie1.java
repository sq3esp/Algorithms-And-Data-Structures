import java.util.Random;

public class Zadanie1 {

	public static void main(String[] args) {
		int table[] = RandomTable.generateRandom(10);

		MaximumPriorityQueue queue = new MaximumPriorityQueue(table);
		System.out.println("Tablica nie posortowana");
		queue.printHeap();

		queue.buildMaxHeap();
		System.out.println("\n\nTablica posortowana (utworzony kopiec)");
		queue.printHeap();
		System.out.println("\n\nŚciągamy elementy ze stosu od największego");
		for (int i = 0; i < 5; i++) {
			System.out.println(queue.extractMax());
		}
		System.out.println("\n\nPozostały stos");
		queue.printHeap();

		System.out.println("\n\nZmieniamy ostatni element stosu na 11");
		queue.change(4, 11);

		System.out.println("Wynikowy stos");
		queue.printHeap();

		System.out.println("\n\nŚciągamy 3 element stosu");
		System.out.println("Ściągnięty element: " + queue.delete(2));
		System.out.println("Wynikowy stos");
		queue.printHeap();


	}
}


class RandomTable {
	public static int[] generateRandom(int x) {
		Random rand = new Random();
		int[] result = new int[x];

		for (int i = 0; i < x; i++) {
			result[i] = rand.nextInt(x);
		}
		return result;
	}
}

class MaximumPriorityQueue {
	public int heapSize;
	public int INF = 100000;

	int A[];

	public MaximumPriorityQueue(int[] table) {
		A = table;
		heapSize = A.length;
	}

	public int getRightChild(int index) {
		if (((2 * index) + 2 < A.length && (index >= 0)))
			return (2 * index) + 2;
		return -1;
	}

	public int getLeftChild(int index) {
		if (((2 * index) + 1 < A.length && (index >= 0)))
			return (2 * index) + 1;
		return -1;
	}

	public int getParent(int index) {
		if ((index > 1) && (index < A.length)) {
			return index / 2;
		}
		return -1;
	}

	public void maxHeapify(int index) {
		int leftChildIndex = getLeftChild(index);
		int rightChildIndex = getRightChild(index);

		int largest = index;

		if ((leftChildIndex < heapSize) && (leftChildIndex > 0)) {
			if (A[leftChildIndex] > A[largest]) {
				largest = leftChildIndex;
			}
		}

		if ((rightChildIndex < heapSize && (rightChildIndex > 0))) {
			if (A[rightChildIndex] > A[largest]) {
				largest = rightChildIndex;
			}
		}

		if (largest != index) {
			int temp;
			temp = A[largest];
			A[largest] = A[index];
			A[index] = temp;
			maxHeapify(largest);
		}
	}

	public void buildMaxHeap() {
		for (int i = heapSize / 2; i >= 0; i--) {
			maxHeapify(i);
		}
	}

	public int maximum() {
		return A[0];
	}

	public int extractMax() { //w tym miejscu zamiast w przypadku braku elementu podawać -1 możemy rzucić wyjątek
		if (heapSize > 0) {
			int result = A[0];
			A[0] = A[heapSize - 1];
			heapSize--;
			maxHeapify(0);
			return result;
		}
		return -1;
	}


	public void change(int index, int value) { //alternatywnie można rzucić wyjątek
		if (heapSize > index) {
			A[index] = value;
			buildMaxHeap();
		}
	}

	public int delete(int index) { //w tym miejscu zamiast w przypadku braku elementu lub nie istniejącego indeksu podawać -1 możemy rzucić wyjątek
		if (heapSize > 0) {
			if (heapSize > index) {
				int result = A[index];
				A[index] = A[heapSize - 1];
				heapSize--;
				buildMaxHeap();
				return result;
			}
		}
		return -1;
	}


	public void printHeap() {
		int end = 0;
		int index = 2;
		for (int i = 0; i < heapSize; i++) {
			System.out.print(A[i] + " ");
			if (i == end) {
				end = ((int) Math.pow(2.0, (double) index)) - 2;
				index++;
				System.out.println("");
			}
		}
	}
}






