// AtualizarJogadorUI.java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AtualizarJogadorUI {
    private JFrame frame;
    private JTextField nomeAntigoField;
    private JTextField nomeNovoField;
    private JComboBox<String> generoBox;

    public AtualizarJogadorUI() {
        frame = new JFrame("Atualizar Jogador");
        frame.setSize(400, 300);
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

        // Campo para o novo gênero
        JLabel generoLabel = new JLabel("Novo Género:");
        generoLabel.setBounds(50, 110, 100, 25);
        frame.add(generoLabel);

        generoBox = new JComboBox<>(new String[]{"Masculino", "Feminino"});
        generoBox.setBounds(150, 110, 200, 25);
        frame.add(generoBox);

        // Botão para atualizar
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(50, 160, 140, 30);
        frame.add(btnAtualizar);

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarJogador();
            }
        });

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(200, 160, 140, 30);
        frame.add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void atualizarJogador() {
        String nomeAntigo = nomeAntigoField.getText();
        String nomeNovo = nomeNovoField.getText();
        String generoNovo = (String) generoBox.getSelectedItem();

        if (nomeAntigo.isEmpty() || nomeNovo.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Os campos não podem estar vazios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            List<String> linhas = new ArrayList<>();
            boolean encontrado = false;

            try (BufferedReader reader = new BufferedReader(new FileReader("jogadores.txt"))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] partes = linha.split(",");
                    if (partes[0].equals(nomeAntigo)) {
                        linhas.add(nomeNovo + "," + generoNovo);
                        encontrado = true;
                    } else {
                        linhas.add(linha);
                    }
                }
            }

            if (encontrado) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("jogadores.txt"))) {
                    for (String linha : linhas) {
                        writer.write(linha);
                        writer.newLine();
                    }
                }
                JOptionPane.showMessageDialog(frame, "Jogador atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(frame, "Jogador não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao atualizar jogador!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
