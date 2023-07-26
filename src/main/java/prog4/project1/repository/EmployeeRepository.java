package prog4.project1.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prog4.project1.repository.entity.Employee;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e JOIN e.phoneNumbers p WHERE p.number LIKE :startsWith%")
    List<Employee> findAllByPhoneNumbersInList(@Param("startsWith") String phoneNumbers);
    List<Employee> findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndSexContainsIgnoreCaseAndFunctionContainsIgnoreCaseAndHireDateBetween(String firstName, String lastName, String sex, String function, LocalDate from, LocalDate to, Sort by);
}
