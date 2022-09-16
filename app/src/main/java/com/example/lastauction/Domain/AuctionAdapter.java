package com.example.lastauction.Domain;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastauction.Member;
import com.example.lastauction.R;
import com.example.lastauction.details;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AuctionAdapter extends RecyclerView.Adapter<AuctionAdapter.Myviewholder> {
    Context context;
    ArrayList<Member> members;
    public AuctionAdapter(Context c,ArrayList<Member> b){
      context = c ;
      members = b;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Myviewholder(LayoutInflater.from(context).inflate(R.layout.recyclerview_image,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        final Member data_position= members.get(position);
        holder.title.setText(members.get(position).getTitle());
        holder.des.setText(members.get(position).getDes());
        holder.lastpri.setText(members.get(position).getPri());
        holder.orgpri.setText(members.get(position).getOrgpri());
        holder.overauc.setText(members.get(position).getOverauc());
        holder.aucnum.setText(members.get(position).getAucnum());
        Picasso.get().load(members.get(position).getImage()).into(holder.image);
        holder.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), details.class);
                intent.putExtra("title",data_position.getTitle());
                intent.putExtra("des",data_position.getDes());
                intent.putExtra("lastpri",data_position.getPri());
                intent.putExtra("orgpri",data_position.getOrgpri());
                intent.putExtra("overauc",data_position.getOverauc());
                intent.putExtra("aucnum",data_position.getAucnum());
                intent.putExtra("image",data_position.getImage());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder{
        TextView title,des,lastpri,orgpri,overauc,aucnum;
        ShapeableImageView image;
        ConstraintLayout layout1;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.nameTextView);
            des = (TextView) itemView.findViewById(R.id.auctionnum);
            lastpri = (TextView) itemView.findViewById(R.id.lastpri);
            image = (ShapeableImageView) itemView.findViewById(R.id.auctionImageView);
            layout1 = (ConstraintLayout) itemView.findViewById(R.id.layout1);
            orgpri = (TextView) itemView.findViewById(R.id.orgprice);
            overauc = (TextView) itemView.findViewById(R.id.overauc1);
            aucnum = (TextView) itemView.findViewById(R.id.aucnumber);
        }
    }

}
