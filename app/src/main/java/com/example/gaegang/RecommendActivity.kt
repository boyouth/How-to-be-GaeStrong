package com.example.gaegang

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gaegang.adapter.RecommendAdapter
import com.example.gaegang.dataClass.RecommendedItem
import kotlinx.android.synthetic.main.activity_recommend.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*
import kotlin.collections.ArrayList


class RecommendActivity : AppCompatActivity()  {


    val TAG = "TAG_RecommencdActivity"

    var stt_text = ""
    lateinit var recList : ArrayList<RecommendedItem>

    fun getLocalIpAddress(): String? {
        try {
            val en: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf: NetworkInterface = en.nextElement()
                val enumIpAddr: Enumeration<InetAddress> = intf.getInetAddresses()
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress: InetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress() && inetAddress is Inet4Address) {
                        return inetAddress.getHostAddress()
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }
        return null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend)


        // intent 전환
        val prev_intent = findViewById(R.id.button_prev) as ImageButton
        prev_intent.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }


        // textView (stt) 값 전달받기
        val getintent = getIntent()
        if (getintent.hasExtra("textStt")) {
            text_stt2.text = getintent.getStringExtra("textStt")
            stt_text = getintent.getStringExtra("textStt").toString()
            recList = getintent.getSerializableExtra("recList") as ArrayList<RecommendedItem>
        } else {
            Log.d(TAG,"stt text 안받아옴")
        }


        // recyclerview
        val mAdapter = RecommendAdapter(this, recList)
        recyclerview_rec.adapter = mAdapter

        val lm = LinearLayoutManager(this)
        recyclerview_rec.layoutManager = lm
        recyclerview_rec.setHasFixedSize(true)
    }


    // 음성 인식 결과 text
    private fun getSttString(): String {
        var result = stt_text

        return result
    }
}