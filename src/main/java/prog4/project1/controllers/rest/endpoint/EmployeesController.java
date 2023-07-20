package prog4.project1.controllers.rest.endpoint;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import prog4.project1.repository.entity.Employee;
import prog4.project1.service.EmployeeService;

import java.io.IOException;
import java.util.Optional;

import static prog4.project1.utils.CustomLogger.logInfo;
import static prog4.project1.utils.Converter.convertToBase64;

@Controller
@AllArgsConstructor
public class EmployeesController {

    private final EmployeeService employeeService;

    @GetMapping("/employees/create")
    public String getForm(Model model) {
        model.addAttribute("employees", new Employee());
        return "employees/create";
    }

    @GetMapping("/employees")
    public String employee(Model model) {
        model.addAttribute("employees", employeeService.getEmployees());
        return "employees/list";
    }

    @GetMapping("/employees/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employees/show";
    }

    @PostMapping("/employees")
    public String form(@RequestParam("photo") MultipartFile file, @ModelAttribute("employees") @Validated Employee employee, Model model, BindingResult result) throws IOException{
       try {
           model.addAttribute("employees", employee);
           logInfo(String.valueOf(result.hasErrors()));
           if (!file.isEmpty()) {
               employee.setPicUrl(convertToBase64(file));
               employeeService.addEmployee(employee);
               return "redirect:/employees";
           }
           return null;
       } catch (Exception e){
           e.printStackTrace();
           return "redirect:/employees/create";
       }
    }

    @PutMapping("/employees/{id}")
    public String updateEmployee(@RequestParam("photo") MultipartFile file, @ModelAttribute("employees") Employee employee, @PathVariable Integer id){
        Employee employeeToUpdate = employeeService.getEmployeeById(id);

        employeeToUpdate.setBirthday(employee.getBirthday());
        employeeToUpdate.setSerialNumber(employee.getSerialNumber());
        employeeToUpdate.setFirstName(employee.getFirstName());
        employeeToUpdate.setLastName(employee.getLastName());

        if(!file.isEmpty()) {
           try {
               employeeToUpdate.setPicUrl(convertToBase64(file));
               employeeService.updateEmployee(employeeToUpdate);
               return "redirect:/employees";
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       };
        return "employee";
    }
}
