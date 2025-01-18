import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistarJogadorUI {
    private JFrame frame;  // A janela principal da interface gráfica
    private JTextField nomeField;  // Campo de texto para o nome do jogador
    private JComboBox<String> generoBox;  // Caixa de seleção para o género do jogador

    // Construtor da interface gráfica
    public RegistarJogadorUI() {
        frame = new JFrame("Registrar Jogador");  // Cria a janela com o título "Registrar Jogador"
        frame.setSize(400, 250);  // Define o tamanho da janela
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Fecha a janela ao clicar no botão de fechar
        frame.setLayout(null);  // Define o layout como nulo para posicionamento manual dos componentes

        // Label e Campo de Nome
        JLabel nomeLabel = new JLabel("Nome:");  // Cria um rótulo para o campo de nome
        nomeLabel.setBounds(50, 30, 80, 25);  // Define a posição e o tamanho do rótulo
        frame.add(nomeLabel);  // Adiciona o rótulo à janela

        nomeField = new JTextField();  // Cria o campo de texto para o nome
        nomeField.setBounds(140, 30, 200, 25);  // Define a posição e o tamanho do campo de texto
        frame.add(nomeField);  // Adiciona o campo de texto à janela

        // Label e Campo de Género
        JLabel generoLabel = new JLabel("Género:");  // Cria um rótulo para o campo de género
        generoLabel.setBounds(50, 70, 80, 25);  // Define a posição e o tamanho do rótulo
        frame.add(generoLabel);  // Adiciona o rótulo à janela

        generoBox = new JComboBox<>(new String[]{"Masculino", "Feminino"});  // Cria uma caixa de seleção com opções de género
        generoBox.setBounds(140, 70, 200, 25);  // Define a posição e o tamanho da caixa de seleção
        frame.add(generoBox);  // Adiciona a caixa de seleção à janela

        // Botão para Registrar
        JButton btnRegistrar = new JButton("Registrar");  // Cria o botão de registro
        btnRegistrar.setBounds(50, 120, 140, 30);  // Define a posição e o tamanho do botão
        frame.add(btnRegistrar);  // Adiciona o botão à janela

        // Ação para Registrar Jogador
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarJogador();  // Chama o método registrarJogador quando o botão for clicado
            }
        });

        // Botão para Voltar
        JButton btnVoltar = new JButton("Voltar");  // Cria o botão de voltar
        btnVoltar.setBounds(200, 120, 140, 30);  // Define a posição e o tamanho do botão
        frame.add(btnVoltar);  // Adiciona o botão à janela

        // Ação para Voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();  // Fecha a janela atual
            }
        });

        frame.setVisible(true);  // Torna a janela visível
    }

    /**
     * Registra o jogador e salva os dados no arquivo "jogadores.txt".
     */
    private void registrarJogador() {
        String nome = nomeField.getText();  // Obtém o nome inserido pelo usuário
        String genero = (String) generoBox.getSelectedItem();  // Obtém o género selecionado na caixa de seleção

        if (nome.isEmpty()) {  // Verifica se o campo de nome está vazio
            JOptionPane.showMessageDialog(frame, "O nome não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);  // Exibe mensagem de erro
            return;  // Retorna sem registrar o jogador
        }

        // Tenta gravar os dados do jogador no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jogadores.txt", true))) {
            writer.write(nome + "," + genero + "\n");  // Escreve o nome e o género no arquivo
            JOptionPane.showMessageDialog(frame, "Jogador registrado com sucesso!");  // Exibe mensagem de sucesso
            nomeField.setText("");  // Limpa o campo de nome
            generoBox.setSelectedIndex(0);  // Restaura o índice padrão da caixa de seleção (Masculino)
        } catch (IOException ex) {  // Captura exceção caso ocorra erro na escrita do arquivo
            JOptionPane.showMessageDialog(frame, "Erro ao salvar o jogador!", "Erro", JOptionPane.ERROR_MESSAGE);  // Exibe mensagem de erro
        }
    }
}
