package com.example.recyclerviewwithjson

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    var animes:MutableList<Anime> = ArrayList()
    lateinit var context:Context

    fun RecyclerAdapter(animes: MutableList<Anime>, context: Context){
        this.animes = animes
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_anime,parent,false))
    }

    override fun getItemCount(): Int {
        return animes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = animes.get(position)
        holder.bind(item,context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val idAnime = view.findViewById<TextView>(R.id.tvId)
        val nameAnime = view.findViewById<TextView>(R.id.tvNombre)
        val photoAnime = view.findViewById<TextView>(R.id.tvPhoto)
        val instrAnime = view.findViewById<TextView>(R.id.tvInstruccs)
        val priceAnime = view.findViewById<TextView>(R.id.tvPrice)
        val categoryAnime = view.findViewById<TextView>(R.id.tvCategory)

        fun bind(animes:Anime, context: Context){
            idAnime.text = animes.productId.toString()
            nameAnime.text = animes.name
            photoAnime.text = animes.photo
            instrAnime.text = animes.instructions
            priceAnime.text = animes.price.toString()
            categoryAnime.text = animes.category
            itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context,animes.name,Toast.LENGTH_SHORT).show() })
        }
    }

}