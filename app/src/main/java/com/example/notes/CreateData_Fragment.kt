package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.notes.databinding.FragmentCreateDataBinding
import com.example.notesaddingapp.viewmodel.NoteViewModel
import android.text.format.DateFormat
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesaddingapp.room.Notes
import java.util.*

class CreateData_Fragment : Fragment() {
    lateinit var binding:FragmentCreateDataBinding
    val viewmodel:NoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentCreateDataBinding.inflate(layoutInflater,container,false)

        binding.notesavebtn.setOnClickListener {
            createsNotes(it)
        }

        return binding.root
    }

    private fun createsNotes(it: View?) {
        val title=binding.ettitle.text.toString()
        val content=binding.etcontent.text.toString()
        if(title.isEmpty() && content.isEmpty()){
            Toast.makeText(activity,"Right Something",Toast.LENGTH_SHORT).show()
        }else{
            val d= Date()
            val notesdate:CharSequence=DateFormat.format("MMMM d,yyyy",d.time)

            val notes= Notes(0,title,content,notesdate.toString())
            viewmodel.addNotes(notes)
            Toast.makeText(activity,"Notes Created Successfully",Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it!!)
                .navigate(R.id.action_createData_Fragment_to_home_fragment)


        }

    }

}
