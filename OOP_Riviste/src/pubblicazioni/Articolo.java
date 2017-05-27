package pubblicazioni;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthorizeCallback;

public class Articolo {
	private String titolo;
	private Year anno;
	private List<Autore> autori;

	public Articolo(String name, Year anno, List<Autore> autori) {
		this.titolo = name;
		this.anno = anno;
		this.autori = autori;
	}

	public String getName() {
		return titolo;
	}
	public String getTitolo() {
		return titolo;
	}

	public Year getAnno() {
		return anno;
	}

}
