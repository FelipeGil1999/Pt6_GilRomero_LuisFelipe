package com.example.pt6_gilromero_luisfelipe;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{


    private List<Ligue> elements;
    private List<Team> elements2;

    public MyRecyclerViewAdapter(List<Ligue> elements) {
        this.elements = elements;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewElement = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);

        return new ViewHolder(viewElement);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTxtElement().setText(elements.get(position).getTeam_name());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textElement;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewHolder.this.mostraElement(v);
                    System.out.println(textElement.getText().toString());
                }
            });

            textElement = itemView.findViewById(R.id.textView);
        }



        private void mostraElement(View v) {

            // Cridem la pantalla de mostrar personatge i li passem les dades
            Intent mostrarTeam = new Intent(v.getContext(), InfoTeam.class);
            Team team = elements2.get(getAdapterPosition());
            mostrarTeam.putExtra("Titles", team.getTitles());
            mostrarTeam.putExtra("Stadium", team.getTeam_stadium());
            v.getContext().startActivity(mostrarTeam);

        }

        public TextView getTxtElement() {
            return textElement;
        }
    }
}
