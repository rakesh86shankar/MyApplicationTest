package com.example.myapplicationtest

import android.os.Bundle

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner
import com.example.myapplicationtest.data.APISource
import com.example.myapplicationtest.data.NewsPaperResponse
import com.example.myapplicationtest.network.retrofit
import com.example.myapplicationtest.services.NewsAPI
import com.example.myapplicationtest.view.NewsPaperListFragment
import com.google.android.material.snackbar.Snackbar

import kotlinx.coroutines.*


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("To Test Conflict",">>>@333")
        replaceFragment(NewsPaperListFragment(),"Page1")
    }

    fun testCoroutines(){
        var mainUIScope = CoroutineScope(Dispatchers.Main)
        var bgWorkerScope = CoroutineScope(Dispatchers.IO)
        var job:Job?=null
        var bgWorkweScopwValue = bgWorkerScope.async {
            //Thread.sleep(1000)
            try {
                val newsAPI : NewsAPI = retrofit().create(NewsAPI::class.java)
                val response :APISource = newsAPI.getNewsSource(APIKey = newsAPIKey).await().body()!!
                Log.v("Corotines Tes>>>","finally"+response.sources.size)
                val response1:NewsPaperResponse = newsAPI.getNewsPaperByName("techcrunch", newsAPIKey).await().body()!!
                Log.v("NewsPaperResponse Tes>>>","finally"+response1.articles.size)
            }catch (e: Exception){
                Log.v("Exception i ",""+e.message)
            }

        }
    }


    fun replaceFragment(fragmentView: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().replace(R.id.containerLayout, fragmentView, tag)
            .addToBackStack("").commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
