package com.interstellar.transport.app.service;

import java.util.List;

import com.interstellar.transport.app.entity.DistanceBoundRouteImpl;
import com.interstellar.transport.app.entity.PlanetImpl;
import com.interstellar.transport.app.entity.TimeBoundRouteImpl;

public interface TransportService {
	public List<PlanetImpl> getAllPlanet();
	public PlanetImpl getPlanetById(String id);
	public List<DistanceBoundRouteImpl> getAllRoutes();
	public List<TimeBoundRouteImpl> getAllTraffic();
}
