package com.cocos.develop.noteadvanced.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.databinding.NoteItemBinding
import com.cocos.develop.noteadvanced.utils.noteListCreator

/**
 * homework com.cocos.develop.noteadvanced.ui.home
 *
 * @author Amina
 * 16.11.2021
 */
class HomeAdapter(private var onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<HomeAdapter.RecyclerItemViewHolder>() {

    private var data: List<NoteData> = arrayListOf()

    fun setData() {
        this.data = noteListCreator()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecyclerItemViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val vb: NoteItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(noteData: NoteData) {
            vb.run {
                if (layoutPosition != RecyclerView.NO_POSITION) {
                    this.headerTextview.text = noteData.name
                    this.descriptionsTextview.text = noteData.description
                    this.dateTextView.text = noteData.date
                }
            }
            itemView.setOnClickListener { openInNewWindow(noteData) }
        }
    }
    private fun openInNewWindow(ItemData: NoteData) {
        onListItemClickListener.onItemClick(ItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: NoteData)
    }
}
