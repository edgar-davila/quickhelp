package com.omsuperg.quickhelp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.omsuperg.quickhelp.adapters.CommentsAdapter;
import com.omsuperg.quickhelp.models.CommentModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsActivity extends AppCompatActivity implements CommentsAdapter.CommentsListener {
    CommentsAdapter commentsAdapter;
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private List<CommentModel> anuncianteComments = new ArrayList<>();
    private List<CommentModel> postulanteComments = new ArrayList<>();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_anunciante:
                    commentsAdapter.setItems(anuncianteComments);
                    return true;
                case R.id.navigation_postulante:
                    commentsAdapter.setItems(postulanteComments);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        anuncianteComments.add(CommentModel.getRandom());
        anuncianteComments.add(CommentModel.getRandom());
        postulanteComments.add(CommentModel.getRandom());
        postulanteComments.add(CommentModel.getRandom());
        postulanteComments.add(CommentModel.getRandom());

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        commentsAdapter = new CommentsAdapter(CommentsActivity.this, CommentsActivity.this, anuncianteComments);
        recyclerView.setAdapter(commentsAdapter);
    }

    @Override
    public void onClick(CommentModel commentModel) {

    }
}
