package entities;

import java.time.LocalDate;

public class SviluppoApplicativi extends Progetto{
	private String repositoryURL;
	private String techStack;
	private String versione;
	
	
	
	public SviluppoApplicativi(int idProgetto, String nome, String descrizione, LocalDate dataCreazione,
			LocalDate dataConsegna, boolean isProgettoGruppo, Utente userCreator, String repositoryURL,
			String techStack, String versione) {
		super(idProgetto, nome, descrizione, dataCreazione, dataConsegna, isProgettoGruppo, userCreator);
		this.repositoryURL = repositoryURL;
		this.techStack = techStack;
		this.versione = versione;
	}
	
	public SviluppoApplicativi(int idProgetto, String nome, String descrizione, LocalDate dataCreazione,
			LocalDate dataConsegna, boolean isProgettoGruppo, String userCreatorMatricola, String repositoryURL,
			String techStack, String versione) {
		super(idProgetto, nome, descrizione, dataCreazione, dataConsegna, isProgettoGruppo, userCreatorMatricola);
		this.repositoryURL = repositoryURL;
		this.techStack = techStack;
		this.versione = versione;
	}

	
	
	public SviluppoApplicativi(String nome, String descrizione, LocalDate dataConsegna, boolean isProgettoGruppo,
			Utente userCreator, String repositoryURL, String techStack, String versione) {
		super(nome, descrizione, dataConsegna, isProgettoGruppo, userCreator);
		this.repositoryURL = repositoryURL;
		this.techStack = techStack;
		this.versione = versione;
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

