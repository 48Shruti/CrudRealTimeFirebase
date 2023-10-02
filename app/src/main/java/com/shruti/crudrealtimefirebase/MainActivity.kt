package com.shruti.crudrealtimefirebase

import android.app.Dialog
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.recyclerview.widget.LinearLayoutManager
import com.shruti.crudrealtimefirebase.databinding.ActivityMainBinding
import com.shruti.crudrealtimefirebase.databinding.CustomDialogBinding

class MainActivity : AppCompatActivity(),NotesInterface {
    lateinit var binding : ActivityMainBinding
    lateinit var adapter: RecyclerAdapter
    lateinit var layoutManager: LinearLayoutManager
    var item = arrayListOf<NotesDataClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        layoutManager = LinearLayoutManager(this)
        binding.recyclerlist.layoutManager = layoutManager
        adapter = RecyclerAdapter(item,this)
        binding.recyclerlist.adapter = adapter
        binding.title = "Crud Using Real Time FireBase"
        binding.mainActivity = this
    }
    fun fabButton(){
        var dialog = Dialog(this)
        var dialogBinding = CustomDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.title = "Title"
        dialogBinding.description = "Description"
        dialogBinding.save = "Save"
        dialogBinding.btnsave.setOnClickListener {
            if (dialogBinding.ettitle.text.isNullOrEmpty()) {
                dialogBinding.ettitle.error = "Enter title"
            } else if (dialogBinding.etdescription.text.isNullOrEmpty()) {
                dialogBinding.etdescription.error = "Enter description"
            } else {
                item.add(
                    NotesDataClass(
                        title = dialogBinding.ettitle.text.toString(),
                        description = dialogBinding.etdescription.text.toString()
                    )
                )

                dialog.dismiss()
            }
        }
        dialog.show()
    }

    override fun updateNotes(dataClass: NotesDataClass, position: Int) {
        var dialog = Dialog(this)
        var dialogBinding = CustomDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.title = "Title"
        dialogBinding.description = "Description"
        dialogBinding.save = "Save"
        dialogBinding.ettitle.setText(dataClass.title)
        dialogBinding.etdescription.setText(dataClass.description)
        dialogBinding.btnsave.setOnClickListener {
            if (dialogBinding.ettitle.text.isNullOrEmpty()){
                dialogBinding.ettitle.error ="Enter title"
            }
            else if (dialogBinding.etdescription.text.isNullOrEmpty()){
                dialogBinding.etdescription.error ="Enter description"
            }
            else
            {
//                item.set(position, NotesDataClass(title = dialogBinding.ettitle.text.toString(),
//                description = dialogBinding.etdescription.text.toString())
//                )

                dialog.dismiss()
            }
            adapter.notifyDataSetChanged()
        }
        dialog.show()

    }
    override fun deleteNotes(dataClass: NotesDataClass, position: Int) {
        item.removeAt(position)
        adapter.notifyDataSetChanged()
    }
}