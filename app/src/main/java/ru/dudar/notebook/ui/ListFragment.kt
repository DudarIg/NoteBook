package ru.dudar.notebook.ui

import androidx.recyclerview.widget.RecyclerView
import ru.dudar.notebook.ui.NotesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.fragment.app.Fragment
import ru.dudar.notebook.R
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dudar.notebook.domain.NoteEntity
import ru.dudar.notebook.ui.NotesListActivity

class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val adapter = NotesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) requireActivity().supportFragmentManager.popBackStack()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) initRecycleView(view) else adapter.notifyDataSetChanged()
    }

    private fun initRecycleView(view: View) {
        recyclerView = view.findViewById(R.id.recycleview)
        recyclerView.setLayoutManager(LinearLayoutManager(context))
        recyclerView.setAdapter(adapter)
        adapter.setOnItemClickListener { item: NoteEntity -> onItemClick(item) }
        adapter.setOnItemLongClickListener { item: NoteEntity -> onItemLongClick(item) }
        adapter.setData((activity as NotesListActivity?)!!.notesRepo.notes)
    }

    private fun onItemClick(item: NoteEntity) {
        openNoteFragment(item)
    }

    fun onItemLongClick(item: NoteEntity) {
        (activity as NotesListActivity?)!!.notesRepo.deleteNote(item.id)
        adapter.notifyDataSetChanged()
    }

    fun openNoteFragment(item: NoteEntity?) {
        (requireActivity() as Controller).startNoteFragment(item)
    }

    internal interface Controller {
        fun startNoteFragment(item: NoteEntity?)
    }
}