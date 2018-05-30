# sht3x
sht3x传感器的库
效果：
![Image text](https://github.com/tthhr/sht3x/raw/master/show.png)

使用方法（kotlin语法）：
1、初始化设备类，参数：I2C端口名，设备地址
device= SHT3x("I2C1", 0x44)
2、使Things从设备读取数据
 device.readFromDevice()
3、获取读取到的温湿度
 device.temperature
 
 