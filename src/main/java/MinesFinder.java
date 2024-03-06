import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesFinder extends JFrame {

    private JPanel painelPrincipal;
    private JLabel labelNorte;
    private JPanel painelEsquerda;
    private JPanel painelCentro;
    private JButton btnFacil;
    private JButton btnSair;
    private JButton btnMedio;
    private JButton btnDificil;
    private JLabel lblRecordes;
    private JLabel lblNivelFacil;
    private JLabel lblJogFacil;
    private JLabel lblNivelMedio;
    private JLabel lblJogMedio;
    private JLabel lblNivelDificil;
    private JLabel lblJogDificil;

    public MinesFinder(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        btnSair.addActionListener(this::btnSairActionPerformed);
        btnFacil.addActionListener(this::btnJogoFacilActionPerformed);
        btnMedio.addActionListener(this::btnJogoMedioActionPerformed);
        btnDificil.addActionListener(this::btnJogoDificilActionPerformed);
    }

    private void btnSairActionPerformed(ActionEvent e) {
        System.exit(0);
    }
    private void btnJogoFacilActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(9,9, 10));
    }
    private void btnJogoMedioActionPerformed(ActionEvent e) {
        // TODO
        var janela = new JanelaDeJogo(new CampoMinado(16,16, 40));
    }
    private void btnJogoDificilActionPerformed(ActionEvent e) {
        // TODO
        var janela = new JanelaDeJogo(new CampoMinado(16,30, 90));
    }

    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }
}
