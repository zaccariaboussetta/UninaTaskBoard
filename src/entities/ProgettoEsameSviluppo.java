package entities;

import java.time.LocalDate;

public class ProgettoEsameSviluppo extends Progetto {
	
	private String codiceEsame;
	private String nomeEsame;
	private int cfu;
	private String docente;
	private LocalDate dataAppello;
	
	private String repositoryURL;
	private String techStack;
	private String versione;
	
	//Reperimento dati DB;
	public ProgettoEsameSviluppo(int idProgetto, String nome, String descrizione, LocalDate dataCreazione,
			LocalDate dataConsegna, boolean isProgettoGruppo, Utente userCreator, String codiceEsame, String nomeEsame,
			int cfu, String docente, LocalDate dataAppello, String repositoryURL, String techStack, String versione) {
		super(idProgetto, nome, descrizione, dataCreazione, dataConsegna, isProgettoGruppo, userCreator);
		this.codiceEsame = codiceEsame;
		this.nomeEsame = nomeEsame;
		this.cfu = cfu;
		this.docente = docente;
		this.dataAppello = dataAppello;
		this.repositoryURL = repositoryURL;
		this.techStack = techStack;
		this.versione = versione;
	}
	
	public ProgettoEsameSviluppo(int idProgetto, String nome, String descrizione, LocalDate dataCreazione,
			LocalDate dataConsegna, boolean isProgettoGruppo, String userCreatorMatricola, String codiceEsame, String nomeEsame,
			int cfu, String docente, LocalDate dataAppello, String repositoryURL, String techStack, String versione) {
		super(idProgetto, nome, descrizione, dataCreazione, dataConsegna, isProgettoGruppo, userCreatorMatricola);
		this.codiceEsame = codiceEsame;
		this.nomeEsame = nomeEsame;
		this.cfu = cfu;
		this.docente = docente;
		this.dataAppello = dataAppello;
		this.repositoryURL = repositoryURL;
		this.techStack = techStack;
		this.versione = versione;
	}

	//Inserimento dati DB;
	public ProgettoEsameSviluppo(String nome, String descrizione, LocalDate dataConsegna, boolean isProgettoGruppo,
			Utente userCreator, String codiceEsame, String nomeEsame, int cfu, String docente, LocalDate dataAppello,
			String repositoryURL, String techStack, String versione) {
		super(nome, descrizione, dataConsegna, isProgettoGruppo, userCreator);
		this.codiceEsame = codiceEsame;
		this.nomeEsame = nomeEsame;
		this.cfu = cfu;
		this.docente = docente;
		this.dataAppello = dataAppello;
		this.repositoryURL = repositoryURL;
		this.techStack = techStack;
		this.versione = versione;
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

	public String getRepositoryURL() {
		return repositoryURL;
	}

	public void setRepositoryURL(String repositoryURL) {
		this.repositoryURL = repositoryURL;
	}

	public String getTechStack() {
		return techStack;
	}

	public void setTechStack(String techStack) {
		this.techStack = techStack;
	}

	public String getVersione() {
		return versione;
	}

	public void setVersione(String versione) {
		this.versione = versione;
	}
	
}
