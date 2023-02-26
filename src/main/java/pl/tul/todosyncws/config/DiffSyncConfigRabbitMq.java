package pl.tul.todosyncws.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.sync.diffsync.PersistenceCallbackRegistry;
import org.springframework.sync.diffsync.config.DiffSyncConfigurerAdapter;
import org.springframework.sync.diffsync.config.EnableDifferentialSynchronization;
import pl.tul.todosyncws.callback.JpaPersistenceCallback;
import pl.tul.todosyncws.data.Todo;
import pl.tul.todosyncws.repository.TodoRepository;

@Configuration
@EnableDifferentialSynchronization
@RequiredArgsConstructor
@Profile("rabbitMq")
public class DiffSyncConfigRabbitMq extends DiffSyncConfigurerAdapter {

    private final TodoRepository todoRepository;

    @Override
    public void addPersistenceCallbacks(PersistenceCallbackRegistry registry) {
        JpaPersistenceCallback<Todo> todoCallback = new JpaPersistenceCallback<>(todoRepository, Todo.class);
        registry.addPersistenceCallback(todoCallback);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableStompBrokerRelay("/topic", "/queue")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setSystemLogin("admin")
                .setSystemPasscode("admin")
                .setClientLogin("admin")
                .setClientPasscode("admin");
        config.setApplicationDestinationPrefixes("/app");
    }
}
