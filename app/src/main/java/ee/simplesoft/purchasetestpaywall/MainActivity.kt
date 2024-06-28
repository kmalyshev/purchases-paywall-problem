package ee.simplesoft.purchasetestpaywall

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
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesConfiguration
import com.revenuecat.purchases.ui.revenuecatui.PaywallDialog
import com.revenuecat.purchases.ui.revenuecatui.PaywallDialogOptions
import ee.simplesoft.purchasetestpaywall.ui.theme.PurchaseTestPaywallTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Purchases.configure(
            PurchasesConfiguration.Builder(
                this,
                "<insert_any_real_key>",
            ).build()
        )

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PurchaseTestPaywallTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                    val paywallDialogOptions = PaywallDialogOptions.Builder()
                        .setShouldDisplayBlock {
                            false
                        }
                        .setRequiredEntitlementIdentifier("premium")
                        .build()
                    PaywallDialog(paywallDialogOptions = paywallDialogOptions)
                }
            }
        }
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
    PurchaseTestPaywallTheme {
        Greeting("Android")
    }
}