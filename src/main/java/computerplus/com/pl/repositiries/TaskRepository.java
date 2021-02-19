package computerplus.com.pl.repositiries;

import computerplus.com.pl.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
