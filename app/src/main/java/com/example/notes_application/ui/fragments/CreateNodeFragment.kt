package com.example.notes_application.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notes_application.R
import com.example.notes_application.databinding.FragmentCreateNodeBinding
import com.example.notes_application.model.Notes
import com.example.notes_application.viewModel.NotesViewModel

import java.util.*


class CreateNodeFragment : Fragment() {
    lateinit var binding:FragmentCreateNodeBinding
    var priority:String="1"
    val viewmodel:NotesViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCreateNodeBinding.inflate(layoutInflater,container,false)
        binding.crGreen.setImageResource(R.drawable.done_24)
        binding.crGreen.setOnClickListener {
            priority="1"
            binding.crGreen.setImageResource(R.drawable.done_24)
            binding.crYellow.setImageResource(0)
            binding.crRed.setImageResource(0)

        }
        binding.crRed.setOnClickListener {
            priority="3"
            binding.crRed.setImageResource(R.drawable.done_24)
            binding.crYellow.setImageResource(0)
            binding.crGreen.setImageResource(0)

        }
        binding.crYellow.setOnClickListener {
            priority="2"
            binding.crYellow.setImageResource(R.drawable.done_24)
            binding.crGreen.setImageResource(0)
            binding.crRed.setImageResource(0)

        }
        binding.savenotesBtn.setOnClickListener {
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        val title=binding.crTitle.text.toString()
        val subtitle=binding.crSubtitle.text.toString()
        val notes=binding.crStart.text.toString()


        val d = Date()
        val notesdate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.getTime())
        val data=Notes(
            null,
            title = title,
            subTitle = subtitle,
            notes = notes,
            date = notesdate.toString(),
            priority = priority
        )
        viewmodel.addNotes(data)
        Toast.makeText(requireContext(),"Notes Created SuccesFully",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNodeFragment_to_home_fragment)


    }


}