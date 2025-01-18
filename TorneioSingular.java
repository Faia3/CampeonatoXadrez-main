import java.util.ArrayList;
import java.util.List;

public class TorneioSingular extends Torneio {
    private String tipo; // Tipo do torneio: "Eliminatórias" ou "Por Pontos"
    private List<Jogador> participantes; // Lista de jogadores participantes do torneio
    private List<PartidaSingular> partidas; // Lista de partidas realizadas no torneio
    private Jogador vencedor; // Jogador vencedor do torneio

    // Construtor que cria um torneio singular com o nome e tipo especificados
    public TorneioSingular(String nome, String tipo) {
        super(nome);  // Chama o construtor da classe base (Torneio)
        this.tipo = tipo;  // Define o tipo do torneio (Eliminatórias ou Por Pontos)
        this.participantes = new ArrayList<>();  // Inicializa a lista de participantes
        this.partidas = new ArrayList<>();  // Inicializa a lista de partidas
    }

    /**
     * Adiciona um jogador ao torneio.
     * 
     * @param jogador O jogador a ser adicionado ao torneio.
     */
    public void adicionarParticipante(Jogador jogador) {
        participantes.add(jogador);  // Adiciona o jogador à lista de participantes
    }

    /**
     * Inicia o torneio com base no tipo: Eliminatórias ou Por Pontos.
     */
    @Override
    public void iniciarTorneio() {
        if (tipo.equalsIgnoreCase("Eliminatórias")) {
            simularEliminatorias();  // Chama o método para simular o torneio no formato eliminatório
        } else if (tipo.equalsIgnoreCase("Por Pontos")) {
            simularPorPontos();  // Chama o método para simular o torneio no formato por pontos
        }
    }

    /**
     * Simula um torneio no formato eliminatório.
     * As partidas são jogadas em eliminatórias, até restar apenas um vencedor.
     */
    private void simularEliminatorias() {
        List<Jogador> jogadoresRestantes = new ArrayList<>(participantes);  // Lista de jogadores restantes no torneio

        while (jogadoresRestantes.size() > 1) {
            List<Jogador> vencedoresRodada = new ArrayList<>();  // Lista de vencedores de cada rodada

            for (int i = 0; i < jogadoresRestantes.size() - 1; i += 2) {
                Jogador jogador1 = jogadoresRestantes.get(i);
                Jogador jogador2 = jogadoresRestantes.get(i + 1);

                PartidaSingular partida = new PartidaSingular(jogador1, jogador2);  // Cria uma partida
                partidas.add(partida);  // Adiciona a partida à lista de partidas
                String vencedorNome = partida.determinarVencedor();  // Determina o vencedor da partida
                Jogador vencedorPartida = jogador1.getNome().equals(vencedorNome) ? jogador1 : jogador2;  // Identifica o vencedor
                vencedoresRodada.add(vencedorPartida);  // Adiciona o vencedor à lista de vencedores

                // Exibe as informações da partida e do vencedor
                System.out.println("Partida: " + jogador1.getNome() + " vs " + jogador2.getNome());
                System.out.println("Vencedor: " + vencedorPartida.getNome());
            }
            jogadoresRestantes = vencedoresRodada;  // Atualiza a lista de jogadores restantes para a próxima rodada
        }

        if (!jogadoresRestantes.isEmpty()) {
            vencedor = jogadoresRestantes.get(0);  // O vencedor final é o único jogador restante
            System.out.println("Campeão do Torneio: " + vencedor.getNome());
        }
        finalizarTorneio();  // Marca o torneio como finalizado
    }

    /**
     * Simula um torneio no formato por pontos.
     * Cada jogador joga contra todos os outros e acumula pontos pelas vitórias.
     */
    private void simularPorPontos() {
        for (int i = 0; i < participantes.size(); i++) {
            for (int j = i + 1; j < participantes.size(); j++) {
                Jogador jogador1 = participantes.get(i);
                Jogador jogador2 = participantes.get(j);

                PartidaSingular partida = new PartidaSingular(jogador1, jogador2);  // Cria uma partida
                partidas.add(partida);  // Adiciona a partida à lista de partidas
                String vencedorNome = partida.determinarVencedor();  // Determina o vencedor da partida
                Jogador vencedorPartida = jogador1.getNome().equals(vencedorNome) ? jogador1 : jogador2;  // Identifica o vencedor
                vencedorPartida.atualizarRanking(3);  // Adiciona 3 pontos ao vencedor no ranking

                // Exibe as informações da partida e do vencedor
                System.out.println("Partida: " + jogador1.getNome() + " vs " + jogador2.getNome());
                System.out.println("Vencedor: " + vencedorPartida.getNome());
            }
        }

        // Determina o jogador com o maior número de pontos (ranking)
        int maiorRanking = Integer.MIN_VALUE;
        for (Jogador jogador : participantes) {
            if (jogador.getRanking() > maiorRanking) {
                vencedor = jogador;  // O jogador com maior ranking é o vencedor
                maiorRanking = jogador.getRanking();
            }
        }

        if (vencedor != null) {
            System.out.println("Campeão do Torneio: " + vencedor.getNome());
        }
        finalizarTorneio();  // Marca o torneio como finalizado
    }

    /**
     * Retorna uma representação em string do torneio.
     * 
     * @return Uma string com o nome, tipo e o vencedor do torneio.
     */
    @Override
    public String toString() {
        return super.toString() + ", Tipo: " + tipo + ", Vencedor: " + (vencedor != null ? vencedor.getNome() : "Ainda não definido");
    }
}
