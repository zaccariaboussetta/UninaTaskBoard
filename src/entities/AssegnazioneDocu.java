package entities;

public class AssegnazioneDocu {
	private AttivitaDocumentazione attivitaDocu;
	private Membro membro;
	private Progetto progetto;
	
	
	
	public AssegnazioneDocu(AttivitaDocumentazione attivitaDocu, Membro membro, Progetto progetto) {
		super();
		this.attivitaDocu = attivitaDocu;
		this.membro = membro;
		this.progetto = progetto;
	}

	
	public AttivitaDocumentazione getAttivitaDocu() {
		return attivitaDocu;
	}

	public void setAttivitaDocu(AttivitaDocumentazione attivitaDocu) {
		this.attivitaDocu = attivitaDocu;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Progetto getProgetto() {
		return progetto;
	}

	public void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}
	
	
}
