package entities;

import java.time.LocalDate;

public class AttivitaDocumentazione extends Attivita{
	private String titoloDocu;
	private String formato;
	private String sezione;
	private String linkRisorsa;
	private Progetto progetto;
	
	
	//Costruttore per reperimento dati dal DB;
	public AttivitaDocumentazione(int idAttivita, String descrizione, LocalDate dataCreazione, LocalDate scadenza,
			String statoAvanzamento, String titoloDocu, String formato, String sezione, String linkRisorsa,
			Progetto progetto) {
		super(idAttivita, descrizione, dataCreazione, scadenza, statoAvanzamento);
		this.titoloDocu = titoloDocu;
		this.formato = formato;
		this.sezione = sezione;
		this.linkRisorsa = linkRisorsa;
		this.progetto = progetto;
	}

	//Costruttore per inserimento dati nel DB;
	public AttivitaDocumentazione(String descrizione, LocalDate scadenza, String titoloDocu, String formato,
			String sezione, String linkRisorsa, Progetto progetto) {
		super(descrizione, scadenza);
		this.titoloDocu = titoloDocu;
		this.formato = formato;
		this.sezione = sezione;
		this.linkRisorsa = linkRisorsa;
		this.progetto = progetto;
	}



	public String getTitoloDocu() {
		return titoloDocu;
	}

	public void setTitoloDocu(String titoloDocu) {
		this.titoloDocu = titoloDocu;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getSezione() {
		return sezione;
	}

	public void setSezione(String sezione) {
		this.sezione = sezione;
	}

	public String getLinkRisorsa() {
		return linkRisorsa;
	}

	public void setLinkRisorsa(String linkRisorsa) {
		this.linkRisorsa = linkRisorsa;
	}

	public Progetto getProgetto() {
		return progetto;
	}

	public void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}
	
	
}
