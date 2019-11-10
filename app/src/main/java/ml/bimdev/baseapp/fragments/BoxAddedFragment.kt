package ml.bimdev.baseapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_email_added.*
import ml.bimdev.baseapp.R

class BoxAddedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_email_added, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { args ->
            tv_info.text =
                getString(R.string.new_box_info, args.getString("email", "null"))
            tv_sync_status.text = getString(
                if (args.getBoolean("sync_enabled", false))
                    R.string.new_box_sync_enabled
                else
                    R.string.new_box_sync_disabled
            )
        }
    }

    companion object {
        fun newInstance(email: String, password: String, syncEnabled: Boolean) =
            BoxAddedFragment().also { fr ->
                fr.arguments = Bundle().apply {
                    putString("email", email)
                    putString("password", password)
                    putBoolean("sync_enabled", syncEnabled)
                }
            }
    }
}
