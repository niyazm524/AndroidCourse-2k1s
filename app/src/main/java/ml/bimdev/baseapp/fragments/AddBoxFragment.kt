package ml.bimdev.baseapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_add_email_box.*
import ml.bimdev.baseapp.R

class AddBoxFragment : Fragment() {

    private var onFilledListener: OnAddBoxFragmentFilled? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAddBoxFragmentFilled) onFilledListener = context
        else throw RuntimeException("Context should implement OnAddBoxFragmentFilled interface")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_email_box, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_add_box.setOnClickListener {
            onFilledListener
                ?.onAddBoxFragmentFilled(
                    et_email.text.toString(), et_password.text.toString(),
                    sw_sync_enabled.isEnabled
                )
        }
    }

    override fun onDetach() {
        super.onDetach()
        onFilledListener = null
    }

    interface OnAddBoxFragmentFilled {
        fun onAddBoxFragmentFilled(email: String, password: String, syncEnabled: Boolean = true)
    }

}
