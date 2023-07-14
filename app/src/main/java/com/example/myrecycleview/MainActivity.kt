package com.example.myrecycleview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvManhwa: RecyclerView
    private val list = ArrayList<Manhwa>()

    override     fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvManhwa = findViewById(R.id.rv_manhwa)
        rvManhwa.setHasFixedSize(true)

        list.addAll(getListManhwa())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about -> {
                val  aboutIntent =  Intent(this@MainActivity, About::class.java)
                startActivity(aboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListManhwa() : ArrayList<Manhwa>{
        // mengambil data dari sumber data, dalam kasus ini akan mengambil dari string.xml
        val dataName = resources.getStringArray(R.array.manhwa_data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataYear = resources.getStringArray(R.array.data_year)
        val dataRate = resources.getStringArray(R.array.data_rate)

        val listManhwa = ArrayList<Manhwa>()
        // dimasukkan kedalam array baru yaitu listManhwa, dimana komposisi nya sudah kita tentukan
        // yaitu data class Manhwa
        for(i in dataName.indices){
            val manhwa = Manhwa(dataName[i], dataDescription[i], dataPhoto[i], dataGenre[i], dataYear[i], dataRate[i])
            listManhwa.add(manhwa)
        }

        return listManhwa
    }

    private fun showRecyclerList(){
        rvManhwa.layoutManager = LinearLayoutManager(this)
        val listManhwaAdapter = ListManhwaAdapter(list)
        rvManhwa.adapter = listManhwaAdapter

        listManhwaAdapter.setOnItemClickCallback(object : ListManhwaAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Manhwa) {

                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra(DetailActivity.EXTRA_DATA,data)
                startActivity(intentToDetail)

            }
        })
    }


}