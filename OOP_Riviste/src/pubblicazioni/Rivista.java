package pubblicazioni;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Rivista {
    private String name;
    private double impactFactor;
    private Map<String, Articolo> articoli = new TreeMap<>();
    static public Map<String, Autore> autori = new TreeMap<>();

    public Rivista(String name, double impactFactor) {
	this.name = name;
	this.impactFactor = impactFactor;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public double getImpactFactor() {
	return impactFactor;
    }

    public void setImpactFactor(double impactFactor) {
        this.impactFactor = impactFactor;
    }

    public void addArticolo(String titolo, Year anno, String... nomiAutori) throws Exception {
	if (articoli.containsKey(titolo))
	    throw new Exception("Duplicato");
	List<Autore> autoriLocal = new ArrayList<>();
	
	for (String autore : nomiAutori) {
	    if (!autori.containsKey(autore))
		autori.put(autore, new Autore(autore, 0));
	}
	for (String autore : nomiAutori) {
	    autori.get(autore).increaseImpatFactor(this.impactFactor);
	    autoriLocal.add(autori.get(autore));
	}
	articoli.put(titolo,new Articolo(titolo,anno, autoriLocal));
    }



    public List<Articolo> getArticoli() {
	List<Articolo> larticoli = new ArrayList<>(articoli.values());
	Collections.sort(larticoli,
		Comparator.comparing(Articolo::getAnno, Comparator.reverseOrder()).thenComparing(Articolo::getName));
	return larticoli;
    }
}
