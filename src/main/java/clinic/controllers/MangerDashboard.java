package clinic.controllers;

import clinic.dtos.ReciptionEmployeDTO;
import clinic.models.Doctor;
import clinic.models.ReciptionEmploye;
import clinic.serviecs.DoctorService;
import clinic.serviecs.ReciptionEmployeeServies;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mangerDashboard")
public class MangerDashboard {
    private ReciptionEmployeeServies reciptionEmployeeServies;
    private DoctorService doctorService;

    public MangerDashboard(ReciptionEmployeeServies reciptionEmployeeServies, DoctorService doctorService) {
        this.reciptionEmployeeServies = reciptionEmployeeServies;
        this.doctorService = doctorService;
    }

    @GetMapping("/reciptionDashboard")
    public String reciptionPage(){
        return "reciptionDashboard";
    }

    @GetMapping("/addemploye")
    public String signUpPage(Model model){
        model.addAttribute("receptionEmployee",new ReciptionEmploye());
        return "addemploye";
    }
    @PostMapping("searchEmployee")
    public String searchEmployee(@RequestParam("search") String search,
                                 @RequestParam("searchType") Optional<String> searchType, Model model){
        if(searchType.isPresent())  {String my_searchType=searchType.get();
            if (my_searchType.equals("Search By Name")) {
                model.addAttribute("employees", reciptionEmployeeServies.findByName(search));

            } else if (my_searchType.equals("Search By ID")) {
                model.addAttribute("employees", reciptionEmployeeServies.findByID(Long.parseLong(search)));
            }
        }else{
            model.addAttribute("employees",reciptionEmployeeServies.getAll());
        }
        List<String> searchRadio=new ArrayList<>();
        searchRadio.add("Search By Name");
        searchRadio.add("Search By ID");
        model.addAttribute("searchRadio",searchRadio);
        return "searchEmployee";
    }


    @GetMapping("searchEmployee")
    public String searchEmployee(Model model){
        List<String> searchRadio=new ArrayList<String>();
        searchRadio.add("Search By Name");
        searchRadio.add("Search By ID");
        model.addAttribute("searchRadio",searchRadio);
        List<ReciptionEmployeDTO> employees=reciptionEmployeeServies.getAll();
        model.addAttribute("employees",employees);
        return "searchEmployee";
    }


    @PostMapping("/addemploye")
    public String handleSignupForm(@ModelAttribute("reciptionEmploye") ReciptionEmploye reciptionEmploye, Model model) {
        // Process the form data (e.g., save to database)
        if (reciptionEmployeeServies.checkUserName(reciptionEmploye.getUserName())) {
            model.addAttribute("error", "Invalid username ");
        } else {
            if(reciptionEmploye.getSex().equals("true"))
                reciptionEmploye.setSex("Male");
            else
                reciptionEmploye.setSex("Female");
            reciptionEmployeeServies.makeAccount(reciptionEmploye);
            // Redirect to another page after form submission
            return "redirect:/mangerDashboard"; // Redirect to login page
        }return "redirect:/mangerDashboard";
    }

    @GetMapping("/addDoctor")
    public String addDoctor(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "addDoctor";
    }

    @PostMapping("/addDoctor")
    public String handleAddingDoctor(@ModelAttribute("doctor") Doctor doctor){

        if(doctor.getSex().equals("true"))
            doctor.setSex("Male");
        else
            doctor.setSex("Female");
        doctorService.addDoctorToDataBase(doctor);
        return "redirect:/mangerDashboard";
    }

    @PostMapping("searchDoctor")
    public String searchDoctorSpecific(@RequestParam("search") String search,
                                       @RequestParam("searchType") Optional<String> searchType, Model model){
        if(searchType.isPresent())  {String my_searchType=searchType.get();
            if (my_searchType.equals("Search By Name")) {
                model.addAttribute("doctors", doctorService.findByName(search));

            } else if (my_searchType.equals("Search By ID")) {
                model.addAttribute("doctors", doctorService.findByID(Long.parseLong(search)));

            }
        }else {
            model.addAttribute("doctors",reciptionEmployeeServies.getAll());
        }
        List<String> searchRadio=new ArrayList<String>();
        searchRadio.add("Search By Name");
        searchRadio.add("Search By ID");
        model.addAttribute("searchRadio",searchRadio);
        return "searchDoctor";
    }


    @GetMapping("searchDoctor")
    public String searchDoctor(Model model){
        List<String> searchRadio=new ArrayList<String>();
        searchRadio.add("Search By Name");
        searchRadio.add("Search By ID");
        model.addAttribute("searchRadio",searchRadio);
        List<Doctor> doctors=doctorService.getAll();
        model.addAttribute("doctors",doctors);
        return "searchDoctor";
    }
    //editEmployee
   @GetMapping("updateEmployee")
    public String editEmployee(@RequestParam long id, Model model){
        model.addAttribute("reciptionEmploye",reciptionEmployeeServies.getById(id));
        return "updateEmployee";
    }
    @PostMapping("/confirmEditEmployee")
    public String updateEmployee(@RequestParam Long id,@ModelAttribute("reciptionEmploye") ReciptionEmploye reciptionEmploye, Model model){
        if (reciptionEmployeeServies.checkUserName(reciptionEmploye.getUserName())) {
            model.addAttribute("error", "Invalid username ");
        } else {
            if(reciptionEmploye.getSex().equals("true"))
                reciptionEmploye.setSex("Male");
            else
                reciptionEmploye.setSex("Female");
            reciptionEmploye.setReciptionEmployeId(id);
            reciptionEmployeeServies.editAccount(reciptionEmploye);
            // Redirect to another page after form submission
            return "redirect:/mangerDashboard"; // Redirect to login page
        }
        return "updateEmployee/"+"{"+reciptionEmploye.getReciptionEmployeId()+"}";
    }
    @GetMapping("/updateDoctor")
    public String updateDoctor(@RequestParam long id, Model model){
        model.addAttribute("doctor",doctorService.findByID(id));
        return "updateDoctor";
    }


    @PostMapping("/confirmEditDoctor")
    public String updateEmployee(@RequestParam Long id,@ModelAttribute("doctor") Doctor doctor){

            if(doctor.getSex().equals("true"))
                doctor.setSex("Male");
            else
                doctor.setSex("Female");
            doctor.setDoctorId(id);
            doctorService.editAccount(doctor);
            // Redirect to another page after form submission
            return "redirect:/mangerDashboard"; // Redirect to login page

    }



    @GetMapping("employee/confirmDeleting")
    public String deleteEmployee(@RequestParam Long id ){
    reciptionEmployeeServies.deleteAccount(id);

    return "redirect:/mangerDashboard/searchEmployee";
    }

    @GetMapping("doctor/confirmDeleting")
    public String deleteDoctor(@RequestParam Long id ,Model model){

        doctorService.deleteAccount(id);
        return "redirect:/mangerDashboard/searchDoctor";
    }

}



