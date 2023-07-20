package prog4.project1.controllers.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class Employee {

    private String fullName;
    private LocalDate birthday;
    private String matricule;

}
