import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VisualizarEstadoTorneiosUI {
    private JFrame frame; // A janela principal da interface gráfica

    // Construtor que cria a interface gráfica para visualizar o estado dos torneios
    public VisualizarEstadoTorneiosUI(List<Torneio> torneios) {
        frame = new JFrame("Estado dos Torneios");  // Cria uma janela com o título "Estado dos Torneios"
        frame.setSize(500, 400);  // Define o tamanho da janela
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Fecha a janela ao clicar no botão de fechar
        frame.setLayout(null);  // Define o layout como nulo, permitindo o posicionamento manual dos componentes

        // Criação da área de texto para exibir os torneios
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);  // A área de texto é somente leitura
        JScrollPane scrollPane = new JScrollPane(textArea);  // Adiciona uma barra de rolagem à área de texto
        scrollPane.setBounds(20, 20, 450, 300);  // Define a posição e o tamanho da área de rolagem
        frame.add(scrollPane);  // Adiciona o JScrollPane à janela

        // Botão para carregar o estado dos torneios
        JButton btnCarregar = new JButton("Carregar Estado");
        btnCarregar.setBounds(20, 330, 200, 30);  // Define a posição e o tamanho do botão
        frame.add(btnCarregar);  // Adiciona o botão à janela

        // Ação do botão Carregar Estado: Exibe os torneios na área de texto
        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarEstadoTorneios(textArea, torneios);  // Chama o método para carregar os dados dos torneios
            }
        });

        // Botão para voltar à janela anterior
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(270, 330, 200, 30);  // Define a posição e o tamanho do botão
        frame.add(btnVoltar);  // Adiciona o botão à janela

        // Ação do botão Voltar: Fecha a janela atual
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();  // Fecha a janela
            }
        });

        frame.setVisible(true);  // Torna a janela visível
    }

    /**
     * Carrega e exibe o estado atual dos torneios na área de texto.
     * 
     * @param textArea A área de texto onde o estado dos torneios será exibido.
     * @param torneios A lista de torneios a ser carregada.
     */
    private void carregarEstadoTorneios(JTextArea textArea, List<Torneio> torneios) {
        if (torneios.isEmpty()) {  // Verifica se não há torneios registrados
            textArea.setText("Nenhum torneio registrado.");  // Exibe mensagem informando que não há torneios
            return;
        }

        StringBuilder builder = new StringBuilder();  // StringBuilder para construir a string de exibição

        // Percorre todos os torneios e adiciona suas representações em string
        for (Torneio torneio : torneios) {
            builder.append(torneio.toString()).append("\n");  // Adiciona a descrição de cada torneio
        }

        textArea.setText(builder.toString());  // Exibe o estado dos torneios na área de texto
    }
}
