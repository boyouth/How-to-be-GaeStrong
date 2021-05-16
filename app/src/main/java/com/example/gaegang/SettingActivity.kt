package com.example.gaegang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val items_uni = resources.getStringArray(R.array.arr_uni)
        val items_major_inmun = resources.getStringArray(R.array.arr_inmun)
        val items_major_jayeon = resources.getStringArray(R.array.arr_jayeon)
        val items_major_sahoe = resources.getStringArray(R.array.arr_sahoe)
        val items_major_global = resources.getStringArray(R.array.arr_global)
        val items_major_gong = resources.getStringArray(R.array.arr_gong)
        val items_major_jeongbo = resources.getStringArray(R.array.arr_jeongbo)
        val items_major_gyeongyeong = resources.getStringArray(R.array.arr_gyeongyeong)
        val items_major_yesul = resources.getStringArray(R.array.arr_yesul)
        val items_major_sabeom = resources.getStringArray(R.array.arr_sabeom)
        val items_major_dosi = resources.getStringArray(R.array.arr_dosi)
        val items_major_saengmyeong = resources.getStringArray(R.array.arr_saengmyeong)
        val items_major_dongbuga = resources.getStringArray(R.array.arr_dongbuga)
        val items_major_beob = resources.getStringArray(R.array.arr_beob)
        var recommended_major=""

        val uniAdapter = object : ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val v = super.getView(position, convertView, parent)
                if (position == count) {
                    //마지막 포지션의 textView 를 힌트 용으로 사용합니다.
                    (v.findViewById<View>(R.id.text_spinner) as TextView).text = ""
                    //아이템의 마지막 값을 불러와 hint로 추가해 줍니다.
                    (v.findViewById<View>(R.id.text_spinner) as TextView).hint = getItem(count)
                }
                return v
            }
            override fun getCount(): Int {
                //마지막 아이템은 힌트용으로만 사용하기 때문에 getCount에 1을 빼줍니다.
                return super.getCount() - 1
            }
        }

        val majorAdapter = object : ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val v = super.getView(position, convertView, parent)
                if (position == count) {
                    //마지막 포지션의 textView 를 힌트 용으로 사용합니다.
                    (v.findViewById<View>(R.id.text_spinner) as TextView).text = ""
                    //아이템의 마지막 값을 불러와 hint로 추가해 줍니다.
                    (v.findViewById<View>(R.id.text_spinner) as TextView).hint = getItem(count)
                }
                return v
            }
            override fun getCount(): Int {
                //마지막 아이템은 힌트용으로만 사용하기 때문에 getCount에 1을 빼줍니다.
                return super.getCount() - 1
            }
        }

        uniAdapter.addAll(items_uni.toMutableList())
        uniAdapter.add("단과대학")
        major_spinner1.adapter=uniAdapter
        major_spinner1.setSelection(uniAdapter.count)
        major_spinner1.dropDownVerticalOffset=dipToPixels(30f).toInt()
        major_spinner1.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){

                    0 ->{
                        majorAdapter.clear()
                        majorAdapter.addAll(items_major_inmun.toMutableList())
                        majorAdapter.add("학과 / 학부")
                        major_spinner2.adapter=majorAdapter
                        major_spinner2.setSelection(majorAdapter.count)
                        major_spinner2.dropDownVerticalOffset=dipToPixels(30f).toInt()
                        major_spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when (position) {
                                    0 -> {recommended_major=items_major_inmun[0]
                                    }
                                    1 -> {recommended_major=items_major_inmun[1]
                                    }
                                    2 -> {recommended_major=items_major_inmun[2]
                                    }
                                    3 -> {recommended_major=items_major_inmun[3]
                                    }
                                    4 -> {recommended_major=items_major_inmun[4]
                                    }
                                    5 -> {recommended_major=items_major_inmun[5]
                                    }
                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Log.d("MyTag", "onNothingSelected")                            }
                        }
                    }
                    1 ->{
                        majorAdapter.clear()
                        majorAdapter.addAll(items_major_jayeon.toMutableList())
                        majorAdapter.add("학과 / 학부")
                        major_spinner2.adapter=majorAdapter
                        major_spinner2.setSelection(majorAdapter.count)
                        major_spinner2.dropDownVerticalOffset=dipToPixels(30f).toInt()
                        major_spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when (position) {
                                    0 -> {recommended_major=items_major_jayeon[0]
                                    }
                                    1 -> {recommended_major=items_major_jayeon[1]
                                    }
                                    2 -> {recommended_major=items_major_jayeon[2]
                                    }
                                    3 -> {recommended_major=items_major_jayeon[3]
                                    }
                                    4 -> {recommended_major=items_major_jayeon[4]
                                    }
                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Log.d("MyTag", "onNothingSelected")                            }
                        }
                    }
                    2 ->{
                        majorAdapter.clear()
                        majorAdapter.addAll(items_major_sahoe.toMutableList())
                        majorAdapter.add("학과 / 학부")
                        major_spinner2.adapter=majorAdapter
                        major_spinner2.setSelection(majorAdapter.count)
                        major_spinner2.dropDownVerticalOffset=dipToPixels(30f).toInt()
                        major_spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when (position) {
                                    0 -> {recommended_major=items_major_sahoe[0]
                                    }
                                    1 -> {recommended_major=items_major_sahoe[1]
                                    }
                                    2 -> {recommended_major=items_major_sahoe[2]
                                    }
                                    3 -> {recommended_major=items_major_sahoe[3]
                                    }
                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Log.d("MyTag", "onNothingSelected")                            }
                        }
                    }
                    3 ->{
                        majorAdapter.clear()
                        majorAdapter.addAll(items_major_global.toMutableList())
                        majorAdapter.add("학과 / 학부")
                        major_spinner2.adapter=majorAdapter
                        major_spinner2.setSelection(majorAdapter.count)
                        major_spinner2.dropDownVerticalOffset=dipToPixels(30f).toInt()
                        major_spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when (position) {
                                    0 -> {recommended_major=items_major_global[0]
                                    }
                                    1 -> {recommended_major=items_major_global[1]
                                    }
                                    2 -> {recommended_major=items_major_global[2]
                                    }
                                    3 -> {recommended_major=items_major_global[3]
                                    }
                                    4 -> {recommended_major=items_major_global[4]
                                    }
                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Log.d("MyTag", "onNothingSelected")                            }
                        }
                    }
                    4 ->{
                        majorAdapter.clear()
                        majorAdapter.addAll(items_major_gong.toMutableList())
                        majorAdapter.add("학과 / 학부")
                        major_spinner2.adapter=majorAdapter
                        major_spinner2.setSelection(majorAdapter.count)
                        major_spinner2.dropDownVerticalOffset=dipToPixels(30f).toInt()
                        major_spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when (position) {
                                    0 -> {recommended_major=items_major_gong[0]
                                    }
                                    1 -> {recommended_major=items_major_gong[1]
                                    }
                                    2 -> {recommended_major=items_major_gong[2]
                                    }
                                    3 -> {recommended_major=items_major_gong[3]
                                    }
                                    4 -> {recommended_major=items_major_gong[4]
                                    }
                                    5 -> {recommended_major=items_major_gong[5]
                                    }
                                    6 -> {recommended_major=items_major_gong[6]
                                    }
                                    7 -> {recommended_major=items_major_gong[7]
                                    }
                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Log.d("MyTag", "onNothingSelected")                            }
                        }
                    }
                    5 ->{
                        majorAdapter.clear()
                        majorAdapter.addAll(items_major_jeongbo.toMutableList())
                        majorAdapter.add("학과 / 학부")
                        major_spinner2.adapter=majorAdapter
                        major_spinner2.setSelection(majorAdapter.count)
                        major_spinner2.dropDownVerticalOffset=dipToPixels(30f).toInt()
                        major_spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when (position) {
                                    0 -> {recommended_major=items_major_jeongbo[0]
                                    }
                                    1 -> {recommended_major=items_major_jeongbo[1]
                                    }
                                    2 -> {recommended_major=items_major_jeongbo[2]
                                    }
                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Log.d("MyTag", "onNothingSelected")                            }
                        }
                    }
                    6 ->{
                        majorAdapter.clear()
                        majorAdapter.addAll(items_major_gyeongyeong.toMutableList())
                        majorAdapter.add("학과 / 학부")
                        major_spinner2.adapter=majorAdapter
                        major_spinner2.setSelection(majorAdapter.count)
                        major_spinner2.dropDownVerticalOffset=dipToPixels(30f).toInt()
                        major_spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when (position) {
                                    0 -> {recommended_major=items_major_gyeongyeong[0]
                                    }
                                    1 -> {recommended_major=items_major_gyeongyeong[1]
                                    }
                                    2 -> {recommended_major=items_major_gyeongyeong[2]
                                    }
                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Log.d("MyTag", "onNothingSelected")                            }
                        }
                    }
                    7 ->{
                        majorAdapter.clear()
                        majorAdapter.addAll(items_major_yesul.toMutableList())
                        majorAdapter.add("학과 / 학부")
                        major_spinner2.adapter=majorAdapter
                        major_spinner2.setSelection(majorAdapter.count)
                        major_spinner2.dropDownVerticalOffset=dipToPixels(30f).toInt()
                        major_spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when (position) {
                                    0 -> {recommended_major=items_major_yesul[0]
                                    }
                                    1 -> {recommended_major=items_major_yesul[1]
                                    }
                                    2 -> {recommended_major=items_major_yesul[2]
                                    }
                                    3 -> {recommended_major=items_major_yesul[3]
                                    }
                                    4 -> {recommended_major=items_major_yesul[4]
                                    }
                                    5 -> {recommended_major=items_major_yesul[5]
                                    }
                                    6 -> {recommended_major=items_major_yesul[6]
                                    }

                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Log.d("MyTag", "onNothingSelected")                            }
                        }
                    }
                    8 ->{
                        majorAdapter.clear()
                        majorAdapter.addAll(items_major_sabeom.toMutableList())
                        majorAdapter.add("학과 / 학부")
                        major_spinner2.adapter=majorAdapter
                        major_spinner2.setSelection(majorAdapter.count)
                        major_spinner2.dropDownVerticalOffset=dipToPixels(30f).toInt()
                        major_spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when (position) {
                                    0 -> {recommended_major=items_major_sabeom[0]
                                    }
                                    1 -> {recommended_major=items_major_sabeom[1]
                                    }
                                    2 -> {recommended_major=items_major_sabeom[2]
                                    }
                                    3 -> {recommended_major=items_major_sabeom[3]
                                    }
                                    4 -> {recommended_major=items_major_sabeom[4]
                                    }
                                    5 -> {recommended_major=items_major_sabeom[5]
                                    }
                                    6 -> {recommended_major=items_major_sabeom[6]
                                    }
                                    7 ->{recommended_major=items_major_sabeom[7]
                                    }
                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Log.d("MyTag", "onNothingSelected")                            }
                        }
                    }
                    9 ->{
                        majorAdapter.clear()
                        majorAdapter.addAll(items_major_dosi.toMutableList())
                        majorAdapter.add("학과 / 학부")
                        major_spinner2.adapter=majorAdapter
                        major_spinner2.setSelection(majorAdapter.count)
                        major_spinner2.dropDownVerticalOffset=dipToPixels(30f).toInt()
                        major_spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when (position) {
                                    0 -> {recommended_major=items_major_dosi[0]
                                    }
                                    1 -> {recommended_major=items_major_dosi[1]
                                    }
                                    2 -> {recommended_major=items_major_dosi[2]
                                    }
                                    3 -> {recommended_major=items_major_dosi[3]
                                    }
                                    4 -> {recommended_major=items_major_dosi[4]
                                    }

                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Log.d("MyTag", "onNothingSelected")                            }
                        }
                    }
                    10 ->{
                        majorAdapter.clear()
                        majorAdapter.addAll(items_major_saengmyeong.toMutableList())
                        majorAdapter.add("학과 / 학부")
                        major_spinner2.adapter=majorAdapter
                        major_spinner2.setSelection(majorAdapter.count)
                        major_spinner2.dropDownVerticalOffset=dipToPixels(30f).toInt()
                        major_spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when (position) {
                                    0 -> {recommended_major=items_major_saengmyeong[0]
                                    }
                                    1 -> {recommended_major=items_major_saengmyeong[1]
                                    }
                                    2 -> {recommended_major=items_major_saengmyeong[2]
                                    }
                                    3 -> {recommended_major=items_major_saengmyeong[3]
                                    }
                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Log.d("MyTag", "onNothingSelected")                            }
                        }
                    }
                    11 ->{
                        majorAdapter.clear()
                        majorAdapter.addAll(items_major_dongbuga.toMutableList())
                        majorAdapter.add("학과 / 학부")
                        major_spinner2.adapter=majorAdapter
                        major_spinner2.setSelection(majorAdapter.count)
                        major_spinner2.dropDownVerticalOffset=dipToPixels(30f).toInt()
                        major_spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when (position) {
                                    0 -> {recommended_major=items_major_dongbuga[0]
                                    }
                                    1 -> {recommended_major=items_major_dongbuga[1]
                                    }
                                    2 -> {recommended_major=items_major_dongbuga[2]
                                    }
                                    3 -> {recommended_major=items_major_dongbuga[3]
                                    }
                                    4 -> {recommended_major=items_major_dongbuga[4]
                                    }
                                    5 -> {recommended_major=items_major_dongbuga[5]
                                    }
                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Log.d("MyTag", "onNothingSelected")                            }
                        }
                    }
                    12 ->{
                        majorAdapter.clear()
                        majorAdapter.addAll(items_major_beob.toMutableList())
                        majorAdapter.add("학과 / 학부")
                        major_spinner2.adapter=majorAdapter
                        major_spinner2.setSelection(majorAdapter.count)
                        major_spinner2.dropDownVerticalOffset=dipToPixels(30f).toInt()
                        major_spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                when (position) {
                                    0 -> {
                                    }
                                }
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Log.d("MyTag", "onNothingSelected")                            }
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("MyTag", "onNothingSelected")            }

        }
        // intent 전환
        val next_intent = findViewById(R.id.button_next) as ImageButton
        next_intent.setOnClickListener {
            val intent= Intent(this,SearchActivity::class.java)
            startActivity(intent)
            Log.d("선택된학과",recommended_major)

            val database : FirebaseDatabase = FirebaseDatabase.getInstance()
            val myRef : DatabaseReference = database.getReference("majorName")
            myRef.setValue(recommended_major)

        }

    }
    private fun dipToPixels(dipValue: Float): Float {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dipValue,
                resources.displayMetrics
        )
    }

}