package com.interstellar.transport.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interstellar.transport.app.entity.DistanceBoundRouteImpl;

public interface DistanceBoundRouteRepository  extends JpaRepository<DistanceBoundRouteImpl, String>{

}
