package prog4.project1.controllers.rest.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthController {

    @GetMapping("/ping")
    public String ping(){
        return "health/ping";
    }

}
