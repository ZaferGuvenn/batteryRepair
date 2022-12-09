package com.lafimsize.batteryrepair

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lafimsize.batteryrepair.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var batteryBroadcast: BroadcastReceiver
    private lateinit var iFilter: IntentFilter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        intentFilterAndBroadcast()

        registerReceiver(batteryBroadcast,iFilter)
    }

    fun intentFilterAndBroadcast() {
        iFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)


        batteryBroadcast= object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                intent?.apply {
                    val temp=getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0)
                    // show the battery temperate in text view
                    binding.sicaklik.text = "$temp${0x00B0.toChar()}C"
                    println("yeni= "+temp)
                    if (temp<185){
                        binding.calisma.setTextColor(Color.RED)
                        //işlemler yaptır..

                        var i=0
                        while (i<10000){
                            var yor=(i*temp)+(i*temp)+(i*temp)+(i*temp)+(i*temp)+(i*temp)+(i*temp)+(i*temp)-
                                    (i*temp)-(i*temp)-(i*temp)-(i*temp)-(i*temp)-(i*temp)-(i*temp)-(i*temp)+
                                    1
                            binding.calisma.text=yor.toString()
                            i++
                        }
                    }else{
                        binding.calisma.setTextColor(Color.GREEN)
                    }
                }
            }

        }
    }
}