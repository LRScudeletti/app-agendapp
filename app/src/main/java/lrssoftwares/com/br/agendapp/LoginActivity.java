package lrssoftwares.com.br.agendapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public static String nomeStatic;
    public static String usuarioStatic;
    public static int tema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CrudClass crudClass = new CrudClass(this);
        tema = crudClass.pesquisarParametro().get(0).getValor();

        switch (tema) {
            case 0:
                getTheme().applyStyle(R.style.AppTheme_NoActionBar, true);
                break;
            case 1:
                getTheme().applyStyle(R.style.TemaPretoNoActionBar, true);
                break;
            case 2:
                getTheme().applyStyle(R.style.TemaRosaNoActionBar, true);
                break;
            case 3:
                getTheme().applyStyle(R.style.TemaVerdeNoActionBar, true);
                break;
            default:
                getTheme().applyStyle(R.style.AppTheme_NoActionBar, true);
                break;
        }

        setContentView(R.layout.activity_login);
        Toolbar toolbarPrincipal = findViewById(R.id.toolbarPrincipal);
        setSupportActionBar(toolbarPrincipal);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.tela_login));
        }

        Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(view -> {
            EditText txtUsuario = findViewById(R.id.txtUsuario);
            EditText txtSenha = findViewById(R.id.txtSenha);

            if (!txtUsuario.getText().toString().equals("") && !txtSenha.getText().toString().equals("")) {
                login(view, txtUsuario.getText().toString(), txtSenha.getText().toString());
            } else {
                Snackbar.make(view, getString(R.string.preencha_todos_campos_login), Snackbar.LENGTH_LONG).setAction("", null).show();
            }
        });

        FloatingActionButton fabCadastrarUsuario = findViewById(R.id.fabCadastrarUsuario);
        fabCadastrarUsuario.setOnClickListener(view -> {
            Intent intentCadastrarUsuario = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
            startActivity(intentCadastrarUsuario);
        });
    }

    private void login(View view, String usuario, String senha) {
        CrudClass crudClass = new CrudClass(this);
        List<UsuarioClass> listaUsuarios = crudClass.PesquisarUsuario(usuario, senha);

        if (listaUsuarios.size() == 1) {
            Intent intentEntrar = new Intent(LoginActivity.this, PrincipalActivity.class);
            nomeStatic = listaUsuarios.get(0).getNome();
            usuarioStatic = listaUsuarios.get(0).getUsuario();
            startActivity(intentEntrar);
            finish();
        } else {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            Snackbar.make(view, getString(R.string.usuario_senha_invalidos), Snackbar.LENGTH_LONG).setAction("", null).show();
        }
    }
}
