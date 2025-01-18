public class Equipa {
    private Jogador jogador1;
    private Jogador jogador2;

    // Construtor
    public Equipa(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
    }

    // Getters
    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    // Nome combinado da equipa
    public String getNomeEquipa() {
        return jogador1.getNome() + " e " + jogador2.getNome();
    }

    @Override
    public String toString() {
        return "Equipa: " + getNomeEquipa();
    }
}
