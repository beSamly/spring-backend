package com.backendapi.repository;

import com.backendapi.entity.maindb.Channel;
import com.backendapi.entity.maindb.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE m.channel.channelId = ?1")
    List<Message> findByChannelId(Long id);

}