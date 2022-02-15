package com.example.myapplicationroomhomework.controller

import android.widget.Toast
import androidx.databinding.ObservableField
import com.example.myapplicationroomhomework.model.entities.Record
import com.example.myapplicationroomhomework.utils.GlobalVariables
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel {
    var fieldPlayer: ObservableField<String> = ObservableField("")
    var fieldScore: ObservableField<String> = ObservableField("")
    var adapter = ObservableField(RvAdapterRecord(arrayListOf()))
    var score
        get() = fieldScore.get().toString().toIntOrNull() ?: 0
        set(value) = fieldScore.set(value.toString())
    var player
        get() = fieldPlayer.get().toString()
        set(value) = fieldPlayer.set(value)
    private val recordDao by lazy { GlobalVariables.instance.appDatabase.recordDao }
    private val applicationContext by lazy { GlobalVariables.instance.applicationContext }
    fun updateAdapter() {
        GlobalScope.launch(Dispatchers.IO) {
            val records = recordDao.getAll()
            adapter.set(RvAdapterRecord(arrayListOf(*records.toTypedArray())))
        }
    }

    fun addRecord() {
        val score = this.score
        val player = this.player
        GlobalScope.launch(Dispatchers.IO) {
            val id = recordDao.insert(Record(player, score))
            fieldPlayer.set("")
            fieldScore.set("")
            with(adapter.get()!!.records) {
                get(lastIndex).id=id.toInt()
            }
        }
        with(adapter.get()!!) {
            records += Record(player, score)
            notifyItemInserted(records.size - 1)
        }

    }

    fun deleteAll() {
        GlobalScope.launch(Dispatchers.IO) {
            recordDao.deleteAll()
            fieldPlayer.set("")
            fieldScore.set("")
        }
        Toast.makeText(applicationContext, "Успешно удалено всё", Toast.LENGTH_LONG).show()
    }
}