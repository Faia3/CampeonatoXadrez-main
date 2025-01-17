import java.util.Random;

// Representa uma partida singular entre dois jogadores
public class PartidaSingular implements Partida {
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador vencedor;

    // Construtor da partida singular
    public PartidaSingular(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
    }

    @Override
    public void iniciarPartida() {
        System.out.println("Iniciando partida entre " + jogador1.getNome() + " e " + jogador2.getNome());
    }

    @Override
    public String determinarVencedor() {
        iniciarPartida();
        Random random = new Random();
        vencedor = random.nextBoolean() ? jogador1 : jogador2;
        vencedor.incrementarPartidasVencidas(); // Atualiza a vitória do jogador
        vencedor.atualizarRanking(5); // Atualiza o ranking do vencedor (5 pontos por vitória em partidas singulares)
        jogador1.incrementarPartidasJogadas();
        jogador2.incrementarPartidasJogadas();
        System.out.println("Cheque mate! Vencedor: " + vencedor.getNome());
        return vencedor.getNome();
    }

    @Override
    public int getTempoLimite() {
        return 30; // Tempo limite para partidas singulares
    }

    public Jogador getVencedor() {
        return vencedor;
    }

    @Override
    public String toString() {
        return "Partida Singular: " + jogador1.getNome() + " vs " + jogador2.getNome();
    }
}
