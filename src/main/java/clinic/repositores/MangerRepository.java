package clinic.repositores;

import clinic.models.Manger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MangerRepository extends JpaRepository<Manger,Long> {
    Manger findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
    Manger findByUserName(@Param("userName") String userName);
}
