package lrssoftwares.com.br.agendapp;

public interface RecyclerViewClickInterface {
    void onClickListener(String idEvento, String dataInicio, String horaInicio, String dataFim, String horaFim,
                         String titulo, String descricao, String local);
}
