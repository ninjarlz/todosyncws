package pl.tul.todosyncws.callback;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.sync.diffsync.PersistenceCallback;
import org.springframework.sync.diffsync.exception.ResourceNotFoundException;

import java.util.List;

@RequiredArgsConstructor
public class JpaPersistenceCallback<T> implements PersistenceCallback<T> {

    private final CrudRepository<T, Long> repository;
    private final Class<T> entityType;

    @Override
    public List<T> findAll() {
        return (List<T>) repository.findAll();
    }

    @Override
    public T findOne(String id) throws ResourceNotFoundException {
        return repository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public void persistChange(T itemToSave) {
        repository.save(itemToSave);
    }

    @Override
    public void persistChanges(List<T> itemsToSave, List<T> itemsToDelete) {
        repository.saveAll(itemsToSave);
        repository.deleteAll(itemsToDelete);
    }

    @Override
    public Class<T> getEntityType() {
        return entityType;
    }

}
