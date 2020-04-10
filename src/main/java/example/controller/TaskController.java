package example.controller;

import example.entity.Status;
import example.entity.Task;
import example.service.ExecutorService;
import example.service.TaskService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private TaskService taskService;
    private ExecutorService executorService;

    @Autowired
    public TaskController(TaskService taskService, ExecutorService executorService) {
        this.taskService = taskService;
        this.executorService = executorService;
    }

    @PostMapping
    public Task createTask(@RequestBody @Valid Task task) {
        return taskService.create(task.getDescription());
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable(name = "id") Long taskId,
        @RequestParam(required = false) Status status,
        @RequestParam(required = false) Long executorId) {
        if (executorId != null) {
            return taskService.update(taskId, status, executorService.findById(executorId));
        } else {
            return taskService.update(taskId, status, null);
        }
    }

    @GetMapping
    public List<Task> getAll(){
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable(name = "id") Long id){
        return taskService.findById(id);
    }
}
