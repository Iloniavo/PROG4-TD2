package prog4.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prog4.project1.repository.entity.PhoneNumber;

import java.util.List;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

    List<PhoneNumber> findAllByNumberStartsWith(String number);

}
