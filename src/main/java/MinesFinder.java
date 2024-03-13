import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private TabelaRecordes recordesFacil;
    private TabelaRecordes recordesMedio;
    private TabelaRecordes recordesDificil;

    private void guardarRecordesDisco() {
        ObjectOutputStream oos = null;
        try {
            File f = new
                    File(System.getProperty("user.home") + File.separator + "minesfinder.recordes");
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(recordesFacil);
            oos.writeObject(recordesMedio);
            oos.writeObject(recordesDificil);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    private void lerRecordesDoDisco() {
        ObjectInputStream ois = null;
        File f = new
                File(System.getProperty("user.home") + File.separator + "minesfinder.recordes");
        if (f.canRead()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                recordesFacil = (TabelaRecordes) ois.readObject();
                recordesMedio = (TabelaRecordes) ois.readObject();
                recordesDificil = (TabelaRecordes) ois.readObject();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE,
                        null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }

    private void recordesFacilActualizado(TabelaRecordes recordes) {
        lblJogFacil.setText(recordes.getNome() + " - " + recordes.getTempo() / 1000);
    }

    private void recordesMedioActualizado(TabelaRecordes recordes) {
        lblJogMedio.setText(recordes.getNome() + " - " + recordes.getTempo() / 1000);
    }

    private void recordesDificilActualizado(TabelaRecordes recordes) {
        lblJogDificil.setText(recordes.getNome() + " - " + recordes.getTempo() / 1000);
    }

    public MinesFinder(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        recordesFacil = new TabelaRecordes();
        recordesMedio = new TabelaRecordes();
        recordesDificil = new TabelaRecordes();

        lerRecordesDoDisco();
        lblJogFacil.setText(recordesFacil.getNome() + " - " + recordesFacil.getTempo() / 1000);
        lblJogFacil.setText(recordesMedio.getNome() + " - " + recordesMedio.getTempo() / 1000);
        lblJogFacil.setText(recordesDificil.getNome() + " - " + recordesDificil.getTempo() / 1000);

        recordesFacil.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesFacilActualizado(recordes);
            }
        });
        recordesMedio.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesMedioActualizado(recordes);
            }
        });
        recordesDificil.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesDificilActualizado(recordes);
            }
        });

        btnSair.addActionListener(this::btnSairActionPerformed);
        btnFacil.addActionListener(this::btnJogoFacilActionPerformed);
        btnMedio.addActionListener(this::btnJogoMedioActionPerformed);
        btnDificil.addActionListener(this::btnJogoDificilActionPerformed);
    }

    private void btnSairActionPerformed(ActionEvent e) {
        guardarRecordesDisco();
        System.exit(0);
    }

    private void btnJogoFacilActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(9, 9, 3), recordesFacil);
    }

    private void btnJogoMedioActionPerformed(ActionEvent e) {
        // TODO
        var janela = new JanelaDeJogo(new CampoMinado(16, 16, 40), recordesMedio);
    }

    private void btnJogoDificilActionPerformed(ActionEvent e) {
        // TODO
        var janela = new JanelaDeJogo(new CampoMinado(16, 30, 90), recordesDificil);
    }

    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }
}
