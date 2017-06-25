package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;
import it.polito.tdp.rivers.db.RiversDao;

public class Simulator {
	
	private RiversDao rdao = new RiversDao();
	private List <River> fiumi;
	
	// Parametri simulazione
	double fmed;
	double k;
	double q;
	double c;
	double foutmin;
	double fin;
	int numSimulazioni;
	double cTotale =0.0;
	
	// Stato del mondo
	
	// Misure d'interesse
	private int giorniInsoddisfatti;
	private double capacitaMedia;
	
	// Coda di eventi
	private PriorityQueue<Event> queue;
	
	public Simulator() {
		super();
		this.giorniInsoddisfatti = 0;
		this.capacitaMedia = 0.0;
		this.queue = new PriorityQueue<>();
		fmed = 0.0;
		k=0.0;
	}
	
	
	public void addFlow(Flow f) {
		Event e = new Event(f.getData(), f.getPortata());
		queue.add(e);
	}
	
	public void setParametri(double fmed, double k, int numeroTotale) {
		this.fmed= fmed;
		this.k=k;
		this.numSimulazioni = numeroTotale;
	}
	
	public void run() {
		this.giorniInsoddisfatti = 0;
		double q = k* fmed * (2592000);
		double c = q/2.0;
		double foutmin = 0.8 * fmed;
		this.cTotale = 0.0;
		
		while (!queue.isEmpty()) {
			Event e = queue.poll();
			
			// Ricavo fin
			fin = e.getFin();
			
			// Calcolo il nuovo c -->  c = c + fin 
			c = c + fin;
			
			// verifico che c < q
			if( c > q){
				// se c > q 
				// c nuovo = q
				c = q;
			} 
			
			// Gestisco probabilita che nel 5 % dei casi foutmin = 10 * foutmin
			double probabilita = Math.random();
		//	if( this.probabilita() == true){
			if( probabilita <= 0.05){
				// la probabilita è del 5 %
				foutmin = 10 * foutmin;
			}
			
			// Verifico se c > foutmin
			if( c > foutmin){
				// ho abbastanza acqua
				c = c - foutmin;
			} else {
				// non ho abbastanza acqua
				c =0;
				this.giorniInsoddisfatti++;
			}
			cTotale+= c;
		}
		capacitaMedia = cTotale / numSimulazioni ;
				
		}
			
	/*
		private boolean probabilita(){
			 int random = (int) (1 + (Math.random() * 100));
			 if( random <= 5){
				 return true;
			 }
			 return false;
		}
	*/	
	
	
	public List <River> getFiumi (){
		if (fiumi== null){
			fiumi =  rdao.getAllRivers();
		}
	    return fiumi;
	}
	

	public void getMisurazioniPerFiume( River r){
		r.setMisurazioni( rdao.getFlowsFromRiver(r));
	}


	public int getGiorniInsoddisfatti() {
		return giorniInsoddisfatti;
	}


	public double getCapacitaMedia() {
		return capacitaMedia;
	}


	

	




















	

}
