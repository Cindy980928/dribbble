package com.xiaoding.Ambled.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xiaoding.Ambled.R
import com.xiaoding.Ambled.adapter.RecyclerViewAdapter
import com.xiaoding.Ambled.bean.ShotMessage
import com.xiaoding.Ambled.utils.Logger
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.io.File


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

        jsonToList()

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

    fun jsonToList():ArrayList<ShotMessage>{
        val shotJson = "[{\"src\":\"https://cdn.dribbble.com/users/2146089/screenshots/6791385/landing_app_f.png\",\"username\":\"Afterglow\",\"shotid\":\"6791385\",\"title\":\"Landing Page - Delicious\"},{\"src\":\"https://cdn.dribbble.com/users/1803663/screenshots/6792624/f.png\",\"username\":\"Febin_Raj\",\"shotid\":\"6792624\",\"title\":\"Windmill Bukovany\"},{\"src\":\"https://cdn.dribbble.com/users/14268/screenshots/6787909/icons.png\",\"username\":\"◒ Unfold | Eddie\",\"shotid\":\"6787909\",\"title\":\"icon set\"},{\"src\":\"https://cdn.dribbble.com/users/688456/screenshots/6788127/strike-chair.png\",\"username\":\"Nathan Riley\",\"shotid\":\"6788127\",\"title\":\"Interactive Product Header\"},{\"src\":\"https://cdn.dribbble.com/users/702789/screenshots/6790895/new_dashboard_3.png\",\"username\":\"Outcrowd\",\"shotid\":\"6790895\",\"title\":\"Dashboard - DreamDom\"},{\"src\":\"https://cdn.dribbble.com/users/1790221/screenshots/6791797/gamely____2.png\",\"username\":\"Brave Wings \uD83D\uDD25\",\"shotid\":\"6791797\",\"title\":\"Find a Team for Sport Game – Web Service\"},{\"src\":\"https://cdn.dribbble.com/users/14268/screenshots/6793381/pantene.png\",\"username\":\"◒ Unfold | Eddie\",\"shotid\":\"6793381\",\"title\":\"Pantene\"},{\"src\":\"https://cdn.dribbble.com/users/150724/screenshots/6793830/000.png\",\"username\":\"Marko Stupic\",\"shotid\":\"6793830\",\"title\":\"Alone\"},{\"src\":\"https://cdn.dribbble.com/users/1579320/screenshots/6789910/trip_planner_animation_-_ugem.png\",\"username\":\"UGEM\",\"shotid\":\"6789910\",\"title\":\"Trip Planner Interaction\"},{\"src\":\"https://cdn.dribbble.com/users/45617/screenshots/6789088/dribbb-10.jpg\",\"username\":\"◒ Unfold | David\",\"shotid\":\"6789088\",\"title\":\"Dribbble 10\"},{\"src\":\"https://cdn.dribbble.com/users/903897/screenshots/6794883/shot.png\",\"username\":\"Yi Li\",\"shotid\":\"6794883\",\"title\":\"Trip app - Mark spots\"},{\"src\":\"https://cdn.dribbble.com/users/1972298/screenshots/6785635/2.png\",\"username\":\"Asha Rajput\",\"shotid\":\"6785635\",\"title\":\"Lampster app\"},{\"src\":\"https://cdn.dribbble.com/users/63973/screenshots/6788304/avasam-1.png\",\"username\":\"Aiste\",\"shotid\":\"6788304\",\"title\":\"avasam - logo design\"},{\"src\":\"https://cdn.dribbble.com/users/3798628/screenshots/6793136/saga_team_reel.png\",\"username\":\"Saga Team\",\"shotid\":\"6793136\",\"title\":\"Saga Team: UX Reel\"},{\"src\":\"https://cdn.dribbble.com/users/2566795/screenshots/6794887/__666.jpg\",\"username\":\"\uD835\uDD38\uD835\uDD6A\",\"shotid\":\"6794887\",\"title\":\"Personal Center\"},{\"src\":\"https://cdn.dribbble.com/users/23569/screenshots/6792860/k16_1.jpg\",\"username\":\"Emir Ayouni\",\"shotid\":\"6792860\",\"title\":\"King Sixteen - Responsive Rebranding (1/2)\"},{\"src\":\"https://cdn.dribbble.com/users/1178585/screenshots/6788672/__1_.png\",\"username\":\"林沐夕\",\"shotid\":\"6788672\",\"title\":\"summer\"},{\"src\":\"https://cdn.dribbble.com/users/1999542/screenshots/6794948/__.png\",\"username\":\"Jerevon\",\"shotid\":\"6794948\",\"title\":\"Healthy life Collection\"},{\"src\":\"https://cdn.dribbble.com/users/2417352/screenshots/6790276/terraforge__1_.png\",\"username\":\"Lisa Parker\",\"shotid\":\"6790276\",\"title\":\"3D Model Store & School Home Page\"},{\"src\":\"https://cdn.dribbble.com/users/345970/screenshots/6765326/shot_1.png\",\"username\":\"Ghani Pradita\",\"shotid\":\"6765326\",\"title\":\"Gladient Iconset for Android\"},{\"src\":\"https://cdn.dribbble.com/users/160641/screenshots/6790564/color3.png\",\"username\":\"Elen Winata\",\"shotid\":\"6790564\",\"title\":\"Under The Sea\"},{\"src\":\"https://cdn.dribbble.com/users/1493913/screenshots/6794936/16_____.gif\",\"username\":\"Sansheng\",\"shotid\":\"6794936\",\"title\":\"TAL Happy birthday!\"},{\"src\":\"https://cdn.dribbble.com/users/4859/screenshots/6790575/sbla_dr.png\",\"username\":\"Cuberto\",\"shotid\":\"6790575\",\"title\":\"Beauty Lounge Landing Page\"},{\"src\":\"https://cdn.dribbble.com/users/2461751/screenshots/6767550/jamaican.png\",\"username\":\"Sudhanshu Tiwari\",\"shotid\":\"6767550\",\"title\":\"The Great Jamaican App (BEER)\"}, {\"src\":\"https://cdn.dribbble.com/users/2146089/screenshots/6791385/landing_app_f.png\",\"username\":\"Afterglow\",\"shotid\":\"6791385\",\"title\":\"Landing Page - Delicious\"},{\"src\":\"https://cdn.dribbble.com/users/1803663/screenshots/6792624/f.png\",\"username\":\"Febin_Raj\",\"shotid\":\"6792624\",\"title\":\"Windmill Bukovany\"}, {\"src\":\"https://cdn.dribbble.com/users/2146089/screenshots/6791385/landing_app_f.png\",\"username\":\"Afterglow\",\"shotid\":\"6791385\",\"title\":\"Landing Page - Delicious\"},{\"src\":\"https://cdn.dribbble.com/users/1803663/screenshots/6792624/f.png\",\"username\":\"Febin_Raj\",\"shotid\":\"6792624\",\"title\":\"Windmill Bukovany\"},{\"src\":\"https://cdn.dribbble.com/users/14268/screenshots/6787909/icons.png\",\"username\":\"◒ Unfold | Eddie\",\"shotid\":\"6787909\",\"title\":\"icon set\"},{\"src\":\"https://cdn.dribbble.com/users/688456/screenshots/6788127/strike-chair.png\",\"username\":\"Nathan Riley\",\"shotid\":\"6788127\",\"title\":\"Interactive Product Header\"},{\"src\":\"https://cdn.dribbble.com/users/702789/screenshots/6790895/new_dashboard_3.png\",\"username\":\"Outcrowd\",\"shotid\":\"6790895\",\"title\":\"Dashboard - DreamDom\"}]"
        lateinit var newshot: ArrayList<ShotMessage>
        val listType = object : TypeToken<ArrayList<ShotMessage>>() {}.type
        newshot= Gson().fromJson(shotJson, listType)
//        Logger.d("newshot","is"+newshot.toString())
        Logger.d("newshot","is"+newshot[1].src)

        return newshot
    }


}



