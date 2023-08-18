package com.mdshamimislam.all_in_one_pdf.activity
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.preference.PreferenceManager
import android.provider.Settings
import android.util.SparseIntArray
import android.view.MenuItem
import android.widget.RelativeLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView
import com.mdshamimislam.all_in_one_pdf.R
import com.mdshamimislam.all_in_one_pdf.Util.BuildConfig
import com.mdshamimislam.all_in_one_pdf.Util.PermissionsUtils
import com.mdshamimislam.all_in_one_pdf.Util.constants
import com.mdshamimislam.all_in_one_pdf.Util.constants.Companion.LAUNCH_COUNT
import com.mdshamimislam.all_in_one_pdf.Util.constants.Companion.REQUEST_CODE_FOR_WRITE_PERMISSION
import com.mdshamimislam.all_in_one_pdf.Util.constants.Companion.THEME_BLACK
import com.mdshamimislam.all_in_one_pdf.Util.constants.Companion.THEME_DARK
import com.mdshamimislam.all_in_one_pdf.Util.constants.Companion.THEME_SYSTEM
import com.mdshamimislam.all_in_one_pdf.Util.constants.Companion.THEME_WHITE
import com.mdshamimislam.all_in_one_pdf.Util.constants.Companion.VERSION_NAME
import com.mdshamimislam.all_in_one_pdf.Util.constants.Companion.WRITE_PERMISSIONS

class MainActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener {

    private var mFeedbackUtils: FeedbackUtils? = null
    private var mNavigationView: NavigationView? = null
     var mSharedPreferences: SharedPreferences? = null
    private val mFragmentSelectedMap: SparseIntArray? = null
    private var mFragmentManagement: FragmentManagement? = null

    private var mSettingsActivityOpenedForManageStoragePermission = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNavigationView = findViewById(R.id.nav_view)


        setThemeOnActivityExclusiveComponents()
        checkAndAskForStoragePermission()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        setSupportActionBar(toolbar)

        // Set navigation drawer

        // Set navigation drawer
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.app_name, R.string.app_name
        )

        //Replaced setDrawerListener with addDrawerListener because it was deprecated.

        //Replaced setDrawerListener with addDrawerListener because it was deprecated.
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        // initialize values

        // initialize values
        initializeValues()

        setXMLParsers()
        // Check for app shortcuts & select default fragment
        // Check for app shortcuts & select default fragment
        val fragment: Fragment = mFragmentManagement.checkForAppShortcutClicked()

        // Check if  images are received

        // Check if  images are received
        handleReceivedImagesIntent(fragment)

        displayFeedBackAndWhatsNew()
//        if (!isStoragePermissionGranted()) {
//            Log.d("TTTG", "onCreate: here1");
//            getRuntimePermissions();
//        }
        //check for welcome activity
        //        if (!isStoragePermissionGranted()) {
//            Log.d("TTTG", "onCreate: here1");
//            getRuntimePermissions();
//        }
        //check for welcome activity
        openWelcomeActivity()


    }

    private fun displayFeedBackAndWhatsNew() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        var count = mSharedPreferences!!.getInt(LAUNCH_COUNT, 0)
        if (count > 0 && count % 15 == 0) {
            mFeedbackUtils.rateUs()
        }
        if (count != -1) {
            mSharedPreferences!!.edit().putInt(LAUNCH_COUNT, count + 1).apply()
        }
        val versionName = mSharedPreferences!!.getString(VERSION_NAME, "")
        if (versionName != null && versionName != BuildConfig.VERSION_NAME) {
            WhatsNewUtils.getInstance().displayDialog(this)
            mSharedPreferences!!.edit().putString(VERSION_NAME, BuildConfig.VERSION_NAME).apply()
        }
    }

    private fun handleReceivedImagesIntent(fragment: Fragment) {
        val intent = intent
        val action = intent.action
        val type = intent.type
        if (type == null || !type.startsWith("image/")) return
        if (Intent.ACTION_SEND_MULTIPLE == action) {
            handleSendMultipleImages(intent, fragment) // Handle multiple images
        } else if (Intent.ACTION_SEND == action) {
            handleSendImage(intent, fragment) // Handle single image
        }
    }

    private fun handleSendMultipleImages(intent: Intent, fragment: Fragment) {
        val imageUris = intent.getParcelableArrayListExtra<Uri>(Intent.EXTRA_STREAM)
        if (imageUris != null) {
            val bundle = Bundle()
            bundle.putParcelableArrayList(getString(R.string.bundleKey), imageUris)
            fragment.arguments = bundle
        }
    }
    private fun handleSendImage(intent: Intent, fragment: Fragment) {
        val uri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
        val imageUris = ArrayList<Uri?>()
        imageUris.add(uri)
        val bundle = Bundle()
        bundle.putParcelableArrayList(getString(R.string.bundleKey), imageUris)
        fragment.arguments = bundle
    }

    private fun setXMLParsers() {
        System.setProperty(
            "org.apache.poi.javax.xml.stream.XMLInputFactory",
            "com.fasterxml.aalto.stax.InputFactoryImpl"
        )
        System.setProperty(
            "org.apache.poi.javax.xml.stream.XMLOutputFactory",
            "com.fasterxml.aalto.stax.OutputFactoryImpl"
        )
        System.setProperty(
            "org.apache.poi.javax.xml.stream.XMLEventFactory",
            "com.fasterxml.aalto.stax.EventFactoryImpl"
        )
    }

    private fun initializeValues() {
        mFeedbackUtils = FeedbackUtils(this)
        mNavigationView!!.setNavigationItemSelectedListener(this)
        mNavigationView!!.setCheckedItem(R.id.nav_home)

        mFragmentManagement = FragmentManagement(this, mNavigationView)
        setTitleMap()
    }

    private fun setThemeOnActivityExclusiveComponents() {
        val toolbarBackgroundLayout = findViewById<RelativeLayout>(R.id.toolbar_background_layout)
        val content = findViewById<MaterialCardView>(R.id.content)
        val mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themeName = mSharedPreferences.getString(
            constants.DEFAULT_THEME_TEXT,
            constants.DEFAULT_THEME
        )
        when (themeName) {
            THEME_WHITE -> {
                toolbarBackgroundLayout.setBackgroundResource(R.drawable.toolbar_bg)
                content.setCardBackgroundColor(resources.getColor(R.color.lighter_gray))
                mNavigationView!!.setBackgroundResource(R.color.white)
            }
            THEME_BLACK -> {
                toolbarBackgroundLayout.setBackgroundResource(R.color.black)
                content.setCardBackgroundColor(resources.getColor(R.color.black))
                mNavigationView!!.setBackgroundResource(R.color.black)
                mNavigationView!!.itemTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.white))
                mNavigationView!!.itemIconTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.white))
                mNavigationView!!.setItemBackgroundResource(R.drawable.navigation_item_selected_bg_selector_dark)
            }
            THEME_DARK -> {
                toolbarBackgroundLayout.setBackgroundResource(R.color.colorBlackAltLight)
                content.setCardBackgroundColor(resources.getColor(R.color.colorBlackAlt))
                mNavigationView!!.setBackgroundResource(R.color.colorBlackAlt)
                mNavigationView!!.itemTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.white))
                mNavigationView!!.itemIconTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.white))
                mNavigationView!!.setItemBackgroundResource(R.drawable.navigation_item_selected_bg_selector_dark)
            }
            THEME_SYSTEM -> if (this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
                toolbarBackgroundLayout.setBackgroundResource(R.color.colorBlackAltLight)
                content.setCardBackgroundColor(resources.getColor(R.color.colorBlackAlt))
                mNavigationView!!.setBackgroundResource(R.color.colorBlackAlt)
                mNavigationView!!.itemTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.white))
                mNavigationView!!.itemIconTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.white))
                mNavigationView!!.setItemBackgroundResource(R.drawable.navigation_item_selected_bg_selector_dark)
            } else {
                toolbarBackgroundLayout.setBackgroundResource(R.drawable.toolbar_bg)
                content.setCardBackgroundColor(resources.getColor(R.color.lighter_gray))
                mNavigationView!!.setBackgroundResource(R.color.white)
            }
            else -> if (this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
                toolbarBackgroundLayout.setBackgroundResource(R.color.colorBlackAltLight)
                content.setCardBackgroundColor(resources.getColor(R.color.colorBlackAlt))
                mNavigationView!!.setBackgroundResource(R.color.colorBlackAlt)
                mNavigationView!!.itemTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.white))
                mNavigationView!!.itemIconTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.white))
                mNavigationView!!.setItemBackgroundResource(R.drawable.navigation_item_selected_bg_selector_dark)
            } else {
                toolbarBackgroundLayout.setBackgroundResource(R.drawable.toolbar_bg)
                content.setCardBackgroundColor(resources.getColor(R.color.lighter_gray))
                mNavigationView!!.setBackgroundResource(R.color.white)
            }
        }
    }


    private fun checkAndAskForStoragePermission() {
        if (!PermissionsUtils.getInstance()?.checkRuntimePermissions(this, WRITE_PERMISSIONS)!!) {
            getRuntimePermissions()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (!Environment.isExternalStorageManager()) {
                    askStorageManagerPermission()
                }
            }
        }
    }

    private fun askStorageManagerPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                AlertDialog.Builder(this)
                    .setTitle(R.string.one_more_thing_text)
                    .setMessage(R.string.storage_manager_permission_rationale)
                    .setCancelable(false)
                    .setPositiveButton(R.string.allow_text) { dialog, which ->
                        val intent =
                            Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        mSettingsActivityOpenedForManageStoragePermission = true
                        startActivity(intent)
                        dialog.dismiss()
                    }
                    .setNegativeButton(R.string.close_app_text) { dialog, which -> finishAndRemoveTask() }
                    .show()
            }
        }
    }

    private fun getRuntimePermissions() {
        PermissionsUtils.getInstance()?.requestRuntimePermissions(
            this,
            WRITE_PERMISSIONS,
            REQUEST_CODE_FOR_WRITE_PERMISSION
        )
    }





    override fun onNavigationItemSelected(item: MenuItem): Boolean {

    }
}