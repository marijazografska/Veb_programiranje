package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.AuthService;
import mk.ukim.finki.wp.lab.service.handler.CustomHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController
{
    private final AuthService userService;

    public AuthController(AuthService userService)
    {
        this.userService = userService;
    }

    @RequestMapping(path = "/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model)
    {
        model.addAttribute("error", error);
        return "login";
    }


    @RequestMapping(method = RequestMethod.GET,path = "/register")
    public String getRegisterPage(@RequestParam(required = false) String error, Model model)
    {
        model.addAttribute("error", error);
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname
    )
    {
        try
        {
            this.userService.register(username, password, repeatedPassword, name, surname);
        }
        catch (RuntimeException ex)
        {
            return CustomHandler.sendRedirect("/auth/register", ex.getMessage());
        }

        return CustomHandler.sendRedirect("/songs", null);
    }


    @RequestMapping(path = "/access-denied")
    public String accessDenied()
    {
        return "access-denied";
    }

    //    ===== THESE METHODS ARE NOT USED AND THERE IS NO NEED TO IMPLEMENT THEM

//    @RequestMapping(method = RequestMethod.POST, path = "/login")
//    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request)
//    {
//        try
//        {
//            User user = authService.login(username, password);
//            request.getSession().setAttribute("user", user);
//        }
//        catch (RuntimeException ex)
//        {
//            return CustomHandler.sendRedirect("/auth/login", ex.getMessage());
//        }
//
//        return "listSongs";
//    }

    //    @RequestMapping(path = "/logout")
//    public String logout(HttpServletRequest request)
//    {
//        request.getSession().invalidate();
//
//
//        return "login";
//    }

}
