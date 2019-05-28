package com.interstellar.transport.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "ROUTES", schema="PUBLIC")
@XmlRootElement(name="route")
@XmlAccessorType(XmlAccessType.FIELD)
public class DistanceBoundRouteImpl extends AbstractRoute {
	
	@Column(name= "DISTANCE")
	@XmlAttribute(name="weitage")
	private double weight;
	
	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public double getWeight() {
		return weight;
	}

}
