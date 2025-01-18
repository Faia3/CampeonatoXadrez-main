import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DarkThemeUI {
    // Definições de cores usadas para o tema escuro
    public static final Color BACKGROUND_COLOR = new Color(36, 36, 36);  // Cor de fundo do tema
    public static final Color BUTTON_COLOR = new Color(64, 64, 64);  // Cor dos botões
    public static final Color HOVER_COLOR = new Color(96, 96, 96);  // Cor quando o botão está em hover (passando o rato por cima)
    public static final Color TEXT_COLOR = Color.WHITE;  // Cor do texto (branco)
    
    private static Font minecraftFont;  // Fonte personalizada (Minecraft)

    static {
        try {
            // Tenta carregar a fonte "minecraft.ttf" para o tema
            minecraftFont = Font.createFont(Font.TRUETYPE_FONT, new File("minecraft.ttf")).deriveFont(14f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(minecraftFont);  // Registra a fonte no ambiente gráfico
        } catch (IOException | FontFormatException e) {
            // Se a fonte não for encontrada, utiliza a fonte padrão "Courier New"
            System.out.println("Fonte Minecraft não encontrada, utilizando fonte alternativa.");
            minecraftFont = new Font("Courier New", Font.PLAIN, 14);
        }
    }

    /**
     * Aplica o tema escuro à janela principal.
     * 
     * @param frame A janela principal onde o tema será aplicado.
     */
    public static void applyDarkTheme(JFrame frame) {
        frame.getContentPane().setBackground(BACKGROUND_COLOR);  // Aplica a cor de fundo escura
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(BUTTON_COLOR, 2));  // Aplica uma borda ao redor da janela
    }

    /**
     * Cria um botão com o estilo do tema escuro.
     * 
     * @param text O texto a ser exibido no botão.
     * @return O botão criado com estilo escuro.
     */
    public static JButton createDarkButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  // Habilita suavização das bordas
                
                // Define a cor do botão baseado no estado (pressionado, em hover, ou normal)
                if (getModel().isPressed()) {
                    g2.setColor(HOVER_COLOR.darker());  // Quando pressionado, cor mais escura
                } else if (getModel().isRollover()) {
                    g2.setColor(HOVER_COLOR);  // Quando está em hover, cor do hover
                } else {
                    g2.setColor(BUTTON_COLOR);  // Cor normal do botão
                }
                
                // Desenha o botão com cantos arredondados
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(BUTTON_COLOR.darker());  // Cor mais escura para a borda
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);  // Desenha a borda arredondada
                
                g2.setFont(minecraftFont);  // Define a fonte personalizada
                g2.setColor(TEXT_COLOR);  // Define a cor do texto
                FontMetrics fm = g2.getFontMetrics();  // Obtém as métricas da fonte
                int x = (getWidth() - fm.stringWidth(getText())) / 2;  // Calcula a posição X do texto para centralizá-lo
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;  // Calcula a posição Y do texto para centralizá-lo
                g2.drawString(getText(), x, y);  // Desenha o texto no botão
                g2.dispose();  // Libera os recursos gráficos
            }
        };

        // Configura o botão com a fonte, cor de fundo e outras propriedades
        button.setFont(minecraftFont);  // Define a fonte personalizada para o texto
        button.setForeground(TEXT_COLOR);  // Define a cor do texto
        button.setBackground(BUTTON_COLOR);  // Define a cor de fundo
        button.setFocusPainted(false);  // Remove o destaque de foco do botão
        button.setBorderPainted(false);  // Remove a borda padrão do botão
        button.setContentAreaFilled(false);  // Remove o preenchimento padrão do botão

        return button;  // Retorna o botão criado
    }
}
