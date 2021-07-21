package br.com.alura.agenda.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Campeao;

public class CampeaoDAO {

    private final static List<Campeao> CAMPEOES = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Campeao campeao) {
        campeao.setId(contadorDeIds);
        CAMPEOES.add(campeao);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public void edita(Campeao campeao) {
        Campeao campeaoEncontrado = buscaCampeaoPeloId(campeao);
        if (campeaoEncontrado != null) {
            int posicaoDoCampeao = CAMPEOES.indexOf(campeaoEncontrado);
            CAMPEOES.set(posicaoDoCampeao, campeao);
        }
    }

    @Nullable
    private Campeao buscaCampeaoPeloId(Campeao campeao) {
        for (Campeao a :
                CAMPEOES) {
            if (a.getId() == campeao.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Campeao> todos() {
        return new ArrayList<>(CAMPEOES);
    }

    public void remove(Campeao campeao) {
        Campeao campeaoDevolvido = buscaCampeaoPeloId(campeao);
        if (campeaoDevolvido != null) {
            CAMPEOES.remove(campeaoDevolvido);
        }
    }
}
