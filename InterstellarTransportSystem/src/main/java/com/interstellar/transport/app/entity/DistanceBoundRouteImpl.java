package com.interstellar.transport.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ROUTES", schema="PUBLIC")
public class DistanceBoundRouteImpl extends AbstractRoute {
	
	@Column(name= "DISTANCE")
	private double weight;
	
	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public double getWeight() {
		return weight;
	}

}
