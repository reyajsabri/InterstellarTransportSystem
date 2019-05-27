package com.interstellar.transport.app.vo;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransportVO {

	@NotNull
	private String source;
	
	@NotNull
	private String destination;
	
	@JsonCreator
	public TransportVO(@JsonProperty("source") String source,
			@JsonProperty("destination") String destination) {
		this.source = source;
		this.destination = destination;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}
}