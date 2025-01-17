// RegistrarArbitroUI.java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrarArbitroUI {
    private JFrame frame;
    private JTextField nomeField;
    private JTextField experienciaField;
    private JTextField licencaField;

    public RegistrarArbitroUI() {
        frame = new JFrame("Registrar Árbitro");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Label e Campo de Nome
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(50, 30, 80, 25);
        frame.add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(140, 30, 200, 25);
        frame.add(nomeField);

        // Label e Campo de Experiência
        JLabel experienciaLabel = new JLabel("Experiência (anos):");
        experienciaLabel.setBounds(50, 70, 120, 25);
        frame.add(experienciaLabel);

        experienciaField = new JTextField();
        experienciaField.setBounds(190, 70, 150, 25);
        frame.add(experienciaField);

        // Label e Campo de Licença
        JLabel licencaLabel = new JLabel("Licença:");
        licencaLabel.setBounds(50, 110, 80, 25);
        frame.add(licencaLabel);

        licencaField = new JTextField();
        licencaField.setBounds(140, 110, 200, 25);
        frame.add(licencaField);

        // Botão para Registrar
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(50, 160, 140, 30);
        frame.add(btnRegistrar);

        // Ação para Registrar Árbitro
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarArbitro();
            }
        });

        // Botão para Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(200, 160, 140, 30);
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

    private void registrarArbitro() {
        String nome = nomeField.getText();
        String experiencia = experienciaField.getText();
        String licenca = licencaField.getText();

        if (nome.isEmpty() || experiencia.isEmpty() || licenca.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("arbitros.txt", true))) {
            writer.write(nome + "," + experiencia + "," + licenca + "\n");
            JOptionPane.showMessageDialog(frame, "Árbitro registrado com sucesso!");
            nomeField.setText("");
            experienciaField.setText("");
            licencaField.setText("");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao salvar o árbitro!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
