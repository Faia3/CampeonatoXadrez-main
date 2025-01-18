import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class GerirCampeonatoUI {
    private JFrame frame;

    public GerirCampeonatoUI() {
        frame = new JFrame("Gerir Campeonato");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Aplicar tema escuro
        DarkThemeUI.applyDarkTheme(frame);

        // Botão para Gerar/Controlar Torneios
        JButton btnGerarTorneios = DarkThemeUI.createDarkButton("Gerar/Controlar Torneios");
        btnGerarTorneios.setBounds(50, 30, 400, 30);
        frame.add(btnGerarTorneios);

        btnGerarTorneios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarControlarTorneios();
            }
        });

        // Botão para Visualizar Estado dos Torneios
        JButton btnVisualizarEstado = DarkThemeUI.createDarkButton("Visualizar Estado dos Torneios");
        btnVisualizarEstado.setBounds(50, 80, 400, 30);
        frame.add(btnVisualizarEstado);

        btnVisualizarEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VisualizarTorneiosUI();
            }
        });

        // Botão para Começar Partida
        JButton btnComecarPartida = DarkThemeUI.createDarkButton("Começar Partida");
        btnComecarPartida.setBounds(50, 130, 400, 30);
        frame.add(btnComecarPartida);

        btnComecarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSelecaoDePartida();
            }
        });

        // Botão para Voltar
        JButton btnVoltar = DarkThemeUI.createDarkButton("Voltar");
        btnVoltar.setBounds(50, 180, 400, 30);
        frame.add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void gerarControlarTorneios() {
        JFrame frame = new JFrame("Gerar/Controlar Torneios");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JLabel label = new JLabel("Selecione o tipo de torneio:");
        label.setBounds(50, 30, 300, 25);
        frame.add(label);

        JButton btnSingulares = DarkThemeUI.createDarkButton("Torneio Singulares");
        btnSingulares.setBounds(50, 70, 300, 30);
        frame.add(btnSingulares);

        JButton btnDuplas = DarkThemeUI.createDarkButton("Torneio Duplas");
        btnDuplas.setBounds(50, 120, 300, 30);
        frame.add(btnDuplas);

        btnSingulares.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerarTorneioSingularesUI();
                frame.dispose();
            }
        });

        btnDuplas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerarTorneioDuplasUI();
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void iniciarSelecaoDePartida() {
        JFrame frame = new JFrame("Selecione o Torneio e os Participantes");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JLabel torneiosLabel = new JLabel("Selecione o Torneio:");
        torneiosLabel.setBounds(30, 20, 200, 25);
        frame.add(torneiosLabel);

        DefaultListModel<String> torneiosModel = new DefaultListModel<>();
        JList<String> torneiosList = new JList<>(torneiosModel);
        JScrollPane torneiosScroll = new JScrollPane(torneiosList);
        torneiosScroll.setBounds(30, 50, 200, 200);
        frame.add(torneiosScroll);

        JLabel participantesLabel = new JLabel("Participantes:");
        participantesLabel.setBounds(300, 20, 200, 25);
        frame.add(participantesLabel);

        DefaultListModel<String> participantesModel = new DefaultListModel<>();
        JList<String> participantesList = new JList<>(participantesModel);
        JScrollPane participantesScroll = new JScrollPane(participantesList);
        participantesScroll.setBounds(300, 50, 200, 200);
        frame.add(participantesScroll);

        torneiosList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String torneioSelecionado = torneiosList.getSelectedValue();
                    if (torneioSelecionado != null) {
                        carregarParticipantes(torneioSelecionado, participantesModel);
                    }
                }
            }
        });

        JButton btnComecarPartida = DarkThemeUI.createDarkButton("Começar Partida");
        btnComecarPartida.setBounds(300, 270, 200, 30);
        frame.add(btnComecarPartida);

        btnComecarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selecionados = participantesList.getSelectedValuesList();
                if (selecionados.size() != 2) {
                    JOptionPane.showMessageDialog(frame, "Selecione exatamente dois participantes!", 
                        "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                new PartidaUI(selecionados.get(0), selecionados.get(1), torneiosList.getSelectedValue());
                verificarVencedor(torneiosList.getSelectedValue());
                frame.dispose();
            }
        });

        carregarTorneios(torneiosModel);
        frame.setVisible(true);
    }

    private void carregarTorneios(DefaultListModel<String> model) {
        File pastaTorneios = new File("Torneios");
        if (!pastaTorneios.exists()) {
            JOptionPane.showMessageDialog(frame, "Nenhum torneio encontrado!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        File[] arquivos = pastaTorneios.listFiles();
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile() && arquivo.getName().endsWith(".txt")) {
                    model.addElement(arquivo.getName().replace(".txt", ""));
                }
            }
        }
    }

    private void carregarParticipantes(String torneio, DefaultListModel<String> model) {
        File arquivoTorneio = new File("Torneios/" + torneio + ".txt");
        model.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoTorneio))) {
            String linha;
            boolean participantesEncontrados = false;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Participantes:")) {
                    participantesEncontrados = true;
                } else if (participantesEncontrados && linha.startsWith("- ")) {
                    model.addElement(linha.replace("- ", "").trim());
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar participantes: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificarVencedor(String torneio) {
        File arquivoTorneio = new File("Torneios/" + torneio + ".txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoTorneio))) {
            StringBuilder conteudo = new StringBuilder();
            String linha;
            int numParticipantes = 0;
            String ultimoParticipante = null;

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("- ")) {
                    numParticipantes++;
                    ultimoParticipante = linha.replace("- ", "").trim();
                }
                conteudo.append(linha).append("\n");
            }

            if (numParticipantes == 1) {
                conteudo = new StringBuilder(conteudo.toString().replace("Estado: a decorrer", 
                    "Estado: terminado, o/a vencedor/a foi " + ultimoParticipante));

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTorneio))) {
                    writer.write(conteudo.toString());
                }

                JOptionPane.showMessageDialog(frame, "Torneio '" + torneio + "' finalizado! Vencedor: " + ultimoParticipante, 
                    "Torneio Finalizado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao verificar vencedor: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
