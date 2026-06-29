package entities;

import java.time.LocalDate;

public class PreparazioneEsami extends Progetto{
	private String codiceEsame;
	private String nomeEsame;
	private int cfu;
	private String docente;
	private LocalDate dataAppello;
	
	
	//Reperimento dati DB;
	public PreparazioneEsami(int idProgetto, String nome, String descrizione, LocalDate dataCreazione,
			LocalDate dataConsegna, boolean isProgettoGruppo, Utente userCreator, String codiceEsame, String nomeEsame,
			int cfu, String docente, LocalDate dataAppello) {
		super(idProgetto, nome, descrizione, dataCreazione, dataConsegna, isProgettoGruppo, userCreator);
		this.codiceEsame = codiceEsame;
		this.nomeEsame = nomeEsame;
		this.cfu = cfu;
		this.docente = docente;
		this.dataAppello = dataAppello;
	}
	
	public PreparazioneEsami(int idProgetto, String nome, String descrizione, LocalDate dataCreazione,
			LocalDate dataConsegna, boolean isProgettoGruppo, String userCreatorMatricola, String codiceEsame, String nomeEsame,
			int cfu, String docente, LocalDate dataAppello) {
		super(idProgetto, nome, descrizione, dataCreazione, dataConsegna, isProgettoGruppo, userCreatorMatricola);
		this.codiceEsame = codiceEsame;
		this.nomeEsame = nomeEsame;
		this.cfu = cfu;
		this.docente = docente;
		this.dataAppello = dataAppello;
	}


	//Inserimento dati DB;
	public PreparazioneEsami(String nome, String descrizione, LocalDate dataConsegna, boolean isProgettoGruppo,
			Utente userCreator, String codiceEsame, String nomeEsame, int cfu, String docente, LocalDate dataAppello) {
		super(nome, descrizione, dataConsegna, isProgettoGruppo, userCreator);
		this.codiceEsame = codiceEsame;
		this.nomeEsame = nomeEsame;
		this.cfu = cfu;
		this.docente = docente;
		this.dataAppello = dataAppello;
	}


	public String getCodiceEsame() {
		return codiceEsame;
	}


	public void setCodiceEsame(String codiceEsame) {
		this.codiceEsame = codiceEsame;
	}


	public String getNomeEsame() {
		return nomeEsame;
	}


	public void setNomeEsame(String nomeEsame) {
		this.nomeEsame = nomeEsame;
	}


	public int getCfu() {
		return cfu;
	}


	public void setCfu(int cfu) {
		this.cfu = cfu;
	}


	public String getDocente() {
		return docente;
	}


	public void setDocente(String docente) {
		this.docente = docente;
	}


	public LocalDate getDataAppello() {
		return dataAppello;
	}


	public void setDataAppello(LocalDate dataAppello) {
		this.dataAppello = dataAppello;
	}
	
}

