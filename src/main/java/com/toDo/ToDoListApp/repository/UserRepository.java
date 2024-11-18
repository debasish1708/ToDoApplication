package com.toDo.ToDoListApp.repository;

import com.toDo.ToDoListApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    void deleteByUserName(String username);
    User findByUserName(String username);
    boolean existsByUserName(String username);
}
