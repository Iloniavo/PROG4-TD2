package prog4.project1.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import prog4.project1.repository.EmployeeRepository;
import prog4.project1.repository.PhoneNumberRepository;
import prog4.project1.repository.entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
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


    //TODO: add order by features, update "update" view, update list view, update "create view"
    public List<Employee> getEmployeesByJPAFilter(String firstName, String lastName, String function, String sex, LocalDate from, LocalDate to, String orderBy , String direction, String phoneNumber){
        return phoneNumber != null ? employeeRepository.findAllByPhoneNumbersInList(phoneNumber) :  employeeRepository.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndSexContainsIgnoreCaseAndFunctionContainsIgnoreCaseAndHireDateBetween(firstName, lastName, sex, function, from, to, Sort.by(Sort.Direction.valueOf(direction), orderBy));
    }
}
