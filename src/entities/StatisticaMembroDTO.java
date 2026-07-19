package entities;

public class StatisticaMembroDTO {
	private String nomeMembro;
	private int taskCompletate;

	public StatisticaMembroDTO(String nomeMembro, int taskCompletate) {
		this.nomeMembro = nomeMembro;
		this.taskCompletate = taskCompletate;
	}

	public String getNomeMembro() {
		return nomeMembro;
	}

	public int getTaskCompletate() {
		return taskCompletate;
	}

	public void incrementa() {
		this.taskCompletate++;
	}
}