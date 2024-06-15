package clinic.serviecs;

import clinic.models.Doctor;
import clinic.repositores.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    ModelMapper modelMapper;
    public void addDoctorToDataBase(Doctor doctor){
        doctorRepository.save(doctor);

    }

    public DoctorService(DoctorRepository doctorRepository,ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper=modelMapper;
    }
    public List<Doctor> findByName(String first_name){
        return doctorRepository.findByFirstName(first_name);
    }
    public Doctor findByID(long id){
        return doctorRepository.findById(id);
    }
    public List<Doctor>  getAll(){
        return doctorRepository.findAll();
    }
    @Transactional
    public void editAccount(Doctor updatedDoctor){
        doctorRepository.updateDoctor(
                updatedDoctor.getAddress(),
                updatedDoctor.getDob(),
                updatedDoctor.getEmail(),
                updatedDoctor.getFirstName(),
                updatedDoctor.getLastName(),
                updatedDoctor.getPhoneNumber(),
                updatedDoctor.getSex(),
                updatedDoctor.getSpecialization(),
                updatedDoctor.getDoctorId()

        );


    }
    public void deleteAccount(long id){
        doctorRepository.deleteById(id);
    }



}
