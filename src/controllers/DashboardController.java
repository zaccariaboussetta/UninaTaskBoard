package controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import entities.AssegnazioneDocu;
import entities.AssegnazioneSviluppo;
import entities.Attivita;
import entities.AttivitaDocumentazione;
import entities.AttivitaSviluppo;
import entities.Membro;
import entities.Progetto;
import entities.StatisticaMembroDTO;
import exceptions.LoadDataException;

public class DashboardController {
	MembroController membroController;
	TaskController taskController;
	ProgettoController progettoController;

	private ArrayList<Attivita> listaTasks;
	private ArrayList<Membro> listaMembri;
	private ArrayList<AssegnazioneDocu> listaAssegnazioneTaskDocu;
	private ArrayList<AssegnazioneSviluppo> listaAssegnazioneTaskSviluppo;

	public DashboardController(MembroController membroController, TaskController taskController, ProgettoController progettoController) {
		this.membroController = membroController;
		this.taskController = taskController;
		this.progettoController = progettoController;
	}

	public void loadData() throws Exception{

		Progetto progetto = SessionController.getInstance().getCorrenteProgetto();

		if(progetto == null) throw new LoadDataException();

		this.listaTasks = taskController.getTasksByProgetto(progetto);
		this.listaMembri = membroController.getMembriByProgetto(progetto);
		this.listaAssegnazioneTaskDocu = new ArrayList<>();
		this.listaAssegnazioneTaskSviluppo = new ArrayList<>();

		for(Membro membro : listaMembri) {

			for(Attivita task : taskController.getTasksAssignedTo(membro)) {

				if(task instanceof AttivitaDocumentazione t) {

					AssegnazioneDocu ad = new AssegnazioneDocu(t, membro, progetto);
					this.listaAssegnazioneTaskDocu.add(ad);

				}
				if(task instanceof AttivitaSviluppo t) {

					AssegnazioneSviluppo as = new AssegnazioneSviluppo(t, membro, progetto);
					this.listaAssegnazioneTaskSviluppo.add(as);

				}
			}



		}

	}

	public ArrayList<Attivita> getListaTasks(){
		return this.listaTasks;
	}

	public ArrayList<Attivita> getListaTasksFiltered(String stato, String tipo, String scadenza, String membro) {

		ArrayList<Attivita> listaTasksFiltrata = listaTasks;

		listaTasksFiltrata = filterByStato(listaTasksFiltrata, stato);
		listaTasksFiltrata = filterByTipo(listaTasksFiltrata, tipo);
		listaTasksFiltrata = filterByScadenza(listaTasksFiltrata, scadenza);
		listaTasksFiltrata = filterByMembro(listaTasksFiltrata, membro);

		return listaTasksFiltrata;
	}

	private ArrayList<Attivita> filterByStato(ArrayList<Attivita> lista, String stato) {
		ArrayList<Attivita> filtrata = new ArrayList<>();
		for (Attivita t : lista) {
			if (t.getStatoAvanzamento().equals(stato)) {
				filtrata.add(t);
			}
		}
		return filtrata;
	}

	private ArrayList<Attivita> filterByTipo(ArrayList<Attivita> lista, String tipoFiltro) {
		if (tipoFiltro.equals("Tutte")) {
			return lista;
		}

		ArrayList<Attivita> filtrata = new ArrayList<>();
		for (Attivita t : lista) {
			if (tipoFiltro.equals("Sviluppo") && t instanceof AttivitaSviluppo) {
				filtrata.add(t);
			} else if (tipoFiltro.equals("Documentazione") && t instanceof AttivitaDocumentazione) {
				filtrata.add(t);
			}
		}
		return filtrata;
	}

	private ArrayList<Attivita> filterByScadenza(ArrayList<Attivita> lista, String scadenzaFiltro) {
		if (scadenzaFiltro.equals("Tutte le date")) {
			return lista;
		}

		ArrayList<Attivita> filtrata = new ArrayList<>();
		LocalDate oggi = LocalDate.now();

		for (Attivita t : lista) {
			if (scadenzaFiltro.equals("Scadute") && t.getScadenza().isBefore(oggi)) {
				filtrata.add(t);
			} else if (scadenzaFiltro.equals("In scadenza")) {
				long giorni = ChronoUnit.DAYS.between(oggi, t.getScadenza());
				if (giorni >= 0 && giorni <= 7) {
					filtrata.add(t);
				}
			}
		}
		return filtrata;
	}

	private ArrayList<Attivita> filterByMembro(ArrayList<Attivita> lista, String membroFiltro) {
		if (membroFiltro.equals("Tutti i membri")) {
			return lista;
		}

		ArrayList<Attivita> filtrata = new ArrayList<>();

		for (Attivita t : lista) {

			String assegnatariDellaTask = getNomiAssegnatariTask(t);

			if (membroFiltro.equals("Nessuno")) {

				if (assegnatariDellaTask.equals("Nessuno")) {
					filtrata.add(t);
				}
			} else {

				if (assegnatariDellaTask.contains(membroFiltro)) {
					filtrata.add(t);
				}
			}
		}

		return filtrata;
	}

	public ArrayList<Membro> getListaMembri(){
		return this.listaMembri;
	}

	public TaskController getTaskController() {
		return this.taskController;
	}

	public String getNomiAssegnatariTask(Attivita t) {
		StringBuilder assegnatari = new StringBuilder();
		boolean primoAggiunto = false;

		if (t instanceof AttivitaDocumentazione) {
			for (AssegnazioneDocu ad : this.listaAssegnazioneTaskDocu) {
				if (ad.getAttivitaDocu().getIdAttivita() == t.getIdAttivita()) {
					if (primoAggiunto) assegnatari.append(", ");
					assegnatari.append(ad.getMembro().getUtente().getNome()).append(" ").append(ad.getMembro().getUtente().getCognome());
					primoAggiunto = true;
				}
			}
		} else if (t instanceof AttivitaSviluppo) {
			for (AssegnazioneSviluppo as : this.listaAssegnazioneTaskSviluppo) {
				if (as.getAttivitaSviluppo().getIdAttivita() == t.getIdAttivita()) {
					if (primoAggiunto) assegnatari.append(", ");
					assegnatari.append(as.getMembro().getUtente().getNome()).append(" ").append(as.getMembro().getUtente().getCognome());
					primoAggiunto = true;
				}
			}
		}


		if (assegnatari.length() == 0) {
			return "Nessuno";
		}

		return assegnatari.toString();
	}


	public int getTotaleAttivita() {
			return this.listaTasks.size();
		}


	public int getConteggioAttivitaPerStato(String stato) {
			int count = 0;
			for (Attivita t : this.listaTasks) {
				if (t.getStatoAvanzamento().equals(stato)) {
					count++;
				}
			}
			return count;
		}


	public int getTotaleAttivitaSviluppo() {
			int count = 0;
			for (Attivita t : this.listaTasks) {
				if (t instanceof AttivitaSviluppo) {
					count++;
				}
			}
			return count;
		}



	public ArrayList<StatisticaMembroDTO> getTaskCompletatePerMembro() {
			ArrayList<StatisticaMembroDTO> statistiche = new ArrayList<>();

			for (Attivita t : this.listaTasks) {
				if (t.getStatoAvanzamento().equals("Done")) {

					String assegnatari = getNomiAssegnatariTask(t);

					if (!assegnatari.equals("Nessuno")) {
						String[] nomi = assegnatari.split(", ");

						for (String nome : nomi) {
							boolean utenteTrovato = false;


							for (StatisticaMembroDTO sm : statistiche) {
								if (sm.getNomeMembro().equals(nome)) {
									sm.incrementa();
									utenteTrovato = true;
									break;
								}
							}


							if (!utenteTrovato) {
								statistiche.add(new StatisticaMembroDTO(nome, 1));
							}
						}
					}
				}
			}

			return statistiche;
		}
}
