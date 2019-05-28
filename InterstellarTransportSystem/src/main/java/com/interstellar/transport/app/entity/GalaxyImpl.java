package com.interstellar.transport.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.interstellar.transport.core.Galaxy;
import com.interstellar.transport.core.Planet;

@XmlRootElement(name = "galaxy")
@XmlAccessorType(XmlAccessType.FIELD)
public class GalaxyImpl implements Galaxy{

	private transient final Set<Planet> planets = new HashSet<>();
	
	@XmlElement( name = "route" )
	private Set<DistanceBoundRouteImpl> routes = new HashSet<>();
    
    public void setRoutes(Set<DistanceBoundRouteImpl> routes) {
		this.routes = routes;
	}

	public Set<DistanceBoundRouteImpl> getRoutes() {
		return routes;
	}

	public void addPlanet(Planet planet) {
    	planets.add(planet);
    }

    
	public Set<Planet> getPlanets() {
		return planets;
	}
}
