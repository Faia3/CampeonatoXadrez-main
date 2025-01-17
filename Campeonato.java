import java.util.ArrayList;
import java.util.List;

public class Campeonato {
    private String nome;
    private List<Torneio> torneios;
    private boolean ativo;

    // Construtor
    public Campeonato(String nome) {
        this.nome = nome;
        this.torneios = new ArrayList<>();
        this.ativo = true;
    }

    // Adiciona um torneio ao campeonato
    public void adicionarTorneio(Torneio torneio) {
        torneios.add(torneio);
        System.out.println("Torneio \"" + torneio.getNome() + "\" adicionado ao campeonato.");
    }

    // Finaliza o campeonato
    public void finalizarCampeonato() {
        this.ativo = false;
        System.out.println("Campeonato \"" + nome + "\" finalizado.");
    }

    // Exibe informações do campeonato
    public void exibirInformacoes() {
        System.out.println("Campeonato: " + nome);
        System.out.println("Ativo: " + (ativo ? "Sim" : "Não"));
        System.out.println("Torneios:");
        for (Torneio torneio : torneios) {
            System.out.println(" - " + torneio.toString());
        }
    }

    // Exibe o ranking consolidado
    public void exibirRanking() {
        System.out.println("Ranking dos jogadores:");
        // Simulação de leitura de rankings de um ficheiro
        System.out.println("(Ranking consolidado seria carregado de um ficheiro rankings.txt)");
    }

    // Atribui prêmio para torneios finalizados
    public void atribuirPremios() {
        for (Torneio torneio : torneios) {
            if (torneio.isTerminado()) {
                System.out.println("Prêmio atribuído para o torneio \"" + torneio.getNome() + "\".");
            } else {
                System.out.println("Torneio \"" + torneio.getNome() + "\" ainda não terminado.");
            }
        }
    }
}
