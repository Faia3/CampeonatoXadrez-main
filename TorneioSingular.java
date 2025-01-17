import java.util.ArrayList;
import java.util.List;

public class TorneioSingular extends Torneio {
    private String tipo; // "Eliminatórias" ou "Por Pontos"
    private List<Jogador> participantes;
    private List<PartidaSingular> partidas;
    private Jogador vencedor;

    // Construtor do torneio singular
    public TorneioSingular(String nome, String tipo) {
        super(nome);
        this.tipo = tipo;
        this.participantes = new ArrayList<>();
        this.partidas = new ArrayList<>();
    }

    // Adiciona um participante ao torneio
    public void adicionarParticipante(Jogador jogador) {
        participantes.add(jogador);
    }

    // Inicia o torneio com base no tipo
    @Override
    public void iniciarTorneio() {
        if (tipo.equalsIgnoreCase("Eliminatórias")) {
            simularEliminatorias();
        } else if (tipo.equalsIgnoreCase("Por Pontos")) {
            simularPorPontos();
        }
    }

    // Simula um torneio eliminatório
    private void simularEliminatorias() {
        List<Jogador> jogadoresRestantes = new ArrayList<>(participantes);

        while (jogadoresRestantes.size() > 1) {
            List<Jogador> vencedoresRodada = new ArrayList<>();

            for (int i = 0; i < jogadoresRestantes.size() - 1; i += 2) {
                Jogador jogador1 = jogadoresRestantes.get(i);
                Jogador jogador2 = jogadoresRestantes.get(i + 1);

                PartidaSingular partida = new PartidaSingular(jogador1, jogador2);
                partidas.add(partida);
                String vencedorNome = partida.determinarVencedor();
                Jogador vencedorPartida = jogador1.getNome().equals(vencedorNome) ? jogador1 : jogador2;
                vencedoresRodada.add(vencedorPartida);

                System.out.println("Partida: " + jogador1.getNome() + " vs " + jogador2.getNome());
                System.out.println("Vencedor: " + vencedorPartida.getNome());
            }
            jogadoresRestantes = vencedoresRodada;
        }

        if (!jogadoresRestantes.isEmpty()) {
            vencedor = jogadoresRestantes.get(0);
            System.out.println("Campeão do Torneio: " + vencedor.getNome());
        }
        finalizarTorneio(); // Marca o torneio como finalizado
    }

    // Simula um torneio por pontos
    private void simularPorPontos() {
        for (int i = 0; i < participantes.size(); i++) {
            for (int j = i + 1; j < participantes.size(); j++) {
                Jogador jogador1 = participantes.get(i);
                Jogador jogador2 = participantes.get(j);

                PartidaSingular partida = new PartidaSingular(jogador1, jogador2);
                partidas.add(partida);
                String vencedorNome = partida.determinarVencedor();
                Jogador vencedorPartida = jogador1.getNome().equals(vencedorNome) ? jogador1 : jogador2;
                vencedorPartida.atualizarRanking(3); // Vitória = 3 pontos no ranking

                System.out.println("Partida: " + jogador1.getNome() + " vs " + jogador2.getNome());
                System.out.println("Vencedor: " + vencedorPartida.getNome());
            }
        }

        // Determinar o jogador com maior ranking
        int maiorRanking = Integer.MIN_VALUE;
        for (Jogador jogador : participantes) {
            if (jogador.getRanking() > maiorRanking) {
                vencedor = jogador;
                maiorRanking = jogador.getRanking();
            }
        }

        if (vencedor != null) {
            System.out.println("Campeão do Torneio: " + vencedor.getNome());
        }
        finalizarTorneio(); // Marca o torneio como finalizado
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo: " + tipo + ", Vencedor: " + (vencedor != null ? vencedor.getNome() : "Ainda não definido");
    }
}
