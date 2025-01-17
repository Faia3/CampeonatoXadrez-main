
public abstract class Torneio {
    private String nome;
    private boolean terminado;

    // Construtor
    public Torneio(String nome) {
        this.nome = nome;
        this.terminado = false;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void finalizarTorneio() {
        this.terminado = true;
        System.out.println("Torneio \"" + nome + "\" finalizado.");
    }

    // Método abstrato para iniciar o torneio
    public abstract void iniciarTorneio();

    @Override
    public String toString() {
        return "Torneio: " + nome + ", Terminado: " + (terminado ? "Sim" : "Não");
    }
}
