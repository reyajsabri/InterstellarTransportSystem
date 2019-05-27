package com.interstellar.transport.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.interstellar.transport.core.Planet;
import com.interstellar.transport.core.Route;

@MappedSuperclass
public abstract class AbstractRoute implements Route {

	@Id
	@Column(name= "ROUTE_ID")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "PLANET_ORIGIN")
	private PlanetImpl source;
	
	@ManyToOne
	@JoinColumn(name = "PLANET_DESTNATION")
	private PlanetImpl destination;

	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Planet getDestination() {
		return destination;
	}

	@Override
	public Planet getSource() {
		return source;
	}
	
	public void setDestination(PlanetImpl destination) {
		this.destination = destination;
	}

	public void setSource(PlanetImpl source) {
		this.source = source;
	}

}
