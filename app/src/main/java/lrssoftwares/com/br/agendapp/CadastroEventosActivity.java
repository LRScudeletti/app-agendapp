package lrssoftwares.com.br.agendapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

public class CadastroEventosActivity extends AppCompatActivity {

    private String dataEvento;
    private boolean cadastrou;

    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch (LoginActivity.tema) {
            case 0:
                getTheme().applyStyle(R.style.AppTheme, true);
                break;
            case 1:
                getTheme().applyStyle(R.style.TemaPreto, true);
                break;
            case 2:
                getTheme().applyStyle(R.style.TemaRosa, true);
                break;
            case 3:
                getTheme().applyStyle(R.style.TemaVerde, true);
                break;
            default:
                getTheme().applyStyle(R.style.AppTheme, true);
                break;
        }

        setContentView(R.layout.activity_eventos_cadastro);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.tela_eventos_cadastro));
        }

        ScrollView vScroll = findViewById(R.id.svCadastroEventos);
        vScroll.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        vScroll.setFocusable(true);
        vScroll.setFocusableInTouchMode(true);
        vScroll.setOnTouchListener((v, event) -> {
            v.requestFocusFromTouch();
            return false;
        });

        if (getIntent().getExtras() != null) {
            dataEvento = getIntent().getExtras().getString("dataFormatada", "");
        }

        EditText txtDataInicio = findViewById(R.id.txtDataInicio);
        txtDataInicio.setText(dataEvento);

        EditText txtHoraInicio = findViewById(R.id.txtHoraInicio);

        EditText txtDataFim = findViewById(R.id.txtDataFim);

        EditText txtHoraFim = findViewById(R.id.txtHoraFim);

        EditText txtTitulo = findViewById(R.id.txtTitulo);

        EditText txtDescricao = findViewById(R.id.txtDescricao);

        EditText txtLocal = findViewById(R.id.txtLocal);

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(view -> {

            String dataFim = (txtDataFim.getText().toString().equals("")) ? txtDataInicio.getText().toString() : txtDataFim.getText().toString();
            String horaFim = (txtHoraFim.getText().toString().equals("")) ? txtHoraFim.getText().toString() : txtHoraFim.getText().toString();

            if (!txtDataInicio.getText().toString().equals("") && !txtHoraInicio.getText().toString().equals("") && !txtTitulo.getText().toString().equals("") && !txtDescricao.getText().toString().equals("") && !txtLocal.getText().toString().equals("")) {
                inserirEvento(view, txtDataInicio.getText().toString(), txtHoraInicio.getText().toString(),
                        dataFim, horaFim, txtTitulo.getText().toString(), txtDescricao.getText().toString(), txtLocal.getText().toString());

                txtDataFim.setText(dataEvento);
                txtHoraInicio.getText().clear();
                txtDataFim.getText().clear();
                txtHoraFim.getText().clear();
                txtTitulo.getText().clear();
                txtDescricao.getText().clear();
                txtLocal.getText().clear();
            } else {
                Snackbar.make(view, getString(R.string.preencha_todos_campos_evento), Snackbar.LENGTH_LONG).setAction("", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void finish() {
        if (cadastrou) {
            Intent retornoIntent = new Intent();
            setResult(RESULT_OK, retornoIntent);
            cadastrou = false;
        }
        super.finish();
    }

    private void inserirEvento(View view, String dataInicio, String horaInicio, String dataFim, String horaFim, String titulo, String descricao, String local) {
        EventoCadastroClass eventoCadastroClass = new EventoCadastroClass();
        eventoCadastroClass.setDataInicio(dataInicio);
        eventoCadastroClass.setHoraInicio(horaInicio);
        eventoCadastroClass.setDataFim(dataFim);
        eventoCadastroClass.setHoraFim(horaFim);
        eventoCadastroClass.setTitulo(titulo);
        eventoCadastroClass.setDescricao(descricao);
        eventoCadastroClass.setLocal(local);
        eventoCadastroClass.setNome(LoginActivity.nomeStatic);
        eventoCadastroClass.setUsuario(LoginActivity.usuarioStatic);

        CrudClass crudClass = new CrudClass(this);
        crudClass.inserirEvento(eventoCadastroClass);

        cadastrou = true;

        Snackbar.make(view, getString(R.string.evento_cadastro_sucesso), Snackbar.LENGTH_LONG).setAction("", null).show();
    }
}