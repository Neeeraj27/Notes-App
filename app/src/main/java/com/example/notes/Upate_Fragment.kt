package com.example.notes

import android.app.Fragment
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*

import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.room.Update
import com.example.notes.databinding.FragmentUpateBinding
import com.example.notesaddingapp.room.Notes
import com.example.notesaddingapp.viewmodel.NoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class Upate_Fragment : androidx.fragment.app.Fragment() {
    val oldnotes by navArgs<Upate_FragmentArgs>()
    lateinit var binding:FragmentUpateBinding
    val viewmodel: NoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentUpateBinding.inflate(layoutInflater,container,false)

        binding.edittitle.setText(oldnotes.data.title)
        binding.editcontent.setText(oldnotes.data.content)


        binding.updatebtn.setOnClickListener {
            updateNotes(it)
        }
        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title=binding.edittitle.text.toString()
        val content=binding.editcontent.text.toString()

        val d= Date()
        val notesdate:CharSequence= DateFormat.format("MMMM d,yyyy ",d.time)

        val notes= Notes(oldnotes.data.id,title,content,notesdate.toString())
        viewmodel.updateNotes(notes)
        Toast.makeText(activity,"Notes Updated Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_upate_Fragment_to_home_fragment)

    }
    //this is for if you want to insert menubar with code
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.deletebar -> {
                    val bottomSheet= BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
                    bottomSheet.setContentView(R.layout.dialog_box)

                    val btnno=bottomSheet.findViewById<Button>(R.id.dialognobtn)
                    val btnyes=bottomSheet.findViewById<Button>(R.id.dialogyesbtn)

                    btnno?.setOnClickListener {
                        bottomSheet.dismiss()
                    }
                    btnyes?.setOnClickListener {
                        val notes= Notes(oldnotes.data.id, "", "", "")
                        viewmodel.deleteNotes(notes)
                        Navigation.findNavController(requireView()).navigate(R.id.action_upate_Fragment_to_home_fragment)
                        Toast.makeText(activity,"Note has been deleted",Toast.LENGTH_SHORT).show()
                        bottomSheet.dismiss()
                    }

                    bottomSheet.show()
                    true
                }
                else -> false
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

}