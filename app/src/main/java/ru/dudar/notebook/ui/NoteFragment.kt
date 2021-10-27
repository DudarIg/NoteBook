package ru.dudar.notebook.ui

import android.widget.EditText
import android.widget.Button
import ru.dudar.notebook.domain.NoteEntity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import ru.dudar.notebook.R
import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.fragment.app.Fragment


class NoteFragment : Fragment() {
    lateinit var titleEditText: EditText
    lateinit var detailEditText: EditText
    lateinit var saveButton: Button
    private var dataId = -1
    private var setData: NoteEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_note_edit, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            setData = requireArguments().getSerializable(ARG_PARAM) as NoteEntity
        }

        if (savedInstanceState != null)
            setData = savedInstanceState.getSerializable("nnn") as NoteEntity

        titleEditText = view.findViewById(R.id.title_edit_text)
        detailEditText = view.findViewById(R.id.delail_edit_text)
        saveButton = view.findViewById(R.id.save_button)

        if (setData != null) {
            dataId = setData!!.id
            titleEditText.setText(setData!!.title)
            detailEditText.setText(setData!!.detail)
        }

        saveButton.setOnClickListener(View.OnClickListener { v: View? ->
            val resultNote = NoteEntity(
                titleEditText.getText().toString(),
                detailEditText.getText().toString()
            )
            resultNote.id = dataId
            if (resultNote.id != -1) {
                (activity as NotesListActivity?)!!.notesRepo.editNote(resultNote.id, resultNote)
            }
            if (resultNote.id == -1) {
                (activity as NotesListActivity?)!!.notesRepo.createNote(resultNote)
            }
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) (activity as NotesListActivity?)!!.openListFr()
            requireActivity().supportFragmentManager.popBackStack()
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("nnn", setData)
    }

    companion object {
        private const val ARG_PARAM = "param"
        @JvmStatic
        fun newInstance(tempData: NoteEntity?): NoteFragment {
            val noteFragment = NoteFragment()
            val bindle = Bundle()
            bindle.putSerializable(ARG_PARAM, tempData)
            noteFragment.arguments = bindle
            return noteFragment
        }
    }
}