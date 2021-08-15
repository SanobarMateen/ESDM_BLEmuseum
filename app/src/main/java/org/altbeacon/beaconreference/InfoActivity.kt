package org.altbeacon.beaconreference

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_info.*
import org.altbeacon.beacon.Beacon
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.MonitorNotifier

class InfoActivity: AppCompatActivity() {
    lateinit var beaconReferenceApplication: BeaconReferenceApplication
    //val match = listOf("1f6b3981-f247-4721-b847-bc62d412ff8e","75b8098b-729e-4307-8a21-d9b8d729204b", "3df861a4-0acd-4e47-8d49-8bfb478a0d2e")
    private val m1 = "1f6b3981-f247-4721-b847-bc62d412ff8e"
    private val m2 = "75b8098b-729e-4307-8a21-d9b8d729204b"
    private val m3 = "3df861a4-0acd-4e47-8d49-8bfb478a0d2e"

    private val f1 = MonaLisaFragment()
    private val f2 = GlowOFHopeFragment()
    private val f3 = StarryNightFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val b = baseFragment()

        beaconReferenceApplication = application as BeaconReferenceApplication
        // These two lines set up a Live Data observer so this Activity can get beacon data from the Application class
        beaconReferenceApplication.rangingData.beacons.observe(this, rangingObserver)
        //beaconReferenceApplication.disableMonitoring()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flfragment, b)
            addToBackStack(null)
            commit()
        }

        back.setOnClickListener {
            finish()
        }
    }

    // This gets called from the BeaconReferenceApplication when ranging callbacks change things
    private val rangingObserver = Observer<Collection<Beacon>> { beacons ->
        val uid = beacons.map { it.id1 }
        val dis = beacons.map { it.distance }
        //Toast.makeText(this,"uuid $uid", Toast.LENGTH_LONG).show()
        /*
        for ((i, item) in uid.withIndex()){
            if(match[i].compareTo(item.toString(),true) == 0){
             Toast.makeText(this,"Match found - $item",Toast.LENGTH_LONG).show()
            }
        }
        */

        for (item in uid){
            if(m1.compareTo(item.toString(),true) == 0){
                //Toast.makeText(this,"Match found1 - $item",Toast.LENGTH_LONG).show()

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flfragment, f1)
                    addToBackStack(null)
                    commit()
                }
            }
            else if(m2.compareTo(item.toString(),true) == 0){
                //Toast.makeText(this,"Match found2 - $item",Toast.LENGTH_LONG).show()

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flfragment, f2)
                    addToBackStack(null)
                    commit()
                }
            }
            else if(m3.compareTo(item.toString(),true) == 0){
                //Toast.makeText(this,"Match found3 - $item",Toast.LENGTH_LONG).show()

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flfragment, f3)
                    addToBackStack(null)
                    commit()
                }
            }
            else{
                val TAG = "InfoActivity"
                Log.i(TAG,item.toString())
                //Toast.makeText(this,"Match Not found",Toast.LENGTH_LONG).show()
            }
        }
    }
}


