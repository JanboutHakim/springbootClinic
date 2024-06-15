package clinic.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item
{    private String purchaseDate;
     private String description;
     private double moneyPaid;
     private double notPaid;

     @Override
     public String toString() {
          return "Item{" +
                  "purchaseDate='" + purchaseDate + '\'' +
                  ", description='" + description + '\'' +
                  ", moneyPaid=" + moneyPaid +
                  ", notPaid=" + notPaid +
                  '}';
     }
}
