package com.example.nyt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nyt.FakeDatabase;
import com.example.nyt.R;
import com.example.nyt.model.Article;
import com.example.nyt.model.Favourite;

public class CatDetailActivity extends AppCompatActivity {
    private TextView headlineTextView;
    private TextView authorTextView;
    private TextView contentTextView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private Button addToFavourites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        // Get the intent that was used to travel to this activity
        Intent intent = getIntent();

        String articleID = intent.getStringExtra("ArticleID");

        // This will now work because first we added the articles that resulted from the Gson
        // conversion to the FakeDatabase.
        Article article = FakeDatabase.getArticleById(articleID);

        headlineTextView = findViewById(R.id.detailHeadline);
        authorTextView = findViewById(R.id.detailAuthor);
        contentTextView = findViewById(R.id.detailContent);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        addToFavourites = findViewById(R.id.addToFavourite);

        headlineTextView.setText(article.getName());
        authorTextView.setText("Origin: " + article.getOrigin());
        textView1.setText("Life Span: " + article.getLife_span());
        textView2.setText(" Dog Friendly (1-5): " + article.getDog_friendly());
        textView3.setText("Wikipedia URL: " + article.getWikipedia_url());
        textView4.setText("ID of Breed: " + article.getId());
        contentTextView.setText("Temperament: " + article.getTemperament());

        addToFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFavouritesActivity();
                Toast.makeText(getApplicationContext(),"Cat Breed added to Favourites, return back home",Toast.LENGTH_LONG).show();
            }
        });

        // Setting the image.
        // Notice that the image we get is very blurry. This is because we've just selected the
        // first link in the JSON (by using index 0 after getMedia()). You could think of a way to
        // write a method that allows you to get the biggest image out of the array of images.

    }
    public void addToFavouritesActivity() {

        String name = (String) headlineTextView.getText();
        String origin = (String) authorTextView.getText();


        MainActivity.favouriteList.add(new Favourite(name, origin));
        System.out.println("Item added");


    }
}
