package com.example.examen.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examen.Modelos.Data;
import com.example.examen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GanadorAdaptador extends RecyclerView.Adapter<GanadorAdaptador.viewholder> {
    List<Data> LD;

    public GanadorAdaptador(List<Data> LD) {
        this.LD = LD;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(LD.get(position));
    }

    @Override
    public int getItemCount() {
        return LD.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        TextView txtCantidad;
        ImageView imgUrl;
        Data dataHolder;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
            imgUrl = itemView.findViewById(R.id.imgUrl);
        }

        public void setData(Data d)
        {
            dataHolder = d;
            txtNombre.setText(dataHolder.getNombre());
            txtCantidad.setText(dataHolder.getNumero());
            //Picasso.get().load(dataHolder.getUrlImg()).into(imgUrl);
        }
    }
}
