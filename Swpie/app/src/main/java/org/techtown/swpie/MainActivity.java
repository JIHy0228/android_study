package org.techtown.swpie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.graphics.Color;
import android.os.Bundle;

import static android.graphics.Color.LTGRAY;

public class MainActivity extends AppCompatActivity implements SimpleTextAdapter.OnStartDragListener{
    private SimpleTextAdapter adapter;
    private ItemTouchHelper itemTouchHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView movieListView = findViewById(R.id.movie_list);

        // set Layout manager
        movieListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        movieListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // set adapter
        adapter = new SimpleTextAdapter(MovieData.getMovieList(), this);
        movieListView.setAdapter(adapter);

         itemTouchHelper = new ItemTouchHelper(
                /*new ItemTouchHelper.SimpleCallback
                (ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.START|ItemTouchHelper.END)
         // 드레그 방향 위 아래 스와이프 방향 좌 우
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return adapter.moveIten(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.removeItem(viewHolder.getAdapterPosition());
            }

            @Override
            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);

                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG){
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
            }

            @Override
            public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.WHITE);
            }
        }*/
        new SimpleTextItemTouchHelper(adapter));
        //itmeTouchHelper 객체에 attachToRecyclerView를 통해 드레그와 스와이프의 대상이 될 리사이클러뷰를 붙임
        itemTouchHelper.attachToRecyclerView(movieListView);
    }

    @Override
    public void OnStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}