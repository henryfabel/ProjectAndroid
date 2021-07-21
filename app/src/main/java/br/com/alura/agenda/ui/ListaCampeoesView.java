package br.com.alura.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.alura.agenda.dao.CampeaoDAO;
import br.com.alura.agenda.model.Campeao;
import br.com.alura.agenda.ui.adapter.ListaCampeoesAdapter;

public class ListaCampeoesView {

    private final ListaCampeoesAdapter adapter;
    private final CampeaoDAO dao = new CampeaoDAO();
    private final Context context;

    public ListaCampeoesView(Context context) {
        this.context = context;
        this.adapter = new ListaCampeoesAdapter(this);
    }

    @SuppressWarnings("unused")
    public void confirmaRemocao(MenuItem item) {
        AlertDialog alertDialog = new AlertDialog
                .Builder(context)
                .setTitle("Removendo Campeão")
                .setMessage("Você deseja remover o campeão?")
                .setPositiveButton("sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Campeao campeaoEscolhido = adapter.getItem(menuInfo.position);

                    remove(campeaoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaCampeoes() {
        adapter.atualiza(dao.todos());
    }

    private void remove(Campeao campeao) {
        dao.remove(campeao);
        adapter.remove(campeao);
    }

    public void configuraAdapter(ListView listaDeCampeoes) {
        listaDeCampeoes.setAdapter(adapter);
    }
}
