package prog4.project1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeesController {
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/employees")
    public String employee(){
        return "employee";
    }
}
