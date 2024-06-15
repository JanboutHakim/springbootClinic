package clinic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReciptionEmployeDTO {

    private long ReciptionEmployeId;
    private String userName;
    private String firstName;
    private  String lastName;
    private String address;
    private String phoneNumber;
    private String sex;
    private String email;
    //date of birth
    private String dob;
    private String education;
    private double salary;
    private int workHours;

}
