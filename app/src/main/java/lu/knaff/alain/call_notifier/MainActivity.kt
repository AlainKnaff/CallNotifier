package lu.knaff.alain.call_notifier

import android.util.Log

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import android.app.role.RoleManager
import android.content.Intent

import lu.knaff.alain.call_notifier.ui.theme.CallNotifierTheme

class MainActivity : ComponentActivity() {
    private final val TAG="MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CallNotifierTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private val REQUEST_ID = 1;

    fun requestRole() {
	val roleManager: RoleManager = getSystemService(ROLE_SERVICE) as RoleManager
	val intent: Intent  = roleManager
	    .createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)
	startActivityForResult(intent, REQUEST_ID)
    }

    @Deprecated("")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
	if (requestCode == REQUEST_ID) {
            if (resultCode == android.app.Activity.RESULT_OK) {
		Log.i(TAG, "Your app is now the call screening app")
            } else {
		Log.i(TAG, "Your app is not the call screening app")
            }
	} else {
	    super.onActivityResult(requestCode, resultCode, data)
	}
    }

    override fun onStart() {
	super.onStart()
	requestRole()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CallNotifierTheme {
        Greeting("Android")
    }
}
