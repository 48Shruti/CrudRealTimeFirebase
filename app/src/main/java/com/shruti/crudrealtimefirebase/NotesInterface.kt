package com.shruti.crudrealtimefirebase

interface NotesInterface {
    fun updateNotes(dataClass: NotesDataClass,position : Int)
    fun deleteNotes(dataClass: NotesDataClass,position: Int)
}