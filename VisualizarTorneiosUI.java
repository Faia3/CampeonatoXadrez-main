import javax.swing.*;
import java.io.*;
import java.util.Objects;

public class VisualizarTorneiosUI {
    private JFrame frame;
    private JList<String> torneiosList;
    private DefaultListModel<String> torneiosModel;

    public VisualizarTorneiosUI() {
        frame = new JFrame("Visualizar Torneios");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Lista de torneios
        JLabel torneiosLabel = new JLabel("Torneios no Campeonato:");
        torneiosLabel.setBounds(50, 30, 200, 25);
        frame.add(torneiosLabel);

        torneiosModel = new DefaultListModel<>();
        torneiosList = new JList<>(torneiosModel);
        JScrollPane torneiosScrollPane = new JScrollPane(torneiosList);
        torneiosScrollPane.setBounds(50, 70, 400, 200);
        frame.add(torneiosScrollPane);

        // Botão para visualizar detalhes
        JButton btnVisualizarDetalhes = new JButton("Visualizar Detalhes");
        btnVisualizarDetalhes.setBounds(50, 300, 200, 30);
        frame.add(btnVisualizarDetalhes);

        btnVisualizarDetalhes.addActionListener(e -> visualizarDetalhes());

        carregarTorneios();
        frame.setVisible(true);
    }

    private void carregarTorneios() {
        File pastaTorneios = new File("Torneios");

        if (!pastaTorneios.exists() || !pastaTorneios.isDirectory()) {
            JOptionPane.showMessageDialog(frame, "Nenhum torneio encontrado! A pasta 'Torneios' não existe.",
                "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        torneiosModel.clear();

        File[] arquivos = Objects.requireNonNull(pastaTorneios.listFiles());
        for (File arquivo : arquivos) {
            if (arquivo.isFile() && arquivo.getName().endsWith(".txt")) {
                String nomeTorneio = arquivo.getName().replace(".txt", "");
                torneiosModel.addElement(nomeTorneio);
            }
        }

        if (torneiosModel.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhum torneio encontrado na pasta 'Torneios'.",
                "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void visualizarDetalhes() {
        String torneioSelecionado = torneiosList.getSelectedValue();

        if (torneioSelecionado == null) {
            JOptionPane.showMessageDialog(frame, "Selecione um torneio para visualizar os detalhes!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File arquivoTorneio = new File("Torneios/" + torneioSelecionado + ".txt");

        if (!arquivoTorneio.exists()) {
            JOptionPane.showMessageDialog(frame, "Arquivo do torneio não encontrado!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoTorneio))) {
            StringBuilder conteudo = new StringBuilder();
            String linha;
            boolean estadoAtualizado = false;
            int participantesRestantes = 0;
            String ultimoParticipante = null;
            StringBuilder participantes = new StringBuilder();

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("- ")) {
                    participantesRestantes++;
                    ultimoParticipante = linha.replace("- ", "").trim();
                    participantes.append(linha).append("\n");
                } else if (linha.startsWith("Estado: a decorrer")) {
                    if (participantesRestantes == 1) {
                        conteudo.append("Estado: finalizado\n");
                        estadoAtualizado = true;
                    } else {
                        conteudo.append(linha).append("\n");
                    }
                } else if (!linha.startsWith("Participantes:") || participantesRestantes > 1) {
                    conteudo.append(linha).append("\n");
                }
            }

            if (participantesRestantes == 1) {
                conteudo.append("Vencedor: ").append(ultimoParticipante).append("\n");
            } else if (participantesRestantes > 1) {
                conteudo.append("Participantes:\n").append(participantes);
            }

            if (estadoAtualizado || participantesRestantes == 1) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTorneio))) {
                    writer.write(conteudo.toString());
                }
            }

            JOptionPane.showMessageDialog(frame, conteudo.toString(),
                "Detalhes do Torneio", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao ler o arquivo do torneio: " + ex.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}