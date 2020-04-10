package example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "executors")
public class Executor {

    @Id
    @GeneratedValue(generator = "executor_generator")
    @SequenceGenerator(name = "executor_generator", sequenceName = "executor_sequence", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20, message = "The name must contain more than 3 characters, but less than 20 characters.")
    private String name;

    public Executor() {
    }

    public Executor(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
