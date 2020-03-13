package it.polito.tdp.parole;

import java.util.*;

public class Parole2 {
	
	private List<String> listaParole;
	
	public Parole2() {
		listaParole=new LinkedList<String>();
	}
	
	public void addParola(String p) {
		listaParole.add(p);
	}
	
	public List<String> getElenco() {
		Collections.sort(listaParole);
		return listaParole;
	}
	
	public void reset() {
		listaParole.clear();
	}


}
