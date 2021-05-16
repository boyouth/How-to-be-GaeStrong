package com.example.gaegang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gaegang.R
import com.example.gaegang.dataClass.RecommendedItem

class RecommendAdapter(val context: Context, val recList: ArrayList<RecommendedItem>) :
RecyclerView.Adapter<RecommendAdapter.Holder>() {

    /*
    * 강의명
    * 교수명
    * 학수번호
    * 대학(원)
    * 학과(부)
    * 이수구분
    * 학년
    * 학점
    * 수업방법
    * 시간,강의실
    */
    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val lecture = itemView?.findViewById<TextView>(R.id.text_lecture)
        val professor = itemView?.findViewById<TextView>(R.id.text_professor)
        val number = itemView?.findViewById<TextView>(R.id.text_number)
        val uni = itemView?.findViewById<TextView>(R.id.text_uni)
        val major = itemView?.findViewById<TextView>(R.id.text_major)
        val classification = itemView?.findViewById<TextView>(R.id.text_classification)
        val grade = itemView?.findViewById<TextView>(R.id.text_grade)
        val credit = itemView?.findViewById<TextView>(R.id.text_credit)
        val teaching_method = itemView?.findViewById<TextView>(R.id.text_teaching_method)
        val time = itemView?.findViewById<TextView>(R.id.text_time)

        fun bind(rec: RecommendedItem, context: Context) {
            lecture?.text = rec.lecture
            professor?.text = rec.professor
            number?.text = rec.number
            uni?.text = rec.uni
            major?.text = rec.major
            classification?.text = rec.classification
            grade?.text=rec.grade
            credit?.text=rec.credit
            teaching_method?.text = rec.teaching_method
            time?.text = rec.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.recommended_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(recList[position], context)
    }

    override fun getItemCount(): Int {
        return recList.size
    }

}
