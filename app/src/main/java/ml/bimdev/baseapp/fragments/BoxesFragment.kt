package ml.bimdev.baseapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_email_boxes.*
import ml.bimdev.baseapp.R

class BoxesFragment : Fragment() {

    private var listener: OnBoxesFabClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBoxesFabClickListener) listener = context
        else throw RuntimeException("Context should implement OnBoxesFabClickListener interface")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_email_boxes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_add_box.setOnClickListener {
            listener?.onBoxesFabClicked()
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnBoxesFabClickListener {
        fun onBoxesFabClicked()
    }
}
