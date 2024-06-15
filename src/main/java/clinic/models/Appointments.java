package clinic.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "Appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_Id")
    private long appointmentId;
    @Column(name = "description",nullable = true)
    private String description;
    @Column(name = "paid",nullable = true)
    private double paid;
    @Column(name = "not_Paid",nullable = true)
    private double notPaid;
    @Column(name = "date")
    private String date;
    @ManyToOne
    @JoinColumn(name="patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctorId")
    private Doctor doctor;

    @Override
    public String toString() {
        return "Appointments{" +
                "appointmentId=" + appointmentId +
                ", description='" + description + '\'' +
                ", paid=" + paid +
                ", notPaid=" + notPaid +
                ", date='" + date + '\'' +
                ", patient=" + patient +
                ", doctor=" + doctor +
                '}';
    }
}
