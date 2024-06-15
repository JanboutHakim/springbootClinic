package clinic.repositores;

import clinic.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Doctor findById(@Param("doctorId") long id);
    @Query(value = "SELECT * FROM clinic.doctor d WHERE lower(d.first_name) like %:firstName% ORDER BY d.first_name", nativeQuery = true)
    List<Doctor> findByFirstName(@Param("firstName") String firstName);

    @Modifying
    @Query(value =  "UPDATE clinic.doctor e SET " +
            "e.address = :address, " +
            "e.dob = :dob, " +
            "e.email = :email, " +
            "e.first_name = :firstName, " +
            "e.last_name = :lastName, " +
            "e.phone_number = :phoneNumber, " +
            "e.sex = :sex, " +
            "e.specialization=:specialization " +
            " WHERE e.doctor_id = :expr",nativeQuery = true)
    void updateDoctor( @Param("address") String address,
                       @Param("dob") String dob,
                       @Param("email") String email,
                       @Param("firstName") String firstName,
                       @Param("lastName") String lastName,
                       @Param("phoneNumber") String phoneNumber,
                       @Param("sex") String sex,
                        @Param("specialization") String specialization,
                       @Param("expr") long expr);

}
