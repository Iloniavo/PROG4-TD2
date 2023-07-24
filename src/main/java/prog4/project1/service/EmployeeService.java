package prog4.project1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import prog4.project1.repository.EmployeeRepository;
import prog4.project1.repository.entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public List<Employee> getFilteredEmployees(String firstName, String lastName, String function, String sex, LocalDate hireDateFrom, LocalDate hireDateTo) {
        // Vérifiez les attributs de recherche un par un et appliquez les filtres en conséquence
        List<Employee> employees = employeeRepository.findAll();

        if (firstName != null && !firstName.isEmpty()) {
            employees = employees.stream()
                    .filter(employee -> employee.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (lastName != null && !lastName.isEmpty()) {
            employees = employees.stream()
                    .filter(employee -> employee.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (function != null && !function.isEmpty()) {
            employees = employees.stream()
                    .filter(employee -> employee.getFunction().toLowerCase().contains(function.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (sex != null && !sex.isEmpty()) {
            employees = employees.stream()
                    .filter(employee -> Objects.equals(employee.getSex(), sex))
                    .collect(Collectors.toList());
        }

        if (hireDateFrom != null && hireDateTo != null) {
            employees = employees.stream()
                    .filter(employee -> employee.getHireDate().isAfter(hireDateFrom.minusDays(1)) &&
                            employee.getHireDate().isBefore(hireDateTo.plusDays(1)))
                    .collect(Collectors.toList());
        } else if (hireDateFrom != null) {
            employees = employees.stream()
                    .filter(employee -> employee.getHireDate().isAfter(hireDateFrom.minusDays(1)))
                    .collect(Collectors.toList());
        } else if (hireDateTo != null) {
            employees = employees.stream()
                    .filter(employee -> employee.getHireDate().isBefore(hireDateTo.plusDays(1)))
                    .collect(Collectors.toList());
        }

        return employees;
    }
    public Employee getEmployeeById(Integer id){
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }


    public void addEmployee(Employee employee){
         employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesByJPAFilter(String firstName, String lastName, String function, String sex, LocalDate from, LocalDate to){
        return employeeRepository.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndSexContainsIgnoreCaseAndFunctionContainsIgnoreCaseAndHireDateBetween(firstName, lastName, sex, function, from, to);
    }
}
