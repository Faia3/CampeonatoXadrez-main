import javax.swing.*;
import java.awt.*;
import java.io.*;

class Tabuleiro extends JPanel {
    private final int BOARD_SIZE = 8;
    private final int TILE_SIZE = 60;
    private Color[][] boardColors;
    
    public Tabuleiro() {
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
        
        // Draw the chess board
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (boardColors[row][col] != null) {
                    g.setColor(boardColors[row][col]);
                } else {
                    g.setColor(Color.WHITE); // Default color if file reading failed
                }
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }
}