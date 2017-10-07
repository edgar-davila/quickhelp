package com.omsuperg.quickhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.omsuperg.quickhelp.adapters.PeticionAdapter;
import com.omsuperg.quickhelp.models.PeticionModel;
import com.omsuperg.quickhelp.models.StatusModel;
import com.omsuperg.quickhelp.transforms.CircleTransform;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PeticionAdapter.PeticionListener {

    private static final String TAG = "MainActivity";
    ImageView imageViewFacebookProfile;
    TextView textviewNameProfile;
    TextView textviewEmailProfile;
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    private AccessToken accessToken;
    private RecyclerView.LayoutManager mLayoutManager;

    private PeticionAdapter peticionAdapter;

    private List<PeticionModel> peticionModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, NewPetitionActivity.class), Constants.NEW_PETITION_CODE);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerMenuDrawable = navigationView.getHeaderView(0);
        imageViewFacebookProfile = headerMenuDrawable.findViewById(R.id.imageViewFacebookProfile);
        textviewNameProfile = headerMenuDrawable.findViewById(R.id.textviewNameProfile);
        textviewEmailProfile = headerMenuDrawable.findViewById(R.id.textviewEmailProfile);

        this.accessToken = getIntent().getParcelableExtra("TokenSession");
        Log.d(TAG, "onCreate: " + this.accessToken.toString());

        getProfileFromFacebook();

        peticionModelList = new ArrayList<>();
        peticionModelList.add(
                new PeticionModel(1, "472567553116840", "Necesito un Redactor", "Redaccion",
                        "Se necesita una persona con conocimeintos en Ingenieria, para redactar un articulo de una revista.",
                        StatusModel.VISIBLE, 1, new Date()));
        peticionModelList.add(
                new PeticionModel(2, "472567553116840", "Ayuda con mudanza", "Mudanza",
                        "Mudanza de un 3er piso por escaleras.",
                        StatusModel.VISIBLE, 1, new Date()));
        peticionModelList.add(
                new PeticionModel(3, "472567553116840", "Ayuda con programa en Python", "Educacion",
                        "Necesito escribir un programa en python para la facu, manejo de matrices",
                        StatusModel.VISIBLE, 1, new Date()));
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        peticionAdapter = new PeticionAdapter(MainActivity.this, MainActivity.this, peticionModelList);
        recyclerView.setAdapter(peticionAdapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.menuItemLogout) {
            LoginManager.getInstance().logOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else if (id == R.id.menuItemComents) {
            startActivity(new Intent(this, CommentsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void getProfileFromFacebook() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v(TAG, response.toString());

                        try {

                            textviewNameProfile.setText(object.getString("name"));
                            textviewEmailProfile.setText(object.getString("email"));
                            Picasso.with(MainActivity.this)
                                    .load("https://graph.facebook.com/" + object.getString("id") + "/picture?type=large")
                                    .transform(new CircleTransform())
                                    .into(imageViewFacebookProfile);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onClick(PeticionModel peticionModel) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK) && (requestCode == Constants.NEW_PETITION_CODE)) {
            peticionAdapter.add((PeticionModel) data.getParcelableExtra(Constants.PETITION));
        }
    }
}
