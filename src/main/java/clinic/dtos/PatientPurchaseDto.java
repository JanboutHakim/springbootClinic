package clinic.dtos;

import clinic.helper.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientPurchaseDto {

    private long PatientID;
    private Item item;


}
