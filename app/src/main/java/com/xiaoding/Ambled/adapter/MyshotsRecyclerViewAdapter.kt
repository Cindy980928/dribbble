package com.xiaoding.Ambled.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.xiaoding.Ambled.R
import com.xiaoding.Ambled.api.entity.MyshotList
import com.xiaoding.Ambled.ui.MainActivity
import com.xiaoding.Ambled.ui.MyshotsActivity

class MyshotsRecyclerViewAdapter : RecyclerView.Adapter<MyshotsRecyclerViewAdapter.MyshotViewHolder>{
    private var context: Context?=null
    private var data:ArrayList<MyshotList>?=null
    constructor(context: Context, data:ArrayList<MyshotList>){
        this.context=context
        this.data=data
    }
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int): MyshotViewHolder {
        var holder: MyshotViewHolder
        holder= MyshotViewHolder(LayoutInflater.from(context).inflate(R.layout.myshots_item, parent, false))
        return holder
    }

    override fun getItemCount(): Int {
        return this.data!!.size
    }

    override fun onBindViewHolder(holder: MyshotViewHolder, position: Int) {
        Glide.with(context!!)
                .load(data!!.get(position).images!!.hidpi.toString())
                .into(holder.myshotImg!!)
//
        holder.myshotTitle!!.setText(data!!.get(position).title)
        holder.myshotDesc!!.setText(data!!.get(position).description!!.replace("<p>", "").replace("</p>",""))
        holder.timeView!!.setText(data!!.get(position).publishedAt)
        holder.myshotImg!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var intent= Intent(context,MainActivity::class.java)
                intent.putExtra("shotId",data!!.get(position).id)
                context!!.startActivity(intent)
            }
        })
    }


    class MyshotViewHolder:RecyclerView.ViewHolder{
        var myshotImg:ImageView?=null
        var myshotTitle:TextView?=null
        var myshotDesc:TextView?=null
        var timeView:TextView?=null

        constructor(itemView:View):super(itemView){
            myshotImg=itemView.findViewById(R.id.myshotImg)
            myshotTitle=itemView.findViewById(R.id.myshotTitle)
            myshotDesc=itemView.findViewById(R.id.myshotDesc)
            timeView=itemView.findViewById(R.id.publishTime)

        }

    }
}