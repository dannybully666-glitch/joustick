package com.joystickapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHidDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.util.Log;

public class HidGamepadService {

    private static BluetoothHidDevice hidDevice;
    private static BluetoothHidDevice.Callback callback;

    public static void init(Context context) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        callback = new BluetoothHidDevice.Callback() {};

        adapter.getProfileProxy(context, new BluetoothProfile.ServiceListener() {
            @Override
            public void onServiceConnected(int profile, BluetoothProfile proxy) {
                hidDevice = (BluetoothHidDevice) proxy;
                register(context);
            }

            @Override
            public void onServiceDisconnected(int profile) {
                hidDevice = null;
            }
        }, BluetoothProfile.HID_DEVICE);
    }

    private static void register(Context context) {
        byte[] descriptor = new byte[]{
            0x05, 0x01,       // Usage Page (Generic Desktop)
            0x09, 0x05,       // Usage (Game Pad)
            (byte)0xA1, 0x01, // Collection (Application)

            0x05, 0x01,
            0x09, 0x30,       // X
            0x09, 0x31,       // Y
            0x15, (byte)0x81, // Min -127
            0x25, 0x7F,       // Max 127
            0x75, 0x08,
            0x95, 0x02,
            (byte)0x81, 0x02, // Input (Data,Var,Abs)

            (byte)0xC0
        };

        hidDevice.registerApp(
                descriptor,
                null,
                null,
                Runnable::run,
                callback
        );

        Log.d("HID", "Gamepad registered");
    }

    public static void sendAxis(float x, float y) {
        if (hidDevice == null) return;

        byte[] report = new byte[2];
        report[0] = (byte) (x * 127);
        report[1] = (byte) (y * 127);

        hidDevice.sendReport(0, report);
    }
}
