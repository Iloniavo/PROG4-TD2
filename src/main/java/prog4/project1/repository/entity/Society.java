package prog4.project1.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Society {
    private String name = "Flowbite";
    private String description = "A simple organization";
    private String slogan = "Work hard until death";
    private String location = "Antananarivo T22 Analakely";
    private String email = "flow-bite@gmail.com";
    private List<String> phoneNumbers = List.of("0320100101");
    private String NIF = "123 456 789";
    private String STAT = "987 654 321";
    private String RCS = "432 987 563";
}
