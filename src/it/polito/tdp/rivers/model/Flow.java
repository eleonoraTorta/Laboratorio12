package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Flow {
	
	 private int id;
	 private LocalDate data;
	 private double portata;
	 private River river;
	 
	 public Flow(int id , LocalDate data, double portata, River river) {
		 	this.id = id;
	 		this.data = data;
	 		this.portata = portata;
	 		this.river = river;
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public double getPortata() {
		return portata;
	}

	public void setPortata(double portata) {
		this.portata = portata;
	}

	public River getRiver() {
		return river;
	}

	public void setRiver(River river) {
		this.river = river;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(portata);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((river == null) ? 0 : river.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flow other = (Flow) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(portata) != Double.doubleToLongBits(other.portata))
			return false;
		if (river == null) {
			if (other.river != null)
				return false;
		} else if (!river.equals(other.river))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " " + data + " " + portata + " " + river + "\n";
	}
	 
	 
	

}
