package clinic.serviecs;


import clinic.models.ReciptionEmploye;
import clinic.repositores.ReciptionEmployeRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomMangerConfigService implements UserDetailsService {


    private final ReciptionEmployeRepository reciptionEmployeRepository;
    public CustomMangerConfigService( ReciptionEmployeRepository reciptionEmployeRepository) {
        this.reciptionEmployeRepository = reciptionEmployeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ReciptionEmploye reciptionEmploye=reciptionEmployeRepository.findByUserName(username);

        if(reciptionEmploye==null){
            throw new UsernameNotFoundException("Not found!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+reciptionEmploye.getRole()));


        return new org.springframework.security.core.userdetails.User(
                reciptionEmploye.getUserName(),
                reciptionEmploye.getPassword(),
                authorities
        );
    }
    public String getRole(){
        return "MANGER";
    }

}
