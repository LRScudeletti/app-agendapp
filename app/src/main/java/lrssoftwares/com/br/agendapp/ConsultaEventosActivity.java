package lrssoftwares.com.br.agendapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class ConsultaEventosActivity extends AppCompatActivity {

    CrudClass crudClass;
    private RecyclerView rvEventos;

    List<EventoCadastroClass> listaEventos;
    EventosAdapter eventosAdapter;

    @SuppressLint("SimpleDateFormat")
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

        setContentView(R.layout.activity_eventos_consulta);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.tela_eventos_calendario));
        }

        crudClass = new CrudClass(this);

        rvEventos = findViewById(R.id.rvEventos);
        rvEventos.setHasFixedSize(true);
        rvEventos.setNestedScrollingEnabled(false);
        rvEventos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        LinearLayoutManager llmExercicios = new LinearLayoutManager(this);
        llmExercicios.setOrientation(LinearLayoutManager.VERTICAL);

        rvEventos.setLayoutManager(llmExercicios);

        carregaEventosLista();
    }

    private void carregaEventosLista() {
        listaEventos = crudClass.PesquisarEventos();

        ArrayList<ItemListaEventosClass> itemListaEventosClasses = new ArrayList<>();

        for (int i = 0; i < listaEventos.size(); i++) {
            ItemListaEventosClass linhaItemClass = new ItemListaEventosClass(listaEventos.get(i).getId(),
                    listaEventos.get(i).getDataInicio(),
                    listaEventos.get(i).getHoraInicio(),
                    listaEventos.get(i).getDataFim(),
                    listaEventos.get(i).getHoraFim(),
                    listaEventos.get(i).getTitulo(),
                    listaEventos.get(i).getDescricao(),
                    listaEventos.get(i).getNome(),
                    listaEventos.get(i).getLocal());

            itemListaEventosClasses.add(linhaItemClass);
        }

        EventosAdapter eventosAdapter = new EventosAdapter(itemListaEventosClasses);
        rvEventos.setAdapter(eventosAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consulta, menu);

        SearchView svPesquisa = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.btnPesquisar));
        svPesquisa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String textoDigitado) {
                CarregarPesquisa(textoDigitado);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void CarregarPesquisa(String textoDigitado) {
        ArrayList<EventoCadastroClass> arrayItensAuxiliar = new ArrayList<>();
        ArrayList<ItemListaEventosClass> linhaItemClass = new ArrayList<>();
        arrayItensAuxiliar.clear();

        int tamanhoTexto = textoDigitado.length();

        for (int i = 0; i < listaEventos.size(); i++)
            if (tamanhoTexto <= listaEventos.get(i).toString().length())
                if (listaEventos.get(i).getNome().toLowerCase().contains(textoDigitado.toLowerCase()))
                    arrayItensAuxiliar.add(listaEventos.get(i));


        for (int i = 0; i < arrayItensAuxiliar.size(); i++) {
            ItemListaEventosClass itens2 = new ItemListaEventosClass(arrayItensAuxiliar.get(i));
            linhaItemClass.add(itens2);
        }

        eventosAdapter = new EventosAdapter(linhaItemClass);
        rvEventos.setAdapter(eventosAdapter);
    }
}
