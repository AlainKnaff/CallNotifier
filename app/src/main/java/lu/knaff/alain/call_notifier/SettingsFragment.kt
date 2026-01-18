package lu.knaff.alain.call_notifier;

import android.text.InputType
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

import androidx.preference.EditTextPreference

class SettingsFragment : PreferenceFragmentCompat() {
    private fun fixInputType(prefName: String,
                             inputType: Int) {
        val preference: EditTextPreference? = findPreference(prefName)
        if(preference == null)
            return // not found
        preference.setOnBindEditTextListener() {
            editText -> editText.setInputType(inputType);
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        /* androidx.preference ignores android:inputType xml
         *s attribute, redo it manually here */
        fixInputType("mail_port",
                     InputType.TYPE_CLASS_NUMBER)
        fixInputType("mail_password",
                     InputType.TYPE_CLASS_TEXT or
                     InputType.TYPE_TEXT_VARIATION_PASSWORD)
    }
}
