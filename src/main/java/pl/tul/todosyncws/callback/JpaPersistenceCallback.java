package pl.tul.todosyncws.callback;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.sync.diffsync.PersistenceCallback;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class JpaPersistenceCallback<T> implements PersistenceCallback<T> {

    private final CrudRepository<T, Long> repository;
    private final Class<T> entityType;

    @Override
    public List<T> findAll() {
        return (List<T>) repository.findAll();
    }

    @Override
    public T findOne(String id) {
        return repository.findById(Long.valueOf(id)).orElseThrow(NoSuchElementException::new);
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
