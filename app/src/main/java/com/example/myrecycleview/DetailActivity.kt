package com.example.myrecycleview


import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailDesc: TextView = findViewById(R.id.detail_desc)
        val detailTitle : TextView = findViewById(R.id.detail_title)
        val detailGenre : TextView = findViewById(R.id.detail_genre)
        val detailYear : TextView = findViewById(R.id.detail_year)
        val detailRate : TextView = findViewById(R.id.detail_rate)
        val detailImage : ImageView = findViewById(R.id.detail_img)



        val user = if(Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(EXTRA_DATA, Manhwa::class.java)

        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if (user!=null){
            detailDesc.text = user.description
            detailTitle.text = user.name
            detailGenre.text = user.genre
            detailYear.text = user.year
            detailRate.text = user.rate

            Glide.with(this)
                .load(user.photo)
                .into(detailImage)
        }

    }
}