import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerarTorneioDuplasUI {
    private JFrame frame;
    private JTextField nomeTorneioField;
    private JList<String> jogadoresList;
    private DefaultListModel<String> jogadoresModel;
    private JList<String> equipasList;
    private DefaultListModel<String> equipasModel;
    private List<Jogador> jogadoresDisponiveis;

    public GerarTorneioDuplasUI() {
        frame = new JFrame("Gerar Torneio de Duplas");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Aplicar tema escuro
        DarkThemeUI.applyDarkTheme(frame);

        JLabel nomeLabel = new JLabel("Nome do Torneio:");
        nomeLabel.setBounds(50, 30, 150, 25);
        nomeLabel.setForeground(DarkThemeUI.TEXT_COLOR);  // Texto em cor clara
        frame.add(nomeLabel);

        nomeTorneioField = new JTextField();
        nomeTorneioField.setBounds(200, 30, 300, 25);
        nomeTorneioField.setBackground(DarkThemeUI.BACKGROUND_COLOR);
        nomeTorneioField.setForeground(DarkThemeUI.TEXT_COLOR);
        frame.add(nomeTorneioField);

        JLabel jogadoresLabel = new JLabel("Jogadores Disponíveis:");
        jogadoresLabel.setBounds(50, 70, 200, 25);
        jogadoresLabel.setForeground(DarkThemeUI.TEXT_COLOR);
        frame.add(jogadoresLabel);

        jogadoresModel = new DefaultListModel<>();
        jogadoresList = new JList<>(jogadoresModel);
        JScrollPane jogadoresScrollPane = new JScrollPane(jogadoresList);
        jogadoresScrollPane.setBounds(50, 100, 200, 300);
        frame.add(jogadoresScrollPane);

        JLabel equipasLabel = new JLabel("Equipas Criadas:");
        equipasLabel.setBounds(300, 70, 200, 25);
        equipasLabel.setForeground(DarkThemeUI.TEXT_COLOR);
        frame.add(equipasLabel);

        equipasModel = new DefaultListModel<>();
        equipasList = new JList<>(equipasModel);
        JScrollPane equipasScrollPane = new JScrollPane(equipasList);
        equipasScrollPane.setBounds(300, 100, 200, 300);
        frame.add(equipasScrollPane);

        JButton btnAdicionarEquipa = DarkThemeUI.createDarkButton("Adicionar Equipa");
        btnAdicionarEquipa.setBounds(50, 420, 200, 30);
        frame.add(btnAdicionarEquipa);

        btnAdicionarEquipa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarEquipa();
            }
        });

        JButton btnRemoverEquipa = DarkThemeUI.createDarkButton("Remover Equipa");
        btnRemoverEquipa.setBounds(300, 420, 200, 30);
        frame.add(btnRemoverEquipa);

        btnRemoverEquipa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerEquipa();
            }
        });

        JButton btnGerar = DarkThemeUI.createDarkButton("Gerar Torneio");
        btnGerar.setBounds(50, 470, 200, 30);
        frame.add(btnGerar);

        btnGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarTorneio();
            }
        });

        JButton btnVoltar = DarkThemeUI.createDarkButton("Voltar");
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

    private void adicionarEquipa() {
        List<String> jogadoresSelecionados = jogadoresList.getSelectedValuesList();

        if (jogadoresSelecionados.size() != 2) {
            JOptionPane.showMessageDialog(frame, "Selecione exatamente 2 jogadores para criar uma equipa!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String equipa = jogadoresSelecionados.get(0) + " e " + jogadoresSelecionados.get(1);
        equipasModel.addElement(equipa);

        jogadoresModel.removeElement(jogadoresSelecionados.get(0));
        jogadoresModel.removeElement(jogadoresSelecionados.get(1));
    }

    private void removerEquipa() {
        String equipaSelecionada = equipasList.getSelectedValue();

        if (equipaSelecionada == null) {
            JOptionPane.showMessageDialog(frame, "Selecione uma equipa para remover!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] jogadoresEquipa = equipaSelecionada.split(" e ");
        for (String jogador : jogadoresEquipa) {
            jogadoresModel.addElement(jogador.trim());
        }

        equipasModel.removeElement(equipaSelecionada);
    }

    private void gerarTorneio() {
        String nomeTorneio = nomeTorneioField.getText().trim();

        if (nomeTorneio.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "O nome do torneio não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (equipasModel.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Adicione pelo menos uma equipa para criar o torneio!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File pastaTorneios = new File("Torneios");
        if (!pastaTorneios.exists()) {
            pastaTorneios.mkdir();
        }

        String arquivoTorneio = "Torneios/" + nomeTorneio + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTorneio))) {
            writer.write("Estado: a decorrer\n");
            writer.write("Equipas:\n");
            for (int i = 0; i < equipasModel.size(); i++) {
                writer.write("- " + equipasModel.getElementAt(i) + "\n");
            }
            JOptionPane.showMessageDialog(frame, "Torneio de Duplas criado com sucesso e salvo em " + arquivoTorneio);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao salvar o arquivo do torneio: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        frame.dispose();
    }
}
