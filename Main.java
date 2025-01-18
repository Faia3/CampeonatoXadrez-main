import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Define o Look and Feel do sistema operativo
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Criação da janela principal
                JFrame frame = new JFrame("Gestão de Campeonatos de Xadrez");
                frame.setSize(500, 450);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);

                // Aplica o tema escuro à janela
                DarkThemeUI.applyDarkTheme(frame);

                JPanel panel = new JPanel();
                panel.setBackground(DarkThemeUI.BACKGROUND_COLOR); // Define a cor de fundo do painel
                panel.setLayout(null); // Layout sem posicionamento automático de componentes
                frame.add(panel);

                // Título da interface
                JLabel titleLabel = new JLabel("Gestão de Xadrez");
                titleLabel.setFont(new Font("Minecraft", Font.BOLD, 24));
                titleLabel.setForeground(DarkThemeUI.TEXT_COLOR);
                titleLabel.setBounds(125, 20, 250, 30);
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(titleLabel);

                // Botões para as funcionalidades
                addButton(panel, "Gerir Jogadores", 150, 80, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new GerirJogadoresUI();  // Abre a janela de gestão de jogadores
                    }
                });

                addButton(panel, "Gerir Árbitros", 150, 130, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new GerirArbitrosUI();  // Abre a janela de gestão de árbitros
                    }
                });

                addButton(panel, "Gerir Campeonato", 150, 180, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new GerirCampeonatoUI();  // Abre a janela de gestão do campeonato
                    }
                });

                addButton(panel, "Visualizar Rankings", 150, 230, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        RankingUI rankingUI = new RankingUI();
                        rankingUI.setVisible(true);  // Abre a janela de visualização de rankings
                    }
                });

                // Botão para visualizar o tabuleiro de xadrez
                addButton(panel, "Ver Tabuleiro", 150, 280, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame chessFrame = new JFrame("Tabuleiro de Xadrez");
                        chessFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        DarkThemeUI.applyDarkTheme(chessFrame);  // Aplica o tema escuro ao tabuleiro
                        
                        SimpleChessBoard chessBoard = new SimpleChessBoard();  // Criação do tabuleiro de xadrez
                        chessFrame.add(chessBoard);

                        chessFrame.pack();
                        chessFrame.setLocationRelativeTo(null);
                        chessFrame.setVisible(true);  // Exibe o tabuleiro de xadrez
                    }
                });

                // Botão para sair da aplicação
                addButton(panel, "Sair", 150, 330, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);  // Encerra a aplicação
                    }
                });

                frame.setVisible(true);  // Exibe a janela principal
            }
        });
    }

    /**
     * Função auxiliar para adicionar botões ao painel.
     * 
     * @param panel O painel onde o botão será adicionado.
     * @param text O texto a ser exibido no botão.
     * @param x A posição x do botão no painel.
     * @param y A posição y do botão no painel.
     * @param action A ação a ser executada quando o botão for clicado.
     * @return O botão criado.
     */
    private static JButton addButton(JPanel panel, String text, int x, int y, ActionListener action) {
        JButton button = DarkThemeUI.createDarkButton(text);  // Criação do botão com tema escuro
        button.setBounds(x, y, 200, 35);  // Define a posição e tamanho do botão
        button.addActionListener(action);  // Adiciona a ação ao botão
        panel.add(button);  // Adiciona o botão ao painel
        return button;
    }
}

// Classe para representar o tabuleiro simples de xadrez
class SimpleChessBoard extends JPanel {
    private final int BOARD_SIZE = 8;  // Tamanho do tabuleiro (8x8)
    private final int TILE_SIZE = 60;  // Tamanho de cada quadrado do tabuleiro
    private Color[][] boardColors;  // Matriz de cores para cada quadrado do tabuleiro
    
    /**
     * Construtor da classe SimpleChessBoard.
     * Inicializa o painel e lê as cores do tabuleiro a partir de um arquivo.
     */
    public SimpleChessBoard() {
        setPreferredSize(new Dimension(BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE));  // Define o tamanho do painel
        setBackground(DarkThemeUI.BACKGROUND_COLOR);  // Define a cor de fundo do painel
        boardColors = new Color[BOARD_SIZE][BOARD_SIZE];  // Inicializa a matriz de cores
        readBoardFromFile();  // Lê as cores do tabuleiro a partir de um arquivo
    }
    
    /**
     * Lê as cores do tabuleiro a partir de um arquivo chamado "tabuleiro.txt".
     */
    private void readBoardFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("tabuleiro.txt"));  // Abre o arquivo
            String line;
            int row = 0;
            
            // Lê as linhas do arquivo
            while ((line = reader.readLine()) != null && row < BOARD_SIZE) {
                String[] colors = line.split(" ");  // Divide a linha em cores
                for (int col = 0; col < BOARD_SIZE && col < colors.length; col++) {
                    if (colors[col].equals("B")) {
                        boardColors[row][col] = new Color(45, 45, 45);  // Cor do quadrado preto
                    } else if (colors[col].equals("W")) {
                        boardColors[row][col] = new Color(200, 200, 200);  // Cor do quadrado branco
                    }
                }
                row++;
            }
            reader.close();  // Fecha o arquivo
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo do tabuleiro: " + e.getMessage());
        }
    }
    
    /**
     * Desenha o tabuleiro de xadrez no painel.
     * 
     * @param g O objeto Graphics usado para desenhar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Desenha os quadrados do tabuleiro
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (boardColors[row][col] != null) {
                    g2d.setColor(boardColors[row][col]);  // Define a cor do quadrado
                } else {
                    g2d.setColor(new Color(200, 200, 200));  // Cor padrão se a cor não estiver definida
                }
                g2d.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);  // Desenha o quadrado
                g2d.setColor(DarkThemeUI.BUTTON_COLOR);  // Cor da borda do quadrado
                g2d.drawRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);  // Desenha a borda
            }
        }
    }
}
