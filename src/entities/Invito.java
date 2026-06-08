package entities;

public class Invito {
	private Utente utenteADmin;
	private Utente utenteInvitato;
	private Progetto progetto;
	
	public Invito(Utente utenteADmin, Utente utenteInvitato, Progetto progetto) {
		super();
		this.utenteADmin = utenteADmin;
		this.utenteInvitato = utenteInvitato;
		this.progetto = progetto;
	}

	public Utente getUtenteADmin() {
		return utenteADmin;
	}

	public void setUtenteADmin(Utente utenteADmin) {
		this.utenteADmin = utenteADmin;
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
