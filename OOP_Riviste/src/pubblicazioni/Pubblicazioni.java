package pubblicazioni;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Pubblicazioni {
    private List<Rivista> riviste = new ArrayList<>();

    public Rivista getRivista(String titolo) throws Exception {
	List<Rivista> rcoll = riviste.stream().filter(r -> r.getName().equals(titolo)).collect(Collectors.toList());
	if (rcoll.size() != 1)
	    throw new Exception("Problema ricerca rivista");
	return rcoll.get(0);
    }

    public List<Rivista> getRiviste() {
	return riviste;
    }

    public Autore getAutore(String nome) throws Exception {
	if (!Rivista.autori.containsKey(nome))
	    throw new Exception("Autore non trovato");
	return Rivista.autori.get(nome);

    }

    public List<Autore> getAutori() {
	List<Autore> autoriL = new ArrayList<>(Rivista.autori.values());
	Collections.sort(autoriL, Comparator.comparing(Autore::getImpactFactor, Comparator.reverseOrder())
		.thenComparing(Autore::getName));
	return autoriL;
    }

    public SortedMap<Year, Long> articoliPerAnno() {
	return riviste.stream().flatMap(r -> r.getArticoli().stream())
		.collect(Collectors.groupingBy(Articolo::getAnno, TreeMap::new, Collectors.counting()));
    }

    public Rivista rivistaMaxArticoli() {
	return riviste.stream().collect(Collectors.maxBy(Comparator.comparing(r -> r.getArticoli().size()))).get();

    }

    public SortedMap<String, Long> articoliPerAutore() {
	Map<String, Long> m1 = Rivista.autori.values().stream()
		.collect(Collectors.toMap(Autore::getName, a -> Long.valueOf(a.getArticoli().size())));
	return new TreeMap<>(m1);

    }

    public SortedMap<Long, TreeSet<String>> autoriPerNarticoli() {
	SortedMap<Long, TreeSet<String>> v2 = new TreeMap<>();
	SortedMap<Long, Set<String>> v1 = articoliPerAutore().entrySet().stream().collect(Collectors
		.groupingBy(e -> e.getValue(), TreeMap::new, Collectors.mapping(e -> e.getKey(), Collectors.toSet())));

	for (Entry<Long, Set<String>> e : v1.entrySet()) {
	    v2.put(e.getKey(), new TreeSet<>(e.getValue()));
	}
	return v2;
    }

}
