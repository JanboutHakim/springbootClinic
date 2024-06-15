package clinic.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name="Doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctorId")
    private long doctorId;
    @Column(name = "first_Name")
    private String firstName;
    @Column(name = "last_Name")
    private  String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_Number")
    private String phoneNumber;
    @Column(name = "sex")
    private String sex;
    @Column(name = "email")
    private String email;
    //date of birth
    @Column(name = "dob")
    private String dob;
    @Column(name = "specialization")
    private String specialization;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointments> appointmentsList;


    public Doctor(long doctorId) {
        this.doctorId = doctorId;
    }


}
