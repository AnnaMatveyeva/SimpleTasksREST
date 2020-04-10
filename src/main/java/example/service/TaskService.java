package example.service;

import example.entity.Executor;
import example.entity.Status;
import example.entity.Task;
import example.exception.TaskNotFoundException;
import example.exception.UnableToChangeTaskExecutorException;
import example.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
        return (List<Task>) taskRepository.findAll();
    }

    public Task create(String desc) {
        Task task = new Task(desc);
        task.setStatus(Status.OPEN);
        return taskRepository.save(task);
    }

    public Task update(Long taskId, Status status, Executor executor) {
        Task task = findById(taskId);
        if (executor != null) {
            setTaskExecutor(task, executor);
        }
        if (status != null) {
            task.setStatus(status);
        }

        return taskRepository.save(task);
    }


    public Task setTaskExecutor(Task task, Executor executor) {
        if (task.getStatus() == Status.OPEN) {
            task.setExecutor(executor);
            return task;
        } else {
            throw new UnableToChangeTaskExecutorException(task.getStatus());
        }
    }

    public Task findById(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        return optionalTask.orElseThrow(() -> new TaskNotFoundException(taskId));
    }
}
