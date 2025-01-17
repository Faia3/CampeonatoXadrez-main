public class Equipe {
    private Jogador jogador1;
    private Jogador jogador2;

    // Construtor
    public Equipe(Jogador jogador1, Jogador jogador2) {
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

    // Nome combinado da equipe
    public String getNomeEquipe() {
        return jogador1.getNome() + " e " + jogador2.getNome();
    }

    @Override
    public String toString() {
        return "Equipe: " + getNomeEquipe();
    }
}
