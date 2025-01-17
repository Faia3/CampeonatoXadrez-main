// CampeonatoSingleton.java
import java.util.ArrayList;
import java.util.List;

public class CampeonatoSingleton {
    private static CampeonatoSingleton instance;
    private List<Torneio> torneios;

    private CampeonatoSingleton() {
        this.torneios = new ArrayList<>();
    }

    public static CampeonatoSingleton getCampeonato() {
        if (instance == null) {
            instance = new CampeonatoSingleton();
        }
        return instance;
    }

    public List<Torneio> getTorneios() {
        return torneios;
    }

    public void adicionarTorneio(Torneio torneio) {
        torneios.add(torneio);
    }

    // Método para buscar torneio pelo nome
    public Torneio getTorneioPorNome(String nome) {
        for (Torneio torneio : torneios) {
            if (torneio.getNome().equalsIgnoreCase(nome)) {
                return torneio;
            }
        }
        return null; // Retorna null se nenhum torneio for encontrado
    }
}
