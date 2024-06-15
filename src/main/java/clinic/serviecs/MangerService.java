package clinic.serviecs;

import clinic.models.Manger;
import clinic.repositores.MangerRepository;
import org.springframework.stereotype.Service;

@Service
public class MangerService {
    private MangerRepository  mangerRepository;

    public MangerService(MangerRepository mangerRepository) {
        this.mangerRepository = mangerRepository;
    }

    public boolean checkAccount(String userName,String password){
        if(this.mangerRepository.findByUserNameAndPassword(userName, password)!=null)
            return true;
        return  false;

    }

    public void addManger(Manger manger){
        mangerRepository.save(manger);
    }
}
