import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcluirJogadorUI {
    private JFrame frame;
    private JTextField nomeField;

    public ExcluirJogadorUI() {
        frame = new JFrame("Excluir Jogador");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Aplicar tema escuro
        DarkThemeUI.applyDarkTheme(frame);

        // Campo para o nome do jogador
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(50, 30, 100, 25);
        nomeLabel.setForeground(DarkThemeUI.TEXT_COLOR);  // Texto em cor clara
        frame.add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(150, 30, 200, 25);
        nomeField.setBackground(DarkThemeUI.BACKGROUND_COLOR);
        nomeField.setForeground(DarkThemeUI.TEXT_COLOR);
        frame.add(nomeField);

        // Botão para excluir
        JButton btnExcluir = DarkThemeUI.createDarkButton("Excluir");
        btnExcluir.setBounds(50, 80, 140, 30);
        frame.add(btnExcluir);

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirJogador();
            }
        });

        // Botão para voltar
        JButton btnVoltar = DarkThemeUI.createDarkButton("Voltar");
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

    private void excluirJogador() {
        String nome = nomeField.getText();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "O nome não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            List<String> linhas = new ArrayList<>();
            boolean encontrado = false;

            try (BufferedReader reader = new BufferedReader(new FileReader("jogadores.txt"))) {
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
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("jogadores.txt"))) {
                    for (String linha : linhas) {
                        writer.write(linha);
                        writer.newLine();
                    }
                }
                JOptionPane.showMessageDialog(frame, "Jogador excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(frame, "Jogador não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao excluir jogador!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
