package com.example.notes_application.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notes_application.R
import com.example.notes_application.databinding.FragmentEditNodesBinding
import com.example.notes_application.model.Notes
import com.example.notes_application.viewModel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class EditNodesFragment : Fragment() {

val oldnotes by navArgs<EditNodesFragmentArgs>()
    lateinit var binding: FragmentEditNodesBinding
    var priority:String="1"
    val viewmodel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentEditNodesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        binding.editTitle.setText(oldnotes.data.title)
        binding.editSubtitle.setText(oldnotes.data.subTitle)
        binding.editStart.setText(oldnotes.data.notes)
        when(oldnotes.data.priority)
        {
            "1"->{
                priority="1"
                binding.crGreen.setImageResource(R.drawable.done_24)
                binding.editYellow.setImageResource(0)
                binding.editRed.setImageResource(0)

            }
            "2"->{
                priority="2"
                binding.editYellow.setImageResource(R.drawable.done_24)
                binding.crGreen.setImageResource(0)
                binding.editRed.setImageResource(0)

            }
            "3"->{
                priority="3"
                binding.editRed.setImageResource(R.drawable.done_24)
                binding.editYellow.setImageResource(0)
                binding.crGreen.setImageResource(0)

            }




        }
        binding.crGreen.setOnClickListener {
            priority="1"
            binding.crGreen.setImageResource(R.drawable.done_24)
            binding.editYellow.setImageResource(0)
            binding.editRed.setImageResource(0)

        }
        binding.editRed.setOnClickListener {
            priority="3"
            binding.editRed.setImageResource(R.drawable.done_24)
            binding.editYellow.setImageResource(0)
            binding.crGreen.setImageResource(0)

        }
        binding.editYellow.setOnClickListener {
            priority="2"
            binding.editYellow.setImageResource(R.drawable.done_24)
            binding.crGreen.setImageResource(0)
            binding.editRed.setImageResource(0)

        }
        binding.editSavenotesBtn.setOnClickListener {
            updateNotes(it)
        }
        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title=binding.editTitle.text.toString()
        val subtitle=binding.editSubtitle.text.toString()
        val notes=binding.editStart.text.toString()


        val d = Date()
        val notesdate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.getTime())
        val data= Notes(
            oldnotes.data.id,
            title = title,
            subTitle = subtitle,
            notes = notes,
            date = notesdate.toString(),
            priority = priority
        )
        viewmodel.updateNotes(data)
        Toast.makeText(requireContext(),"Notes Updated SuccesFully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNodesFragment_to_home_fragment)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete)
        {
            val bottomsheet:BottomSheetDialog= BottomSheetDialog(requireContext())
            bottomsheet.setContentView(R.layout.dailog_delete)
            val textviewYes=bottomsheet.findViewById<TextView>(R.id.dailog_yes)
            val textviewNo=bottomsheet.findViewById<TextView>(R.id.dailog_no)
            textviewNo?.setOnClickListener {
                bottomsheet.dismiss()
            }
            textviewYes?.setOnClickListener {
                viewmodel.deleteNotes(oldnotes.data.id!!)
                bottomsheet.dismiss()

            }
            bottomsheet.show()

        }
        return super.onOptionsItemSelected(item)
    }


}