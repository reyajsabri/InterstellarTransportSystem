package com.interstellar.transport.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.interstellar.transport.core.Planet;
import com.interstellar.transport.core.Route;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({AdaptedRoute.class})
@XmlJavaTypeAdapter(RouteAdapter.class)
public abstract class AbstractRoute implements Route {

	@Id
	@Column(name= "ROUTE_ID")
	@XmlAttribute(name="id")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "PLANET_ORIGIN")
	@XmlElement(name="sourcePlanet")
	private PlanetImpl source;
	
	@ManyToOne
	@JoinColumn(name = "PLANET_DESTNATION")
	@XmlElement(name="destinationPlanet")
	private PlanetImpl destination;
	
	@XmlAttribute(name="type")
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
