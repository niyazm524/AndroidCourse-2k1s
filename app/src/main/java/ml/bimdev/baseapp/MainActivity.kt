package ml.bimdev.baseapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        nav_view.setNavigationItemSelectedListener(this::onNavigationItemSelected)
        nav_view.setCheckedItem(R.id.nav_inbox)
        onNavigationItemSelected(nav_view.menu.findItem(R.id.nav_inbox))
        fab.hide()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onBackPressed() = when {
        drawer.isDrawerOpen(GravityCompat.START) -> drawer.closeDrawer(GravityCompat.START)
        supportFragmentManager.backStackEntryCount > 0 ->
            supportFragmentManager.popBackStack()
        else -> super.onBackPressed()
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

        }
        supportActionBar?.title = item.title
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
