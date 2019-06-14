package lrssoftwares.com.br.agendapp;

//region [ IMPORTS ]

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
//endregion

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ViewHolder> {

    //region [ VARIAVEIS ]
    private final ArrayList<ItemListaEventosClass> itemListaEventosClasses;
    //endregion

    EventosAdapter(ArrayList<ItemListaEventosClass> linhaListaEventoClass) {
        this.itemListaEventosClasses = linhaListaEventoClass;
    }

    //region [ EVENTOS ]
    @NonNull
    @Override
    public EventosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_eventos_consulta_item, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EventosAdapter.ViewHolder holder, int position) {
        ItemListaEventosClass row_pos = itemListaEventosClasses.get(position);

        holder.txtId.setText(Integer.toString(row_pos.getId()));

        holder.txtTitulo.setText(row_pos.getTitulo());
        holder.txtNome.setText(row_pos.getNome());
        holder.txtDataInicio.setText(holder.itemView.getResources().getString(R.string.adapter_inicio) + " " + row_pos.getDataInicio() + " " + row_pos.getHoraInicio());
        holder.txtHoraInicio.setText(row_pos.getHoraInicio());
        holder.txtDataFim.setText(holder.itemView.getResources().getString(R.string.adapter_fim) + " " + row_pos.getDataFim() + " " + row_pos.getHoraFim());
        holder.txtHoraFim.setText(row_pos.getHoraFim());
        holder.txtDescricao.setText(holder.itemView.getResources().getString(R.string.adapter_descricao) + " " + row_pos.getDescricao());
        holder.txtLocal.setText(holder.itemView.getResources().getString(R.string.adapter_local) + " " + row_pos.getLocal());


        if (holder.getItemViewType() == 0) {
            switch (LoginActivity.tema) {
                case 0:
                    holder.itemView.setBackgroundResource(R.color.corAzulClaro);
                    break;
                case 1:
                    holder.itemView.setBackgroundResource(R.color.corPretoClaro);
                    break;
                case 2:
                    holder.itemView.setBackgroundResource(R.color.corRosaClaro);
                    break;
                case 3:
                    holder.itemView.setBackgroundResource(R.color.corVerdeClaro);
                    break;
                default:
                    holder.itemView.setBackgroundResource(R.color.corAzulClaro);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemListaEventosClasses.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? 0 : 1;
    }
    //endregion

    //region [ CLASSES ]
    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txtId;
        final TextView txtNome;
        final TextView txtDataInicio;
        final TextView txtHoraInicio;
        final TextView txtDataFim;
        final TextView txtHoraFim;
        final TextView txtTitulo;
        final TextView txtDescricao;
        final TextView txtLocal;

        ViewHolder(View itemView) {
            super(itemView);

            txtId = itemView.findViewById(R.id.txtId);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtDataInicio = itemView.findViewById(R.id.txtDataInicio);
            txtHoraInicio = itemView.findViewById(R.id.txtHoraInicio);
            txtDataFim = itemView.findViewById(R.id.txtDataFim);
            txtHoraFim = itemView.findViewById(R.id.txtHoraFim);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            txtLocal = itemView.findViewById(R.id.txtLocal);
        }
    }
    //endregion
}