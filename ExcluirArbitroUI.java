// ExcluirArbitroUI.java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcluirArbitroUI {
    private JFrame frame;
    private JTextField nomeField;

    public ExcluirArbitroUI() {
        frame = new JFrame("Excluir Árbitro");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Campo para o nome do árbitro
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(50, 30, 100, 25);
        frame.add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(150, 30, 200, 25);
        frame.add(nomeField);

        // Botão para excluir
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(50, 80, 140, 30);
        frame.add(btnExcluir);

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirArbitro();
            }
        });

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(200, 80, 140, 30);
        frame.add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void excluirArbitro() {
        String nome = nomeField.getText();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "O nome não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            List<String> linhas = new ArrayList<>();
            boolean encontrado = false;

            try (BufferedReader reader = new BufferedReader(new FileReader("arbitros.txt"))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    if (!linha.split(",")[0].equals(nome)) {
                        linhas.add(linha);
                    } else {
                        encontrado = true;
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
                JOptionPane.showMessageDialog(frame, "Árbitro excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(frame, "Árbitro não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao excluir árbitro!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
