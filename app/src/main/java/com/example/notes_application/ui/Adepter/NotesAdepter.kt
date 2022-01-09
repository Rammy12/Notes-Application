package com.example.notes_application.ui.Adepter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_application.R
import com.example.notes_application.databinding.ItemNotesBinding
import com.example.notes_application.model.Notes
import com.example.notes_application.ui.fragments.home_fragmentDirections

class NotesAdepter(val requireContext: Context, var notesList: List<Notes>) :RecyclerView.Adapter<NotesAdepter.notesviewholder>(){
    class notesviewholder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesviewholder {
        return notesviewholder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: notesviewholder, position: Int) {
        val data =notesList[position]
        holder.binding.viewTitle.text=data.title
        holder.binding.viewSubtitle.text=data.subTitle
        holder.binding.viewDate.text=data.date
        when(data.priority)
        {
            "1"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_shape)
            }
            "2"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_shape)
            }
            "3"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_shape)
            }




        }
        holder.binding.root.setOnClickListener {
            val action=home_fragmentDirections.actionHomeFragmentToEditNodesFragment2(data)
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount()=notesList.size
    fun Filltering(newfilltertedlist: ArrayList<Notes>) {
        notesList=newfilltertedlist
        notifyDataSetChanged()

    }


}