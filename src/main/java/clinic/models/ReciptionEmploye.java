package clinic.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ReciptionEmploye")
public class ReciptionEmploye {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReciptionEmployeId")
    private long ReciptionEmployeId;
    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private  String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "phoneNumber",length = 10)
    private String phoneNumber;
    @Column(name = "sex")
    private String sex;
    @Column(name = "email")
    private String email;
    //date of birth
    @Column(name = "dob")
    private String dob;
    @Column(name = "education")
    private String education;
    @Column(name = "salary",nullable = true)
    private double salary;
    @Column(name = "workHours",nullable = true)
    private int workHours;
    @Column(name="role")
    private String role;
}
