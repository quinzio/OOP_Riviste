package pubblicazioni;

import java.util.ArrayList;
import java.util.List;

public class Autore {
    private String name;
    private double impactFactor;
    private List<Articolo> articoli = new ArrayList<>();

    public Autore(String name, double impactFactor) {
	this.name = name;
	this.impactFactor = impactFactor;
    }
    
    public void addArticolo(Articolo a) {
	articoli.add(a);
    }
    
    public List<Articolo> getArticoli() {
	return articoli;
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
    
    public void increaseImpatFactor(double impactFactor) {
	this.impactFactor += impactFactor;
    }

    public void setImpactFactor(double impactFactor) {
        this.impactFactor = impactFactor;
    }

}
