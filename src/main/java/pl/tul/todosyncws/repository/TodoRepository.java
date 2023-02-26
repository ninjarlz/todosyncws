package pl.tul.todosyncws.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.tul.todosyncws.data.Todo;

public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {}
