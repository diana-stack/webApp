package computerplus.com.pl.repositiries;

import computerplus.com.pl.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
