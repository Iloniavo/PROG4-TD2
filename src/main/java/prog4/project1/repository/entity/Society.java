package prog4.project1.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Society {

    private String name = "Flowbite";
    private String description = "A simple organization";
    private String location = "Antananarivo";
    private String email = "flow-bite@gmail.com";
    private List<PhoneNumber> phoneNumbers = List.of();

}
