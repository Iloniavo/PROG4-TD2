package prog4.project1.controllers.rest.endpoint;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;

@Controller
public class AuthController {

    @ModelAttribute
    public void checkAuthentication(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Cookie[] cookies = request.getCookies();

        boolean isSessionCookie = false;

        if ( cookies != null){
            for ( Cookie c : cookies){
                if("session".equals(c.getName())){
                    isSessionCookie = true;
                    break;
                }
            }
        }
        if(!isSessionCookie) {
            response.sendRedirect("/login");
        }
    }

}
