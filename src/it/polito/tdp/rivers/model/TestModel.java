package it.polito.tdp.rivers.model;

public class TestModel {

	public static void main(String[] args) {
	
		Simulator s = new Simulator();
		River river = new River (1, "Jokulsa Eystri River");
		s.getMisurazioniPerFiume(river);
		System.out.println(river.getPrima().toString());
		System.out.println();
		System.out.println(river.getUltima().toString());
		System.out.println();
		System.out.println(river.getTotaleMisurazioni());
		System.out.println();
		System.out.println(river.getPortataMedia());
		System.out.println();
	}

}
