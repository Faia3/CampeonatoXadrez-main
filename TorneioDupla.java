import java.util.ArrayList;
import java.util.List;

public class TorneioDupla extends Torneio {
    private List<Equipa> equipas; // Lista de equipas participantes
    private List<PartidaDupla> partidas; // Lista de partidas realizadas
    private String tipoTorneio; // Tipo do torneio (sempre por Pontos para este tipo de torneio)

    // Construtor que cria um torneio de duplas com o nome especificado
    public TorneioDupla(String nome) {
        super(nome);  // Chama o construtor da classe base (Torneio)
        this.tipoTorneio = "Pontos";  // Torneios de dupla sempre são por pontos
        this.equipas = new ArrayList<>();  // Inicializa a lista de equipas
        this.partidas = new ArrayList<>();  // Inicializa a lista de partidas
    }

    /**
     * Adiciona uma nova equipa ao torneio.
     * Cada equipa é composta por dois jogadores.
     * 
     * @param jogador1 O primeiro jogador da equipa.
     * @param jogador2 O segundo jogador da equipa.
     */
    public void adicionarEquipa(Jogador jogador1, Jogador jogador2) {
        if (jogador1 == null || jogador2 == null) {
            throw new IllegalArgumentException("Os dois jogadores da equipa devem ser válidos.");
        }
        equipas.add(new Equipa(jogador1, jogador2));  // Cria a equipa e adiciona à lista de equipas
    }

    /**
     * Adiciona uma partida ao torneio.
     * 
     * @param partida A partida de dupla a ser adicionada.
     */
    public void adicionarPartida(PartidaDupla partida) {
        partidas.add(partida);  // Adiciona a partida à lista de partidas
    }

    /**
     * Inicia o torneio de duplas, simulando as partidas e atualizando o ranking.
     */
    public void iniciarTorneio() {
        System.out.println("Iniciando o torneio de duplas: " + getNome());
        simularPorPontos();  // Chama o método para simular as partidas por pontos
    }

    /**
     * Simula as partidas do torneio, utilizando o formato por pontos.
     * Após cada partida, o vencedor é impresso.
     */
    private void simularPorPontos() {
        for (PartidaDupla partida : partidas) {
            partida.iniciarPartida();  // Inicia cada partida
            System.out.println("Vencedores: " +
                    partida.getVencedores()[0].getNome() + " e " +
                    partida.getVencedores()[1].getNome());  // Exibe os vencedores de cada partida
        }

        System.out.println("Ranking atualizado após o torneio.");
    }

    /**
     * Retorna uma representação em string do torneio.
     * 
     * @return Uma string com o nome, tipo, número de equipas e partidas do torneio.
     */
    @Override
    public String toString() {
        return "Torneio Dupla: " + getNome() + ", Tipo: " + tipoTorneio +
                ", Total de equipas: " + equipas.size() +
                ", Total de partidas: " + partidas.size();
    }
}
