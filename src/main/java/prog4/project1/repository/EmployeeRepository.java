package prog4.project1.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prog4.project1.repository.entity.Employee;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndSexContainsIgnoreCaseAndFunctionContainsIgnoreCaseAndHireDateBetween(String firstName, String lastName, String sex, String function, LocalDate from, LocalDate to, Sort by);
}
