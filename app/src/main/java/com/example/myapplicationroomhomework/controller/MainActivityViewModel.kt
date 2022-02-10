package com.example.myapplicationroomhomework.controller

import android.widget.Toast
import androidx.databinding.ObservableField
import com.example.myapplicationroomhomework.utils.GlobalVariables
import com.example.myapplicationroomhomework.model.entities.KeyValuePair
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityViewModel {
    var fieldKey: ObservableField<String> = ObservableField("")
    var fieldValue: ObservableField<String> = ObservableField("")

    private var keyValuePairsRepository =
        GlobalVariables.instance.appDatabase.keyValuePairsRepository
    private var applicationContext = GlobalVariables.instance.applicationContext

    fun getByKey() {
        GlobalScope.launch(Dispatchers.IO) {
            var key = fieldKey.get().toString()

            var value = keyValuePairsRepository.getByKey(key)

            if (value == null) {
                fieldValue.set("key not found")
            } else {
                fieldValue.set(value)
            }
        }
    }

    fun addKeyValuePair() {
        GlobalScope.launch(Dispatchers.IO) {
            var key = fieldKey.get().toString()
            var value = fieldValue.get().toString()

            keyValuePairsRepository.insert(KeyValuePair(key, value))

            fieldKey.set("")
            fieldValue.set("")
        }
        Toast.makeText(applicationContext, "Успешно добавлено", Toast.LENGTH_LONG).show()
    }

    fun updateValueByKey() {
        GlobalScope.launch(Dispatchers.IO) {
            var key = fieldKey.get().toString()
            var value = fieldValue.get().toString()

            keyValuePairsRepository.update(KeyValuePair(key, value))

            fieldKey.set("")
            fieldValue.set("")
        }
        Toast.makeText(applicationContext, "Успешно обновлено", Toast.LENGTH_LONG).show()
    }

    fun deleteByKey(){
        GlobalScope.launch(Dispatchers.IO) {
            var key = fieldKey.get().toString()

            keyValuePairsRepository.deleteByKey(key)

            fieldKey.set("")
            fieldValue.set("")
        }
        Toast.makeText(applicationContext, "Успешно удалено по ключу", Toast.LENGTH_LONG).show()
    }

    fun deleteAll(){
        GlobalScope.launch(Dispatchers.IO) {

            keyValuePairsRepository.deleteAll()

            fieldKey.set("")
            fieldValue.set("")
        }
        Toast.makeText(applicationContext, "Успешно удалено всё", Toast.LENGTH_LONG).show()
    }
}