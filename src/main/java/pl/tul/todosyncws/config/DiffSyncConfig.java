package pl.tul.todosyncws.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.sync.diffsync.PersistenceCallbackRegistry;
import org.springframework.sync.diffsync.config.DiffSyncConfigurerAdapter;
import org.springframework.sync.diffsync.config.EnableDifferentialSynchronization;
import pl.tul.todosyncws.callback.JpaPersistenceCallback;
import pl.tul.todosyncws.data.Todo;
import pl.tul.todosyncws.repository.TodoRepository;

@Configuration
@EnableDifferentialSynchronization
@RequiredArgsConstructor
public class DiffSyncConfig extends DiffSyncConfigurerAdapter {

    private final TodoRepository todoRepository;

    @Override
    public void addPersistenceCallbacks(PersistenceCallbackRegistry registry) {
        JpaPersistenceCallback<Todo> todoCallback = new JpaPersistenceCallback<>(todoRepository, Todo.class);
        registry.addPersistenceCallback(todoCallback);
    }
}
