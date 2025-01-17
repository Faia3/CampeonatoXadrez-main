import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

public class RankingUI extends JFrame {
    private JTable rankingTable;
    private JScrollPane scrollPane;

    public RankingUI() {
        setTitle("Ranking de Jogadores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true); // Remove window decoration

        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(0, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Ranking de Jogadores");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);

        // Table setup
        String[] columnNames = {"Posição", "Nome", "Pontos", "Vitórias", "Partidas Jogadas"};
        Object[][] data = getPlayerRankingData();
        
        rankingTable = new JTable(data, columnNames);
        rankingTable.setFillsViewportHeight(true);
        rankingTable.getTableHeader().setReorderingAllowed(false); // Prevent column reordering
        rankingTable.getTableHeader().setResizingAllowed(false); // Prevent column resizing
        rankingTable.setEnabled(false); // Make table non-editable
        
        // Center align all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < rankingTable.getColumnCount(); i++) {
            rankingTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Style the table
        rankingTable.setRowHeight(25);
        rankingTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        rankingTable.setFont(new Font("Arial", Font.PLAIN, 12));
        
        scrollPane = new JScrollPane(rankingTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton closeButton = new JButton("Fechar");
        closeButton.setPreferredSize(new Dimension(100, 30));
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);

        // Add components to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        // Make sure the window can't be moved
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentMoved(java.awt.event.ComponentEvent e) {
                setLocationRelativeTo(null);
            }
        });
    }

    private Object[][] getPlayerRankingData() {
        List<Jogador> jogadores = PlayerManager.getInstance().getJogadores();
        
        // Sort players by ranking points (highest to lowest)
        jogadores.sort((j1, j2) -> Integer.compare(j2.getRanking(), j1.getRanking()));

        // Convert to table data
        Object[][] data = new Object[jogadores.size()][5];
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador j = jogadores.get(i);
            data[i][0] = i + 1;
            data[i][1] = j.getNome();
            data[i][2] = j.getRanking();
            data[i][3] = j.getPartidasVencidas();
            data[i][4] = j.getPartidasJogadas();
        }
        
        // If no players, return empty row with message
        if (jogadores.isEmpty()) {
            return new Object[][]{{"--", "Nenhum jogador registrado", "--", "--", "--"}};
        }

        return data;
    }

    public void refreshRanking() {
        Object[][] newData = getPlayerRankingData();
        String[] columnNames = {"Posição", "Nome", "Pontos", "Vitórias", "Partidas Jogadas"};
        rankingTable.setModel(new JTable(newData, columnNames).getModel());
    }
}