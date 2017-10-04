package Entidades;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.sise.applinea1.R;

/**
 * Created by JuanCR on 04/10/2017.
 */

public class EstacionesAdapter extends RecyclerView.Adapter<EstacionesAdapter.ViewHolder> {

    private List<Estaciones> estacion;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;

    public EstacionesAdapter(List<Estaciones> estacion,int layout,OnItemClickListener listener){
        this.estacion=estacion;
        this.layout=layout;
        this.itemClickListener=listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        context=parent.getContext();
        ViewHolder vh=new ViewHolder(view);
        return vh   ;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
holder.bind(estacion.get(position),itemClickListener);
    }

    @Override
    public int getItemCount() {
        return estacion.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id_estacion;
        public TextView nombre_estacion;
        public TextView descipcion;
        public TextView direccion;
        public TextView distrito;
        public TextView latitud;
        public TextView longitud;
        public ViewHolder(View itemView) {
            super(itemView);

            nombre_estacion=(TextView) itemView.findViewById(R.id.txtNombreEstacion);
            direccion=(TextView)itemView.findViewById(R.id.txtDireccion);
        }

        private void bind(final Estaciones estacion,final OnItemClickListener listener){
            nombre_estacion.setText(estacion.getNombre_estacion());
            direccion.setText(estacion.getDireccion());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(estacion,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Estaciones estacion,int position);
    }
}
