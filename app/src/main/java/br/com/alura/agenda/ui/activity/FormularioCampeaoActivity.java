package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.CampeaoDAO;
import br.com.alura.agenda.model.Campeao;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_CAMPEAO;

public class FormularioCampeaoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_CAMPEAO = "Novo campeão";
    private static final String TITULO_APPBAR_EDITA_CAMPEAO = "Edita campeao";
    private final CampeaoDAO dao = new CampeaoDAO();
    private Campeao campeao = new Campeao();
    private EditText campoNome;
    private EditText campoTipo;
    private EditText campoEmail;
    private EditText campoLane;
    private EditText campoDano;
    private EditText campoRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_campeao);
        inicializacaoDosCampos();
        carregaCampeao();

        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Campeao campeaoCriado = new Campeao();
                Toast.makeText(FormularioCampeaoActivity.this,
                        "cliquei no botão salvar", Toast.LENGTH_SHORT).show();
                dao.salva(campeaoCriado);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_campeao_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_campeao_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaCampeao() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_CAMPEAO)) {
            setTitle(TITULO_APPBAR_EDITA_CAMPEAO);
            campeao = (Campeao) dados.getSerializableExtra(CHAVE_CAMPEAO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_CAMPEAO);
            campeao = new Campeao();
        }
    }

    private void preencheCampos() {
        campoNome.setText(campeao.getNome());
        campoTipo.setText(campeao.getTipo());
        campoEmail.setText(campeao.getEmail());
        }

    private void finalizaFormulario() {
        preencheCampeao();
        if (campeao.temIdValido()) {
            dao.edita(campeao);
        } else {
            dao.salva(campeao);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_campeao_nome);
        campoTipo = findViewById(R.id.activity_formulario_campeao_tipo);
        campoEmail = findViewById(R.id.activity_formulario_campeao_email);
    }

    private void preencheCampeao() {
        String nome = campoNome.getText().toString();
        String telefone = campoTipo.getText().toString();
        String email = campoEmail.getText().toString();

        campeao.setNome(nome);
        campeao.setTipo(telefone);
        campeao.setEmail(email);
    }

}
