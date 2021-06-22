package computerplus.com.pl.repositiries;

import computerplus.com.pl.models.Task;
import computerplus.com.pl.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    Optional<Task> findById(UUID id);
}
