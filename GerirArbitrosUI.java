
// GerirArbitrosUI.java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerirArbitrosUI {
    private JFrame frame;

    public GerirArbitrosUI() {
        frame = new JFrame("Gerir Árbitros");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Botão para Registrar Árbitro
        JButton btnRegistrar = new JButton("Registrar Árbitro");
        btnRegistrar.setBounds(100, 30, 200, 30);
        frame.add(btnRegistrar);

        // Ação para Registrar Árbitro
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrarArbitroUI();
            }
        });

        // Botão para Consultar Árbitro
        JButton btnConsultar = new JButton("Consultar Árbitro");
        btnConsultar.setBounds(100, 70, 200, 30);
        frame.add(btnConsultar);

        // Botão para Consultar Árbitros
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarArbitroUI();
            }
        });

        // Botão para Atualizar Árbitro
        JButton btnAtualizar = new JButton("Atualizar Árbitro");
        btnAtualizar.setBounds(100, 110, 200, 30);
        frame.add(btnAtualizar);

        // Ação para Atualizar Árbitro
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AtualizarArbitroUI();
            }
        });

        // Botão para Excluir Árbitro
        JButton btnExcluir = new JButton("Excluir Árbitro");
        btnExcluir.setBounds(100, 150, 200, 30);
        frame.add(btnExcluir);

        // Ação para Excluir Árbitro
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExcluirArbitroUI();
            }
        });

        // Botão para Voltar
        JButton btnVoltar = new JButton("Voltar");
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
