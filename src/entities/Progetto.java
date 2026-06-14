package entities;
import java.time.*;

public class Progetto {
	private int idProgetto;
	private String nome;
	private String descrizione;
	private LocalDate dataCreazione;
	private LocalDate dataConsegna;
	private boolean isProgettoGruppo;
	private Utente userCreator;
	
	//Costruttore per riperimento dati dal DB;
	public Progetto(int idProgetto,String nome, String descrizione, LocalDate dataCreazione, LocalDate dataConsegna,
			boolean isProgettoGruppo, Utente userCreator) {
		
		this.idProgetto = idProgetto;
		this.nome = nome;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione; 
		this.dataConsegna = dataConsegna;
		this.isProgettoGruppo = isProgettoGruppo;
		this.userCreator = userCreator;
		
	}
	
	public Progetto(String nome, String descrizione, LocalDate dataCreazione, LocalDate dataConsegna) {
		
		this.nome = nome;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione; 
		this.dataConsegna = dataConsegna;
		
	}
	
	//Costruttore per inserimento dati nel DB;
	public Progetto(String nome, String descrizione, LocalDate dataConsegna,
			boolean isProgettoGruppo, Utente userCreator) {
		
		this.nome = nome;
		this.descrizione = descrizione;
		this.dataConsegna = dataConsegna;
		this.isProgettoGruppo = isProgettoGruppo;
		this.userCreator = userCreator;
		
	}
	
	
	public int getIdProgetto() {
		return idProgetto;
	}
	public void setIdProgetto(int idProgetto) {
		this.idProgetto = idProgetto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public LocalDate getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public LocalDate getDataConsegna() {
		return dataConsegna;
	}
	public void setDataConsegna(LocalDate dataConsegna) {
		this.dataConsegna = dataConsegna;
	}
	public boolean isProgettoGruppo() {
		return isProgettoGruppo;
	}
	public void setProgettoGruppo(boolean isProgettoGruppo) {
		this.isProgettoGruppo = isProgettoGruppo;
	}
	public Utente getUserAdmin() {
		return userCreator;
	}
	public void setUserAdmin(Utente userAdmin) {
		this.userCreator = userAdmin;
	}
}
