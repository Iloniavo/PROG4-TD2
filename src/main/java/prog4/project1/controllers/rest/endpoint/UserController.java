package prog4.project1.controllers.rest.endpoint;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import prog4.project1.repository.entity.Session;
import prog4.project1.repository.entity.User;
import prog4.project1.service.SessionService;
import prog4.project1.service.UserService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;


    @GetMapping("/login")
    public String loginForm(Model model, HttpSession session){
        model.addAttribute("user", new User());
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model, HttpSession session){
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    @PostMapping("/login")
    public String loginAttempt(@ModelAttribute("user") User user, HttpSession session, HttpServletResponse response){
        User userToCheck = userService.getByEmailAndPassword(user.getEmail(), user.getPassword());
        Session currentSession = Session.builder()
                .user(userToCheck)
                .expirationDate(Instant.now().plus(1, ChronoUnit.DAYS))
                .build();
        sessionService.addSession(currentSession);
        Cookie cookie = new Cookie("session", String.valueOf(currentSession.getId()));
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);
        return (userToCheck == null) ? "redirect:/error/error-404" : "redirect:/employees";
    }

    @PostMapping("/signup")
    public String signupAttempt(@ModelAttribute("user") User user, @RequestParam String confirmPassword){
        if(!Objects.equals(user.getPassword(), confirmPassword)){
            return "error/error-400";
        }
        userService.saveUser(user);
        return "redirect:/employees";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalider la session en cours
        request.getSession().invalidate();

        // Supprimer les cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

        return "redirect:/login";
    }


}
