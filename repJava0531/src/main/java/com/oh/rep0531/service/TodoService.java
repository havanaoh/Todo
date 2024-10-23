package com.oh.rep0531.service;

import com.oh.rep0531.Entity.TodoEntity;
import com.oh.rep0531.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public String testService(){
        TodoEntity todoEntity = TodoEntity.builder().title("my first todo item").build();
        todoRepository.save(todoEntity);
        TodoEntity savaEntity = todoRepository.findById(todoEntity.getId()).get();
        return savaEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity){
        validate(entity);
        todoRepository.save(entity);
        log.info("Entity ID : {} is saved.", entity.getId());
        return todoRepository.findByUserId(entity.getUserId());
    }

    public void validate(final TodoEntity entity){
        if(entity ==null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }
        if(entity.getUserId()==null){
            log.warn("Unknown User");
            throw new RuntimeException("Unknown User");
        }
    }

    public List<TodoEntity> retrieve(final String userId){
        return todoRepository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity todoEntity){
        validate(todoEntity);
        final Optional<TodoEntity> original = todoRepository.findById(todoEntity.getId());
        original.ifPresent(todo ->{
            //반환된 todoEntity가 존재하면 값을 새 entity 값으로 덮어 씌운다.
            todo.setTitle(todoEntity.getTitle());
            todo.setDone(todoEntity.isDone());
            todoRepository.save(todo);
        });
//        51~55라인과 동일 람다식이냐 안냐
//        if(original.isPresent()){
//            final TodoEntity todo = original.get();
//            todo.setTitle(todoEntity.getTitle());
//            todo.setDone(todo.isDone());
//            todoRepository.save(todo);
//        }
        return retrieve(todoEntity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity todoEntity){
        validate(todoEntity);
        try {
            todoRepository.delete(todoEntity);
        }catch (Exception e){
            log.error("error deleting entity ", todoEntity.getId(), e);
            throw new RuntimeException("error deleting entity " + todoEntity.getId());
        }
        return retrieve(todoEntity.getUserId());
    }
}
