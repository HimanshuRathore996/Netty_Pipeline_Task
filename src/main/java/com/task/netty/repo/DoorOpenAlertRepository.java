package com.task.netty.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.netty.entity.DoorOpenAlert;

public interface DoorOpenAlertRepository extends JpaRepository<DoorOpenAlert, Long>{

}
