// ConsultarArbitroUI.java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConsultarArbitroUI {
    private JFrame frame;

    public ConsultarArbitroUI() {
        frame = new JFrame("Consultar Árbitros");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Text Area para exibir os árbitros
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 20, 350, 200);
        frame.add(scrollPane);

        // Botão para carregar árbitros
        JButton btnCarregar = new JButton("Carregar Árbitros");
        btnCarregar.setBounds(20, 230, 170, 30);
        frame.add(btnCarregar);

        // Ação para carregar árbitros
        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarArbitros(textArea);
            }
        });

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(200, 230, 170, 30);
        frame.add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void carregarArbitros(JTextArea textArea) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("arbitros.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                builder.append(linha).append("\n");
            }
            textArea.setText(builder.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar árbitros!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
