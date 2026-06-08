package entities;

public class AssegnazioneSviluppo {
	private AttivitaSviluppo attivitaSviluppo;
	private Membro membro;
	private Progetto progetto;
	
	
	//Inserimento e reperimento dati dal DB;
	public AssegnazioneSviluppo(AttivitaSviluppo attivitaSviluppo, Membro membro, Progetto progetto) {
		super();
		this.attivitaSviluppo = attivitaSviluppo;
		this.membro = membro;
		this.progetto = progetto;
	}


	public AttivitaSviluppo getAttivitaSviluppo() {
		return attivitaSviluppo;
	}


	public void setAttivitaSviluppo(AttivitaSviluppo attivitaSviluppo) {
		this.attivitaSviluppo = attivitaSviluppo;
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
