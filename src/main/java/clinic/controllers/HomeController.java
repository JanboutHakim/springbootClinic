package clinic.controllers;

import clinic.models.ReciptionEmploye;
import clinic.serviecs.MangerService;
import clinic.serviecs.ReciptionEmployeeServies;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    private ReciptionEmployeeServies reciptionEmployeeServies;
    private MangerService mangerService;
    private PasswordEncoder passwordEncoder;


    public HomeController(ReciptionEmployeeServies reciptionEmployeeServies, MangerService mangerService,PasswordEncoder passwordEncoder) {
        this.reciptionEmployeeServies = reciptionEmployeeServies;
        this.mangerService = mangerService;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/login")
    public String handleLogin(@RequestParam("role") String role){
        if(role.equals("ROLE_MANAGER"))
            return "mangerDashboard";
        if(role.equals("ROLE_EMPLOYEE"))
            return "employeeDashboard";
       return "/mangerLogin";
    }

    @GetMapping("/employeelogin")
    public String employeeLogin(){
        return "employeelogin";
    }

    @GetMapping("/mangerLogin")
    public String mangerLogin(){return  "/mangerLogin";}

    @PostMapping("/employeelogin")
    public String processEmployeeLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {

        if (this.reciptionEmployeeServies.checkAccount(username, password)) {
            // Authentication successful, redirect to dashboard or home page

            return "redirect:/employeeDashboard";
        } else {

            // Authentication failed, add error message to model and return login page
            model.addAttribute("error", "Invalid username or password");
            return "employeelogin";}
    }

    @GetMapping("/mangerDashboard")
    public String manger() {
        return "mangerDashboard";
    }

    @GetMapping("/employeeDashboard")
    public String employeeDashboard() {
        return "employeeDashboard";
    }

}
