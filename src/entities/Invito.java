package entities;

public class Invito {
	private Utente utenteAdmin;
	private Utente utenteInvitato;
	private Progetto progetto;
	
	private String matricolaInvitante;
	private int idProgetto ;
	
	public Invito(Utente utenteAdmin, Utente utenteInvitato, Progetto progetto) {
		this.utenteAdmin = utenteAdmin;
		this.utenteInvitato = utenteInvitato;
		this.progetto = progetto;
	}
	
	
	public Invito(String invitante, int id) {
		this.matricolaInvitante = invitante;
		this.idProgetto = id;
	}

	public Utente getUtenteAdmin() {
		return utenteAdmin;
	}

	public void setUtenteAmin(Utente utenteAdmin) {
		this.utenteAdmin = utenteAdmin;
	}

	public Utente getUtenteInvitato() {
		return utenteInvitato;
	}

	public void setUtenteInvitato(Utente utenteInvitato) {
		this.utenteInvitato = utenteInvitato;
	}

	public Progetto getProgetto() {
		return progetto;
	}

	public void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}
	
	
	public int getIdProgetto() {
		return idProgetto;
	}

	public String getMatricolaInvitante() {
		return matricolaInvitante;
	}

	
	
}
