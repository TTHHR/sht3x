package cn.qingyuyu.sht3xtest

import android.app.Activity
import android.os.Bundle
import android.util.Log
import cn.qingyuyu.sht3x.SHT3x

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
private lateinit var device: SHT3x
    private var runnning=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stopbutton.setOnClickListener {
            runnning=false
            if(device!=null)
            {
                device.close()
            }
        }
    }

    override fun onStart() {
        try {
            device= SHT3x("I2C1", 0x44)
            Thread(Runnable {
                while (runnning)
                {
                        device.readFromDevice()
                        runOnUiThread {
                            textView.text="温度：${device.temperature }\n湿度：${device.humidity}"
                        }
                        Thread.sleep(500)
                    }
            }).start()


        }
        catch (e:Exception)
        {
            Log.e("e",e.toString())
        }
        super.onStart()
    }
}
