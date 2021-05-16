package com.example.gaegang

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.gaegang.R.id
import com.example.gaegang.dataClass.RecommendedItem
import com.google.firebase.database.*
import java.util.ArrayList
import kotlin.concurrent.timer

class SearchActivity : AppCompatActivity() {

    val TAG = "TAG_SearchActivity"

    var mRecognizer: SpeechRecognizer? = null
    var sttBtn: ImageButton? = null
    var textView: TextView? = null
    val PERMISSION = 1

    val recList: ArrayList<RecommendedItem> = arrayListOf<RecommendedItem>()
    private var firebaseDatabase: FirebaseDatabase? = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = firebaseDatabase!!.getReference()
    var mRootDatabaseReference = FirebaseDatabase.getInstance().reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // stt
        if (Build.VERSION.SDK_INT >= 29) {
            // 퍼미션 체크
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.INTERNET,
                Manifest.permission.RECORD_AUDIO), PERMISSION)
        }
        textView = findViewById<View>(R.id.text_stt) as TextView
        sttBtn = findViewById<View>(R.id.button_stt) as ImageButton
        intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent!!.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, packageName)
        intent!!.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")
        sttBtn!!.setOnClickListener { v: View? ->
            mRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
            mRecognizer?.run {
                setRecognitionListener(listener)
                startListening(intent)
            }
        }


        // intent 전환
        val next_intent = findViewById(id.button_next) as ImageButton
        next_intent.setOnClickListener {
            val intent = Intent(this, RecommendActivity::class.java)
            val customLoading = LoadingActivity(this)
            if (textView!!.text == "※ 이 곳에 음성 인식 결과가 나타납니다.") {
                Toast.makeText(applicationContext, "음성 인식 결과가 없습니다.",
                    Toast.LENGTH_SHORT).show()

            } else {
                var second : Int = 0

                val myRef : DatabaseReference = firebaseDatabase!!.getReference("sttText")
                myRef.setValue(textView!!.text.toString())
                customLoading.show()

                timer(period = 3000, initialDelay=3000){
                    second++
                    print(second)
                    if (second==1){
                        getList()
                    }
                    if (second==3){
                        val check: DatabaseReference = firebaseDatabase!!.getReference("check")

                        check.get().addOnSuccessListener {
                            if (it.value == "exist") {
                                intent.putExtra("textStt", textView!!.text)
                                intent.putExtra("recList",recList)
                                startActivity(intent)
                            }
                            else{
                                Toast.makeText(applicationContext, "검색 결과가 없습니다.",
                                        Toast.LENGTH_SHORT).show()
                                customLoading.cancel()
                            }
                        }.addOnFailureListener{
                            Log.e("firebase", "Error getting data", it)
                        }
                        cancel()
                    }
                }
            }
        }

        val prev_intent = findViewById(id.button_prev) as ImageButton
        prev_intent.setOnClickListener {
            val intent2= Intent(this,SettingActivity::class.java)
            startActivity(intent2)
        }
    }

    private val listener: RecognitionListener = object : RecognitionListener {
        override fun onReadyForSpeech(params: Bundle) {
            Toast.makeText(applicationContext, "음성인식을 시작합니다.",
                Toast.LENGTH_SHORT).show()
        }

        override fun onBeginningOfSpeech() {}
        override fun onRmsChanged(rmsdB: Float) {}
        override fun onBufferReceived(buffer: ByteArray) {}
        override fun onEndOfSpeech() {}
        override fun onError(error: Int) {
            val message: String
            message = when (error) {
                SpeechRecognizer.ERROR_AUDIO -> "오디오 에러"
                SpeechRecognizer.ERROR_CLIENT -> "클라이언트 에러"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "퍼미션 없음"
                SpeechRecognizer.ERROR_NETWORK -> "네트워크 에러"
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "네트웍 타임아웃"
                SpeechRecognizer.ERROR_NO_MATCH -> "찾을 수 없음"
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RECOGNIZER가 바쁨"
                SpeechRecognizer.ERROR_SERVER -> "서버가 이상함"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "말하는 시간초과"
                else -> "알 수 없는 오류임"
            }
            Toast.makeText(applicationContext, "에러가 발생하였습니다. : " +
                    message, Toast.LENGTH_SHORT).show()
        }

        override fun onResults(results: Bundle) {
            // 말을 하면 ArrayList에 단어를 넣고 textView에 단어를 이어줍니다.
            val matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            for (i in matches!!.indices) {
                textView!!.text = matches[i]
            }
        }

        override fun onPartialResults(partialResults: Bundle) {}
        override fun onEvent(eventType: Int, params: Bundle) {}
    }


    fun getList() {
//        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val myclass: DatabaseReference = firebaseDatabase!!.getReference("Recommendedclass")
        val check: DatabaseReference = firebaseDatabase!!.getReference("check")

        check.get().addOnSuccessListener {
            if (it.value == "null") {
                return@addOnSuccessListener
            } else {
                //Read from the database
                val postListener = object : ValueEventListener {
                    var Lecture = ""
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // Get Post object and use the values to update the UI
                        val value = dataSnapshot.value
                        //textView.text = "$value"
                        Log.d(TAG, "Value is: " + value.toString());


                        Lecture = value.toString()//강의 스트링으로 받기
                        val arr = Lecture.split("], [")
                        val recommendedClasses = Array(10, { item -> "" })
                        //스트링 형태 분리
                        for (i in 0 until arr.size) {
                            if (i == 0) {
                                val str1 = arr[i].replace("[[", "")
//                    Log.w(TAG, ">>"+ i + " " + str1)
                                recommendedClasses[i] = str1
                            } else if (i == 9) {
                                val str2 = arr[i].replace("]]", "")
//                    Log.w(TAG, ">>"+ i + " " + str2)
                                recommendedClasses[i] = str2
                            } else {
                                val str3 = arr[i]
//                    Log.w(TAG, ">>"+ i + " " + arr[i])
                                recommendedClasses[i] = str3
                            }
                            2
                        }
                        //데이터 갯수만큼 반복,recList에추가
                        for (i in 0 until recommendedClasses.size) {
                            val classArray = recommendedClasses[i].split(", ")
                            if (classArray.size > 9) {
                                recList.add(RecommendedItem(classArray[0], classArray[1], classArray[2], classArray[3], classArray[4],
                                        classArray[5], classArray[6], classArray[7], classArray[8], classArray[9]))
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                    }
                }
                myclass.addValueEventListener(postListener)
            }
        }
    }
}