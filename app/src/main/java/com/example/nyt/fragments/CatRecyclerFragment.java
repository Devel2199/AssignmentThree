package com.example.nyt.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nyt.CatAdapter;
import com.example.nyt.FakeDatabase;
import com.example.nyt.activities.MainActivity;
import com.example.nyt.R;
import com.example.nyt.model.Article;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;


public class CatRecyclerFragment extends Fragment {

    private RecyclerView recyclerView;
    public EditText theText;
    public Button button;

    public CatRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        theText = view.findViewById(R.id.searchFilter);
        button = view.findViewById(R.id.button);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        // REFER to the comments in BookRecyclerAdapter

        final CatAdapter catAdapter = new CatAdapter();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://api.thecatapi.com/v1/breeds";
        System.out.println("Hi how are you");



        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Article[] article = gson.fromJson(response, Article[].class);
                        List<Article> articleCat = Arrays.asList(article);
                        catAdapter.setData(articleCat);
                        recyclerView.setAdapter(catAdapter);
                        // We have reworked FakeDatabase to act as a place to store these Articles, such that we
                        // can access them via their ID. This will allow our intents to the DetailView to keep
                        // functioning.
                        FakeDatabase.saveArticlesToFakeDatabase(articleCat);
                        System.out.println("this is the onresponse");
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("Error");
            }
        });
        queue.add(stringRequest);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSearchActivity();
            }
        });
        return view;





    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity parent = (MainActivity) getActivity();
        parent.showCoolMessage("cool (from CatRecyclerFragment onResume)");
    }

    public void newSearchActivity() {
        final CatAdapter catAdapter = new CatAdapter();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String addQ = theText.getText().toString();
        String url2 = "https://api.thecatapi.com/v1/breeds/search?q=" + addQ;
        System.out.println(addQ);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Article[] article = gson.fromJson(response, Article[].class);
                        List<Article> articleCat = Arrays.asList(article);
                        catAdapter.setData(articleCat);
                        recyclerView.setAdapter(catAdapter);
                        // We have reworked FakeDatabase to act as a place to store these Articles, such that we
                        // can access them via their ID. This will allow our intents to the DetailView to keep
                        // functioning.
                        FakeDatabase.saveArticlesToFakeDatabase(articleCat);
                        System.out.println("this is the onresponse");
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("Error");
            }
        });
        queue.add(stringRequest);
    }

}
