package ml.bimdev.baseapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import ml.bimdev.baseapp.fragments.*

class MainActivity : AppCompatActivity(),
    BoxesFragment.OnBoxesFabClickListener, AddBoxFragment.OnAddBoxFragmentFilled {

    private var isOnLastFragment = false
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        nav_view.setNavigationItemSelectedListener(this::onNavigationItemSelected)
        nav_view.setCheckedItem(R.id.nav_inbox)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.nav_host_fragment, InboxFragment())
            .addToBackStack("root")
            .commit()
        title = nav_view.menu.findItem(R.id.nav_inbox).title
        drawer.closeDrawer(GravityCompat.START)

        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() = when {
        drawer.isDrawerOpen(GravityCompat.START) -> drawer.closeDrawer(GravityCompat.START)
        supportFragmentManager.backStackEntryCount > 1 -> {
            if (isOnLastFragment) {
                supportFragmentManager.popBackStack(
                    "root",
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                isOnLastFragment = false
            } else supportFragmentManager.popBackStack()
        }
        else -> super.onBackPressed()
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment = getFragmentById(item.itemId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
        title = item.title
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBoxesFabClicked() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_right)
            .replace(R.id.nav_host_fragment, AddBoxFragment())
            .addToBackStack(AddBoxFragment::class.java.name)
            .commit()
        title = "Добавление ящика"
    }

    override fun onAddBoxFragmentFilled(email: String, password: String, syncEnabled: Boolean) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.animator.enter_from_top, R.animator.exit_to_right)
            .replace(
                R.id.nav_host_fragment,
                BoxAddedFragment.newInstance(email, password, syncEnabled)
            )
            .addToBackStack(AddBoxFragment::class.java.name)
            .commit()
        isOnLastFragment = true
    }

    private fun getFragmentById(itemId: Int) = when (itemId) {
        R.id.nav_inbox -> InboxFragment()
        R.id.nav_sent -> SentFragment()
        R.id.nav_drafts -> DraftsFragments()
        R.id.nav_email_boxes -> BoxesFragment()
        else -> error("No such item in drawer")
    }
}
