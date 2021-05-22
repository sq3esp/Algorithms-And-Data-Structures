package List2.Zadanie1b;

public class Zadanie1b {

	public static void main(String[] args) {
		Student students[] = new Student[5];
		students[0] = new Student(107982, "Kowalski", "Jan", 5.5);
		students[1] = new Student(561561, "Adamczyk", "Adam", 3.5);
		students[2] = new Student(456123, "Jarosik", "Jedrzej", 4);
		students[3] = new Student(123456, "Płóciennik", "Tomasz", 4.5);
		students[4] = new Student(324645, "Rasik", "Błażej", 2);
		StudentList studentList = new StudentList(students);

		//wyświetlenie wszystkich studentów
		studentList.show();
		//zmiana oceny na 4
		studentList.changeGrade(123456, 2);
		//wyświetlenie wszystkich studentów
		studentList.show();
		//wyświetlenie średniej ocen>=3
		studentList.averageGrade();
		//Wyświetlenie studentów którzy oblali przedmiot
		studentList.showFailedStudents();
		//otrzymanie tablicy dwu wymiarowej z tablicami posortowanych studentów
		Student[][] table = studentList.sortStudents();
		//wyświetlenie otrzymanej tablicy studentów
		System.out.println("\nStudenci którzy nie zdali");
		for (Student x : table[0]) {
			System.out.println(x);
		}
		System.out.println("\nStudenci którzy zdali:");
		for (Student x : table[1]) {
			System.out.println(x);
		}

	}
}


class Student {
	private int index;
	private String surname, name;
	private double grade;

	public Student(int index, String surname, String name, double grade) {
		this.index = index;
		this.surname = surname;
		this.name = name;
		this.grade = grade;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return index + " " + surname + " " + name + " " + grade;
	}
}


class StudentList {
	Student student[];


	public StudentList(Student[] student) {
		this.student = student;
	}

	public void show() {
		ArrayIterator<Student> iterator = new ArrayIterator<>(student);
		System.out.println("\nPełna lista:");
		while (!iterator.isDone()) {
			System.out.println(iterator.currentItem());
			iterator.next();
		}
	}

	public boolean changeGrade(int index, double grade) {
		if (grade < 2 || grade > 6) return false;

		FilterIterator<Student> filterIterator = new FilterIterator<>(new ArrayIterator<>(student), new Predicate<Student>() {
			public boolean accept(Student arg) {
				return arg.getIndex() == index;
			}
		});

		while (!filterIterator.isDone()) {
			Student student = filterIterator.currentItem();
			student.setGrade(grade);
			filterIterator.findNextValid();
		}
		return true;
	}

	public void averageGrade() {
		FilterIterator<Student> filterIterator = new FilterIterator<>(new ArrayIterator<>(student), new Predicate<Student>() {
			public boolean accept(Student arg) {
				return arg.getGrade() >= 3.0;
			}
		});

		double average = 0;
		int i = 0;

		while (!filterIterator.isDone()) {
			Student student = filterIterator.currentItem();
			average += student.getGrade();
			i++;
			filterIterator.findNextValid();
		}

		if (i > 0) {
			System.out.println("\nŚrednia ocen pozytywnych: " + average / i);
		} else {
			System.out.println("Brak ocen spełniających dane kryteria");
		}
	}

	public void showFailedStudents() {
		FilterIterator<Student> filterIterator = new FilterIterator<>(new ArrayIterator<>(student), new Predicate<Student>() {
			public boolean accept(Student arg) {
				return arg.getGrade() < 3.0;
			}
		});

		System.out.println("\nStudenci, którzy nie otrzymali oceny pozytywnej:");
		while (!filterIterator.isDone()) {
			Student student = filterIterator.currentItem();
			System.out.println(student);
			filterIterator.findNextValid();
		}
	}

	public Student[][] sortStudents() { //tutaj przekładam złożoność pamięciową nad obliczeniową
		int failedCounter = 0;
		int passedCounter = 0;

		FilterIterator<Student> failedIterator = new FilterIterator<>(new ArrayIterator<>(student), new Predicate<Student>() {
			public boolean accept(Student arg) {
				return arg.getGrade() < 3.0;
			}
		});

		FilterIterator<Student> passedIterator = new FilterIterator<>(new ArrayIterator<>(student), new Predicate<Student>() {
			public boolean accept(Student arg) {
				return arg.getGrade() >= 3.0;
			}
		});

		while (!failedIterator.isDone()) {
			failedIterator.findNextValid();
			failedCounter++;
		}

		while (!passedIterator.isDone()) {
			passedIterator.findNextValid();
			passedCounter++;
		}

		Student sortedStudents[][] = new Student[2][];

		sortedStudents[0] = new Student[failedCounter];
		sortedStudents[1] = new Student[passedCounter];

		failedCounter = 0;
		passedCounter = 0;

		failedIterator = new FilterIterator<>(new ArrayIterator<>(student), new Predicate<Student>() {
			public boolean accept(Student arg) {
				return arg.getGrade() < 3.0;
			}
		});

		passedIterator = new FilterIterator<>(new ArrayIterator<>(student), new Predicate<Student>() {
			public boolean accept(Student arg) {
				return arg.getGrade() >= 3.0;
			}
		});

		while (!failedIterator.isDone()) {
			sortedStudents[0][failedCounter++] = failedIterator.currentItem();
			failedIterator.findNextValid();
		}

		while (!passedIterator.isDone()) {
			sortedStudents[1][passedCounter++] = passedIterator.currentItem();
			passedIterator.findNextValid();
		}

		return sortedStudents;
	}
}


interface Iterator<T> {
	void first();

	void next();

	boolean isDone();

	T currentItem();
}


class ArrayIterator<T> implements Iterator<T> {
	private T array[];
	private int pos = 0;

	public ArrayIterator(T anArray[]) {
		array = anArray;
	}

	public void first() {
		pos = 0;
	}

	public T currentItem() {
		return array[pos];
	}

	public void next() {
		pos = pos + 1;
	}

	public boolean isDone() {
		if (pos == array.length || array[pos] == null) {
			return true;
		} else {
			return false;
		}
	}

}

interface Predicate<T> {
	boolean accept(T arg);
}

class FilterIterator<T> implements Iterator<T> {
	private Iterator<T> iterator;
	private Predicate<T> predicate;
	private T current = null;
	private boolean hasNext;

	public FilterIterator(Iterator<T> iterator, Predicate<T> predicate) {
		this.iterator = iterator;
		this.predicate = predicate;
		hasNext = true;
		current = iterator.currentItem();
	}

	public T currentItem() {
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
			current = iterator.currentItem();
			iterator.next();
			if (predicate.accept(current)) {
				return;
			}
		}
		hasNext = false;
	}


}