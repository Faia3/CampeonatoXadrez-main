import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class SimpleChessBoard extends JPanel {
    private final int BOARD_SIZE = 8;
    private final int TILE_SIZE = 60;
    private Color[][] boardColors;
    
    public SimpleChessBoard() {
        setPreferredSize(new Dimension(BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE));
        boardColors = new Color[BOARD_SIZE][BOARD_SIZE];
        readBoardFromFile();
    }
    
    private void readBoardFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("tabuleiro.txt"));
            String line;
            int row = 0;
            
            while ((line = reader.readLine()) != null && row < BOARD_SIZE) {
                String[] colors = line.split(" ");
                for (int col = 0; col < BOARD_SIZE && col < colors.length; col++) {
                    if (colors[col].equals("B")) {
                        boardColors[row][col] = Color.BLACK;
                    } else if (colors[col].equals("W")) {
                        boardColors[row][col] = Color.WHITE;
                    }
                }
                row++;
            }
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading chess board file: " + e.getMessage());
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (boardColors[row][col] != null) {
                    g.setColor(boardColors[row][col]);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Gestão de Campeonatos de Xadrez");
                frame.setSize(400, 350);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel panel = new JPanel();
                frame.add(panel);
                colocarComponentesNoPanel(panel);

                frame.setVisible(true);
            }
        });
    }

    private static void colocarComponentesNoPanel(JPanel panel) {
        panel.setLayout(null);

        // Botão para Gerir Jogadores
        JButton btnGerirJogadores = new JButton("Gerir Jogadores");
        btnGerirJogadores.setBounds(100, 30, 200, 30);
        panel.add(btnGerirJogadores);

        btnGerirJogadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerirJogadoresUI();
            }
        });

        // Botão para Gerir Árbitros
        JButton btnGerirArbitros = new JButton("Gerir Árbitros");
        btnGerirArbitros.setBounds(100, 70, 200, 30);
        panel.add(btnGerirArbitros);

        btnGerirArbitros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerirArbitrosUI();
            }
        });

        // Botão para Gerir Campeonato
        JButton btnGerirCampeonato = new JButton("Gerir Campeonato");
        btnGerirCampeonato.setBounds(100, 110, 200, 30);
        panel.add(btnGerirCampeonato);

        btnGerirCampeonato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerirCampeonatoUI();
            }
        });

        // Botão para Visualizar Rankings
        JButton btnRanking = new JButton("Visualizar Rankings");
        btnRanking.setBounds(100, 150, 200, 30);
        panel.add(btnRanking);

        btnRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RankingUI rankingUI = new RankingUI();
                rankingUI.setVisible(true);
            }
        });

        // Novo Botão para Ver Tabuleiro de Xadrez
        JButton btnChess = new JButton("Ver Tabuleiro de Xadrez");
        btnChess.setBounds(100, 190, 200, 30);
        panel.add(btnChess);

        btnChess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame chessFrame = new JFrame("Tabuleiro de Xadrez");
                chessFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                SimpleChessBoard chessBoard = new SimpleChessBoard();
                chessFrame.add(chessBoard);
                
                chessFrame.pack();
                chessFrame.setLocationRelativeTo(null);
                chessFrame.setVisible(true);
            }
        });

        // Botão para Sair (moved down to accommodate chess board button)
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(100, 230, 200, 30);
        panel.add(btnSair);

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}