package com.mumayank.airsecureproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import com.mumayank.airsecure.AirSecure
import com.mumayank.airsecureproject.ui.theme.AirSecureProjectTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(AirSecure) {
            setSecureWindow(window)
            viewModel.viewModelScope.launch {
                val securityViolations = getAppSecurityReport(this@MainActivity)
                if (securityViolations is AirSecure.AppSecurityReport.AppIsNotSecure) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "App is not secure", Toast.LENGTH_SHORT)
                            .show()
                        // optionally find all the violations here: securityViolations.appSecurityViolationTypes
                    }
                }
                checkAppSecurityReportPeriodically(
                    this@MainActivity
                ) { appSecurityViolationTypes ->
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "App is not secure", Toast.LENGTH_SHORT)
                            .show()
                        // optionally find all the violations here: appSecurityViolationTypes
                    }
                }
            }
        }
        enableEdgeToEdge()
        setContent {
            AirSecureProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                    }
                }
            }
        }
    }
}