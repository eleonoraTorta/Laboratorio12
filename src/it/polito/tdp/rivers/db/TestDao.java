package it.polito.tdp.rivers.db;

import it.polito.tdp.rivers.model.River;

public class TestDao {

	public static void main(String[] args) {
		
		RiversDao r = new RiversDao();
		River river = new River (1, "Jokulsa Eystri River");
		System.out.println(r.getAllRivers());
		System.out.println(r.getFlowsFromRiver(river));

	}

}
