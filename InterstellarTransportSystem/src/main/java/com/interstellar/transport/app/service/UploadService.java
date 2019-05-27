package com.interstellar.transport.app.service;

import java.util.List;

import com.interstellar.transport.app.entity.DistanceBoundRouteImpl;
import com.interstellar.transport.app.entity.PlanetImpl;
import com.interstellar.transport.app.entity.TimeBoundRouteImpl;

public interface UploadService {
	public void saveUploadDataToDB(List<PlanetImpl> planets, List<DistanceBoundRouteImpl> routes, List<TimeBoundRouteImpl> traffics);
}
