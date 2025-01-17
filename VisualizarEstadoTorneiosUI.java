// VisualizarEstadoTorneiosUI.java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VisualizarEstadoTorneiosUI {
    private JFrame frame;

    public VisualizarEstadoTorneiosUI(List<Torneio> torneios) {
        frame = new JFrame("Estado dos Torneios");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Text Area para exibir os torneios
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 20, 450, 300);
        frame.add(scrollPane);

        // Botão para carregar estado dos torneios
        JButton btnCarregar = new JButton("Carregar Estado");
        btnCarregar.setBounds(20, 330, 200, 30);
        frame.add(btnCarregar);

        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarEstadoTorneios(textArea, torneios);
            }
        });

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(270, 330, 200, 30);
        frame.add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void carregarEstadoTorneios(JTextArea textArea, List<Torneio> torneios) {
        if (torneios.isEmpty()) {
            textArea.setText("Nenhum torneio registrado.");
            return;
        }

        StringBuilder builder = new StringBuilder();
        for (Torneio torneio : torneios) {
            builder.append(torneio.toString()).append("\n");
        }
        textArea.setText(builder.toString());
    }
}
