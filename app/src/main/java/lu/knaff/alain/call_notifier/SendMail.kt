package lu.knaff.alain.call_notifier

import java.util.Properties
import java.util.function.Consumer
import androidx.preference.PreferenceManager
import android.util.Log
import android.content.Context

import jakarta.mail.Message
import jakarta.mail.Session
import jakarta.mail.Transport
import jakarta.mail.PasswordAuthentication
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage


import java.util.concurrent.Executors
import java.util.concurrent.ExecutorService

class SendMail {

    class Authenticator(val user: String?, val pass: String?) :
        jakarta.mail.Authenticator() {
        protected override fun getPasswordAuthentication():
            PasswordAuthentication {
            return PasswordAuthentication(user, pass)
        }
    }

    companion object {
        private final val TAG="SendMail"

        private val executor : ExecutorService by lazy {
            Executors.newSingleThreadExecutor() }

        fun send(context: Context, subject: String, contents: String,
                 errNotif: Consumer<Exception>?) {
            val preferences = PreferenceManager
                .getDefaultSharedPreferences(context).all
            val props  = Properties()
            props.put("mail.smtp.host", preferences.get("mail_host"))
            props.put("mail.smtp.auth", "true")
            props.put("mail.smtp.starttls.enable", "true")
            props.put("mail.smtp.port", preferences.get("mail_port"))
            val auth = Authenticator(preferences.get("mail_user") as String?,
                                     preferences.get("mail_password") as String?)
            val session = Session.getInstance(props,auth)

            try {
                val message = MimeMessage(session)
                // set From email field
                message.setFrom(InternetAddress(preferences.get("mail_sender") as String?))
                // set To email field
                message.setRecipient(Message.RecipientType.TO, InternetAddress(preferences.get("mail_recipient") as String?))
                // set email subject field
                message.setSubject(subject)

                // set the content of the email message
                message.setText(contents)

                // send the email message
                executor.submit() {
                    Log.i(TAG, "Sending mail")
                    try {
                        Transport.send(message)
                        Log.i(TAG, "Sent mail")
                    } catch(e: Exception) {
                        Log.e(TAG, "Exception while sending mail ",e)
                        if(errNotif != null)
                            errNotif.accept(e)
                    }
                }
            } catch(e: Exception) {
                Log.e(TAG, "Exception while sending mail ",e)
                if(errNotif != null)
                    errNotif.accept(e)
            }
        }
    }
}
