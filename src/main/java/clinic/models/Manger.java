package clinic.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class Manger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mangerId")
    private long mangerId;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "user_Name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
}
