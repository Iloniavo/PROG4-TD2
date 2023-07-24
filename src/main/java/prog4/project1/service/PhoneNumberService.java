package prog4.project1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import prog4.project1.repository.PhoneNumberRepository;
import prog4.project1.repository.entity.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

import static prog4.project1.utils.Converter.transformMultiplePhoneNumbers;

@Service
@AllArgsConstructor
public class PhoneNumberService {

    private final PhoneNumberRepository repository;
    private final EmployeeService employeeService;
    public List<PhoneNumber> getAllPhoneNumbers(){
        return repository.findAll();
    }

    public void addPhoneNumbers(String input, Integer ownerId){
        List<String> numberList = transformMultiplePhoneNumbers(input);
        List<PhoneNumber> phoneNumbers = new ArrayList<>();

        for (String s : numberList) {
            phoneNumbers.add(PhoneNumber.builder()
                    .number(s)
                    .owner(employeeService.getEmployeeById(ownerId))
                    .build()
            );
        }

       repository.saveAll(phoneNumbers);

    }

}
