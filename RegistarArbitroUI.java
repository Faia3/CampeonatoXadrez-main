import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistarArbitroUI {
    private JFrame frame;  // A janela principal da interface gráfica
    private JTextField nomeField;  // Campo de texto para o nome do árbitro
    private JTextField experienciaField;  // Campo de texto para a experiência do árbitro (em anos)
    private JTextField licencaField;  // Campo de texto para a licença do árbitro

    // Construtor da interface gráfica
    public RegistarArbitroUI() {
        frame = new JFrame("Registrar Árbitro");  // Cria a janela com o título "Registrar Árbitro"
        frame.setSize(400, 300);  // Define o tamanho da janela
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Fecha a janela ao clicar no botão de fechar
        frame.setLayout(null);  // Define o layout como nulo para posicionamento manual dos componentes

        // Label e Campo de Nome
        JLabel nomeLabel = new JLabel("Nome:");  // Cria um rótulo para o campo de nome
        nomeLabel.setBounds(50, 30, 80, 25);  // Define a posição e o tamanho do rótulo
        frame.add(nomeLabel);  // Adiciona o rótulo à janela

        nomeField = new JTextField();  // Cria o campo de texto para o nome
        nomeField.setBounds(140, 30, 200, 25);  // Define a posição e o tamanho do campo de texto
        frame.add(nomeField);  // Adiciona o campo de texto à janela

        // Label e Campo de Experiência
        JLabel experienciaLabel = new JLabel("Experiência (anos):");  // Cria um rótulo para o campo de experiência
        experienciaLabel.setBounds(50, 70, 120, 25);  // Define a posição e o tamanho do rótulo
        frame.add(experienciaLabel);  // Adiciona o rótulo à janela

        experienciaField = new JTextField();  // Cria o campo de texto para a experiência
        experienciaField.setBounds(190, 70, 150, 25);  // Define a posição e o tamanho do campo de texto
        frame.add(experienciaField);  // Adiciona o campo de texto à janela

        // Label e Campo de Licença
        JLabel licencaLabel = new JLabel("Licença:");  // Cria um rótulo para o campo de licença
        licencaLabel.setBounds(50, 110, 80, 25);  // Define a posição e o tamanho do rótulo
        frame.add(licencaLabel);  // Adiciona o rótulo à janela

        licencaField = new JTextField();  // Cria o campo de texto para a licença
        licencaField.setBounds(140, 110, 200, 25);  // Define a posição e o tamanho do campo de texto
        frame.add(licencaField);  // Adiciona o campo de texto à janela

        // Botão para Registrar
        JButton btnRegistrar = new JButton("Registrar");  // Cria o botão de registro
        btnRegistrar.setBounds(50, 160, 140, 30);  // Define a posição e o tamanho do botão
        frame.add(btnRegistrar);  // Adiciona o botão à janela

        // Ação para Registrar Árbitro
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarArbitro();  // Chama o método registrarArbitro quando o botão for clicado
            }
        });

        // Botão para Voltar
        JButton btnVoltar = new JButton("Voltar");  // Cria o botão de voltar
        btnVoltar.setBounds(200, 160, 140, 30);  // Define a posição e o tamanho do botão
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
     * Registra o árbitro e salva os dados no arquivo "arbitros.txt".
     */
    private void registrarArbitro() {
        String nome = nomeField.getText();  // Obtém o nome inserido pelo usuário
        String experiencia = experienciaField.getText();  // Obtém a experiência inserida pelo usuário
        String licenca = licencaField.getText();  // Obtém a licença inserida pelo usuário

        // Verifica se todos os campos foram preenchidos
        if (nome.isEmpty() || experiencia.isEmpty() || licenca.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);  // Exibe mensagem de erro
            return;  // Retorna sem registrar o árbitro
        }

        // Tenta gravar os dados do árbitro no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("arbitros.txt", true))) {
            writer.write(nome + "," + experiencia + "," + licenca + "\n");  // Escreve o nome, experiência e licença no arquivo
            JOptionPane.showMessageDialog(frame, "Árbitro registrado com sucesso!");  // Exibe mensagem de sucesso
            nomeField.setText("");  // Limpa o campo de nome
            experienciaField.setText("");  // Limpa o campo de experiência
            licencaField.setText("");  // Limpa o campo de licença
        } catch (IOException ex) {  // Captura exceção caso ocorra erro na escrita do arquivo
            JOptionPane.showMessageDialog(frame, "Erro ao salvar o árbitro!", "Erro", JOptionPane.ERROR_MESSAGE);  // Exibe mensagem de erro
        }
    }
}
