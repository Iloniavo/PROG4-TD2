package prog4.project1.repository.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static prog4.project1.utils.Converter.generateRandomNumber;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;

    @Column(unique = true)
    private String serialNumber;

    private String lastName;

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(columnDefinition = "text")
    @Nullable
    private String picUrl;

    @OneToMany
    private List<PhoneNumber> phoneNumbers;

    private LocalDate hireDate = LocalDate.now();

    private LocalDate resignationDate;

    @Enumerated(EnumType.STRING)
    private SocioProfessionalCategory socialCategory;

    private Integer dependentChildren;

    private String cnapsSerialNumber;

    private String function;

    private String pro_email;

    private String ps_email;
    public enum Sex {
        H, F
    }

    public enum SocioProfessionalCategory {
        M1, M2, OS1, OS2, OS3, OP1
    }

    @PrePersist
    private void generateSerialNumber() {
        String prefix = "EMP";
        String uniquePart = generateRandomNumber();
        this.serialNumber = prefix + uniquePart;
    }
}
