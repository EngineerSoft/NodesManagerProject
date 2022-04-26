package com.mascir.nodesmanagerproject

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbDeviceConnection
import android.hardware.usb.UsbManager
import android.util.Log
import android.widget.Toast
import com.felhr.usbserial.UsbSerialDevice
import com.felhr.usbserial.UsbSerialInterface

class UsbSerialClass(private val context: Context, private var m_usbManager: UsbManager) {

    companion object{
        val ACTION_USB_PERMISSION = "permission"
    }


    var m_device: UsbDevice? = null
    var m_serial: UsbSerialDevice? = null
    var m_connection: UsbDeviceConnection? = null


    fun startUsbConnecting() {
        val usbDevices: HashMap<String, UsbDevice>? = m_usbManager.deviceList
        if(!usbDevices?.isEmpty()!!) {
            var keep = true
            usbDevices.forEach { entry ->
                m_device = entry.value
                val deviceVendorId: Int? = m_device?.vendorId
                Toast.makeText(context, "vendorId: " + deviceVendorId, Toast.LENGTH_LONG).show()
                if(deviceVendorId == 1155){
                    val intent: PendingIntent = PendingIntent.getBroadcast(context, 0, Intent(ACTION_USB_PERMISSION), 0)
                    m_usbManager.requestPermission(m_device, intent)
                    keep = false
                    Toast.makeText(context, "connection successful", Toast.LENGTH_LONG).show()
                }else{
                    m_connection = null
                    m_device = null
                    Toast.makeText(context, "Unable to connect", Toast.LENGTH_LONG).show()
                }
                if(!keep){
                    return
                }
            }
        }else{
            Toast.makeText(context, "No device connected", Toast.LENGTH_LONG).show()
        }
    }

    fun sendData(input: String) {
        m_serial?.write(input.toByteArray())
        Log.i("Serial", "Sending data: " + input.toByteArray())
    }

    fun readData(){
        val intBuffer = 6 // SerialPort.BytesToRead;
        val bytesReceived = ByteArray(intBuffer)
        //val indata: Int = m_serial.read(bytesReceived, 0, intBuffer)

    }

    fun disconnect() {
        m_serial?.close()
    }

    val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent?.action == ACTION_USB_PERMISSION){
                val granted: Boolean = intent.extras!!.getBoolean(UsbManager.EXTRA_PERMISSION_GRANTED)
                if(granted){
                    m_connection = m_usbManager.openDevice(m_device)
                    m_serial = UsbSerialDevice.createUsbSerialDevice(m_device, m_connection)
                    if(m_serial != null){
                        if(m_serial!!.open()){
                            m_serial!!.setBaudRate(115200)
                            m_serial!!.setDataBits(UsbSerialInterface.DATA_BITS_8)
                            m_serial!!.setStopBits(UsbSerialInterface.STOP_BITS_1)
                            m_serial!!.setParity(UsbSerialInterface.PARITY_NONE)
                            m_serial!!.setFlowControl(UsbSerialInterface.FLOW_CONTROL_OFF)
                        }else {
                            Toast.makeText(context, "Port not open", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(context, "Port is null", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, "Permission not granted", Toast.LENGTH_LONG).show()
                }
            } else if(intent?.action == UsbManager.ACTION_USB_DEVICE_ATTACHED){
                startUsbConnecting()
            } else if(intent?.action == UsbManager.ACTION_USB_DEVICE_DETACHED){
                disconnect()
            }
        }
    }

    fun getUsbBroadcastReceiver() : BroadcastReceiver{
        return broadcastReceiver
    }
}