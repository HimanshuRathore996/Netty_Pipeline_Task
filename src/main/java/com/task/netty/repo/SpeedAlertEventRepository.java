package com.task.netty.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.netty.entity.SpeedAlertEvent;

public interface SpeedAlertEventRepository extends JpaRepository<SpeedAlertEvent, Long> {

}
