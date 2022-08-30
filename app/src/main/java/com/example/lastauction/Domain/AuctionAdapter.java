package com.example.lastauction.Domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastauction.Member;
import com.example.lastauction.R;
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
        holder.title.setText(members.get(position).getTitle());
        holder.des.setText(members.get(position).getDes());
        Picasso.get().load(members.get(position).getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder{
        TextView title,des;
        ImageView image;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.nameTextView);
            des = (TextView) itemView.findViewById(R.id.descriptionTextView);
            image = (ImageView) itemView.findViewById(R.id.auctionImageView);
        }
    }

}
