package pe.edu.sise.applinea1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Entidades.Estaciones;
import Entidades.EstacionesAdapter;

import static pe.edu.sise.applinea1.ClassConstante.*;

/**
 * Created by Angel Mamani on 6/05/2017.
 */

public class EstacionAdapter extends RecyclerView.Adapter<EstacionAdapter.EstacionViewHolder> {

    private ArrayList<Entidad_Estacion> items;
    private int layout;
    private OnItemClickListener itemClickListener;
    private static Context thiscontext;

    public EstacionAdapter(ArrayList<Entidad_Estacion> items, int layout, OnItemClickListener listener) {
        this.items = items;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    public EstacionAdapter(ArrayList<Entidad_Estacion> items){
        this.items = items;
    }

    public static class EstacionViewHolder extends RecyclerView.ViewHolder{
        public ImageView icon;
        public TextView descripcion;
        public TextView descripcionGeneral;
        public TextView direccion;
        public TextView latitud;
        public TextView longitud;
        public TextView descripcionestacion;

        public EstacionViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.ImgEstaciones);
            descripcion = (TextView) itemView.findViewById(R.id.txtNombreEstacion);
            descripcionGeneral = (TextView) itemView.findViewById(R.id.txtDistrito);
            direccion = (TextView) itemView.findViewById(R.id.txtDireccion);
            latitud = (TextView) itemView.findViewById(R.id.txtLatitud);
            longitud = (TextView) itemView.findViewById(R.id.txtLongitud);
            descripcionestacion = (TextView) itemView.findViewById(R.id.txtDescripcion);
        }

        //
        private void bind(final Entidad_Estacion estacion, final EstacionAdapter.OnItemClickListener listener) {

            String url = DOMINIO + URL_IMG + "/"+estacion.getIcon();

            Picasso.with(thiscontext)
                    .load(url)
                    .error(R.mipmap.ic_launcher)
                    .fit()
                    .centerInside()
                    .into(icon);

            descripcion.setText(estacion.getDescripcion());
            descripcionGeneral.setText(estacion.getDescripcionGeneral());
            direccion.setText(estacion.getDireccion());
            latitud.setText(estacion.getLatitud());
            longitud.setText(estacion.getLongitud());
            descripcionestacion.setText(estacion.getDescripcionestacion());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(estacion, getAdapterPosition());
                }
            });
        }

    }

    @Override
    public EstacionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        thiscontext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_estaciones, parent, false );
        return new EstacionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EstacionViewHolder holder, int position) {
        holder.bind(items.get(position), itemClickListener);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Entidad_Estacion estacion, int position);
    }

}

