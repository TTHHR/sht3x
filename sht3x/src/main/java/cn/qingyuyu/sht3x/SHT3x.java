package cn.qingyuyu.sht3x;

import com.google.android.things.pio.I2cDevice;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;


public class SHT3x {
    private byte start[]={0x22, 0x2B};
    private byte stop[]={(byte)0xE0, 0x00};
    private float temperature,humidity;
    private I2cDevice device=null;
    public SHT3x(String bus,byte addr) throws IOException {
        device = PeripheralManager.getInstance().openI2cDevice(bus, addr);
    }

    public float getTemperature()
    {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void readFromDevice() throws IOException, InterruptedException {
        if(device!=null)
        {
            device.write(start, 2);

            byte[] result = new byte[6];

            device.write(stop, 2);

            Thread.sleep(1);

            device.read(result, 6);

            //get temperature
            float rawData = (float)(((result[0] & 255) << 8) + (result[1] & 255));
            rawData *= 175.0F;
            rawData /= (float)'\uffff';
            temperature=rawData - 45.0f;

            //get humi
            rawData = (float)(((result[3] & 255) << 8) + (result[4] & 255));
            rawData *= (float)100;
            rawData /= (float)'\uffff';
            humidity=rawData;
        }
        else
            throw new IOException("device is null,not open? open fail?");
    }
    public void close() throws IOException {
        if(device!=null)
            device.close();
    }
}
