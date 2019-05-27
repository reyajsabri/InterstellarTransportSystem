package com.interstellar.transport.app.entity;

import java.util.HashSet;
import java.util.Set;

import com.interstellar.transport.core.Galaxy;
import com.interstellar.transport.core.Planet;

public class GalaxyImpl implements Galaxy{

	private final Set<Planet> planets = new HashSet<>();
    
    public void addPlanet(Planet planet) {
    	planets.add(planet);
    }

	public Set<Planet> getPlanets() {
		return planets;
	}
}
