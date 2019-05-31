package com.interstellar.transport.app.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="route")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdaptedRoute extends AbstractRoute {

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
