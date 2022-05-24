package com.practice.androidboilerplate.retrofit

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.practice.androidboilerplate.R
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.retrofit_practice)
        textView = findViewById<TextView>(R.id.tv_text)
        val retrofitInstance =
            RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)


        var postResponse: LiveData<Response<AlbumsItem>> = liveData {
            var response = retrofitInstance.uploadAlbum(AlbumsItem(101, "Test", 3))
            emit(response)
        }
        postResponse.observe(this) {
            var response = it.body()

            textView.text = "${response?.id} ${response?.title} ${response?.userId} "
        }

        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retrofitInstance.getSortedAlbums(3)
            emit(response)

        }
        responseLiveData.observe(this) {
            val albums = it.body()?.listIterator()
            if (albums != null) {
                while (albums.hasNext()) {

                    val album = albums.next()
                    var result = "${album.id}\n ${album.title}\n\n"
                    //  textView.append(result)

                }

            }
        }

        val pathResponse: LiveData<Response<AlbumsItem>> = liveData {
            var response = retrofitInstance.getAlbumBuId(3)
            emit(response)
        }

        pathResponse.observe(this) {
            val title = it.body()?.title
            //  Toast.makeText(this,title,Toast.LENGTH_LONG).show()
        }
    }
}