package org.techtown.mycovidapilocal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    // 아이템을 담을 배열리스트
    private ArrayList<MainData> arrayList;

    public MainAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    // 처음으로 생성될때 생명주기를 뜻함
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    // 실제 추가될때에 대한 생명주기
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {
        holder.gubnu.setText(arrayList.get(position).getGubnu());
        holder.defCnt.setText(arrayList.get(position).getDefCnt());
        holder.incDec.setText(arrayList.get(position).getIncDec());
        holder.localOccCnt.setText(arrayList.get(position).getLocalOccCnt());
        holder.deathCnt.setText(arrayList.get(position).getDeathCnt());
        holder.isolClearCnt.setText(arrayList.get(position).getIsolClearCnt());
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView gubnu;
        protected TextView defCnt;
        protected TextView incDec;
        protected TextView localOccCnt;
        protected TextView deathCnt;
        protected TextView isolClearCnt;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.gubnu = (TextView) itemView.findViewById(R.id.gubun);
            this.defCnt = (TextView) itemView.findViewById(R.id.defCnt);
            this.incDec = (TextView) itemView.findViewById(R.id.incDec);
            this.localOccCnt = (TextView) itemView.findViewById(R.id.localOccCnt);
            this.deathCnt = (TextView) itemView.findViewById(R.id.deathCnt);
            this.isolClearCnt = (TextView) itemView.findViewById(R.id.isolClearCnt);

        }
    }
}
