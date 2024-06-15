package clinic.serviecs;

import clinic.dtos.ReciptionEmployeDTO;
import clinic.models.ReciptionEmploye;
import clinic.repositores.ReciptionEmployeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReciptionEmployeeServies {
    @Autowired
   private ReciptionEmployeRepository reciptionEmployeRepository;
   private final ModelMapper modelMapper;
    private ReciptionEmploye reciptionEmploye;

    public ReciptionEmployeeServies(ReciptionEmployeRepository reciptionEmployeRepository, ModelMapper modelMapper ) {
        this.reciptionEmployeRepository = reciptionEmployeRepository;
        this.modelMapper = modelMapper;
    }
  public boolean checkAccount(String username,String password){
       reciptionEmploye =this.reciptionEmployeRepository.findByUserNameAndPassword(username,password);
      return reciptionEmploye != null;

  }
  public boolean checkUserName(String username){
        reciptionEmploye =this.reciptionEmployeRepository.findByUserName(username);
      return reciptionEmploye != null;

  }
  public void makeAccount(ReciptionEmploye reciptionEmploye){
        reciptionEmploye.setRole("EMPLOYEE");
      this.reciptionEmployeRepository.save(reciptionEmploye);
  }


  public List<ReciptionEmploye> findByName(String first_name){
      System.out.println(first_name);
     return reciptionEmployeRepository.findByFirstName(first_name);
  }
  public ReciptionEmploye findByID(long id){
      return reciptionEmployeRepository.findById(id).get();
  }
  public List<ReciptionEmployeDTO>  getAll(){
      return reciptionEmployeRepository.findAll().stream().map(RE -> this.modelMapper.map(RE, ReciptionEmployeDTO.class) ).collect(Collectors.toList());
  }

  public ReciptionEmploye getById(long id){
      return  reciptionEmployeRepository.findById(id).get();
  }
  @Transactional
  public void editAccount(ReciptionEmploye updatedReciptionEmploye){
      ReciptionEmploye reciptionEmploye=new ReciptionEmploye();
     modelMapper.map(updatedReciptionEmploye,reciptionEmploye);
      // Call the repository method to update the ReciptionEmploye
      reciptionEmployeRepository.updateReciptionEmploye(
              reciptionEmploye.getAddress(),
              reciptionEmploye.getDob(),
              reciptionEmploye.getEducation(),
              reciptionEmploye.getEmail(),
              reciptionEmploye.getFirstName(),
              reciptionEmploye.getLastName(),
              reciptionEmploye.getPassword(),
              reciptionEmploye.getPhoneNumber(),
              reciptionEmploye.getSalary(),
              reciptionEmploye.getSex(),
              reciptionEmploye.getUserName(),
              reciptionEmploye.getWorkHours(),
              updatedReciptionEmploye.getReciptionEmployeId() // expr
      );

    }

    public void deleteAccount(long id){
        reciptionEmployeRepository.deleteById(id);
    }
}
