package clinic.models;

import clinic.helper.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document("patientPurchase")
public class PatientPurchase {
    @Id
    private String  id;

    private long patientId;

    private List<Item> items;

    @Override
    public String toString() {
        return "MongoDbPurchase{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", items=" + items.toString() +
                '}';
    }
}
