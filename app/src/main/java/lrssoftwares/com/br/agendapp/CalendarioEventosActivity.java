package lrssoftwares.com.br.agendapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarioEventosActivity extends AppCompatActivity implements RecyclerViewClickInterface, RecyclerViewLongClickInterface {

    CrudClass crudClass;
    CalendarView calendarView;
    private RecyclerView rvEventos;

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

        setContentView(R.layout.activity_eventos_calendario);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.tela_eventos_calendario));
        }

        crudClass = new CrudClass(this);

        calendarView = findViewById(R.id.calendarViewEventos);
        calendarView.setOnDayClickListener(eventDay -> {
            Calendar diaSelecionado = eventDay.getCalendar();

            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            String dataFormatada = formatoData.format(diaSelecionado.getTime());

            Intent intentCadastrarUsuario = new Intent(CalendarioEventosActivity.this, CadastroEventosActivity.class);
            intentCadastrarUsuario.putExtra("nome", LoginActivity.nomeStatic);
            intentCadastrarUsuario.putExtra("usuario", LoginActivity.usuarioStatic);
            intentCadastrarUsuario.putExtra("dataFormatada", dataFormatada);
            startActivityForResult(intentCadastrarUsuario, 999);
        });

        rvEventos = findViewById(R.id.rvEventos);
        rvEventos.setHasFixedSize(true);
        rvEventos.setNestedScrollingEnabled(false);
        rvEventos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        LinearLayoutManager llmExercicios = new LinearLayoutManager(this);
        llmExercicios.setOrientation(LinearLayoutManager.VERTICAL);

        rvEventos.setLayoutManager(llmExercicios);

        carregarEventosCalendario();

        carregaEventosLista();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {
            carregarEventosCalendario();
            carregaEventosLista();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calendario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemTrocarVisualizacao) {
            if (rvEventos.getVisibility() == View.GONE) {
                rvEventos.setVisibility(View.VISIBLE);
                calendarView.setVisibility(View.GONE);
            } else {
                calendarView.setVisibility(View.VISIBLE);
                rvEventos.setVisibility(View.GONE);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickListener(String idEvento, String dataInicio, String horaInicio, String dataFim, String horaFim, String titulo, String descricao, String local) {
        Intent intentAtualizarUsuario = new Intent(CalendarioEventosActivity.this, AtualizacaoEventosActivity.class);
        intentAtualizarUsuario.putExtra("idEvento", idEvento);
        intentAtualizarUsuario.putExtra("dataInicio", dataInicio);
        intentAtualizarUsuario.putExtra("horaInicio", horaInicio);
        intentAtualizarUsuario.putExtra("dataFim", dataFim);
        intentAtualizarUsuario.putExtra("horaFim", horaFim);
        intentAtualizarUsuario.putExtra("titulo", titulo);
        intentAtualizarUsuario.putExtra("descricao", descricao);
        intentAtualizarUsuario.putExtra("local", local);
        startActivityForResult(intentAtualizarUsuario, 999);
    }

    @Override
    public void onLongonClickListener(String idEvento) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.mensagem_excluir_confirmacao))
                .setPositiveButton(R.string.botao_sim, (dialog, id) -> excluirEvento(Integer.parseInt(idEvento)))
                .setNegativeButton(R.string.botao_nao, (dialog, id) -> {

                });

        builder.create();
        builder.show();
    }

    @SuppressLint("SimpleDateFormat")
    private void carregarEventosCalendario() {
        List<EventoCadastroClass> listaEventosCalendario = crudClass.PesquisarEventosUsuario(LoginActivity.usuarioStatic);

        if (listaEventosCalendario.size() > 0) {
            List<EventDay> eventos = new ArrayList<>();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            Date data = null;

            for (int i = 0; i < listaEventosCalendario.size(); i++) {
                Calendar calendario = Calendar.getInstance();
                try {
                    data = formatoData.parse(listaEventosCalendario.get(i).getDataInicio());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calendario.setTime(data);

                eventos.add(new EventDay(calendario, R.drawable.evento_icone));
            }

            CalendarView calendarViewEventos = findViewById(R.id.calendarViewEventos);
            calendarViewEventos.setEvents(eventos);
        }
    }

    private void carregaEventosLista() {
        List<EventoCadastroClass> listaEventos = crudClass.PesquisarEventosUsuario(LoginActivity.usuarioStatic);

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

        EventosUsuarioAdapter eventosUsuarioAdapter = new EventosUsuarioAdapter(itemListaEventosClasses);
        eventosUsuarioAdapter.setRecyclerViewClickClass(this);
        eventosUsuarioAdapter.setRecyclerViewLongClickClass(this);
        rvEventos.setAdapter(eventosUsuarioAdapter);
    }

    private void excluirEvento(int idEvento) {
        crudClass.excluirEvento(idEvento);

        carregarEventosCalendario();
        carregaEventosLista();
        Snackbar.make(rvEventos, getString(R.string.mensagem_excluir_sucesso), Snackbar.LENGTH_LONG).setAction("", null).show();
    }
}
