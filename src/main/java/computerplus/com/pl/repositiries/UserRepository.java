package computerplus.com.pl.repositiries;

import computerplus.com.pl.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{
    Optional<User> findById(UUID id);
}
