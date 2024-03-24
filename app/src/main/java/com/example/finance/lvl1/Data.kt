package com.example.finance.lvl1

class Data (val dia : Int, val mes : Int, val ano : Int){




    fun getDataString() : String{
        val diaString = if (dia < 10){
            "0${dia}"
        }else{
            "${dia}"
        }
        val mesString = if (mes < 10 ){
            "0${mes}"
        }else{
            "${mes}"
        }
        return "${diaString}/${mesString}/${ano}"
    }
}