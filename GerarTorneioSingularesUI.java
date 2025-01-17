import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerarTorneioSingularesUI {
    private JFrame frame;
    private JTextField nomeTorneioField;
    private JList<String> jogadoresList;
    private DefaultListModel<String> jogadoresModel;
    private JList<String> participantesList;
    private DefaultListModel<String> participantesModel;
    private List<Jogador> jogadoresDisponiveis;

    public GerarTorneioSingularesUI() {
        frame = new JFrame("Gerar Torneio Singular");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome do Torneio:");
        nomeLabel.setBounds(50, 30, 150, 25);
        frame.add(nomeLabel);

        nomeTorneioField = new JTextField();
        nomeTorneioField.setBounds(200, 30, 300, 25);
        frame.add(nomeTorneioField);

        JLabel jogadoresLabel = new JLabel("Jogadores Disponíveis:");
        jogadoresLabel.setBounds(50, 70, 200, 25);
        frame.add(jogadoresLabel);

        jogadoresModel = new DefaultListModel<>();
        jogadoresList = new JList<>(jogadoresModel);
        JScrollPane jogadoresScrollPane = new JScrollPane(jogadoresList);
        jogadoresScrollPane.setBounds(50, 100, 200, 300);
        frame.add(jogadoresScrollPane);

        JLabel participantesLabel = new JLabel("Participantes Adicionados:");
        participantesLabel.setBounds(300, 70, 200, 25);
        frame.add(participantesLabel);

        participantesModel = new DefaultListModel<>();
        participantesList = new JList<>(participantesModel);
        JScrollPane participantesScrollPane = new JScrollPane(participantesList);
        participantesScrollPane.setBounds(300, 100, 200, 300);
        frame.add(participantesScrollPane);

        JButton btnAdicionarParticipante = new JButton("Adicionar Participante");
        btnAdicionarParticipante.setBounds(50, 420, 200, 30);
        frame.add(btnAdicionarParticipante);

        btnAdicionarParticipante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarParticipante();
            }
        });

        JButton btnRemoverParticipante = new JButton("Remover Participante");
        btnRemoverParticipante.setBounds(300, 420, 200, 30);
        frame.add(btnRemoverParticipante);

        btnRemoverParticipante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerParticipante();
            }
        });

        JButton btnGerar = new JButton("Gerar Torneio");
        btnGerar.setBounds(50, 470, 200, 30);
        frame.add(btnGerar);

        btnGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarTorneio();
            }
        });

        JButton        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(300, 470, 200, 30);
        frame.add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        carregarJogadores();
        frame.setVisible(true);
    }

    private void carregarJogadores() {
        jogadoresDisponiveis = new ArrayList<>();
        jogadoresModel.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("jogadores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Jogador jogador = new Jogador(parts[0].trim(), parts[1].trim());
                    jogadoresDisponiveis.add(jogador);
                    jogadoresModel.addElement(jogador.getNome());
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar jogadores: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarParticipante() {
        String jogadorSelecionado = jogadoresList.getSelectedValue();
        if (jogadorSelecionado == null) {
            JOptionPane.showMessageDialog(frame, "Selecione um jogador para adicionar ao torneio!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        participantesModel.addElement(jogadorSelecionado);
        jogadoresModel.removeElement(jogadorSelecionado);
    }

    private void removerParticipante() {
        String participanteSelecionado = participantesList.getSelectedValue();
        if (participanteSelecionado == null) {
            JOptionPane.showMessageDialog(frame, "Selecione um participante para remover do torneio!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        jogadoresModel.addElement(participanteSelecionado);
        participantesModel.removeElement(participanteSelecionado);
    }

    private void gerarTorneio() {
        String nomeTorneio = nomeTorneioField.getText().trim();
        if (nomeTorneio.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "O nome do torneio não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (participantesModel.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Adicione pelo menos um participante para criar o torneio!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        File pastaTorneios = new File("Torneios");
        if (!pastaTorneios.exists()) {
            pastaTorneios.mkdir();
        }
        String arquivoTorneio = "Torneios/" + nomeTorneio + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTorneio))) {
            writer.write("Estado: a decorrer\n");
            writer.write("Participantes:\n");
            for (int i = 0; i < participantesModel.size(); i++) {
                writer.write("- " + participantesModel.getElementAt(i) + "\n");
            }
            JOptionPane.showMessageDialog(frame, "Torneio Singular criado com sucesso e salvo em " + arquivoTorneio);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao salvar o arquivo do torneio: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        frame.dispose();
    }
}

