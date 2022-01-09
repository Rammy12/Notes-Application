package com.example.notes_application.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes_application.R
import com.example.notes_application.databinding.FragmentHomeFragmentBinding

import com.example.notes_application.model.Notes
import com.example.notes_application.ui.Adepter.NotesAdepter
import com.example.notes_application.viewModel.NotesViewModel


class home_fragment:Fragment(){
    lateinit var binding: FragmentHomeFragmentBinding
    val viewModel:NotesViewModel by viewModels()
    var oldMyNotes= arrayListOf<Notes>()
    lateinit var adepter: NotesAdepter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeFragmentBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        val staggeredGridLayoutManager=
            StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.RecViewAllNotes.layoutManager=GridLayoutManager(requireContext(),2)
        //get all notes
        viewModel.getNotes().observe(viewLifecycleOwner,{notesList->
            oldMyNotes=notesList as ArrayList<Notes>
            adepter=NotesAdepter(requireContext(),notesList)
            binding.RecViewAllNotes.adapter=adepter
        })
        //filtering high
        binding.filterHigh.setOnClickListener {
            viewModel.gethighNotes().observe(viewLifecycleOwner,{notesList->
                oldMyNotes=notesList as ArrayList<Notes>
                adepter=NotesAdepter(requireContext(),notesList)
                binding.RecViewAllNotes.adapter=adepter
            })
        }
        //filtering medium
        binding.filterMedium.setOnClickListener {
            viewModel.getmediumNotes().observe(viewLifecycleOwner,{notesList->
                oldMyNotes=notesList as ArrayList<Notes>
                adepter=NotesAdepter(requireContext(),notesList)
                binding.RecViewAllNotes.adapter=adepter
            })
        }
        //filtering low
        binding.filterLow.setOnClickListener {
            viewModel.getlowNotes().observe(viewLifecycleOwner,{notesList->
                oldMyNotes=notesList as ArrayList<Notes>
                adepter=NotesAdepter(requireContext(),notesList)
                binding.RecViewAllNotes.adapter=adepter
            })
        }

        binding.AllNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner,{notesList->
                oldMyNotes=notesList as ArrayList<Notes>
                binding.RecViewAllNotes.layoutManager=GridLayoutManager(requireContext(),2)
                binding.RecViewAllNotes.adapter=NotesAdepter(requireContext(),notesList)

            })

        }

        binding.btnAdd.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_home_fragment_to_createNodeFragment)

        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item=menu.findItem(R.id.app_bar_search)
        val searchview=item.actionView as SearchView
        searchview.queryHint="Enter Notes Here.."
        searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Notesfilltering(p0)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun Notesfilltering(p0: String?) {
        val newfilltertedlist= arrayListOf<Notes>()
        for(i in oldMyNotes)
        {

            if(i.title!!.contains(p0!!)  || i.subTitle!!.contains(p0!!))
            {
                newfilltertedlist.add(i)
            }
        }
        adepter.Filltering(newfilltertedlist)
    }


}


