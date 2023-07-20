package prog4.project1.controllers.rest.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import prog4.project1.controllers.rest.models.Employee;

import static prog4.project1.utils.Converter.getFullName;

@Component
@AllArgsConstructor
public class EmployeeRestMapper {
    public Employee toRest(prog4.project1.repository.entity.Employee domain){
        return Employee.builder()
                .birthday(domain.getBirthday())
                .matricule(domain.getSerialNumber())
                .fullName(getFullName(domain.getFirstName(), domain.getLastName()))
                .build();
    }
}
