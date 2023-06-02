package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notes.databinding.FragmentHomeFragmentBinding
import com.example.notesaddingapp.viewmodel.NoteViewModel


class Home_fragment : Fragment() {
    lateinit var binding:FragmentHomeFragmentBinding
    val viewModel:NoteViewModel by viewModels()
//    var notelist= arrayListOf<Notes>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeFragmentBinding.inflate(layoutInflater,container,false)

        binding.floatingbtn.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_home_fragment_to_createData_Fragment)
        }

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
//            notelist = notesList as ArrayList<Notes>

            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerView.adapter = MyAdapter(requireContext(), notesList)
        }
        return binding.root
    }

}