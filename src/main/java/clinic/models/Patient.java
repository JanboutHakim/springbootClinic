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
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patientId")
    private long patientId;
    @Column(name = "first_Name")
    private String firstName;
    @Column(name = "last_Name")
    private  String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_Number",length = 10)
    private String phoneNumber;
    @Column(name = "sex")
    private String sex;
    @Column(name = "email")
    private String email;
    @Column(name = "dob")
    private String  dob;
    @OneToMany(mappedBy = "patient")
    private Set<Appointments> appointments;


    public Patient(long patientId) {
        this.patientId = patientId;
    }
}
