package lrssoftwares.com.br.agendapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ConfiguracoesActivity extends AppCompatActivity {

    private int posicao = 0;
    CrudClass crudClass;

    //region [ EVENTOS ]
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

        setContentView(R.layout.activity_configuracoes);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.tela_configuracoes);
        }

        Spinner spinnerTemas = findViewById(R.id.spTemas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.temas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTemas.setAdapter(adapter);

        spinnerTemas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posicao = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnAlterarTema = findViewById(R.id.btnAlterarTema);
        btnAlterarTema.setOnClickListener(view -> {
            ParametroClass parametroClass = new ParametroClass();
            parametroClass.setId(1);
            parametroClass.setNome("Tema");
            parametroClass.setValor(posicao);

            crudClass = new CrudClass(getApplication());
            crudClass.atualizarParametro(parametroClass);

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                Intent intentCadastrarUsuario = new Intent(ConfiguracoesActivity.this, LoginActivity.class);
                startActivity(intentCadastrarUsuario);
                finish();

            }, 1000);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    //endregion
}
