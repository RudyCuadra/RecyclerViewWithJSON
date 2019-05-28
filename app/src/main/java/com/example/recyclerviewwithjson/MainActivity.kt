package com.example.recyclerviewwithjson

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    val mAdapter:RecyclerAdapter = RecyclerAdapter()
    lateinit var json:String
    lateinit var animeDataList: List<Anime>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = Gson()
        json = loadData("data.json")
        Log.d("JSON", json)
        animeDataList = gson.fromJson(json, Array<Anime>::class.java).toList()

        /*for((index) in animeDataList.withIndex()){
            println(animeDataList[index].toString())
        }*/

        setUpRecyclerView()
    }

    fun setUpRecyclerView(){
        mRecyclerView = findViewById<RecyclerView>(R.id.rvAnimes)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapter(getAnimes(),this)
        mRecyclerView.adapter = mAdapter
    }

    fun getAnimes() : MutableList<Anime>{
        var animes : MutableList<Anime> = ArrayList()

        for((index) in animeDataList.withIndex()){
            animes.add(Anime(animeDataList[index].productId,animeDataList[index].name,animeDataList[index].photo,animeDataList[index].instructions, animeDataList[index].price,animeDataList[index].category))
        }

        //animes.add(Anime(1,"Nombre","Photo","Instrucciones", 1.2F,"Categoria"))
        return animes
    }

    fun loadData(inFile: String) : String{
        var tContents = ""

        try{
            val stream = assets.open(inFile)

            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            tContents = String(buffer)
        }catch (e: IOException){

        }
        return tContents
    }
}
