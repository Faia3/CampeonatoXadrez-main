import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConsultarJogadorUI {
    private JFrame frame;

    public ConsultarJogadorUI() {
        frame = new JFrame("Consultar Jogadores");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Aplicar tema escuro
        DarkThemeUI.applyDarkTheme(frame);

        // Text Area para exibir os jogadores
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(DarkThemeUI.BACKGROUND_COLOR);
        textArea.setForeground(DarkThemeUI.TEXT_COLOR);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 20, 350, 200);
        frame.add(scrollPane);

        // Botão para carregar jogadores
        JButton btnCarregar = DarkThemeUI.createDarkButton("Carregar Jogadores");
        btnCarregar.setBounds(20, 230, 170, 30);
        frame.add(btnCarregar);

        // Ação para carregar jogadores
        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarJogadores(textArea);
            }
        });

        // Botão para voltar
        JButton btnVoltar = DarkThemeUI.createDarkButton("Voltar");
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

    private void carregarJogadores(JTextArea textArea) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("jogadores.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                builder.append(linha).append("\n");
            }
            textArea.setText(builder.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar jogadores!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
