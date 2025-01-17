import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class PlayerManager {
    private static PlayerManager instance;
    private List<Jogador> jogadores;
    private static final String ARQUIVO_JOGADORES = "jogadores.txt";
    private static final String ARQUIVO_RANKINGS = "rankings.txt";

    private PlayerManager() {
        jogadores = new ArrayList<>();
        carregarJogadoresESync();
    }

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    public List<Jogador> getJogadores() {
        carregarJogadoresESync();
        return jogadores;
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
        salvarJogadores();
        sincronizarRankings();
    }

    public void adicionarVitoria(Jogador jogador) {
        jogador.incrementarPartidasVencidas();
        jogador.atualizarRanking(3); // 3 points per victory
        salvarJogadores();
        sincronizarRankings();
    }

    private void carregarJogadoresESync() {
        jogadores.clear();
        carregarJogadoresDoArquivo();
        sincronizarRankings();
    }

    private void carregarJogadoresDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_JOGADORES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    String[] dados = linha.split(",");
                    if (dados.length >= 2) {
                        Jogador jogador = new Jogador(dados[0].trim(), dados[1].trim());
                        // If rankings exist for this player, load them
                        Jogador jogadorRanking = buscarJogadorNoRanking(jogador.getNome());
                        if (jogadorRanking != null) {
                            jogador.atualizarRanking(jogadorRanking.getRanking());
                            for (int i = 0; i < jogadorRanking.getPartidasVencidas(); i++) {
                                jogador.incrementarPartidasVencidas();
                            }
                            for (int i = 0; i < jogadorRanking.getPartidasJogadas(); i++) {
                                jogador.incrementarPartidasJogadas();
                            }
                        }
                        jogadores.add(jogador);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar jogadores: " + e.getMessage());
        }
    }

    private Jogador buscarJogadorNoRanking(String nome) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_RANKINGS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 2 && dados[0].trim().equals(nome)) {
                    Jogador jogador = new Jogador(dados[0].trim(), dados[1].trim());
                    if (dados.length >= 3) {
                        jogador.atualizarRanking(Integer.parseInt(dados[2].trim()));
                    }
                    if (dados.length >= 4) {
                        for (int i = 0; i < Integer.parseInt(dados[3].trim()); i++) {
                            jogador.incrementarPartidasVencidas();
                        }
                    }
                    if (dados.length >= 5) {
                        for (int i = 0; i < Integer.parseInt(dados[4].trim()); i++) {
                            jogador.incrementarPartidasJogadas();
                        }
                    }
                    return jogador;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao buscar jogador no ranking: " + e.getMessage());
        }
        return null;
    }

    private void salvarJogadores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_JOGADORES))) {
            for (Jogador jogador : jogadores) {
                writer.println(String.format("%s,%s",
                        jogador.getNome(),
                        jogador.getGenero()));
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar jogadores: " + e.getMessage());
        }
    }

    private void sincronizarRankings() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_RANKINGS))) {
            for (Jogador jogador : jogadores) {
                writer.println(String.format("%s,%s,%d,%d,%d",
                        jogador.getNome(),
                        jogador.getGenero(),
                        jogador.getRanking(),
                        jogador.getPartidasVencidas(),
                        jogador.getPartidasJogadas()));
            }
        } catch (IOException e) {
            System.out.println("Erro ao sincronizar rankings: " + e.getMessage());
        }
    }
}