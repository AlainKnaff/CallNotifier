package lu.knaff.alain.call_notifier.services

import java.util.Date

import android.util.Log

import android.telecom.Call
import android.telecom.CallScreeningService

import lu.knaff.alain.call_notifier.SendMail

public class CallNotificationService : CallScreeningService() {
    private final val TAG="CallScreeningService"

    override fun onScreenCall (callDetails: Call.Details) {
	Log.i(TAG, "Call notified");

	if(callDetails.getCallDirection() == Call.Details.DIRECTION_INCOMING) {
            var number = "unknown"
	    val handle = callDetails.handle
	    if(handle != null) {
                number = handle.schemeSpecificPart
	        Log.i(TAG, "Phone number: "+number)
	    }
            respondToCall(callDetails, CallResponse.Builder().build())
            val msg = "Call from "+number+" on "+Date();
            SendMail.send(this, msg, msg, null)
	}
    }
}
