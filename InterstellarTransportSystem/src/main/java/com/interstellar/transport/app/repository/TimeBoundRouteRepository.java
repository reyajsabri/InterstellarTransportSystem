package com.interstellar.transport.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interstellar.transport.app.entity.TimeBoundRouteImpl;

public interface TimeBoundRouteRepository  extends JpaRepository<TimeBoundRouteImpl, String>{

}