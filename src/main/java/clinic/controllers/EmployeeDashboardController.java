package clinic.controllers;
import clinic.dtos.AppointmentsDTO;
import clinic.dtos.PatientPurchaseDto;
import clinic.models.Patient;
import clinic.serviecs.AppointmentsService;
import clinic.serviecs.PatientPurchaseService;
import clinic.serviecs.PatientServies;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("employeeDashboard")
@AllArgsConstructor
public class EmployeeDashboardController {
    PatientServies patientServies;
    AppointmentsService appointmentsService;
    PatientPurchaseService purchaseService;


    @GetMapping("/addPatient")
    public String handleOpeningPatient(Model model){
        model.addAttribute("patient",new Patient());
        purchaseService.addDummyData();
        return "addPatient";
    }

    @PostMapping("/searchPatient")
    public String searchEmployee(@RequestParam("search") String search,
                                 @RequestParam("searchType") Optional<String> searchType, Model model){
        if(searchType.isPresent())  {
            String my_searchType=searchType.get();
            switch (my_searchType){
                case "Search By ID":model.addAttribute("patients", patientServies.findByID(Long.parseLong(search)));
                break;
                case "Search By First Name": model.addAttribute("patients", patientServies.findByName(search));
                break;
                case "Search By Last Name":model.addAttribute("patients", patientServies.findLastName(search));
                break;
                case "Search By Address" : model.addAttribute("patients",patientServies.findByAddress(search));
                    break;
                case "Search By Gender": model.addAttribute("patients",patientServies.findByGender(search));
                    break;
                case "Search By Phone Number": model.addAttribute("patients",patientServies.findByNumber(search));
                    break;
                case "Search By Email":model.addAttribute("patients",patientServies.findByEmail(search));
                    break;
                default:
                    System.out.println("error");
                    break;
            }

        }
        System.out.println(search);
        List<String> searchRadio= radioList();
        model.addAttribute("searchRadio",searchRadio);
        return "searchPatient";
    }

    @GetMapping("/searchPatient")
    public String searchEmployee(Model model){
        List<String> searchRadio = radioList();

        model.addAttribute("searchRadio",searchRadio);
        List<Patient> patients=patientServies.getAll();
        model.addAttribute("patients",patients);
        return "searchPatient";
    }

    @org.jetbrains.annotations.NotNull
    private static List<String> radioList() {
        List<String> searchRadio=new ArrayList<>();
        searchRadio.add("Search By ID");
        searchRadio.add("Search By First Name");
        searchRadio.add("Search By Last Name");
        searchRadio.add("Search By Gender");
        searchRadio.add("Search By Address");
        searchRadio.add("Search By Phone Number");
        searchRadio.add("Search By Email");
        return searchRadio;
    }

    @PostMapping("/addPatient")
    public String handleAddingPatient(@ModelAttribute("patient") Patient patient) {
        // Process the form data (e.g., save to database)

            if(patient.getSex().equals("true"))
                patient.setSex("Male");
            else
                patient.setSex("Female");
            patientServies.addPatient(patient);
            // Redirect to another page after form submission
        return "redirect:/employeeDashboard";
    }

    @PostMapping("/addAppointments")
    public String handleAddAppointments(@ModelAttribute("appointments") AppointmentsDTO appointmentsDTO) {
        // Process the form data (e.g., save to database)
        appointmentsService.addAppointment(appointmentsDTO);
        // Redirect to another page after form submission
        return "redirect:/employeeDashboard";
    }

    @GetMapping("addAppointments")
    public String addAppointments(Model model){
        AppointmentsDTO appointmentsDTO1 =new AppointmentsDTO();
        model.addAttribute("appointments", appointmentsDTO1);
        return "addAppointments";
    }



    @GetMapping("searchAppointments")
    public String searchAppointments(Model model){
        model.addAttribute("appointments",appointmentsService.getAllAppointments());
        return "searchAppointments";
    }

    @GetMapping("updateAppointment")
    public String editAppointment(@RequestParam long id, Model model){
        model.addAttribute("appointments",appointmentsService.getDtoClass(id));
        return "updateAppointment";
    }

    @PostMapping("/confirmUpdateAppointment")
    public String confirmUpdateAppointment(@RequestParam Long id,@ModelAttribute("appointments") AppointmentsDTO appointmentsDTO)
    {   appointmentsDTO.setAppointmentId(id);
        System.out.println(appointmentsDTO.toString());
        appointmentsService.updateAppointment(appointmentsDTO);
        return "redirect:/employeeDashboard/searchAppointments";
    }




    @PostMapping("/confirmEditPatient")
    public String updatePatient(@RequestParam Long id, @ModelAttribute("patient") Patient patient){
        if(patient.getSex().equals("true"))
            patient.setSex("Male");
        else
            patient.setSex("Female");
        patient.setPatientId(id);
        patientServies.updatePatient(patient);
        // Redirect to another page after form submission
        return "redirect:/employeeDashboard/searchPatient"; // Redirect to login page

    }

    @GetMapping("updatePatient")
    public String editEmployee(@RequestParam long id, Model model){
        model.addAttribute("patient",patientServies.findByID(id));
        return "updatePatient";
    }

    @GetMapping("patient/confirmDeleting")
    public String deletePatient(@RequestParam Long id ){
        patientServies.deletePatient(id);

        return "redirect:/employeeDashboard/searchPatient";
    }

    @GetMapping("/addPurchase")
    public String patientPurchase(@RequestParam long id,Model model){
        model.addAttribute("ID",id);
        model.addAttribute("patientPurchase",new PatientPurchaseDto());
      //  purchaseService.addDummyData();
        return "/addPurchase";
    }
    @PostMapping("/addPurchase")
    public String handleAddingPurchase(@RequestParam long id,@ModelAttribute("patientPurchase") PatientPurchaseDto patientPurchaseDto){
        patientPurchaseDto.setPatientID(id);
        purchaseService.addPurchase(patientPurchaseDto);
        return "redirect:/employeeDashboard";

    }

    @GetMapping("/allBills")
    public String allPurchase(@RequestParam long id,Model model){
        purchaseService.getByPatient(id);
        model.addAttribute("name",patientServies.findByID(id).getFirstName()+" "+patientServies.findByID(id).getLastName());
        model.addAttribute("PatientItems",purchaseService.getByPatient(id).getItems());
        return "allBills";
    }

    @GetMapping("appointment/confirmAppointmentDeleting")
    public String deleteAppointment(@RequestParam long id){
        appointmentsService.deleteAppointment(id);
        return "redirect:/employeeDashboard/searchAppointments";
    }

    @PostMapping("/searchAppointments")
    public String searchAppointments(@RequestParam("search") String search,
                                       Model model){

        model.addAttribute("appointments" ,appointmentsService.findByPatientName(Long.parseLong(search)));

        return "/searchAppointments";
    }
}
