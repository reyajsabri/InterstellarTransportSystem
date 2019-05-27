package com.interstellar.transport.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interstellar.transport.app.entity.PlanetImpl;

public interface PlanetRepository extends JpaRepository<PlanetImpl, String>{

}
