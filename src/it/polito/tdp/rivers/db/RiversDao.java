package it.polito.tdp.rivers.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

public class RiversDao {
	
	public List <River> getAllRivers(){
		
		final String sql = "SELECT * FROM river ORDER BY id ASC";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			List <River> lista = new ArrayList <River>();

			while (rs.next()) {
				
				int id = rs.getInt("id");
				String nome = rs.getString("name");
				
				River river = new River(id, nome);
				lista.add(river);
			}

			rs.close();
			conn.close();
			return lista ;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}
	
	
	public TreeMap <Integer, Flow> getFlowsFromRiver (River r){
		
		final String sql = "SELECT * " +
				 "FROM flow " +
				 "WHERE river = ? " +
				 "ORDER BY id ASC";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			
			ResultSet rs = st.executeQuery();
			TreeMap <Integer, Flow> mappa = new TreeMap <Integer, Flow>();

			while (rs.next()) {
				
				int id = rs.getInt("id");
				Date data = rs.getDate("day");
				double portata = rs.getFloat("flow");
				int idFiume = rs.getInt("river");
				
				Flow flow = new Flow (id, data.toLocalDate() , portata, r);
				mappa.put(flow.getId(), flow);
			}

			rs.close();
			conn.close();
			return mappa ;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		
	}

}
