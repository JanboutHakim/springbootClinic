package clinic.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentHelper {
    private long appointmentId;
    private String patientName;
    private String doctorName;
    private String date;
    private String description;
    private double paid;
    private double notPaid;
}
