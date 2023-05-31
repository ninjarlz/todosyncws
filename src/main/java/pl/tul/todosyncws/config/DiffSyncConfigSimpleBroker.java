package pl.tul.todosyncws.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.sync.diffsync.PersistenceCallbackRegistry;
import org.springframework.sync.diffsync.config.DiffSyncConfigurerAdapter;
import org.springframework.sync.diffsync.config.EnableDifferentialSynchronization;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import pl.tul.todosyncws.callback.JpaPersistenceCallback;
import pl.tul.todosyncws.data.Todo;
import pl.tul.todosyncws.repository.TodoRepository;

@Configuration
@EnableDifferentialSynchronization
@RequiredArgsConstructor
@Profile("simpleBroker")
public class DiffSyncConfigSimpleBroker extends DiffSyncConfigurerAdapter {

    private final TodoRepository todoRepository;

    @Override
    public void addPersistenceCallbacks(PersistenceCallbackRegistry registry) {
        JpaPersistenceCallback<Todo> todoCallback = new JpaPersistenceCallback<>(todoRepository, Todo.class);
        registry.addPersistenceCallback(todoCallback);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        String path = String.format("/%s/websocket", diffSyncPath);
        registry.addEndpoint(path).setAllowedOrigins("*");
        registry.addEndpoint(path).setAllowedOrigins("*").withSockJS();
    }
}
