package clinic.serviecs;

import clinic.dtos.PatientPurchaseDto;
import clinic.helper.Item;
import clinic.models.PatientPurchase;
import clinic.repositores.PorchesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class PatientPurchaseService {
    @Autowired
    PorchesRepository porchesRepository;

    public PatientPurchaseService(PorchesRepository porchesRepository) {
        this.porchesRepository = porchesRepository;
    }
    public List<PatientPurchase> getAll(){
        return porchesRepository.findAll();
    }
    public PatientPurchase getById(long id){
        return porchesRepository.findFirstByPatientId(id);
    }

    public PatientPurchase getByPatient(long id){
        return porchesRepository.findFirstByPatientId(id);
    }

    public void addPurchase(PatientPurchaseDto patientPurchasedto)
    {  PatientPurchase checkPatientPurchase=porchesRepository.findFirstByPatientId(patientPurchasedto.getPatientID());
        if(checkPatientPurchase==null) {
            List<Item> items = new ArrayList<>();
            items.add(patientPurchasedto.getItem());
            PatientPurchase patientPurchase = new PatientPurchase();
            patientPurchase.setPatientId(patientPurchasedto.getPatientID());
            patientPurchase.setItems(items);
            porchesRepository.save(patientPurchase);
        }
        else {
            // Create a new Item
            Item newItem = new Item();
            newItem.setPurchaseDate(patientPurchasedto.getItem().getPurchaseDate()); // Set the purchase date
            newItem.setDescription(patientPurchasedto.getItem().getDescription()); // Set the description
            newItem.setMoneyPaid(patientPurchasedto.getItem().getMoneyPaid()); // Set the amount paid
            newItem.setNotPaid(patientPurchasedto.getItem().getNotPaid()); // Set the outstanding amount

            // Retrieve the existing PatientPurchase by ID
            PatientPurchase existingPurchase = porchesRepository.findFirstByPatientId(patientPurchasedto.getPatientID());

            // Add the new item to the items list
            existingPurchase.getItems().add(newItem);

            // Save the updated PatientPurchase
            porchesRepository.save(existingPurchase);
        }

    }
    public void delete(long id){
        porchesRepository.deleteByPatientId(id);
    }


    @Transactional
    public void addItem(long purchaseId){
        // Assuming you have an existing PatientPurchase with a specific ID (e.g., 123)

        // Create a new Item
        Item newItem = new Item();
        newItem.setPurchaseDate("2024-05-09"); // Set the purchase date
        newItem.setDescription("Please Workkk!!!"); // Set the description
        newItem.setMoneyPaid(800.0); // Set the amount paid
        newItem.setNotPaid(555.0); // Set the outstanding amount

        // Retrieve the existing PatientPurchase by ID
        PatientPurchase existingPurchase = porchesRepository.findFirstByPatientId(purchaseId);

        // Add the new item to the items list
        existingPurchase.getItems().add(newItem);

        // Save the updated PatientPurchase
        porchesRepository.save(existingPurchase);
    }

    public void addDummyData() {
        List<PatientPurchase> dummyData = createDummyData();
        porchesRepository.saveAll(dummyData);
    }

    private List<PatientPurchase> createDummyData() {
        List<PatientPurchase> dummyData = new ArrayList<>();
        for (int i = 4; i <= 16; i++) {
            PatientPurchase patientPurchase = new PatientPurchase();
          //  patientPurchase.setId(i);
            patientPurchase.setPatientId(i); // Example patient ID
            patientPurchase.setItems(createDummyItems());
            dummyData.add(patientPurchase);
        }
        return dummyData;
    }

    private List<Item> createDummyItems() {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 3; i++) { // Assuming each patient has 3 items
            Item item = new Item();
            item.setPurchaseDate("2024-05-09"); // Example purchase date
            item.setDescription("Item " + i); // Example description
            item.setMoneyPaid(100.0 * i); // Example money paid
            item.setNotPaid(50.0 * i); // Example not paid
            items.add(item);
        }
        return items;
    }
}
