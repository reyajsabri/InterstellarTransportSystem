package com.interstellar.transport.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TRAFFIC", schema="PUBLIC")	
public class TimeBoundRouteImpl extends AbstractRoute {

	@Column(name= "DELAY")
	private double weight;
	
	@Override
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
