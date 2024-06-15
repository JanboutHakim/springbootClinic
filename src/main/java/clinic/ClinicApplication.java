package clinic;

import clinic.helper.Item;
import clinic.models.Doctor;
import clinic.models.ReciptionEmploye;
import clinic.repositores.PorchesRepository;
import clinic.repositores.ReciptionEmployeRepository;
import clinic.serviecs.ReciptionEmployeeServies;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ClinicApplication {


	public static void main(String[] args) {
		SpringApplication.run(ClinicApplication.class, args);

	}
	//you can see this method in all project classes
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}

	@Bean
	public static PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}





}
