package com.xiaoding.Ambled.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import com.xiaoding.Ambled.R
import com.xiaoding.Ambled.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {
    var navigationView: NavigationView? =null
    var recyclerView:RecyclerView?=null
    var adapter:RecyclerViewAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationView=findViewById<NavigationView>(R.id.nav_view)
        navigationView!!.setItemIconTintList(null)//让侧滑菜单项显示本身颜色

        setSupportActionBar(toolbar)
        /*显示Home图标*/
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val mToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, 0, 0)
        drawer_layout.addDrawerListener(mToggle)
        /*同步drawerlayout的状态*/
        mToggle.syncState()
        supportActionBar?.setDisplayShowTitleEnabled(false)


//        nav_view.setNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.nav_shot->{
//                    drawer_layout.closeDrawer(GravityCompat.START)
//                }
//                R.id.nav_like->{
//
//                }
//                R.id.nav_follow->{
//
//                }
//                R.id.nav_logout->{
//
//                }
//                true
//            }
//
//        }
        recyclerView=findViewById(R.id.recyclerView)
        adapter=RecyclerViewAdapter(this,createData())
        recyclerView!!.setLayoutManager(StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL))
        recyclerView!!.setAdapter(adapter)


    }
    fun createData():List<Int>{
        var data:List<Int>
        data=ArrayList<Int>()
        for(i in 1..100){
            if(i%2==0){
                data.add(R.drawable.login)
            }
            else{
                data.add(R.drawable.login)
            }
        }
        return data
    }

}