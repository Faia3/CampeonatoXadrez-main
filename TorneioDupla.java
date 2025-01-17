import java.util.ArrayList;
import java.util.List;

public class TorneioDupla extends Torneio {
    private List<Equipe> equipes; // Lista de equipes participantes
    private List<PartidaDupla> partidas; // Lista de partidas realizadas
    private String tipoTorneio; // Por Pontos

    // Construtor
    public TorneioDupla(String nome) {
        super(nome);
        this.tipoTorneio = "Pontos"; // Torneios de dupla sempre são por pontos
        this.equipes = new ArrayList<>();
        this.partidas = new ArrayList<>();
    }

    // Adiciona uma equipe ao torneio
    public void adicionarEquipe(Jogador jogador1, Jogador jogador2) {
        if (jogador1 == null || jogador2 == null) {
            throw new IllegalArgumentException("Os dois jogadores da equipe devem ser válidos.");
        }
        equipes.add(new Equipe(jogador1, jogador2));
    }

    // Adiciona uma partida ao torneio
    public void adicionarPartida(PartidaDupla partida) {
        partidas.add(partida);
    }

    // Simula o torneio
    public void iniciarTorneio() {
        System.out.println("Iniciando o torneio de duplas: " + getNome());
        simularPorPontos();
    }

    // Simula o torneio no formato pontos
    private void simularPorPontos() {
        for (PartidaDupla partida : partidas) {
            partida.iniciarPartida();
            System.out.println("Vencedores: " +
                    partida.getVencedores()[0].getNome() + " e " +
                    partida.getVencedores()[1].getNome());
        }

        System.out.println("Ranking atualizado após o torneio.");
    }

    @Override
    public String toString() {
        return "Torneio Dupla: " + getNome() + ", Tipo: " + tipoTorneio +
                ", Total de equipes: " + equipes.size() +
                ", Total de partidas: " + partidas.size();
    }
}
