/**
 * This class sends String objects to MOPED.
 */
package potential_couscous.couscousdrive.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class CarCom {
    public final String ACC_KEY = "acckey";
    public final String PLATOON_KEY = "platoonkey";
    public final String MANUAL_KEY = "manualkey";

    private Socket mAutoSocket; // Couscous server socket
    private PrintWriter mAutoOut;

    /**
     * This Constructor will initiate the Sockets and throws exception
     * if not able to establish connection.
     *
     * @param autoSocket
     * @throws IOException
     */
    public CarCom(Socket autoSocket) throws IOException {
        mAutoSocket = autoSocket;
        init();
    }

    private void init() throws IOException {
        mAutoOut = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                mAutoSocket.getOutputStream())), true);
        System.out.println("autoOut complete");
    }

    /**
     * This method checks if CarCom object is connected to MOPED
     *
     * @return true if connected, otherwise false
     */
    public boolean isConnected() {
        if (mAutoSocket != null && mAutoSocket.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * Closing connection to all Sockets and Printwriters
     *
     * @return true if close was done correct. False if something went wrong.
     */
    public boolean close() {
        try {
            mAutoOut.close();
            mAutoSocket.close();

            mAutoSocket = null;
            mAutoOut = null;

            return true;
        } catch (IOException e) {
            return false;
        }

    }

    /**
     * Sending data to different sockets depending on key.
     *
     * @param key  constant values from CarCom
     * @param data String data will be sent to car
     */
    public void sendData(String key, String data) {
        System.out.println(data);

        if (key.equals(ACC_KEY)) {
            if (data != null) {
                mAutoOut.println(data);
                return;
            }
        }

        if (key.equals(ACC_KEY) || key.equals(PLATOON_KEY)) {
            sendData(key);
            return;
        }

        if (key.equals(MANUAL_KEY)) {
            if (data != null) {
                mAutoOut.println(data);
            }
        }
    }

    /**
     * This method sends String objects to car
     *
     * @param key constant values from CarCom
     */
    public void sendData(String key) {
        switch (key) {
            case ACC_KEY:
                mAutoOut.println("a");
                break;
            case PLATOON_KEY:
                mAutoOut.println("p");
                break;
            case MANUAL_KEY:
                mAutoOut.println("m");
                break;
            default:
                // nothing should be done here
                System.out.println("Something went wrong sending data from CarCom class");
        }
    }
}
