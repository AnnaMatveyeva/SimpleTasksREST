package example.service;

import example.entity.Executor;
import example.exception.ExecutorNotFounException;
import example.repository.ExecutorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecutorService {

    private ExecutorRepository executorRepository;

    @Autowired
    public ExecutorService(ExecutorRepository executorRepository) {
        this.executorRepository = executorRepository;
    }

    public List<Executor> findAll(){
        return (List<Executor>) executorRepository.findAll();
    }

    public Executor createExecutor(String name) {
        Executor executor = new Executor(name);
        return executorRepository.save(executor);
    }

    public Executor findById(Long executorId) {
        Optional<Executor> optionalExecutor = executorRepository.findById(executorId);
        return optionalExecutor.orElseThrow(() -> new ExecutorNotFounException(executorId));
    }
}
