package com.example.gaegang.dataClass

import java.io.Serializable

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

data class RecommendedItem(
    val lecture: String?,
    val professor: String?,
    val number: String?,
    val uni: String?,
    val major: String?,
    val classification: String?,
    val grade: String?,
    val credit: String?,
    val teaching_method: String?,
    val time: String?
) :Serializable{}
