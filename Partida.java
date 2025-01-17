
public interface Partida {
    void iniciarPartida();
    String determinarVencedor(); // Retorna o nome do vencedor ou equipa vencedora
    int getTempoLimite(); // Retorna o tempo limite da partida em minutos
}
