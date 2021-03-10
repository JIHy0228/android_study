package org.techtown.swpie;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.SimpleTextViewHolder> implements SimpleTextItemTouchHelper.OnItemTouchListener {
    private List<String> simpleTextList;
    private OnStartDragListener onStartDragListener;

    public SimpleTextAdapter(List<String> simpleTextList, OnStartDragListener onStartDragListener){
        this.simpleTextList = simpleTextList;
        this.onStartDragListener = onStartDragListener;
    }
    @NonNull
    @Override
    public SimpleTextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_simple_text, parent, false);

        return new SimpleTextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleTextViewHolder holder, int position) {
        holder.bind(simpleTextList.get(position));
    }

    @Override
    public int getItemCount() {
        if (simpleTextList != null) {
            return simpleTextList.size();
        }
        return 0;
    }

    // 데이터를 이동 시키기 위한 moveItem
    @Override
    public boolean moveIten(int fromPostion,int toPosition){
        String text = simpleTextList.get(fromPostion); // 움직이고자 하는 데이터 보관
        simpleTextList.remove(fromPostion); // from 항목에 해당하는 list를 지움
        simpleTextList.add(toPosition, text); // text를 toPosition에 해당하는 위치에 저장
        notifyItemMoved(fromPostion, toPosition);
        // 리사이클러 뷰에도 이동했다는 사실을 알려서 실제 ViewHolder를 세로운 데이터로 바인딩하게 알려줘야함
        return true;
    }

    // 아이템이 삭제될때 호출되는 removeItem
    @Override
    public void removeItem(int position) {
        simpleTextList.remove(position);
        notifyItemRemoved(position);
    }

    public class SimpleTextViewHolder extends RecyclerView.ViewHolder implements GestureDetector.OnGestureListener{
        private TextView simpleText;
        private GestureDetector gestureDetector; // viewHolder를 롱 클릭하지않고 바로 DragHandle을 바로 적용하기 위해 생성

        public SimpleTextViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleText = itemView.findViewById(R.id.simple_text);
            gestureDetector = new GestureDetector(itemView.getContext(),this);
            itemView.findViewById(R.id.drag_handle).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return gestureDetector.onTouchEvent(event);
                }
            });
            itemView.findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(getAdapterPosition());
                }
            });
        }

        public void bind(String text) {
            simpleText.setText(text);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            onStartDragListener.OnStartDrag(this);
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

    public interface OnStartDragListener {
        void OnStartDrag(RecyclerView.ViewHolder viewHolder);
    }
}
