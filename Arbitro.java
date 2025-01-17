
public class Arbitro {
    private String nome;
    private int experiencia; // Anos de experiência
    private String licenca;  // Número da licença de árbitro

    // Construtor
    public Arbitro(String nome, int experiencia, String licenca) {
        this.nome = nome;
        this.experiencia = experiencia;
        this.licenca = licenca;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public String getLicenca() {
        return licenca;
    }

    public void setLicenca(String licenca) {
        this.licenca = licenca;
    }

    @Override
    public String toString() {
        return String.format("Árbitro: %s, Experiência: %d anos, Licença: %s",
                nome, experiencia, licenca);
    }
}
