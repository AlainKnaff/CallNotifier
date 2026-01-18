package lu.knaff.alain.call_notifier.services

import android.util.Log

import android.telecom.Call
import android.telecom.CallScreeningService

public class CallNotificationService : CallScreeningService() {
    private final val TAG="CallScreeningService"

    override fun onScreenCall (callDetails: Call.Details) {
	Log.i(TAG, "call detected");

	if(callDetails.getCallDirection() == Call.Details.DIRECTION_INCOMING) {
	    Log.i(TAG, "Call from display name: "+callDetails.getCallerDisplayName())
	    val handle = callDetails.handle
	    if(handle != null)
		Log.i(TAG, "Handle: "+handle.schemeSpecificPart)
	    respondToCall(callDetails, CallResponse.Builder().build())
	}
    }
}
