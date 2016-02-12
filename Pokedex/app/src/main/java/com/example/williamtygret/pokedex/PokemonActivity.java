package com.example.williamtygret.pokedex;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;

public class PokemonActivity extends AppCompatActivity {
    private int mId;


    Typeface mPokemonFont;
    ImageLoader mImageLoader;
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        mPokemonFont = Typeface.createFromAsset(getAssets(),"Pokemon GB.ttf");


        //Looked up how to create a Volley Network image view to display images from the internet
        mRequestQueue = Volley.newRequestQueue(PokemonActivity.this);
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });


        //gets the info from my database and plug it into my pokemon activity display
        int id = getIntent().getIntExtra("id",-1);
        mId=id;
        if(id >= 0){
            PokemonDatabaseHelper dbh = PokemonDatabaseHelper.getInstance(PokemonActivity.this);
            String name = dbh.getPokemonName(id);
            TextView textView = (TextView)findViewById(R.id.pokemonName);
            textView.setTypeface(mPokemonFont);
            textView.setText(name);
        }
        if(id >= 0){
            PokemonDatabaseHelper dbh = PokemonDatabaseHelper.getInstance(PokemonActivity.this);
            String description = dbh.getPokemonDescription(id);
            TextView textView = (TextView)findViewById(R.id.pokemonDescription);
            textView.setTypeface(mPokemonFont);
            textView.setText(description);
        }
        if(id >= 0){
            PokemonDatabaseHelper dbh = PokemonDatabaseHelper.getInstance(PokemonActivity.this);
            String hp = dbh.getPokemonHp(id);
            TextView textView = (TextView)findViewById(R.id.hpText);
            textView.setTypeface(mPokemonFont);
            textView.setText("HP:"+hp);
        }
        Log.d("findtype","got here");
        if(id >= 0){
            PokemonDatabaseHelper dbh = PokemonDatabaseHelper.getInstance(PokemonActivity.this);
            String type = dbh.getPokemonType(id);
            Log.d("findtype","the type being returned is: "+type);
            TextView textView = (TextView)findViewById(R.id.pokemonType);
            textView.setTypeface(mPokemonFont);
            textView.setText(type+" Type");

            //changes the text color of the pokemon type. each type has a specific color
            if(type.equals("Fire")) {
                textView.setTextColor(Color.rgb(178,34,34));
            }
            if(type.equals("Water")){
                textView.setTextColor(Color.rgb(0,0,205));
            }
            if(type.equals("Electric")){
                textView.setTextColor(Color.rgb(218,165,32));
            }
            if(type.equals("Grass")){
                textView.setTextColor(Color.rgb(51,102,0));
            }
            if(type.equals("Psychic")){
                textView.setTextColor(Color.rgb(75,0,130));
            }
            if(type.equals("Dragon")){
                textView.setTextColor(Color.rgb(0,128,128));
            }
            if(type.equals("Ice")){
                textView.setTextColor(Color.rgb(70,130,180));
            }
            if(type.equals("Rock")){
                textView.setTextColor(Color.rgb(105,105,105));
            }
            if(type.equals("Normal")){
                textView.setTextColor(Color.rgb(214,121,100));
            }
            if(type.equals("Bug")){
                textView.setTextColor(Color.rgb(104,142,35));
            }
            if(type.equals("Poison")){
                textView.setTextColor(Color.rgb(255,0,255));
            }
            if(type.equals("Fighting")){
                textView.setTextColor(Color.rgb(204,102,0));
            }
            if(type.equals("Ground")){
                textView.setTextColor(Color.rgb(102,51,0));
            }
            if(type.equals("Flying")){
                textView.setTextColor(Color.rgb(51,153,255));
            }
            if(type.equals("Ghost")){
                textView.setTextColor(Color.rgb(153,51,152));
            }
            if(type.equals("Fairy")){
                textView.setTextColor(Color.rgb(255,51,153));
            }
        }

        //pulls the image from the internet, I was able to assign a column in my database to correlate with the addresses of the images
        PokemonDatabaseHelper dbh = PokemonDatabaseHelper.getInstance(PokemonActivity.this);
            String img = dbh.getPokemonImg(id);
        NetworkImageView pokeImage = (NetworkImageView)findViewById(R.id.networkImageView);
        String url="http://assets.pokemon.com/assets/cms2/img/pokedex/full/"+img+".png";
        Log.d("urlimg","the url is: "+url);
        pokeImage.setImageUrl(url,VolleySingleton.getInstance(PokemonActivity.this).getImageLoader());


        //android made this for me which is SO TITE
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.getBackgroundTintList();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            //updates the database column COL_IS_FAVORITED to make it favorited and a 0 to unfavorite
                PokemonDatabaseHelper dbh = PokemonDatabaseHelper.getInstance(PokemonActivity.this);
                int isFavorited=dbh.getPokemonFavorited(PokemonActivity.this.mId);
                int newValue=0;
                if(isFavorited<=0){
                    newValue=1;
                    Snackbar.make(view, "Pokemon was Caught!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    Snackbar.make(view, "Pokemon was Released!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                String idPoke = Integer.toString(PokemonActivity.this.mId);
                Log.d("PokemonActvity","The id of the poke is "+idPoke);
                dbh.update(idPoke,newValue);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent();
        setResult(Activity.RESULT_OK,in);
        finish();
    }
}
