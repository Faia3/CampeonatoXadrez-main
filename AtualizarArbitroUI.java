// AtualizarArbitroUI.java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AtualizarArbitroUI {
    private JFrame frame;
    private JTextField nomeAntigoField;
    private JTextField nomeNovoField;
    private JTextField experienciaField;
    private JTextField licencaField;

    public AtualizarArbitroUI() {
        frame = new JFrame("Atualizar Árbitro");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Campo para o nome antigo
        JLabel nomeAntigoLabel = new JLabel("Nome Atual:");
        nomeAntigoLabel.setBounds(50, 30, 100, 25);
        frame.add(nomeAntigoLabel);

        nomeAntigoField = new JTextField();
        nomeAntigoField.setBounds(150, 30, 200, 25);
        frame.add(nomeAntigoField);

        // Campo para o novo nome
        JLabel nomeNovoLabel = new JLabel("Novo Nome:");
        nomeNovoLabel.setBounds(50, 70, 100, 25);
        frame.add(nomeNovoLabel);

        nomeNovoField = new JTextField();
        nomeNovoField.setBounds(150, 70, 200, 25);
        frame.add(nomeNovoField);

        // Campo para nova experiência
        JLabel experienciaLabel = new JLabel("Nova Experiência:");
        experienciaLabel.setBounds(50, 110, 120, 25);
        frame.add(experienciaLabel);

        experienciaField = new JTextField();
        experienciaField.setBounds(180, 110, 170, 25);
        frame.add(experienciaField);

        // Campo para nova licença
        JLabel licencaLabel = new JLabel("Nova Licença:");
        licencaLabel.setBounds(50, 150, 100, 25);
        frame.add(licencaLabel);

        licencaField = new JTextField();
        licencaField.setBounds(150, 150, 200, 25);
        frame.add(licencaField);

        // Botão para atualizar
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(50, 200, 140, 30);
        frame.add(btnAtualizar);

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarArbitro();
            }
        });

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(200, 200, 140, 30);
        frame.add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void atualizarArbitro() {
        String nomeAntigo = nomeAntigoField.getText();
        String nomeNovo = nomeNovoField.getText();
        String experienciaNova = experienciaField.getText();
        String licencaNova = licencaField.getText();

        if (nomeAntigo.isEmpty() || nomeNovo.isEmpty() || experienciaNova.isEmpty() || licencaNova.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos os campos devem estar preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            List<String> linhas = new ArrayList<>();
            boolean encontrado = false;

            try (BufferedReader reader = new BufferedReader(new FileReader("arbitros.txt"))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] partes = linha.split(",");
                    if (partes[0].equals(nomeAntigo)) {
                        linhas.add(nomeNovo + "," + experienciaNova + "," + licencaNova);
                        encontrado = true;
                    } else {
                        linhas.add(linha);
                    }
                }
            }

            if (encontrado) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("arbitros.txt"))) {
                    for (String linha : linhas) {
                        writer.write(linha);
                        writer.newLine();
                    }
                }
                JOptionPane.showMessageDialog(frame, "Árbitro atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(frame, "Árbitro não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao atualizar árbitro!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
