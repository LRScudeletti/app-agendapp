package lrssoftwares.com.br.agendapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

public class CadastroUsuarioActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
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

        setContentView(R.layout.activity_cadastro_usuario);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.tela_cadastro_usuarios));
        }

        ScrollView vScroll = findViewById(R.id.svCadastrarUsuario);
        vScroll.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        vScroll.setFocusable(true);
        vScroll.setFocusableInTouchMode(true);
        vScroll.setOnTouchListener((v, event) -> {
            v.requestFocusFromTouch();
            return false;
        });

        EditText txtNome = findViewById(R.id.txtNome);
        EditText txtUsuario = findViewById(R.id.txtUsuario);
        EditText txtSenha = findViewById(R.id.txtSenha);
        EditText txtRepetirSenha = findViewById(R.id.txtRepetirSenha);

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(view -> {
            if (!txtNome.getText().toString().equals("") && !txtUsuario.getText().toString().equals("") && !txtSenha.getText().toString().equals("") && !txtRepetirSenha.getText().toString().equals("")) {
                inserirUsuario(view, txtNome.getText().toString(), txtUsuario.getText().toString(), txtSenha.getText().toString());

                txtNome.getText().clear();
                txtUsuario.getText().clear();
                txtSenha.getText().clear();
                txtRepetirSenha.getText().clear();
            } else {
                Snackbar.make(view, getString(R.string.preencha_todos_campos_usuario), Snackbar.LENGTH_LONG).setAction("", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void inserirUsuario(View view, String nome, String usuario, String senha) {
        UsuarioClass usuarioClass = new UsuarioClass();
        usuarioClass.setNome(nome);
        usuarioClass.setUsuario(usuario);
        usuarioClass.setSenha(senha);

        CrudClass crudClass = new CrudClass(this);
        crudClass.inserirUsuario(usuarioClass);

        Snackbar.make(view, getString(R.string.usuario_cadastro_sucesso), Snackbar.LENGTH_LONG).setAction("", null).show();
    }
}