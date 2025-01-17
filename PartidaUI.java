import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class PartidaUI {
    private JFrame frame;
    private JLabel timerLabel;
    private int remainingTime = 30 * 60; // 30 minutos em segundos
    private Timer timer;

    public PartidaUI(String participante1, String participante2, String torneio) {
        frame = new JFrame("Partida");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Timer
        timerLabel = new JLabel(formatTime(remainingTime), SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setBounds(200, 20, 200, 40);
        frame.add(timerLabel);

        // Participante 1
        JLabel participante1Label = new JLabel(participante1, SwingConstants.CENTER);
        participante1Label.setFont(new Font("Arial", Font.PLAIN, 18));
        participante1Label.setBounds(50, 100, 200, 30);
        frame.add(participante1Label);

        JButton btnXequeMate1 = new JButton("Xeque Mate");
        btnXequeMate1.setBounds(50, 150, 200, 40);
        frame.add(btnXequeMate1);

        btnXequeMate1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarPartida(participante2, torneio);
            }
        });

        // Participante 2
        JLabel participante2Label = new JLabel(participante2, SwingConstants.CENTER);
        participante2Label.setFont(new Font("Arial", Font.PLAIN, 18));
        participante2Label.setBounds(350, 100, 200, 30);
        frame.add(participante2Label);

        JButton btnXequeMate2 = new JButton("Xeque Mate");
        btnXequeMate2.setBounds(350, 150, 200, 40);
        frame.add(btnXequeMate2);

        btnXequeMate2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarPartida(participante1, torneio);
            }
        });

        iniciarTimer();
        frame.setVisible(true);
    }

    private void iniciarTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                remainingTime--;
                timerLabel.setText(formatTime(remainingTime));

                if (remainingTime <= 0) {
                    timer.cancel();
                    JOptionPane.showMessageDialog(frame, "Tempo esgotado! A partida terminou sem vencedores.", 
                        "Fim de Partida", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }
            }
        }, 0, 1000); // Atualiza o timer a cada segundo
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

    private void finalizarPartida(String perdedor, String torneio) {
        timer.cancel();

        // Remove o perdedor do arquivo do torneio
        File arquivoTorneio = new File("Torneios/" + torneio + ".txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoTorneio))) {
            StringBuilder conteudo = new StringBuilder();
            String linha;

            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().equals("- " + perdedor)) {
                    conteudo.append(linha).append("\n");
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTorneio))) {
                writer.write(conteudo.toString());
            }

            JOptionPane.showMessageDialog(frame, "Vencedor determinado! " + perdedor + " foi eliminado.", 
                "Partida Finalizada", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao atualizar o arquivo do torneio: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }

        frame.dispose();
    }
}
