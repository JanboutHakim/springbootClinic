package clinic.serviecs;

import clinic.models.Patient;
import clinic.repositores.PatientRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServies {
    private PatientRepository patientRepository;
    ModelMapper modelMapper;
    public PatientServies(PatientRepository productRepository,ModelMapper modelMapper) {
        this.patientRepository = productRepository;
        this.modelMapper=modelMapper;
    }


    public List<Patient> getAll(){
        List<Patient> patients=this.patientRepository.findAll();
        return patients;
    }
    public void addPatient(Patient patient){
        patientRepository.save(patient);
    }

    public List<Patient> findByName(String first_name){
        return patientRepository.findByFirstName(first_name.toLowerCase());
    }

    public List<Patient> findLastName(String lastname){

        return patientRepository.findByLastName(lastname.toLowerCase());
    }
    public Patient findByID(long id){
        if(patientRepository.findById(id).isPresent())
         return patientRepository.findById(id).get();
        else
            return null;
    }
    public List<Patient> findByAddress(String address){
        return patientRepository.findByAddress(address.toLowerCase());

    }
    public List<Patient> findByGender(String gender) {
        return patientRepository.findByGender(gender.toLowerCase());
    }
    public void deletePatient(long id){
        patientRepository.deleteById(id);
    }
    public List<Patient> findByEmail(String email){
        return patientRepository.findByEmail(email.toLowerCase());

    }
    public List<Patient> findByNumber(String number){
        return patientRepository.findByNumber(number.toLowerCase());

    }
    public void updatePatient(Patient upDatedPatient){
        patientRepository.save(upDatedPatient);
    }

}
