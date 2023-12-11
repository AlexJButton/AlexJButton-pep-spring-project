package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    List<Message> findByPostedBy(Integer posted_by);
}
