package prog4.project1.controllers.rest.endpoint;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import prog4.project1.repository.EmployeeRepository;
import prog4.project1.repository.entity.Employee;
import prog4.project1.service.EmployeeService;
import prog4.project1.service.PhoneNumberService;
import prog4.project1.utils.CsvFileGenerator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static prog4.project1.utils.Converter.convertToBase64;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeesController extends AuthController{

    private final EmployeeService employeeService;
    private final PhoneNumberService phoneNumberService;
    private final CsvFileGenerator csvFileGenerator;
    private final EmployeeRepository employeeRepository;

    @GetMapping("/employees/create")
    public String getForm(Model model) {
        model.addAttribute("employees", new Employee());
        return "employees/create";
    }

    @GetMapping("/employees")
    public String getFilteredEmployees(
            @RequestParam(required = false, defaultValue = "") String firstNameQuery,
            @RequestParam(required = false, defaultValue = "") String lastNameQuery,
            @RequestParam(required = false, defaultValue = "") String functionQuery,
            @RequestParam(required = false, defaultValue = "") String sexQuery,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate hireDateFrom,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate hireDateTo,
            HttpSession session,
            Model model
    ) {

        session.setAttribute("firstName", firstNameQuery);
        session.setAttribute("lastName", lastNameQuery);
        session.setAttribute("function", functionQuery);
        session.setAttribute("sex", sexQuery);
        if(hireDateFrom != null) {
            session.setAttribute("hireDateFrom", hireDateFrom.toString());
        }
        if(hireDateTo != null) {
            session.setAttribute("hireDateTo", hireDateTo.toString());
        }
        if (hireDateFrom == null) {
            hireDateFrom = LocalDate.parse("2000-01-01") ;
        }
        if (hireDateTo == null) {
            hireDateTo = LocalDate.now();
        }
        List<Employee> filteredEmployees = employeeService.getEmployeesByJPAFilter(firstNameQuery, lastNameQuery, functionQuery, sexQuery, hireDateFrom, hireDateTo);
        //List<Employee> filteredEmployees = employeeRepository.findAllByHireDateBetween(hireDateFrom, hireDateTo);
        model.addAttribute("employees", filteredEmployees);
        return "employees/list";
    }

    @GetMapping("/export-to-csv")
    public void exportIntoCSV(
            HttpServletResponse response,
            HttpSession session,
            Model model
    ) throws IOException {
        LocalDate hireFrom = null;
        LocalDate hireTo = null;
        String firstNameQuery = (String) session.getAttribute("firstName");
        String lastNameQuery = (String) session.getAttribute("lastName");
        String functionQuery = (String) session.getAttribute("function");
        String sexQuery = (String) session.getAttribute("sex");
        String hireDateFrom = (String) session.getAttribute("hireDateFrom");
        String hireDateTo = (String) session.getAttribute("hireDateTo");
        
        if(hireDateFrom != null) {
            hireFrom = LocalDate.parse(hireDateFrom);
        }

        if(hireDateTo != null) {
            hireTo = LocalDate.parse(hireDateTo);
        }
        
        List<Employee> employees = employeeService.getFilteredEmployees(firstNameQuery, lastNameQuery, functionQuery, sexQuery, hireFrom, hireTo);
        model.addAttribute("employees", employees);
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"employees.csv\"");
        csvFileGenerator.writeStudentsToCsv(employees, response.getWriter());
    }

    @GetMapping("/employees/{id}/update")
    public String getById(@PathVariable Integer id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employees/update";
    }



    @GetMapping("/employees/{id}/card")
    public String getCardById(@PathVariable Integer id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employees/card";
    }

    @PostMapping("/employees")
    public String form(@RequestParam("photo") MultipartFile file, @RequestParam("phoneNumbers") String phoneNumbers, @ModelAttribute("employees") Employee employee, Model model) {

        try {
            employee.setPicUrl(convertToBase64(file));
           employeeService.addEmployee(employee);
           phoneNumberService.addPhoneNumbers(phoneNumbers, employee.getId());
            return "redirect:/employees";
       } catch (Exception e){
           e.printStackTrace();
           return "redirect:/employees/create";
       }
    }

    @PutMapping("/employees/{id}")
    public String updateEmployee(@RequestParam("photo") MultipartFile file, @ModelAttribute("employees") Employee employee, @PathVariable Integer id) throws IOException {
        Employee employeeToUpdate = employeeService.getEmployeeById(id);

        employeeToUpdate.setBirthday(employee.getBirthday());
        employeeToUpdate.setSerialNumber(employee.getSerialNumber());
        employeeToUpdate.setFirstName(employee.getFirstName());
        employeeToUpdate.setLastName(employee.getLastName());

        if(!file.isEmpty()) {
               employeeToUpdate.setPicUrl(convertToBase64(file));
       }
        if(employee.getBirthday() != null){
            employeeToUpdate.setBirthday(employee.getBirthday());
        }


        employeeService.updateEmployee(employeeToUpdate);
        return "redirect:/employees/list";
    }



}
