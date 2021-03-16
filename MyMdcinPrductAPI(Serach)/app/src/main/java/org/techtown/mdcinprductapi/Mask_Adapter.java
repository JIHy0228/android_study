package org.techtown.mdcinprductapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Mask_Adapter extends RecyclerView.Adapter<Mask_Adapter.CustomerViewHolder> {

    private ArrayList<Mask_list> masklist;

    public Mask_Adapter(ArrayList<Mask_list> masklist) {
        this.masklist = masklist;
    }

    // 이전 검색된 데이터를 지우기 위함

    public void ClearMaskList() {
        int size = this.masklist.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                masklist.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }

    @NonNull
    @Override
    public Mask_Adapter.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mast_list,parent,false);
        CustomerViewHolder holder =new CustomerViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Mask_Adapter.CustomerViewHolder holder, int position) {

        holder.iv_profile.setImageResource(masklist.get(position).getIv_profile());
        holder.ITEM_NAME.setText(masklist.get(position).getITEM_NAME());
        holder.ENTP_NAME.setText(masklist.get(position).getENTP_NAME());
        holder.ITEM_PERMIT_DATE.setText(masklist.get(position).getITEM_PERMIT_DATE());

    }

    @Override
    public int getItemCount() {
        return (null != masklist ? masklist.size() : 0);
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        protected ImageView iv_profile;
        protected TextView ITEM_NAME;
        protected TextView ENTP_NAME;
        protected TextView ITEM_PERMIT_DATE;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = (ImageView) itemView.findViewById(R.id.profile);
            this.ITEM_NAME = (TextView) itemView.findViewById(R.id.ITEM_NAME);
            this.ENTP_NAME = (TextView) itemView.findViewById(R.id.ENTP_NAME);
            this.ITEM_PERMIT_DATE = (TextView) itemView.findViewById(R.id.ITEM_PERMIT_DATE);
        }
    }
}
