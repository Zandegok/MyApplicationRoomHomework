package com.example.myapplicationroomhomework.controller

import android.view.*
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationroomhomework.R
import com.example.myapplicationroomhomework.databinding.RecordViewBinding
import com.example.myapplicationroomhomework.model.entities.Record
import com.example.myapplicationroomhomework.utils.GlobalVariables
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class RvAdapterRecord(val records: ArrayList<Record>) :
    RecyclerView.Adapter<RvAdapterRecord.RecordViewHolder>() {
    inner class RecordViewHolder internal constructor(val binding: RecordViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val fieldPlayer = ObservableField("")
        val fieldScore = ObservableField("")
        val fieldId = ObservableField(0)
        private val recordDao by lazy { GlobalVariables.instance.appDatabase.recordDao }
        fun delete() {
            val record = records[layoutPosition]
            records.remove(record)
            notifyItemRemoved(layoutPosition)
            GlobalScope.launch(Dispatchers.IO) { recordDao.delete(record) }
        }

        fun update() {
            val record = records[layoutPosition].apply {
                player = fieldPlayer.get().toString()
                score=fieldScore.get().toString().toIntOrNull() ?: 0
            }
            GlobalScope.launch(Dispatchers.IO) { recordDao.update(record) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val v = RecordViewBinding.bind(LayoutInflater.from(parent.context)
            .inflate(R.layout.record_view, parent, false))
        return RecordViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        with(holder)
        {
            binding.viewModel = holder
            with(records[position]) {
                fieldId.set(id)
                fieldPlayer.set(player)
                fieldScore.set(score.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return records.size
    }
}