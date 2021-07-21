package br.com.alura.agenda.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Campeao;
import br.com.alura.agenda.ui.ListaCampeoesView;

public class ListaCampeoesAdapter extends BaseAdapter {

    private final List<Campeao> campeoes = new ArrayList<>();
    //    private boolean attachToRoot;

    public ListaCampeoesAdapter(ListaCampeoesView context){
    }

    // getCount() -> representa a quantidade de elementos do adapter;
    @Override
    public int getCount() {
        return campeoes.size();
    }

    // getItem() -> Retorna o elemento pela posição;
    @Override
    public Campeao getItem(int posicao) {
        return campeoes.get(posicao);
    }

    // getItemId() -> retornar o id do elemento pela posição;
    @Override
    public long getItemId(int posicao) {
        return campeoes.get(posicao).getId();
    }
//    getView() -> cria a view para cada elemento.
    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Campeao campeaoDevolvido = campeoes.get(posicao);
        vincula(viewCriada, campeaoDevolvido);
        return viewCriada;
    }

    private void vincula(View view, Campeao campeao) {
        TextView nome = view.findViewById(R.id.item_campeao_nome);
        nome.setText(campeao.getNome());

        TextView tipo = view.findViewById(R.id.item_campeao_tipo);
        tipo.setText(campeao.getTipo());

        TextView dano = view.findViewById(R.id.item_campeao_dano);
        dano.setText(campeao.getDano());
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_lista_campeao, viewGroup, false);
    }

    private void clear() {
        this.campeoes.clear();
    }

    private void addAll(List<Campeao> campeoes) {
        this.campeoes.addAll(campeoes);
    }

    public void atualiza(List<Campeao> campeoes){
        clear();
        addAll(campeoes);
        notifyDataSetChanged();
    }

    public void remove(Campeao campeao) {
        campeoes.remove(campeao);
        notifyDataSetChanged();
    }
}
