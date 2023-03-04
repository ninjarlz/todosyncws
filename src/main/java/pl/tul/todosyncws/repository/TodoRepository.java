package pl.tul.todosyncws.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.tul.todosyncws.data.Todo;

import java.util.List;

public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {
    List<Todo> findAll();
}
