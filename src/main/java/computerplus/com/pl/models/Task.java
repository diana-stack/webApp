package computerplus.com.pl.models;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tasks", schema = "test_sh")
public class Task {
    @Id
    @GeneratedValue
    private UUID id;
    
    private String title;
    
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private User user;

    public User getUser() {      
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "startdate")
    private Date startDate;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "enddate")
    private Date endDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                title.equals(task.title) &&
                user.equals(task.user) &&
                startDate.equals(task.startDate) &&
                endDate.equals(task.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, user, startDate, endDate);
    }
}
