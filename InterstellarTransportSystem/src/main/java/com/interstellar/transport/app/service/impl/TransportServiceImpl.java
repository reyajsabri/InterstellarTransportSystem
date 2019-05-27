package com.interstellar.transport.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interstellar.transport.app.entity.DistanceBoundRouteImpl;
import com.interstellar.transport.app.entity.PlanetImpl;
import com.interstellar.transport.app.entity.TimeBoundRouteImpl;
import com.interstellar.transport.app.repository.DistanceBoundRouteRepository;
import com.interstellar.transport.app.repository.PlanetRepository;
import com.interstellar.transport.app.repository.TimeBoundRouteRepository;
import com.interstellar.transport.app.service.TransportService;

@Service
public class TransportServiceImpl implements TransportService{

	@Autowired
	PlanetRepository planetRepository;
	@Autowired
	DistanceBoundRouteRepository distanceBoundRouteRepogitory;
	@Autowired
	TimeBoundRouteRepository timeBoundRouteRepogitory;
	
	@Override
	public List<PlanetImpl> getAllPlanet(){
		return planetRepository.findAll();
	}
	
	@Override
	public PlanetImpl getPlanetById(String id){
		return planetRepository.findById(id).get();
	}
	
	@Override
	public List<DistanceBoundRouteImpl> getAllRoutes(){
		return distanceBoundRouteRepogitory.findAll();
	}
	
	@Override
	public List<TimeBoundRouteImpl> getAllTraffic(){
		return timeBoundRouteRepogitory.findAll();
	}
}
