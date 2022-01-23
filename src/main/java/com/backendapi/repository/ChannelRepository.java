package com.backendapi.repository;

import com.backendapi.entity.maindb.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

}