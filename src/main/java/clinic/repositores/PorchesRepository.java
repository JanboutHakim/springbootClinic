package clinic.repositores;

import clinic.models.PatientPurchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;



public interface PorchesRepository extends MongoRepository<PatientPurchase,String> {
        PatientPurchase findFirstByPatientId(@Param("patientId") long id);
        void deleteByPatientId(@Param("_id") long id);

}
