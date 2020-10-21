package com.example.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), newsItemClicked {

    private lateinit var mAdapter: newsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
            fetchData()
        mAdapter = newsListAdapter( this )
        recyclerView.adapter= mAdapter


    }

    private fun fetchData() {
        val url ="https://newsapi.org/v2/top-headlines?country=in&apiKey=7bcfb219c26b4906853159e744a9487e"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                   val newsJsonArray = it.getJSONArray("articles")
                   val newsArray = ArrayList<News>()
                   for (i in 0 until newsJsonArray.length()){
                       val newsJsonObject = newsJsonArray.getJSONObject(i)
                       val news = News(
                           newsJsonObject.getString("title"),
                           newsJsonObject.getString("author"),
                           newsJsonObject.getString("url"),
                           newsJsonObject.getString("urlToImage")

                       )
                       newsArray.add(news)
                   }
                mAdapter.updatedNews(newsArray)
            },
            Response.ErrorListener {

            }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)


    }

    override fun onItemclicked(item: News) {

    }




}