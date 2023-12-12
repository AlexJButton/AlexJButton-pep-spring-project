package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Integer>{
}
