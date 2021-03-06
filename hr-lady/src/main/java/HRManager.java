import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HRManager {

	private List<Employee> allEmployees = new ArrayList<>();

	Employee create(String firstName, String lastName, String dateOfBirth) {
		Employee employee = new Employee(firstName, lastName, dateOfBirth);
		allEmployees.add(employee);
		return employee;
	}

	public List<Employee> findAll() {
		return allEmployees;
	}

	public List<Employee> searchByLastName(String lastName) {
		{
			return allEmployees.stream().filter(Employee -> Employee.getLastName().contains(lastName))
				.collect(Collectors.toList());
		} // bierzemy liste wszystkich pracownikow allEmployees. otwieramy stream  ktory daje nam mozliwosc
		// operowania na kazdym elemencie. filtr przyjmuje lamde . Tworzymy lokalna zmienna employee typu
		// Employee. geterem wyciagamy wszystkich pracownikow i sprawdzamy czy ich lastname jest nasz last
		// name po czym collect zbieramy w liste a return zwraca
	}

	public List<Employee> searchByPhrase(String phrase) {
		return allEmployees.stream().filter(employee -> employee.matches(phrase)).collect(Collectors.toList());
	}

	public List<Employee> sortByFirstName() {
		//return allEmployees.stream().sorted(Comparator.comparing(Employee::getFirstName)).collect(Collectors
		//.toList());
		return allEmployees.stream().sorted().collect(Collectors.toList());
	}

	public List<Employee> sortByFirstNameBubble() {
		for (int j = 0; j < allEmployees.size() - 1; j++) {
			for (int i = 0; i < allEmployees.size() - 1 - j; i++) {
				if (allEmployees.get(i).getFirstName().compareTo(allEmployees.get(i + 1).getFirstName
					()) < 0) {


					Employee temp = allEmployees.get(i);
					allEmployees.set(i, allEmployees.get(i + 1));
					allEmployees.set(i + 1, temp);
				}
			}
		}
		return allEmployees;
	}

	public List<Employee> quickSort(List<Employee> employees, int low, int high) {
		if (low < high) {
			int pi = partition(employees, low, high);

			quickSort(employees, low, pi - 1);
			quickSort(employees, pi + 1, high);
		}
		return employees;
	}

	private int partition(List<Employee> employees, int low, int high) {
		Employee pivot = employees.get(high);
		int i = low - 1;
		for (int j = low; j <= high - 1; j++) {
			if (employees.get(j).getLastName().compareTo(pivot.getLastName()) <= 0) {
				i++;
				Employee temp = employees.get(j);
				employees.set(j, employees.get(i));
				employees.set(i, temp);
			}
		}
		Employee temp = employees.get(i + 1);
		employees.set(i + 1, employees.get(high));
		employees.set(high, temp);
		return i + 1;
	}
}
