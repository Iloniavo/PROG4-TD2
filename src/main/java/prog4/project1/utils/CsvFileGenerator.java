package prog4.project1.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;
import prog4.project1.repository.entity.Employee;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvFileGenerator {
    public void writeStudentsToCsv(List<Employee> employees, Writer writer) {
        try {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            printer.printRecord("id", "first name", "last name", "birthday", "sex", "function", "hire date", "resignation date", "pic url", "cnaps serial number", "serial number",
                    "social catergory","identity card id","ientity cadr delivery date", "identity card delivery place", "dependent children"
                    );
            for (Employee employee : employees) {
                printer.printRecord(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getBirthday(),
                        employee.getSex(),
                        employee.getFunction(),
                        employee.getHireDate(),
                        employee.getResignationDate(),
                        employee.getPicUrl(),
                        employee.getCnapsSerialNumber(),
                        employee.getSerialNumber(),
                        employee.getSocialCategory(),
                        employee.getIdentityCardId(),
                        employee.getIdentityCardDelDate(),
                        employee.getIdentityCardDelPlace(),
                        employee.getDependentChildren()
                        );
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
    }
    }
}
