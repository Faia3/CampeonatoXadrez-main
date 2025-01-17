// RegistrarJogadorUI.java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrarJogadorUI {
    private JFrame frame;
    private JTextField nomeField;
    private JComboBox<String> generoBox;

    public RegistrarJogadorUI() {
        frame = new JFrame("Registrar Jogador");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Label e Campo de Nome
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(50, 30, 80, 25);
        frame.add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(140, 30, 200, 25);
        frame.add(nomeField);

        // Label e Campo de Género
        JLabel generoLabel = new JLabel("Género:");
        generoLabel.setBounds(50, 70, 80, 25);
        frame.add(generoLabel);

        generoBox = new JComboBox<>(new String[]{"Masculino", "Feminino"});
        generoBox.setBounds(140, 70, 200, 25);
        frame.add(generoBox);

        // Botão para Registrar
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(50, 120, 140, 30);
        frame.add(btnRegistrar);

        // Ação para Registrar Jogador
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarJogador();
            }
        });

        // Botão para Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(200, 120, 140, 30);
        frame.add(btnVoltar);

        // Ação para Voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void registrarJogador() {
        String nome = nomeField.getText();
        String genero = (String) generoBox.getSelectedItem();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "O nome não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jogadores.txt", true))) {
            writer.write(nome + "," + genero + "\n");
            JOptionPane.showMessageDialog(frame, "Jogador registrado com sucesso!");
            nomeField.setText("");
            generoBox.setSelectedIndex(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao salvar o jogador!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
