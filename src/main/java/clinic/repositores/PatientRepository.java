package clinic.repositores;

import clinic.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Query(value = "SELECT * FROM clinic.patient p WHERE lower(p.first_name) like %:firstName% ORDER BY p.first_name", nativeQuery = true)
    List<Patient> findByFirstName(@Param("firstName") String first_name);
    @Query(value = "SELECT * FROM clinic.patient p WHERE lower(p.last_name) like %:lastName% ORDER BY p.last_name", nativeQuery = true)
    List<Patient> findByLastName(@Param("lastName") String last_name );
    @Query(value = "SELECT * FROM clinic.patient p WHERE lower(p.address) like %:address% ORDER BY p.address", nativeQuery = true)
    List<Patient> findByAddress(@Param("address") String address );
    @Query(value = "SELECT * FROM clinic.patient p WHERE lower(p.sex) like %:sex% ORDER BY p.sex", nativeQuery = true)
    List<Patient> findByGender(@Param("sex") String sex );
    @Query(value = "SELECT * FROM clinic.patient p WHERE lower(p.email) like %:Email% ORDER BY p.email", nativeQuery = true)
    List<Patient> findByEmail(@Param("Email") String email );
    @Query(value = "SELECT * FROM clinic.patient p WHERE lower(p.phone_number) like %:phoneNumber% ORDER BY p.phone_number", nativeQuery = true)
    List<Patient> findByNumber(@Param("phoneNumber") String number );


}
