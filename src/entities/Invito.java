package entities;

public class Invito {
	private Utente utenteAdmin;
	private Utente utenteInvitato;
	private Progetto progetto;
	
	public Invito(Utente utenteAdmin, Utente utenteInvitato, Progetto progetto) {
		super();
		this.utenteAdmin = utenteAdmin;
		this.utenteInvitato = utenteInvitato;
		this.progetto = progetto;
	}

	public Utente getUtenteADmin() {
		return utenteAdmin;
	}

	public void setUtenteADmin(Utente utenteAdmin) {
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
	
	
}
