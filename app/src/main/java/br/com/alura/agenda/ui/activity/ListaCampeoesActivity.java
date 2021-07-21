package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Campeao;
import br.com.alura.agenda.ui.ListaCampeoesView;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_CAMPEAO;

public class ListaCampeoesActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de campeões";
    private final ListaCampeoesView listaCampeoesView = new ListaCampeoesView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_campeoes);
        setTitle(TITULO_APPBAR);
        configuraFabNovoCampeao();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_campeoes_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_campeoes_menu_remover) {
            listaCampeoesView.confirmaRemocao(item);

        }

        return super.onContextItemSelected(item);
    }

    private void configuraFabNovoCampeao() {
        FloatingActionButton botaoNovoCampeao = findViewById(R.id.activity_lista_fab_novo_campeao);
        botaoNovoCampeao.setOnClickListener(view -> abreFormularioModoInsereCampeao());
    }

    //       Método para abrir formulário em outra activity através do botão adiciona!
    private void abreFormularioModoInsereCampeao() {
        startActivity(new Intent(this, FormularioCampeaoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaCampeoesView.atualizaCampeoes();
    }

    private void configuraLista() {
        ListView listaDeCampeoes = findViewById(R.id.activity_lista_campeoes);
        listaCampeoesView.configuraAdapter(listaDeCampeoes);
        configuraListenerDeCliquePorItem(listaDeCampeoes);
        registerForContextMenu(listaDeCampeoes);
    }

    private void configuraListenerDeCliquePorItem(ListView ListadeCampeoes) {
        ListadeCampeoes.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Campeao campeaoEscolhido = (Campeao) adapterView.getItemAtPosition(posicao);
            abreFormularioModoEditaCampeao(campeaoEscolhido);
        });
    }

    private void abreFormularioModoEditaCampeao(Campeao campeao) {
        Intent vaiParaFormularioActivity = new Intent(ListaCampeoesActivity.this, FormularioCampeaoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_CAMPEAO, campeao);
        startActivity(vaiParaFormularioActivity);
    }
}
