package main;

import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

import pubblicazioni.Articolo;
import pubblicazioni.Autore;
import pubblicazioni.Pubblicazioni;
import pubblicazioni.Rivista;

public class Main {

	public static void main(String[] args) throws Exception {
		Pubblicazioni p = new Pubblicazioni();
		p.addRivista("corriere scientifico", 1.5);
		Rivista cs = p.getRivista("corriere scientifico");
		Rivista fm = p.addRivista("fisica e metafisica", 1.2);
		Rivista uie = p.addRivista("universo in espansione", 2.4);
		try {
			p.addRivista("corriere scientifico", 3.5);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		uie.addArticolo("big bang aggiornato", Year.of(2014), "quercia",
				"acero");
		fm.addArticolo("materia oscura", Year.of(2010), "quercia", "faggio");
		cs.addArticolo("nanotecnologie", Year.of(2012), "faggio", "acero");
		cs.addArticolo("alcuni problemi aperti", Year.of(2012), "quercia");
		try {
			cs.addArticolo("alcuni problemi aperti", Year.of(2014), "quercia");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		List<Articolo> articoli = cs.getArticoli(); // ordinati per anno e
													// titolo
		for (Articolo a : articoli)
			System.out.println(a.getAnno() + " " + a.getTitolo());
		Autore quercia = p.getAutore("quercia");
		articoli = quercia.getArticoli();
		for (Articolo a : articoli)
			System.out.println(a.getAnno() + " " + a.getTitolo());
		List<Rivista> riviste = p.getRiviste();
		for (Rivista r : riviste)
			System.out.println(r.getTitolo() + " " + r.getIF());
		for (Autore a : p.getAutori())
			System.out.println(a.getNome() + " " + a.getIF());

		SortedMap<Year, Long> mappaArticoliPerAnno = p.articoliPerAnno();
		System.out.println("mappaArticoliPerAnno: " + mappaArticoliPerAnno);
		Rivista r = p.rivistaMaxArticoli();
		System.out.println("rivistaMaxArticoli: " + r.getTitolo() + " "
				+ r.getArticoli().size());
		SortedMap<String, Long> mappaArticoliPerAutore = p.articoliPerAutore();
		System.out.println("mappaArticoliPerAutore: " + mappaArticoliPerAutore);
		SortedMap<Long, TreeSet<String>> mappaAutoriPerNarticoli = p
				.autoriPerNarticoli();
		System.out.println("mappaAutoriPerNarticoli: "
				+ mappaAutoriPerNarticoli);

		p = new Pubblicazioni();
		System.out.println("lettura file");
		p.letturaFile("pubblicazioni.txt");
		riviste = p.getRiviste();
		for (Rivista r1 : riviste)
			System.out.println(r1.getTitolo() + " " + r1.getIF());
		for (Autore a : p.getAutori())
			System.out.println(a.getNome() + " " + a.getIF());
	}

}
