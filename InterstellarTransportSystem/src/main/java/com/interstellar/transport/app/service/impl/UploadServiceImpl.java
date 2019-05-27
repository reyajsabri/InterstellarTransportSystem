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
import com.interstellar.transport.app.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService{
	
	@Autowired
	PlanetRepository planetRepository;
	@Autowired
	DistanceBoundRouteRepository distanceBoundRouteRepogitory;
	@Autowired
	TimeBoundRouteRepository timeBoundRouteRepogitory;
	
	@Override
	public void saveUploadDataToDB(List<PlanetImpl> planets, List<DistanceBoundRouteImpl> routes, List<TimeBoundRouteImpl> traffics) {
		planetRepository.saveAll(planets);
		planetRepository.flush();
		distanceBoundRouteRepogitory.saveAll(routes);
		distanceBoundRouteRepogitory.flush();
		timeBoundRouteRepogitory.saveAll(traffics);
		timeBoundRouteRepogitory.flush();
	}

}
