import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerirJogadoresUI {
    private JFrame frame;

    public GerirJogadoresUI() {
        frame = new JFrame("Gerir Jogadores");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Aplicar tema escuro
        DarkThemeUI.applyDarkTheme(frame);

        // Botão para Registrar Jogador
        JButton btnRegistrar = DarkThemeUI.createDarkButton("Registrar Jogador");
        btnRegistrar.setBounds(100, 30, 200, 30);
        frame.add(btnRegistrar);

        // Ação para Registrar Jogador
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistarJogadorUI();
            }
        });

        // Botão para Consultar Jogador
        JButton btnConsultar = DarkThemeUI.createDarkButton("Consultar Jogador");
        btnConsultar.setBounds(100, 70, 200, 30);
        frame.add(btnConsultar);

        // Botão para Consultar Jogadores
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarJogadorUI();
            }
        });

        // Botão para Atualizar Jogador
        JButton btnAtualizar = DarkThemeUI.createDarkButton("Atualizar Jogador");
        btnAtualizar.setBounds(100, 110, 200, 30);
        frame.add(btnAtualizar);

        // Ação para Atualizar Jogador
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AtualizarJogadorUI();
            }
        });

        // Botão para Excluir Jogador
        JButton btnExcluir = DarkThemeUI.createDarkButton("Excluir Jogador");
        btnExcluir.setBounds(100, 150, 200, 30);
        frame.add(btnExcluir);

        // Ação para Excluir Jogador
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExcluirJogadorUI();
            }
        });

        // Botão para Voltar
        JButton btnVoltar = DarkThemeUI.createDarkButton("Voltar");
        btnVoltar.setBounds(100, 190, 200, 30);
        frame.add(btnVoltar);

        // Ação para Voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
