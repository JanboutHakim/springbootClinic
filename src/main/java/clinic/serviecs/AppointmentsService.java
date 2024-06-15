package clinic.serviecs;

import clinic.dtos.AppointmentsDTO;
import clinic.helper.AppointmentHelper;
import clinic.models.Appointments;
import clinic.models.Doctor;
import clinic.models.Patient;
import clinic.repositores.AppointmentsRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AppointmentsService {
    @Autowired
    private AppointmentsRepository appointmentsRepository;
    private DoctorService doctorService;
    private PatientServies patientServies;
    ModelMapper modelMapper;
public void addAppointment(@NotNull AppointmentsDTO appointmentDTO){
        Appointments appointment=new Appointments();
        appointment.setAppointmentId(appointmentDTO.getAppointmentId());
        appointment.setPaid(appointmentDTO.getPaid());
        appointment.setDescription(appointmentDTO.getDescription());
        appointment.setNotPaid(appointmentDTO.getNotPaid());
        appointment.setDate(appointment.getDate());

        appointment.setDoctor(new Doctor(appointmentDTO.getDoctor()));
        appointment.setPatient(new Patient(appointmentDTO.getPatient()));


    System.out.println(appointment.toString());
        appointmentsRepository.save(appointment);
    }
    public List<AppointmentHelper> getAllAppointments(){
        List<AppointmentHelper> appointmentHelpers=new ArrayList<>();
        List<Appointments> appointments=appointmentsRepository.findAll();
        for(Appointments appointment:appointments){
            AppointmentHelper appointmentHelper = getAppointmentHelper(appointment);
            appointmentHelpers.add(appointmentHelper);
        }



        return appointmentHelpers;
    }

    public Appointments findById(long id){
        return appointmentsRepository.findById(id).get();
    }
    public AppointmentsDTO getDtoClass(long id){
       Appointments appointments= appointmentsRepository.findById(id).get();
       AppointmentsDTO appointmentsDTO=new AppointmentsDTO();
       modelMapper.map(appointments,appointmentsDTO);
        return appointmentsDTO;
    }

    @Transactional
    public void updateAppointment(AppointmentsDTO appointmentsDTO) {
        appointmentsRepository.updateAppointments(
                appointmentsDTO.getAppointmentId(),
                appointmentsDTO.getDescription(),
                appointmentsDTO.getPaid(),
                appointmentsDTO.getNotPaid(),
                appointmentsDTO.getDate(),
                appointmentsDTO.getPatient(),
                appointmentsDTO.getDoctor()
        );
    }

    public void deleteAppointment(long id){
    appointmentsRepository.deleteById(id);
    }

    private  @NotNull AppointmentHelper getAppointmentHelper(Appointments appointment) {
        AppointmentHelper appointmentHelper=new AppointmentHelper();
        appointmentHelper.setAppointmentId(appointment.getAppointmentId());
        appointmentHelper.setPaid(appointment.getPaid());
        appointmentHelper.setNotPaid(appointment.getNotPaid());
        appointmentHelper.setDescription(appointment.getDescription());
        appointmentHelper.setDate(appointment.getDate());
        appointmentHelper.setDoctorName(doctorService.findByID(appointment.getDoctor().getDoctorId()).getFirstName()+" "+doctorService.findByID(appointment.getDoctor().getDoctorId()).getLastName());
        appointmentHelper.setPatientName(patientServies.findByID(appointment.getPatient().getPatientId()).getFirstName()+" "+patientServies.findByID(appointment.getPatient().getPatientId()).getLastName());
        return appointmentHelper;
    }
    public List<AppointmentHelper> findByPatientName(long id){
        List<AppointmentHelper> appointmentHelpers=new ArrayList<>();
        List<Appointments> appointments=appointmentsRepository.findByPatientName(id);
        for(Appointments appointment:appointments){
            AppointmentHelper appointmentHelper = getAppointmentHelper(appointment);
            appointmentHelpers.add(appointmentHelper);
        }
        return appointmentHelpers;
    }


}
