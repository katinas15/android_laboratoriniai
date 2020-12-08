package com.example.libraryble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.UUID;

import no.nordicsemi.android.ble.BleManager;
import no.nordicsemi.android.ble.PhyRequest;
import no.nordicsemi.android.ble.callback.profile.ProfileDataCallback;
import no.nordicsemi.android.ble.data.Data;

public class MyBleManager extends BleManager {
    final static UUID SERVICE_UUID = UUID.fromString("XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX");
    final static UUID FIRST_CHAR = UUID.fromString("XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX");
    final static UUID SECOND_CHAR = UUID.fromString("XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX");

    // Client characteristics
    private BluetoothGattCharacteristic firstCharacteristic, secondCharacteristic;

    MyBleManager(@NonNull final Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected BleManagerGattCallback getGattCallback() {
        return new MyManagerGattCallback();
    }

    @Override
    public void log(final int priority, @NonNull final String message) {
        if (priority == Log.ERROR) {
            Log.println(priority, "MyBleManager", message);
        }
    }

    /**
     * BluetoothGatt callbacks object.
     */
    private class MyManagerGattCallback extends BleManagerGattCallback {

        // This method will be called when the device is connected and services are discovered.
        // You need to obtain references to the characteristics and descriptors that you will use.
        // Return true if all required services are found, false otherwise.
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public boolean isRequiredServiceSupported(@NonNull final BluetoothGatt gatt) {
            final BluetoothGattService service = gatt.getService(SERVICE_UUID);
            if (service != null) {
                firstCharacteristic = service.getCharacteristic(FIRST_CHAR);
                secondCharacteristic = service.getCharacteristic(SECOND_CHAR);
            }
            // Validate properties
            boolean notify = false;
            if (firstCharacteristic != null) {
                final int properties = dataCharacteristic.getProperties();
                notify = (properties & BluetoothGattCharacteristic.PROPERTY_NOTIFY) != 0;
            }
            boolean writeRequest = false;
            if (secondCharacteristic != null) {
                final int properties = controlPointCharacteristic.getProperties();
                writeRequest = (properties & BluetoothGattCharacteristic.PROPERTY_WRITE) != 0;
                secondCharacteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);
            }
            // Return true if all required services have been found
            return firstCharacteristic != null && secondCharacteristic != null
                    && notify && writeRequest;
        }

        // If you have any optional services, allocate them here. Return true only if
        // they are found.
        @Override
        protected boolean isOptionalServiceSupported(@NonNull final BluetoothGatt gatt) {
            return super.isOptionalServiceSupported(gatt);
        }

        // Initialize your device here. Often you need to enable notifications and set required
        // MTU or write some initial data. Do it here.
        @Override
        protected void initialize() {
            // You may enqueue multiple operations. A queue ensures that all operations are
            // performed one after another, but it is not required.
            beginAtomicRequestQueue()
                    .add(requestMtu(247) // Remember, GATT needs 3 bytes extra. This will allow packet size of 244 bytes.
                            .with((device, mtu) -> log(Log.INFO, "MTU set to " + mtu))
                            .fail((device, status) -> log(Log.WARN, "Requested MTU not supported: " + status)))
                    .add(setPreferredPhy(PhyRequest.PHY_LE_2M_MASK, PhyRequest.PHY_LE_2M_MASK, PhyRequest.PHY_OPTION_NO_PREFERRED)
                            .fail((device, status) -> log(Log.WARN, "Requested PHY not supported: " + status)))
                    .add(enableNotifications(firstCharacteristic))
                    .done(device -> log(Log.INFO, "Target initialized"))
                    .enqueue();
            // You may easily enqueue more operations here like such:
            writeCharacteristic(secondCharacteristic, "Hello World!".getBytes())
                    .done(device -> log(Log.INFO, "Greetings sent"))
                    .enqueue();
            // Set a callback for your notifications. You may also use waitForNotification(...).
            // Both callbacks will be called when notification is received.
            setNotificationCallback(firstCharacteristic, callback);
            // If you need to send very long data using Write Without Response, use split()
            // or define your own splitter in split(DataSplitter splitter, WriteProgressCallback cb).
            writeCharacteristic(secondCharacteristic, "Very, very long data that will no fit into MTU")
                    .split()
                    .enqueue();
        }

        @Override
        protected void onDeviceDisconnected() {
            // Device disconnected. Release your references here.
            firstCharacteristic = null;
            secondCharacteristic = null;
        }
    }

    // Define your API.

    private abstract class FluxHandler implements ProfileDataCallback {
        @Override
        public void onDataReceived(@NonNull final BluetoothDevice device, @NonNull final Data data) {
            // Some validation?
            if (data.size() != 1) {
                onInvalidDataReceived(device, data);
                return;
            }
            onFluxCapacitorEngaged();
        }

        abstract void onFluxCapacitorEngaged();
    }

    /**
     * Initialize time machine.
     */
    public void enableFluxCapacitor(final int year) {
        waitForNotification(firstCharacteristic)
                .trigger(
                        writeCharacteristic(secondCharacteristic, new FluxJumpRequest(year))
                                .done(device -> log(Log.INDO, "Power on command sent"))
                )
                .with(new FluxHandler() {
                    public void onFluxCapacitorEngaged() {
                        log(Log.WARN, "Flux Capacitor enabled! Going back to the future in 3 seconds!");

                        sleep(3000).enqueue();
                        write(secondCharacteristic, "Hold on!".getBytes())
                                .done(device -> log(Log.WARN, "It's " + year + "!"))
                                .fail((device, status) -> "Not enough flux? (status: " + status + ")")
                                .enqueue();
                    }
                })
                .enqueue();
    }

    /**
     * Aborts time travel. Call during 3 sec after enabling Flux Capacitor and only if you don't
     * like 2020.
     */
    public void abort() {
        cancelQueue();
    }
}
