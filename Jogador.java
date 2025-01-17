
public class Jogador {
    private String nome;
    private int ranking;
    private String genero;
    private int partidasJogadas;
    private int partidasVencidas;

    // Construtor
    public Jogador(String nome, String genero) {
        this.nome = nome;
        this.genero = genero;
        this.ranking = 0;
        this.partidasJogadas = 0;
        this.partidasVencidas = 0;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRanking() {
        return ranking;
    }

    public void atualizarRanking(int pontos) {
        this.ranking += pontos;
    }

    public String getGenero() {
        return genero;
    }

    public int getPartidasJogadas() {
        return partidasJogadas;
    }

    public void incrementarPartidasJogadas() {
        this.partidasJogadas++;
    }

    public int getPartidasVencidas() {
        return partidasVencidas;
    }

    public void incrementarPartidasVencidas() {
        this.partidasVencidas++;
    }

    @Override
    public String toString() {
        return String.format("Jogador: %s, Género: %s, Ranking: %d, Vitórias: %d, Partidas: %d",
                nome, genero, ranking, partidasVencidas, partidasJogadas);
    }
}
