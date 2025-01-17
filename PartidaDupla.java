
import java.util.Random;

public class PartidaDupla implements Partida {
    private Jogador[] equipa1; // Equipa composta por 2 jogadores
    private Jogador[] equipa2; // Equipa composta por 2 jogadores
    private final int tempoLimite = 40; // Tempo limite em minutos
    private Jogador[] vencedores;

    // Construtor
    public PartidaDupla(Jogador jogador1, Jogador jogador2, Jogador jogador3, Jogador jogador4) {
        this.equipa1 = new Jogador[]{jogador1, jogador2};
        this.equipa2 = new Jogador[]{jogador3, jogador4};
    }

    @Override
    public void iniciarPartida() {
        System.out.println("Partida iniciada entre as equipas:");
        System.out.println("Equipa 1: " + equipa1[0].getNome() + " e " + equipa1[1].getNome());
        System.out.println("Equipa 2: " + equipa2[0].getNome() + " e " + equipa2[1].getNome());
        determinarVencedor();
    }

    @Override
    public String determinarVencedor() {
        Random random = new Random();
        boolean equipa1Venceu = random.nextBoolean();

        vencedores = equipa1Venceu ? equipa1 : equipa2;

        // Atualiza os rankings e estatísticas dos jogadores
        for (Jogador jogador : vencedores) {
            jogador.incrementarPartidasVencidas();
            jogador.atualizarRanking(3);
        }
        for (Jogador jogador : equipa1) {
            jogador.incrementarPartidasJogadas();
        }
        for (Jogador jogador : equipa2) {
            jogador.incrementarPartidasJogadas();
        }

        return equipa1Venceu
                ? equipa1[0].getNome() + " e " + equipa1[1].getNome()
                : equipa2[0].getNome() + " e " + equipa2[1].getNome();
    }

    @Override
    public int getTempoLimite() {
        return tempoLimite;
    }

    public Jogador[] getVencedores() {
        return vencedores;
    }

    @Override
    public String toString() {
        return "Partida Dupla: " +
                equipa1[0].getNome() + " e " + equipa1[1].getNome() + " vs " +
                equipa2[0].getNome() + " e " + equipa2[1].getNome() +
                ", Tempo limite: " + tempoLimite + " minutos";
    }
}
