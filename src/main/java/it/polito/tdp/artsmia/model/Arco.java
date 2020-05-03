package it.polito.tdp.artsmia.model;

public class Arco {

	private ArtObject o1;
	private ArtObject o2;
	private int peso;

	public Arco(ArtObject o1, ArtObject o2, int peso) {
		this.o1 = o1;
		this.o2 = o2;
		this.peso = peso;
	}

	public ArtObject getO1() {
		return o1;
	}

	public ArtObject getO2() {
		return o2;
	}

	public int getPeso() {
		return peso;
	}

}
