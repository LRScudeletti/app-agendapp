package lrssoftwares.com.br.agendapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch (LoginActivity.tema) {
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

        setContentView(R.layout.activity_principal);

        Toolbar toolbarPrincipal = findViewById(R.id.toolbarPrincipal);
        toolbarPrincipal.setTitle(getString(R.string.tela_principal));

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbarPrincipal, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CardView cvCadastrarEventos = findViewById(R.id.cvCadastrarEventos);
        cvCadastrarEventos.setOnClickListener(view -> {
            Intent intentCadastrarUsuario = new Intent(PrincipalActivity.this, CalendarioEventosActivity.class);
            startActivity(intentCadastrarUsuario);
        });

        CardView cvConsultarEventos = findViewById(R.id.cvConsultarEventos);
        cvConsultarEventos.setOnClickListener(view -> {
            Intent intentCadastrarUsuario = new Intent(PrincipalActivity.this, ConsultaEventosActivity.class);
            startActivity(intentCadastrarUsuario);
        });

        switch (LoginActivity.tema) {
            case 0:
                cvCadastrarEventos.setCardBackgroundColor(getColor(R.color.colorPrimary));
                cvConsultarEventos.setCardBackgroundColor(getColor(R.color.colorPrimary));
                break;
            case 1:
                cvCadastrarEventos.setCardBackgroundColor(getColor(R.color.corPretoPrimary));
                cvConsultarEventos.setCardBackgroundColor(getColor(R.color.corPretoPrimary));
                break;
            case 2:
                cvCadastrarEventos.setCardBackgroundColor(getColor(R.color.corRosaPrimary));
                cvConsultarEventos.setCardBackgroundColor(getColor(R.color.corRosaPrimary));
                break;
            case 3:
                cvCadastrarEventos.setCardBackgroundColor(getColor(R.color.corVerdePrimary));
                cvConsultarEventos.setCardBackgroundColor(getColor(R.color.corVerdePrimary));
                break;
            default:
                cvCadastrarEventos.setCardBackgroundColor(getColor(R.color.colorPrimary));
                cvConsultarEventos.setCardBackgroundColor(getColor(R.color.colorPrimary));
                break;
        }

        FloatingActionButton fabContato = findViewById(R.id.fabContato);
        fabContato.setOnClickListener(view -> {
            Intent intentCadastrarUsuario = new Intent(PrincipalActivity.this, ContatoActivity.class);
            startActivity(intentCadastrarUsuario);
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemConfiguracoes) {
            Intent intentConfiguracoes = new Intent(PrincipalActivity.this, ConfiguracoesActivity.class);
            startActivity(intentConfiguracoes);
        } else if (id == R.id.itemCompartilhar) {
            String appPackageName = getPackageName();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String descricao = "\n" + getString(R.string.recomendo_aplicativo) + "\n";
            descricao += "https://play.google.com/store/apps/details?id=" + appPackageName + "\n\n";
            intent.putExtra(Intent.EXTRA_TEXT, descricao);
            startActivity(Intent.createChooser(intent, getString(R.string.selecione_opcao_compartilhar)));
        } else if (id == R.id.itemAvaliar) {
            final String appPackageName = getPackageName();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }

        } else if (id == R.id.itemSobre) {
            Intent intentSobre = new Intent(PrincipalActivity.this, SobreActivity.class);
            startActivity(intentSobre);

        } else if (id == R.id.itemSair) {
            Intent intentCadastrarUsuario = new Intent(PrincipalActivity.this, LoginActivity.class);
            startActivity(intentCadastrarUsuario);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
