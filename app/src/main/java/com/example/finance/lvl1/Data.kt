package com.example.finance.lvl1

class Data (val dia : Int, val mes : Int, val ano : Int){




    fun getDataString() : String{
        return "${dia}/${mes}/${ano}"
    }
}