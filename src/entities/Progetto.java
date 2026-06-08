package entities;
import java.time.*;

public class Progetto {
	private int idProgetto;
	private String nome;
	private String descrizione;
	private LocalDate dataCreazione;
	private LocalDate dataConsegna;
	private boolean isProgettoGruppo;
	private Utente userAdmin;
	
	
	public Progetto(String nome, String descrizione, LocalDate dataCreazione, LocalDate dataConsegna,
			boolean isProgettoGruppo, Utente userAdmin) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione; //FIXME: Ricorda che il DB inserirà autoamticamente la data di crazione con CURRENT_DATE;
		this.dataConsegna = dataConsegna;
		this.isProgettoGruppo = isProgettoGruppo;
		this.userAdmin = userAdmin;
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
		return userAdmin;
	}
	public void setUserAdmin(Utente userAdmin) {
		this.userAdmin = userAdmin;
	}
}
