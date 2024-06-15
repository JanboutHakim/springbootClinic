package clinic.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor()
public class AppointmentsDTO {
    private long appointmentId;
    private long patient;
    private long doctor;
    private String date;
    private String description;
    private double paid;
    private double notPaid;

    @Override
    public String toString() {
        return "AppointmentsDTO{" +
                "appointmentId=" + appointmentId +
                ", patient=" + patient +
                ", doctorID=" + doctor +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", paid=" + paid +
                ", notPaid=" + notPaid +
                '}';
    }
}
