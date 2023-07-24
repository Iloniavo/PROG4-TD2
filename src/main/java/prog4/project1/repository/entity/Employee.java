package prog4.project1.repository.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import static prog4.project1.utils.Converter.generateRandomNumber;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // automatically generated

    private String firstName;

    @Column(unique = true)
    private String serialNumber; // automatically generated

    private String lastName;

    private LocalDate birthday;

    private String sex;

    @Column(columnDefinition = "text")
    @Nullable
    private String picUrl;

    @OneToMany(mappedBy = "owner")
    private List<PhoneNumber> phoneNumbers;

    private LocalDate hireDate = LocalDate.now();// automatically generated

    private LocalDate resignationDate;// null by default

    private String socialCategory;

    private Integer dependentChildren;

    private String location;

    private String cnapsSerialNumber;

    private String function;

    private String proEmail;

    private String psEmail;

    private String identityCardDelPlace;

    private String identityCardId;

    private LocalDate identityCardDelDate;

    @PrePersist
    private void generateSerialNumber() {
        String prefix = "EMP";
        String uniquePart = generateRandomNumber();
        this.serialNumber = prefix + uniquePart;
    }
}
