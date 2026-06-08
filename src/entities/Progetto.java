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
}
