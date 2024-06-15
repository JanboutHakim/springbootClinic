package clinic.repositores;

import clinic.models.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentsRepository extends JpaRepository<Appointments,Long> {
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE clinic.appointments a SET " +
            "a.description = :description, " +
            "a.paid = :paid, " +
            "a.not_Paid = :notPaid, " +
            "a.date = :date, " +
            "a.patient_Id = :patientId, " +
            "a.doctor_Id = :doctorId " +
            " WHERE a.appointment_id = :appointmentId")
    void updateAppointments(@Param("appointmentId") long appointmentId,
                            @Param("description") String description,
                            @Param("paid") double paid,
                            @Param("notPaid") double notPaid,
                            @Param("date") String date,
                            @Param("patientId") long patientId,
                            @Param("doctorId") long doctorId);


    @Query(nativeQuery = true,value = "SELECT * FROM appointments where patient_id=:id; ")
    List<Appointments> findByPatientName(@Param("id") long id);
}
