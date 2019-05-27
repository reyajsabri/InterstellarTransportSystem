package com.interstellar.transport.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.interstellar.transport.core.Planet;
import com.interstellar.transport.core.Route;

@Entity
@Table(name = "PLANET_NAMES", schema="PUBLIC")
public class PlanetImpl implements Planet {

	@Id
	@Column(name= "PLANET_NODE")
	private String id;
	@Column(name= "PLANET_NAME")
	private String name;
    
	@Transient
    private final Set<Route> neighbourPlanets = new HashSet<>();
     
    public PlanetImpl() {
    	
    }
    public PlanetImpl(String name) {
        this.name = name;
    }
 
    @Override
    public void addNeighbourPlanet(Route route) {
    	neighbourPlanets.add(route);
    }
  
    @Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public Set<Route> getNeighbourPlanets() {
		return neighbourPlanets;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof Planet)) {
			return false;
		}
		
		Planet other = (Planet)obj;
		if(id.equals(other.getId()) && name.equals(other.getName())) {
			return true;
		}
		return false;
    }

}
