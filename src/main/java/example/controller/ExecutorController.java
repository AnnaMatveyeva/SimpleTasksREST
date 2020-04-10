package example.controller;

import example.entity.Executor;
import example.service.ExecutorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/executor")
public class ExecutorController {

    private ExecutorService executorService;

    @Autowired
    public ExecutorController(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @PostMapping
    public Executor createExecutor(@RequestParam String name) {
        return executorService.createExecutor(name);
    }

    @GetMapping
    public List<Executor> getAll(){
        return executorService.findAll();
    }

    @GetMapping("/{id}")
    public Executor getById(@PathVariable(name = "id") Long id){
        return executorService.findById(id);
    }
}
