package com.task.netty.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.netty.entity.CurrentLocationEvent;

public interface CurrentLocationEventRepository extends JpaRepository<CurrentLocationEvent, Long> {

}
