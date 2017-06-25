package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Event implements Comparable <Event>{
	
	
	private LocalDate time;
	private double fin;

	public Event(LocalDate time, double fin) {
		super();
		this.time = time;
		this.fin= fin;

	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}


	public double getFin() {
		return fin;
	}

	public void setFin(double fin) {
		this.fin = fin;
	}

	@Override
	public int compareTo(Event altro) {
		if( this.time.isBefore(altro.time)){
			return -1;
		}
		if( this.time.isAfter(altro.time)){
			return -1;
		}
		return 0;
	}
	
	
	

}
