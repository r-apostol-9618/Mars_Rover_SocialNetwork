package com.example.nasa_project.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.nasa_project.R
import com.example.nasa_project.data.model.ModelRoverCuriosity
import com.example.nasa_project.data.model.Photo
import kotlinx.android.synthetic.main.item_view_rover.view.*

class MyAdapterOpportunityRover(private val dataListOpportunityRover:List<Photo>) :
    RecyclerView.Adapter<OpportunityRoverViewHolder>() {

    private lateinit var context:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpportunityRoverViewHolder {
        context = parent.context
        return OpportunityRoverViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view_rover,parent,false))
    }

    override fun getItemCount(): Int = dataListOpportunityRover.size

    override fun onBindViewHolder(holder: OpportunityRoverViewHolder, position: Int) {
        val data = dataListOpportunityRover[position]
        // Gestione CircularProgressDrawable
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        holder.itemView.userNameText.text = data.rover?.name
        holder.itemView.userCommentText.text = "Foto scattata dalla camera "+data.camera?.full_name
        Glide.with(context.applicationContext).load(data.img_src).apply(

            RequestOptions().fitCenter()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(Target.SIZE_ORIGINAL)
                .placeholder(circularProgressDrawable)
                .error(R.drawable.ic_connection_error)

        ).into(holder.itemView.imageView_APOD)

    }
}

class OpportunityRoverViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)