import java.io.Serializable;
import java.util.ArrayList;

public class TabelaRecordes implements Serializable {
    private String nome;
    private long tempo;
    private transient ArrayList<TabelaRecordesListener> listeners;


    public TabelaRecordes() {
        nome = "Anónimo";
        tempo = 9999999;
        listeners=new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public long getTempo() {
        return tempo;
    }

    public void setRecorde(String nome, long tempo) {
        if(tempo < this.tempo) {
            this.nome = nome;
            this.tempo = tempo;
            notifyRecordesActualizados();
        }
    }

    public void addTabelaRecordesListener(TabelaRecordesListener list) {
        if (listeners == null) listeners = new ArrayList<>();
        listeners.add(list);
    }
    public void removeTabelaRecordesListener(TabelaRecordesListener list) {
        if (listeners != null)
            listeners.remove(list);
    }

    private void notifyRecordesActualizados() {
        if (listeners != null) {
            for (TabelaRecordesListener list:listeners)
                list.recordesActualizados(this);
        }

    }

}
