package clinic.repositores;

import clinic.models.ReciptionEmploye;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReciptionEmployeRepository extends JpaRepository<ReciptionEmploye,Long> {
   ReciptionEmploye findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
   ReciptionEmploye findByUserName(@Param("userName") String userName);

  // List<ReciptionEmploye> findByFirstName(@Param("firstname") String first_name);

     @Query(value = "SELECT * FROM clinic.reciption_employe e WHERE lower(e.first_name) like %:firstName% ORDER BY e.first_name", nativeQuery = true)
   List<ReciptionEmploye> findByFirstName(@Param("firstName") String firstName);
    @Modifying
    @Query(nativeQuery = true,
          value =  "UPDATE clinic.reciption_employe e SET " +
            "e.address = :address, " +
            "e.dob = :dob, " +
            "e.education = :education, " +
            "e.email = :email, " +
            "e.first_name = :firstName, " +
            "e.last_name = :lastName, " +
            "e.password = :password, " +
            "e.phone_number = :phoneNumber, " +
            "e.salary = :salary, " +
            "e.sex = :sex, " +
            "e.user_name = :userName, " +
            "e.work_hours = :workHours " +
            "WHERE e.reciption_employe_id = :expr")
    void updateReciptionEmploye(
            @Param("address") String address,
            @Param("dob") String dob,
            @Param("education") String education,
            @Param("email") String email,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("password") String password,
            @Param("phoneNumber") String phoneNumber,
            @Param("salary") double salary,
            @Param("sex") String sex,
            @Param("userName") String userName,
            @Param("workHours") int workHours,
            @Param("expr") long expr);
}


