package entities;

public class Utente {
	private String nome;
	private String cognome;
	private String matricola;
	private String emailIstituzionale;
	private String password;

	
	public Utente(String nome, String cognome, String matricola, String emailIstituzionale, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
		this.emailIstituzionale = emailIstituzionale;
		this.password = password;
	}
	
	
	public Utente(String nome, String cognome, String matricola) {
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getEmailIstituzionale() {
		return emailIstituzionale;
	}

	public void setEmailIstituzionale(String emailIstituzionale) {
		this.emailIstituzionale = emailIstituzionale;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	};
	
	
}
