package com.xiaoding.Ambled.ui

import android.Manifest
import android.app.DownloadManager
import android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xiaoding.Ambled.R
import com.xiaoding.Ambled.adapter.RecyclerViewAdapter
import com.xiaoding.Ambled.bean.ShotMessage
import com.xiaoding.Ambled.utils.Logger
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.io.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(){
    var navigationView: NavigationView? =null
    var recyclerView:RecyclerView?=null
    var webView:WebView?=null
    var adapter:RecyclerViewAdapter?=null
    var myshotfragment:MyshotFragment?=null
    var imgSrc:String?=null
    var path:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        path=Environment.getExternalStorageDirectory().getPath()+"/DCIM"
        recyclerView=findViewById(R.id.recyclerView)
        adapter=RecyclerViewAdapter(this,createData())
        webView=findViewById(R.id.img)
        navigationView=findViewById<NavigationView>(R.id.nav_view)
        navigationView!!.setItemIconTintList(null)//让侧滑菜单项显示本身颜色

        registerForContextMenu(webView)//给webVeiw注册长按事件

        setSupportActionBar(toolbar)
        /*显示Home图标*/
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val mToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, 0, 0)
        drawer_layout.addDrawerListener(mToggle)
        /*同步drawerlayout的状态*/
        mToggle.syncState()
        supportActionBar?.setDisplayShowTitleEnabled(false)

        webView!!.setWebViewClient(object :WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        })

        //通过接口传递src
        adapter!!.setOnImgClickListener(object : RecyclerViewAdapter.onImgListener {
            override fun onImgClick(src: String) {
                imgSrc=src
                recyclerView!!.setVisibility(View.GONE)
                webView!!.setVisibility(View.VISIBLE)
                webView!!.getSettings().setUseWideViewPort(true);//让webview读取网页设置的viewport，pc版网页
                webView!!.getSettings().setLoadWithOverviewMode(true);
                Logger.d("src",src)
                webView!!.loadUrl(src)
            }
        })


        navigationView!!.setNavigationItemSelectedListener { it->
        var frament:Fragment?=null
            when (it.itemId) {
                R.id.nav_allshots->{

                }
                R.id.nav_upload->{

                }
                R.id.nav_shot->{
                    frament=myshotfragment
                }

                R.id.nav_logout->{

                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

        recyclerView!!.setLayoutManager(StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL))
        recyclerView!!.setAdapter(adapter)


    }

    //重写返回键
    override fun onBackPressed() {
        if(webView!!.visibility==View.VISIBLE){
            webView!!.setVisibility(View.GONE)
            recyclerView!!.setVisibility(View.VISIBLE)
        }
        else{
            finish()
        }

    }

    fun createData():ArrayList<ShotMessage>{
        return jsonToList()
    }

    fun jsonToList():ArrayList<ShotMessage>{
        val shotJson = "[{\"src\":\"https://cdn.dribbble.com/users/2146089/screenshots/6791385/landing_app_f.png\",\"username\":\"Afterglow\",\"shotid\":\"6791385\",\"title\":\"Landing Page - Delicious\"},{\"src\":\"https://cdn.dribbble.com/users/1803663/screenshots/6792624/f.png\",\"username\":\"Febin_Raj\",\"shotid\":\"6792624\",\"title\":\"Windmill Bukovany\"},{\"src\":\"https://cdn.dribbble.com/users/14268/screenshots/6787909/icons.png\",\"username\":\"◒ Unfold | Eddie\",\"shotid\":\"6787909\",\"title\":\"icon set\"},{\"src\":\"https://cdn.dribbble.com/users/688456/screenshots/6788127/strike-chair.png\",\"username\":\"Nathan Riley\",\"shotid\":\"6788127\",\"title\":\"Interactive Product Header\"},{\"src\":\"https://cdn.dribbble.com/users/702789/screenshots/6790895/new_dashboard_3.png\",\"username\":\"Outcrowd\",\"shotid\":\"6790895\",\"title\":\"Dashboard - DreamDom\"},{\"src\":\"https://cdn.dribbble.com/users/1790221/screenshots/6791797/gamely____2.png\",\"username\":\"Brave Wings \uD83D\uDD25\",\"shotid\":\"6791797\",\"title\":\"Find a Team for Sport Game – Web Service\"},{\"src\":\"https://cdn.dribbble.com/users/14268/screenshots/6793381/pantene.png\",\"username\":\"◒ Unfold | Eddie\",\"shotid\":\"6793381\",\"title\":\"Pantene\"},{\"src\":\"https://cdn.dribbble.com/users/150724/screenshots/6793830/000.png\",\"username\":\"Marko Stupic\",\"shotid\":\"6793830\",\"title\":\"Alone\"},{\"src\":\"https://cdn.dribbble.com/users/1579320/screenshots/6789910/trip_planner_animation_-_ugem.png\",\"username\":\"UGEM\",\"shotid\":\"6789910\",\"title\":\"Trip Planner Interaction\"},{\"src\":\"https://cdn.dribbble.com/users/45617/screenshots/6789088/dribbb-10.jpg\",\"username\":\"◒ Unfold | David\",\"shotid\":\"6789088\",\"title\":\"Dribbble 10\"},{\"src\":\"https://cdn.dribbble.com/users/903897/screenshots/6794883/shot.png\",\"username\":\"Yi Li\",\"shotid\":\"6794883\",\"title\":\"Trip app - Mark spots\"},{\"src\":\"https://cdn.dribbble.com/users/1972298/screenshots/6785635/2.png\",\"username\":\"Asha Rajput\",\"shotid\":\"6785635\",\"title\":\"Lampster app\"},{\"src\":\"https://cdn.dribbble.com/users/63973/screenshots/6788304/avasam-1.png\",\"username\":\"Aiste\",\"shotid\":\"6788304\",\"title\":\"avasam - logo design\"},{\"src\":\"https://cdn.dribbble.com/users/3798628/screenshots/6793136/saga_team_reel.png\",\"username\":\"Saga Team\",\"shotid\":\"6793136\",\"title\":\"Saga Team: UX Reel\"},{\"src\":\"https://cdn.dribbble.com/users/2566795/screenshots/6794887/__666.jpg\",\"username\":\"\uD835\uDD38\uD835\uDD6A\",\"shotid\":\"6794887\",\"title\":\"Personal Center\"},{\"src\":\"https://cdn.dribbble.com/users/23569/screenshots/6792860/k16_1.jpg\",\"username\":\"Emir Ayouni\",\"shotid\":\"6792860\",\"title\":\"King Sixteen - Responsive Rebranding (1/2)\"},{\"src\":\"https://cdn.dribbble.com/users/1178585/screenshots/6788672/__1_.png\",\"username\":\"林沐夕\",\"shotid\":\"6788672\",\"title\":\"summer\"},{\"src\":\"https://cdn.dribbble.com/users/1999542/screenshots/6794948/__.png\",\"username\":\"Jerevon\",\"shotid\":\"6794948\",\"title\":\"Healthy life Collection\"},{\"src\":\"https://cdn.dribbble.com/users/2417352/screenshots/6790276/terraforge__1_.png\",\"username\":\"Lisa Parker\",\"shotid\":\"6790276\",\"title\":\"3D Model Store & School Home Page\"},{\"src\":\"https://cdn.dribbble.com/users/345970/screenshots/6765326/shot_1.png\",\"username\":\"Ghani Pradita\",\"shotid\":\"6765326\",\"title\":\"Gladient Iconset for Android\"},{\"src\":\"https://cdn.dribbble.com/users/160641/screenshots/6790564/color3.png\",\"username\":\"Elen Winata\",\"shotid\":\"6790564\",\"title\":\"Under The Sea\"},{\"src\":\"https://cdn.dribbble.com/users/1493913/screenshots/6794936/16_____.gif\",\"username\":\"Sansheng\",\"shotid\":\"6794936\",\"title\":\"TAL Happy birthday!\"},{\"src\":\"https://cdn.dribbble.com/users/4859/screenshots/6790575/sbla_dr.png\",\"username\":\"Cuberto\",\"shotid\":\"6790575\",\"title\":\"Beauty Lounge Landing Page\"},{\"src\":\"https://cdn.dribbble.com/users/2461751/screenshots/6767550/jamaican.png\",\"username\":\"Sudhanshu Tiwari\",\"shotid\":\"6767550\",\"title\":\"The Great Jamaican App (BEER)\"}, {\"src\":\"https://cdn.dribbble.com/users/14268/screenshots/6787909/icons.png\",\"username\":\"◒ Unfold | Eddie\",\"shotid\":\"6787909\",\"title\":\"icon set\"},{\"src\":\"https://cdn.dribbble.com/users/688456/screenshots/6788127/strike-chair.png\",\"username\":\"Nathan Riley\",\"shotid\":\"6788127\",\"title\":\"Interactive Product Header\"},{\"src\":\"https://cdn.dribbble.com/users/702789/screenshots/6790895/new_dashboard_3.png\",\"username\":\"Outcrowd\",\"shotid\":\"6790895\",\"title\":\"Dashboard - DreamDom\"},{\"src\":\"https://cdn.dribbble.com/users/1615730/screenshots/6789531/finance.png\",\"username\":\"Filip Legierski\",\"shotid\":\"6789531\",\"title\":\"TimeNote Desktop App - Finance\"},{\"src\":\"https://cdn.dribbble.com/users/32512/screenshots/6639218/automotive_cluster_by_gleb.png\",\"username\":\"Gleb Kuznetsov✈\",\"shotid\":\"6639218\",\"title\":\"Autonomous driving experience for Seres EV\"},{\"src\":\"https://cdn.dribbble.com/users/56427/screenshots/6789459/kefane_dribbble_1-01.jpg\",\"username\":\"Jacek Janiczak\",\"shotid\":\"6789459\",\"title\":\"Icons set\"},{\"src\":\"https://cdn.dribbble.com/users/1169587/screenshots/6791414/dribbble_commerce_platform.png\",\"username\":\"Yehor Kosinov\",\"shotid\":\"6791414\",\"title\":\"Order page design\"},{\"src\":\"https://cdn.dribbble.com/users/297873/screenshots/6793123/the_tower_v2_dribbble.jpg\",\"username\":\"Nick Matej\",\"shotid\":\"6793123\",\"title\":\"Tower Tarot - Rework\"},{\"src\":\"https://cdn.dribbble.com/users/203446/screenshots/6792959/dribbble_mockup_june_9__1_.png\",\"username\":\"Slava Kornilov\",\"shotid\":\"6792959\",\"title\":\"News\"},{\"src\":\"https://cdn.dribbble.com/users/1864252/screenshots/6794875/wechatimg33.jpeg\",\"username\":\"Blue\",\"shotid\":\"6794875\",\"title\":\"Lots of coupons\"},{\"src\":\"https://cdn.dribbble.com/users/515705/screenshots/6787077/stickers.jpg\",\"username\":\"Leo Natsume\",\"shotid\":\"6787077\",\"title\":\"Huawei | Sticker pack | Kings of the Weekend\"},{\"src\":\"https://cdn.dribbble.com/users/23017/screenshots/6774127/output.png\",\"username\":\"Konrad Kolasa\",\"shotid\":\"6774127\",\"title\":\"126 vs 500\"},{\"src\":\"https://cdn.dribbble.com/users/452635/screenshots/6791704/229.1.pre.png\",\"username\":\"Prakhar Neel Sharma\",\"shotid\":\"6791704\",\"title\":\"Dashboards collection for schedule and monitoring {part II}\"},{\"src\":\"https://cdn.dribbble.com/users/608072/screenshots/6789131/paperpillar_mobile_lp.png\",\"username\":\"Rifai Muhamad\",\"shotid\":\"6789131\",\"title\":\"Paperpillar Mobile Landing Page\"},{\"src\":\"https://cdn.dribbble.com/users/37624/screenshots/6793397/screen_shot_2019-07-16_at_10.36.08_am.png\",\"username\":\"Brandon Land\",\"shotid\":\"6793397\",\"title\":\"Hawt Rodney\"},{\"src\":\"https://cdn.dribbble.com/users/1776107/screenshots/6791575/3.jpg\",\"username\":\"Isa\",\"shotid\":\"6791575\",\"title\":\"The museum interface\"},{\"src\":\"https://cdn.dribbble.com/users/191949/screenshots/6792154/bl_2019_stasis_releases.jpg\",\"username\":\"Emrich Office\",\"shotid\":\"6792154\",\"title\":\"Bottle Logic 2019 Stasis Releases (so far)\"},{\"src\":\"https://cdn.dribbble.com/users/59947/screenshots/6792291/muti-1.jpg\",\"username\":\"MUTI\",\"shotid\":\"6792291\",\"title\":\"Plants\"},{\"src\":\"https://cdn.dribbble.com/users/2091580/screenshots/6794849/cc5.gif\",\"username\":\"\uD835\uDC8B\uD835\uDC8A\uD835\uDC86\uD835\uDC94\",\"shotid\":\"6794849\",\"title\":\"APP List Deletion Effect\"},{\"src\":\"https://cdn.dribbble.com/users/1126935/screenshots/6792375/mobile_ui.png\",\"username\":\"PurpleSnails\",\"shotid\":\"6792375\",\"title\":\"Finance App\"},{\"src\":\"https://cdn.dribbble.com/users/2186832/screenshots/6794964/02.png\",\"username\":\"Yu Long\",\"shotid\":\"6794964\",\"title\":\"Block Chain Payment Information and Market Hot Ranking Page\"},{\"src\":\"https://cdn.dribbble.com/users/1588664/screenshots/6792459/1234.png\",\"username\":\"Dimest\",\"shotid\":\"6792459\",\"title\":\"Reading for ipad\"},{\"src\":\"https://cdn.dribbble.com/users/2285351/screenshots/6789419/dribbble-shot-friendly-3_2x__1_.png\",\"username\":\"Asish Sunny\",\"shotid\":\"6789419\",\"title\":\"UI Components\"},{\"src\":\"https://cdn.dribbble.com/users/2469603/screenshots/6785155/frame_3.png\",\"username\":\"Bakhtiyar \uD83D\uDE31\",\"shotid\":\"6785155\",\"title\":\"Oliver Illustration\"},{\"src\":\"https://cdn.dribbble.com/users/295355/screenshots/6791441/effylogofinal.jpg\",\"username\":\"Jordan Jenkins\",\"shotid\":\"6791441\",\"title\":\"effy logo\"},{\"src\":\"https://cdn.dribbble.com/users/774375/screenshots/6795847/shot.png\",\"username\":\"Vladimir Gruev\",\"shotid\":\"6795847\",\"title\":\"Freelancers’ Bank \uD83C\uDFAF Splash, Overview, Wallet\"},{\"src\":\"https://cdn.dribbble.com/users/1211065/screenshots/6784316/frame_2.8.png\",\"username\":\"Victa Wille\",\"shotid\":\"6784316\",\"title\":\"Banking app\"},{\"src\":\"https://cdn.dribbble.com/users/139104/screenshots/6793820/skeleton_key.gif\",\"username\":\"Joshua Ariza\",\"shotid\":\"6793820\",\"title\":\"Skeleton Key Agency Identity\"},{\"src\":\"https://cdn.dribbble.com/users/1978404/screenshots/6796205/service_design_days-_website_concept.png\",\"username\":\"Alexandr Kotelevets\",\"shotid\":\"6796205\",\"title\":\"Service Design Days Website Concept\"},{\"src\":\"https://cdn.dribbble.com/users/501822/screenshots/6790552/dribbble_a_wq_blog_alternative_version_scroll_15.07.19.mp4.png\",\"username\":\"Zhenya Rynzhuk\",\"shotid\":\"6790552\",\"title\":\"A+WQ Website Alternative Blog Animation\"},{\"src\":\"https://cdn.dribbble.com/users/1051782/screenshots/6788550/mobile-data.jpg\",\"username\":\"JasonGYF\",\"shotid\":\"6788550\",\"title\":\"Mobile Data\"},{\"src\":\"https://cdn.dribbble.com/users/962743/screenshots/6790399/post_-_icons.png\",\"username\":\"Charlie Isslander\",\"shotid\":\"6790399\",\"title\":\"Chicago Lights — Iconography\"},{\"src\":\"https://cdn.dribbble.com/users/121405/screenshots/6785163/matt-anderson-ddg_blog-2019-donations-_2x.png\",\"username\":\"Matt Anderson\",\"shotid\":\"6785163\",\"title\":\"Houseplants – Spread Privacy: Donations\"},{\"src\":\"https://cdn.dribbble.com/users/57127/screenshots/6785656/brandbook_1x.jpg\",\"username\":\"Barthelemy Chalvet\",\"shotid\":\"6785656\",\"title\":\"Brandbook\"},{\"src\":\"https://cdn.dribbble.com/users/60266/screenshots/6793224/illustrations.gif\",\"username\":\"Gustavo Zambelli\",\"shotid\":\"6793224\",\"title\":\"The Guide: illustrations\"},{\"src\":\"https://cdn.dribbble.com/users/400493/screenshots/6790349/musiquest_cowboy_01.gif\",\"username\":\"Latham Arnott\",\"shotid\":\"6790349\",\"title\":\"Cowpoke WIP\"},{\"src\":\"https://cdn.dribbble.com/users/15687/screenshots/6791533/lotr-bilbo.png\",\"username\":\"Roman Klčo\",\"shotid\":\"6791533\",\"title\":\"Bilbo's House\"},{\"src\":\"https://cdn.dribbble.com/users/126876/screenshots/6789874/kotbular2.png\",\"username\":\"Lukas Svarc\",\"shotid\":\"6789874\",\"title\":\"Content Creation - Long Scroll\"},{\"src\":\"https://cdn.dribbble.com/users/388397/screenshots/6790233/wipmagalie2.jpg\",\"username\":\"Mark van Leeuwen\",\"shotid\":\"6790233\",\"title\":\"A work in progress\"},{\"src\":\"https://cdn.dribbble.com/users/2451688/screenshots/6792790/lettering_collection_15___________________1.jpg\",\"username\":\"Yevdokimov Kirill\",\"shotid\":\"6792790\",\"title\":\"Some Lettering Logotypes\"},{\"src\":\"https://cdn.dribbble.com/users/2071468/screenshots/6790604/cocktails.png\",\"username\":\"Andrei Enache\",\"shotid\":\"6790604\",\"title\":\"Cocktail Bar Page\"},{\"src\":\"https://cdn.dribbble.com/users/46461/screenshots/6792511/sitka-kroneberger_snow-goose.jpg\",\"username\":\"Kevin Kroneberger\",\"shotid\":\"6792511\",\"title\":\"Sitka Gear\"},{\"src\":\"https://cdn.dribbble.com/users/44274/screenshots/6790703/codika.png\",\"username\":\"Mateusz Madura\",\"shotid\":\"6790703\",\"title\":\"Codika\"},{\"src\":\"https://cdn.dribbble.com/users/1895731/screenshots/6793947/dribbble-cold-brew-design-designs-agency-can-packaging-drink-florida-hollywood-beach-summer-concept-.jpg\",\"username\":\"Zeki Michael\",\"shotid\":\"6793947\",\"title\":\"Palm Coffee Komodo Cold Brew\"},{\"src\":\"https://cdn.dribbble.com/users/68544/screenshots/6790200/risingsun.png\",\"username\":\"Dalius Stuoka | logo designer\",\"shotid\":\"6790200\",\"title\":\"Rising Sun Church\"},{\"src\":\"https://cdn.dribbble.com/users/1081436/screenshots/6790544/untitled-9.jpg\",\"username\":\"Artur Stotch\",\"shotid\":\"6790544\",\"title\":\"Character: Workplace\"},{\"src\":\"https://cdn.dribbble.com/users/18850/screenshots/6779196/nasa_stamp3_johnmata.png\",\"username\":\"John Mata\",\"shotid\":\"6779196\",\"title\":\"Apollo XI - 50th Anniversary\"},{\"src\":\"https://cdn.dribbble.com/users/230124/screenshots/6787410/n_sketch.jpg\",\"username\":\"Kakha Kakhadzen\",\"shotid\":\"6787410\",\"title\":\"N / Sketch\"},{\"src\":\"https://cdn.dribbble.com/users/1248571/screenshots/6795160/___2.png\",\"username\":\"VikesTan\",\"shotid\":\"6795160\",\"title\":\"Programmer2\"},{\"src\":\"https://cdn.dribbble.com/users/2125046/screenshots/6790427/crypto_currency_app_2x.png\",\"username\":\"Luova Studio\",\"shotid\":\"6790427\",\"title\":\"Cryptocurrency Wallet App\"},{\"src\":\"https://cdn.dribbble.com/users/26952/screenshots/6793461/kona_dribbb.jpg\",\"username\":\"Drew Lakin\",\"shotid\":\"6793461\",\"title\":\"Kona Coffee Purveyors\"},{\"src\":\"https://cdn.dribbble.com/users/1104799/screenshots/6786645/workspace-icons-d-2x.png\",\"username\":\"Vy Tat\",\"shotid\":\"6786645\",\"title\":\"Workspace Icons\"},{\"src\":\"https://cdn.dribbble.com/users/60166/screenshots/6784588/strawberry.jpg\",\"username\":\"Yoga Perdana\",\"shotid\":\"6784588\",\"title\":\"Strawberry\"},{\"src\":\"https://cdn.dribbble.com/users/1012997/screenshots/6792158/neveo-locations.png\",\"username\":\"Iryna Korshak\",\"shotid\":\"6792158\",\"title\":\"Neveo\"},{\"src\":\"https://cdn.dribbble.com/users/92173/screenshots/6791913/reuss_1.png\",\"username\":\"Ruslanlatypov\",\"shotid\":\"6791913\",\"title\":\"Paaatterns!\"},{\"src\":\"https://cdn.dribbble.com/users/45397/screenshots/6785639/travel_2x.png\",\"username\":\"Emre Mazursky\",\"shotid\":\"6785639\",\"title\":\"Itinerary ✈️\"},{\"src\":\"https://cdn.dribbble.com/users/214840/screenshots/6790557/dribbble.png\",\"username\":\"Alan Podemski\",\"shotid\":\"6790557\",\"title\":\"Bubble.is - Redesign & Rethinking Concept\"},{\"src\":\"https://cdn.dribbble.com/users/1061278/screenshots/6791305/dragon.png\",\"username\":\"Koen\",\"shotid\":\"6791305\",\"title\":\"Dragon Logo Design\"},{\"src\":\"https://cdn.dribbble.com/users/914217/screenshots/6788122/1_uber_lite_screens.png\",\"username\":\"Uber Design\",\"shotid\":\"6788122\",\"title\":\"Designed for the world\"},{\"src\":\"https://cdn.dribbble.com/users/1420376/screenshots/6786939/1600x1200__1_.png\",\"username\":\"George Finnbogason\",\"shotid\":\"6786939\",\"title\":\"Virtual AI Asisstant\"},{\"src\":\"https://cdn.dribbble.com/users/153131/screenshots/6789556/one_switch_2x.png\",\"username\":\"Andreas Storm\",\"shotid\":\"6789556\",\"title\":\"One Switch menu bar icon\"},{\"src\":\"https://cdn.dribbble.com/users/1568450/screenshots/6790308/camp_dribbble-01.png\",\"username\":\"Gytis Jonaitis\",\"shotid\":\"6790308\",\"title\":\"Camp icons\"},{\"src\":\"https://cdn.dribbble.com/users/1821976/screenshots/6791326/patterns____figure.jpg\",\"username\":\"Rokas Aleliunas\",\"shotid\":\"6791326\",\"title\":\"Patterns    Figure\"},{\"src\":\"https://cdn.dribbble.com/users/3257356/screenshots/6791841/10fbde81348291.5cfd386543b88.jpg\",\"username\":\"Mohamed Helmy\",\"shotid\":\"6791841\",\"title\":\"Rostom\"},{\"src\":\"https://cdn.dribbble.com/users/79571/screenshots/6791269/branch_2.png\",\"username\":\"Pawel Olek\",\"shotid\":\"6791269\",\"title\":\"Git Branches - Blog post illustration\"},{\"src\":\"https://cdn.dribbble.com/users/1575908/screenshots/6791156/ig2.jpg\",\"username\":\"Matt Wojtaś\",\"shotid\":\"6791156\",\"title\":\"#2 - IG. Dark mode concept.\"},{\"src\":\"https://cdn.dribbble.com/users/997070/screenshots/6752254/10.png\",\"username\":\"Johny vino™\",\"shotid\":\"6752254\",\"title\":\"Voice mail\"},{\"src\":\"https://cdn.dribbble.com/users/131151/screenshots/6793518/drb_cjbowl.png\",\"username\":\"Jacob Cummings\",\"shotid\":\"6793518\",\"title\":\"CJ Bowl-o-rama\"},{\"src\":\"https://cdn.dribbble.com/users/58267/screenshots/6791206/nu-pt3.jpg\",\"username\":\"Salih Küçükağa\",\"shotid\":\"6791206\",\"title\":\"Nü Water pt.3\"},{\"src\":\"https://cdn.dribbble.com/users/1789165/screenshots/6788602/____.png\",\"username\":\"LaisyWang\",\"shotid\":\"6788602\",\"title\":\"Pet Hospital Illustration\"},{\"src\":\"https://cdn.dribbble.com/users/24011/screenshots/6790085/r4ms3s_portfolio.png\",\"username\":\"Jaromir Kavan\",\"shotid\":\"6790085\",\"title\":\"Paralax retrowave portfolio (r4ms3s)\"},{\"src\":\"https://cdn.dribbble.com/users/517502/screenshots/6792022/interstyle_dashboard_dribbble_shot.png\",\"username\":\"Maria Batura\",\"shotid\":\"6792022\",\"title\":\"Interstyle Dashboard\"},{\"src\":\"https://cdn.dribbble.com/users/3333458/screenshots/6789748/my_first_dribble_shot.png\",\"username\":\"Manisha Verma\",\"shotid\":\"6789748\",\"title\":\"My First Dribbble Shot\"},{\"src\":\"https://cdn.dribbble.com/users/2951903/screenshots/6787791/fragrance_store-02.png\",\"username\":\"Shirley Yao\",\"shotid\":\"6787791\",\"title\":\"Fragrance Store 02\"},{\"src\":\"https://cdn.dribbble.com/users/870536/screenshots/6789107/stakeholders.png\",\"username\":\"Harsh Vijay\",\"shotid\":\"6789107\",\"title\":\"Stakeholders Section\"},{\"src\":\"https://cdn.dribbble.com/users/772985/screenshots/6789641/sky_ham.jpg\",\"username\":\"Mohamed Chahin\",\"shotid\":\"6789641\",\"title\":\"Sky Ham!\"},{\"src\":\"https://cdn.dribbble.com/users/550246/screenshots/6789658/dribbble-v3.png\",\"username\":\"Martin Maderic\",\"shotid\":\"6789658\",\"title\":\"Cryptocurrency app\"},{\"src\":\"https://cdn.dribbble.com/users/1150247/screenshots/6790495/untitled.png\",\"username\":\"Roman Lel\",\"shotid\":\"6790495\",\"title\":\"Clover bank mobile app\"},{\"src\":\"https://cdn.dribbble.com/users/257123/screenshots/6790310/squares.png\",\"username\":\"Kemal Sanli\",\"shotid\":\"6790310\",\"title\":\"motif\"},{\"src\":\"https://cdn.dribbble.com/users/598216/screenshots/6792101/train_env_color.png\",\"username\":\"Gustavo Henrique\",\"shotid\":\"6792101\",\"title\":\"Train\"},{\"src\":\"https://cdn.dribbble.com/users/77275/screenshots/6792991/careersdrib.png\",\"username\":\"Gene Ross\",\"shotid\":\"6792991\",\"title\":\"Cruise Careers\"},{\"src\":\"https://cdn.dribbble.com/users/3661265/screenshots/6790445/debut_shot_final_nograin.gif\",\"username\":\"Degordian\",\"shotid\":\"6790445\",\"title\":\"Helloshot\"},{\"src\":\"https://cdn.dribbble.com/users/1787323/screenshots/6792155/cute_astro_dribbble-01.png\",\"username\":\"catalyst\",\"shotid\":\"6792155\",\"title\":\"Flying to the stars! ⭐\uD83C\uDF20\uD83D\uDC69\u200D\uD83D\uDE80\"},{\"src\":\"https://cdn.dribbble.com/users/1037/screenshots/6793805/img_0273.jpg\",\"username\":\"Rik Catlow\",\"shotid\":\"6793805\",\"title\":\"Karate Burger - Hiyah!\"},{\"src\":\"https://cdn.dribbble.com/users/1045098/screenshots/6791482/attrition-ui-agus-maulana.png\",\"username\":\"Agus Maulana\",\"shotid\":\"6791482\",\"title\":\"App design for HR company\"},{\"src\":\"https://cdn.dribbble.com/users/9964/screenshots/6786183/asking-for-a-friend.png\",\"username\":\"VISU∆L jams\",\"shotid\":\"6786183\",\"title\":\"Asking for a Friend\"},{\"src\":\"https://cdn.dribbble.com/users/466659/screenshots/6790425/simplabs-ember.png\",\"username\":\"Halo Lab\",\"shotid\":\"6790425\",\"title\":\"Simplabs Services Web Page\"},{\"src\":\"https://cdn.dribbble.com/users/102849/screenshots/6772241/skooby_login_error_animation.png\",\"username\":\"ALEX BENDER\",\"shotid\":\"6772241\",\"title\":\"Skooby Login Error Animation\"},{\"src\":\"https://cdn.dribbble.com/users/348942/screenshots/6751652/jaybird-homepage.png\",\"username\":\"Quintin Lodge\",\"shotid\":\"6751652\",\"title\":\"Jaybird \uD83C\uDFA7 - Homepage\"},{\"src\":\"https://cdn.dribbble.com/users/1481172/screenshots/6786518/animations_web.gif\",\"username\":\"Kate Laguta\",\"shotid\":\"6786518\",\"title\":\"Golden Suisse\"},{\"src\":\"https://cdn.dribbble.com/users/1227650/screenshots/6784592/accord.jpg\",\"username\":\"Dogstudio\",\"shotid\":\"6784592\",\"title\":\"Accord - Discovery\"},{\"src\":\"https://cdn.dribbble.com/users/170412/screenshots/6791107/artboard.png\",\"username\":\"KK UI Store\",\"shotid\":\"6791107\",\"title\":\"Blöcke Web UI/UX Kit\"},{\"src\":\"https://cdn.dribbble.com/users/505441/screenshots/6793396/image.png\",\"username\":\"Brethren Design Co\",\"shotid\":\"6793396\",\"title\":\"Botanical Labs Seal\"},{\"src\":\"https://cdn.dribbble.com/users/74271/screenshots/6785933/nightowls_emblem.jpg\",\"username\":\"Eight Hour Day\",\"shotid\":\"6785933\",\"title\":\"Night Owls\"},{\"src\":\"https://cdn.dribbble.com/users/1298934/screenshots/6789477/wordmarks-01.png\",\"username\":\"gdimidesign\",\"shotid\":\"6789477\",\"title\":\"Wordmark Logos Collection\"},{\"src\":\"https://cdn.dribbble.com/users/1290843/screenshots/6788657/gameboyz.png\",\"username\":\"Brian Houtz\",\"shotid\":\"6788657\",\"title\":\"Gameboy Colors\"},{\"src\":\"https://cdn.dribbble.com/users/722246/screenshots/6787338/iconhome.gif\",\"username\":\"Yup Nguyen\",\"shotid\":\"6787338\",\"title\":\"iconAnimation - Home\"},{\"src\":\"https://cdn.dribbble.com/users/1928598/screenshots/6794945/___copy_9.png\",\"username\":\"Innerpeace\",\"shotid\":\"6794945\",\"title\":\"Financial data design\"},{\"src\":\"https://cdn.dribbble.com/users/64052/screenshots/6769073/samson-03.png\",\"username\":\"Jennifer Hood\",\"shotid\":\"6769073\",\"title\":\"Detail of Illustrations\"},{\"src\":\"https://cdn.dribbble.com/users/1912068/screenshots/6793420/carry-on-items.jpg\",\"username\":\"Mia Ditmanson\",\"shotid\":\"6793420\",\"title\":\"Carry-On Items\"},{\"src\":\"https://cdn.dribbble.com/users/1004297/screenshots/6792054/backervoice.png\",\"username\":\"Ana Moreno\",\"shotid\":\"6792054\",\"title\":\"Backervoice Hero \uD83D\uDCE3\"},{\"src\":\"https://cdn.dribbble.com/users/286774/screenshots/6793855/prospera_logo_dribbble.png\",\"username\":\"João Apolinário\",\"shotid\":\"6793855\",\"title\":\"Prospera Tea\"},{\"src\":\"https://cdn.dribbble.com/users/418188/screenshots/6791101/tubik_gallery_app_ui.png\",\"username\":\"tubik\",\"shotid\":\"6791101\",\"title\":\"Gallery App Ui\"},{\"src\":\"https://cdn.dribbble.com/users/2666141/screenshots/6791227/__-03.jpg\",\"username\":\"yu zhang\",\"shotid\":\"6791227\",\"title\":\"Life Icon\"},{\"src\":\"https://cdn.dribbble.com/users/1545170/screenshots/6791732/d-sp-page-builder-10-secret.png\",\"username\":\"Saikat Kumar\",\"shotid\":\"6791732\",\"title\":\"10 Secret Features of SP Page Builder\"},{\"src\":\"https://cdn.dribbble.com/users/134635/screenshots/6787180/reinvently_new.png\",\"username\":\"Sergey Semernyov\",\"shotid\":\"6787180\",\"title\":\"Reinvently\"},{\"src\":\"https://cdn.dribbble.com/users/2637339/screenshots/6789564/brooke_tshirt.png\",\"username\":\"Brooke Chantrachuck\",\"shotid\":\"6789564\",\"title\":\"Ivan\"},{\"src\":\"https://cdn.dribbble.com/users/1260346/screenshots/6773136/voiceflow_process.png\",\"username\":\"Lance\",\"shotid\":\"6773136\",\"title\":\"Voiceflow - Process\"}]"
        lateinit var newshot: ArrayList<ShotMessage>
        val listType = object : TypeToken<ArrayList<ShotMessage>>() {}.type
        newshot= Gson().fromJson(shotJson, listType)
//        Logger.d("newshot","is"+newshot.toString())
        Logger.d("newshot","is"+newshot[1].src)

        return newshot
    }

    //捕获WebView组件中长按事件,重写 Activity子类的onCreateContextMenu函数。
    override fun onCreateContextMenu(menu: ContextMenu, view: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        webView!!.hitTestResult?.let {
            when (it.type) {
                WebView.HitTestResult.IMAGE_TYPE,
                WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE -> {
                    Logger.d("viewtype",it.type.toString())
                    menu.setHeaderTitle("Image Options")
                    menu.add(0, 1, 0, "Download")
                }
                else -> Logger.d("App does not yet handle target type: ${it.type}")
            }
        }
        super.onCreateContextMenu(menu, view, menuInfo)
        Logger.d("oncreatemune","长按了")

    }

    //当上面add的菜单项被点击
    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
            == PackageManager.PERMISSION_GRANTED) {
            webView!!.hitTestResult?.let {
                when (it.type) {
                    WebView.HitTestResult.IMAGE_TYPE,
                    WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE -> {
                        Logger.d("viewtype",it.type.toString())
                        downloadFile(imgSrc!!,path!!)
                    }
                    else -> Logger.d("App does not yet handle target type: ${it.type}")
                }
            }
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1)
            }
        }
        return super.onContextItemSelected(item)
    }

    //判断动态权限申请的结果
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                //todo:permission granted
                downloadFile(imgSrc!!,path!!)

            } else{
                //todo:permission denied
            }
        }
    }


    private fun downloadFile(url:String,path:String) {
        val uri = Uri.parse(url)
        val guessedFileName = URLUtil.guessFileName(url, null, null)
        val request = DownloadManager.Request(uri).apply {
            allowScanningByMediaScanner()
            setDestinationInExternalPublicDir(path, guessedFileName)
            setNotificationVisibility(VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        }
        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)

        Logger.d("filepath",path+"/"+guessedFileName)
        val contentUri = Uri.fromFile(File(path+File.separator+guessedFileName))
        Logger.d("contenturl",contentUri.toString())
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,contentUri)
        sendBroadcast(mediaScanIntent)

    }

}



