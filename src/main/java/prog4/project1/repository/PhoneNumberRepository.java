package prog4.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prog4.project1.repository.entity.Employee;
import prog4.project1.repository.entity.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

}
