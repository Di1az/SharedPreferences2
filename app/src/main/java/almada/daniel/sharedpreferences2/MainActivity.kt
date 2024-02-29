package almada.daniel.sharedpreferences2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import almada.daniel.sharedpreferences2.PreferenceHelper.clearValues
import almada.daniel.sharedpreferences2.PreferenceHelper.password
import almada.daniel.sharedpreferences2.PreferenceHelper.defaultPreference
import almada.daniel.sharedpreferences2.PreferenceHelper.userId

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val CUSTOM_PREF_NAME = "User_data"
    lateinit var btnSave: Button
    lateinit var btnClear: Button
    lateinit var btnShow: Button
    lateinit var btnShowDefault: Button
    lateinit var inPassword: TextView
    lateinit var inUserId: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inPassword = findViewById(R.id.inPassword)
        inUserId = findViewById(R.id.inUserId)
        btnSave = findViewById(R.id.btnSave)
        btnClear = findViewById(R.id.btnClear)
        btnShow = findViewById(R.id.btnShow)
        btnShowDefault = findViewById(R.id.btnShowDefault)

        btnSave.setOnClickListener(this)
        btnClear.setOnClickListener(this)
        btnShow.setOnClickListener(this)
        btnShowDefault.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val prefs = PreferenceHelper.customPreference(this, CUSTOM_PREF_NAME)
        when (v?.id) {
            R.id.btnSave -> {
                if (inPassword.text.toString().isEmpty() || inUserId.text.toString().isEmpty()) {

                } else {
                    prefs.password = inPassword.text.toString()
                    prefs.userId = inUserId.text.toString().toInt()
                }
            }
            R.id.btnClear -> {
                prefs.clearValues()
            }
            R.id.btnShow -> {
                if (prefs.password.isNullOrEmpty()) {
                    inUserId.setText("")
                    inPassword.setText(prefs.password)
                } else {
                    inUserId.setText(prefs.userId.toString())
                    inPassword.setText(prefs.password)
                }
            }
            R.id.btnShowDefault -> {
                val defaultPrefs = defaultPreference(this)
                if (defaultPrefs.userId == 0) {
                    inUserId.setText("")
                } else {
                    inUserId.setText(defaultPrefs.userId.toString())
                }
                inPassword.setText(defaultPrefs.password)
            }
        }
    }
}