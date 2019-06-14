package lrssoftwares.com.br.agendapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContatoActivity extends AppCompatActivity {

    //region [ VARIAVEIS ]
    private EditText txtMensagem;

    private boolean aplicativoEmailAberto = false;
    //endregion

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

        setContentView(R.layout.activity_contato);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.tela_contato);
        }

        txtMensagem = findViewById(R.id.txtDescricao);

        Button btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(view -> carregarMensagem());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        aplicativoEmailAberto = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        aplicativoEmailAberto = true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (aplicativoEmailAberto) {
            Toast.makeText(ContatoActivity.this, getString(R.string.mensagem_sucesso), Toast.LENGTH_SHORT).show();
            txtMensagem.getText().clear();
        }
    }
    //endregion

    //region [ METODOS ]
    private void carregarMensagem() {
        if (new UtilidadesClass().verificarConexao(getBaseContext())) {
            if (!txtMensagem.getText().toString().equals("")) {

                String destino = "rogerio.scudeletti@gmail.com";
                String mensagem = txtMensagem.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{destino});
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.tela_contato));
                intent.setType("message/rfc822");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(("<p>" + getString(R.string.mensagem) + "<br>" + mensagem + "</p>"), Html.FROM_HTML_MODE_LEGACY));
                else
                    //noinspection deprecation
                    intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(("<p>" + getString(R.string.mensagem) + "<br>" + mensagem + "</p>")));

                startActivityForResult(Intent.createChooser(intent, getString(R.string.aplicacao_email)), 0);

            } else {
                Toast.makeText(ContatoActivity.this, getString(R.string.preencha_campos_contato), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ContatoActivity.this, getString(R.string.verificar_conexao_contato), Toast.LENGTH_SHORT).show();
        }
    }
}
//endregion


