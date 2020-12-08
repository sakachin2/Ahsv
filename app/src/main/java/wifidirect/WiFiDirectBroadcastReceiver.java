//*CID://+va40R~:                             update#=   31;       //~va40R~
//*************************************************************************//~1A65I~
//2020/11/04 va40 of BTMJ5 Android10(api29) upgrade                //~va40I~
//1Ah8 2020/06/01 detect wifip2p discovery stopped(available from API16 android4.1 JellyBean)//~1Ah8I~
//1Ac4 2015/07/06 WD:try disable wifi direct at unpair             //~1Ac4I~
//1A6s 2015/02/17 move NFC starter from WifiDirect dialog to MainFrame//~1A65I~
//1A65 2015/01/29 implement Wifi-Direct function(>=Api14:android4.0)//~1A65I~
//*************************************************************************//~1A65I~
/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wifidirect;                                                //~1A65R~

import jagoclient.Dump;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;                               //~1A65I~
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
//import android.net.NetworkInfo;                                  //~va40R~
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.net.wifi.p2p.WifiP2pInfo;                           //~va40I~
//~1A65I~
import androidx.core.app.ActivityCompat;

import com.Ahsv.AG;                                                //~1A65I~
import com.Ahsv.AView;
import com.Ahsv.R;
import com.Ahsv.Utils;
import com.btmtest.utils.UView;

/**
 * A BroadcastReceiver that notifies of important wifi p2p events.
 */
@TargetApi(AG.ICE_CREAM_SANDWICH)   //api14                           //~1A65R~
public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

    //  private WifiP2pManager manager;                                //~1A65R~
    protected WifiP2pManager manager;                              //~1A65I~
    //  private Channel channel;                                       //~1A65R~
    protected Channel channel;                                     //~1A65I~
    private WiFiDirectActivity activity;

    /**
     * @param manager WifiP2pManager system service
     * @param channel Wifi p2p channel
     * @param activity activity associated with the receiver
     */
    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, Channel channel,
                                       WiFiDirectActivity activity) {
        super();
        this.manager = manager;
        this.channel = channel;
        this.activity = activity;
    }

    /*
     * (non-Javadoc)
     * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
     * android.content.Intent)
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onReceive(Context context, Intent intent) {
        try                                                          //~1A65I~
        {                                                            //~1A65I~
            String action = intent.getAction();
            if (Dump.Y)
                Dump.println("WiFiDirectBroadCastReceiver.onReceive action=" + action);//~1Ah8I~
            if (WifiP2pManager.WIFI_P2P_DISCOVERY_CHANGED_ACTION.equals(action))//~1Ah8I~
            {                                                          //~1Ah8I~
                int state = intent.getIntExtra(WifiP2pManager.EXTRA_DISCOVERY_STATE, -1);//~1Ah8I~
                if (Dump.Y)
                    Dump.println("WiFiDirectBroadCastReceiver:onReceive:P2P_DISCOVERY_CHANGED state=" + state);//~1Ah8R~
                if (state == WifiP2pManager.WIFI_P2P_DISCOVERY_STOPPED) //=1//~1Ah8R~
                {                                                      //~1Ah8I~
                    AView.showToastLong(R.string.WD_DiscoveryStopped); //~1Ah8R~
                    WDA.getDeviceListFragment().onStoppedDiscovery();  //~1Ah8I~
                }                                                      //~1Ah8I~
//            else                                                 //~1Ah8R~
//            if (state == WifiP2pManager.WIFI_P2P_DISCOVERY_STARTED)//=2//~1Ah8R~
//            {                                                    //~1Ah8R~
//                AView.showToast(R.string.WD_DiscoveryStarted);          //~5219I~//~1Ah8R~
//            }                                                    //~1Ah8R~
            }                                                          //~1Ah8I~
            else                                                       //~1Ah8I~
                if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {

                    // UI update to indicate wifi p2p status.
                    int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
                    if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                        // Wifi Direct mode is enabled
                        activity.setIsWifiP2pEnabled(true);
//              WDA.disableWiFi();//@@@@test                       //~1Ac4I~
                    } else {
                        activity.setIsWifiP2pEnabled(false);
                        activity.resetData();

                    }
//          Log.d(WiFiDirectActivity.TAG, "P2P state changed - " + state);//~1A65R~
                    if (Dump.Y)
                        Dump.println("WiFiDirectBroadCastReceiver:onReceive:P2P state changed - " + state);//~1A65I~
                } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

                    // request available peers from the wifi p2p manager. This is an
                    // asynchronous call and the calling activity is notified with a
                    // callback on PeerListListener.onPeersAvailable()
                    if (manager != null) {
//              manager.requestPeers(channel, (PeerListListener) activity.getFragmentManager()//~1A65R~
//                      .findFragmentById(R.id.frag_list));        //~1A65R~
//                      if (ActivityCompat.checkSelfPermission(AG.context,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)//~va40R~
                        if (!UView.isPermissionGrantedLocation())  //+va40R~
                        {                                          //~va40R~
                            // TODO: Consider calling              //~va40R~
                            //    ActivityCompat#requestPermissions//~va40R~
                            // here to request the missing permissions, and then overriding//~va40R~
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,//~va40R~
                            //                                          int[] grantResults)//~va40R~
                            // to handle the case where the user grants the permission. See the documentation//~va40R~
                            // for ActivityCompat#requestPermissions for more details.//~va40R~
                            return;                                //~va40R~
                        }                                          //~va40R~
                        manager.requestPeers(channel, (PeerListListener) WDA.getDeviceListFragment());//~1A65R~
                    }
//          Log.d(WiFiDirectActivity.TAG, "P2P peers changed");    //~1A65R~
                    if (Dump.Y)
                        Dump.println("WiFiDirectBroadCastReceiver:onReceive:P2P peers changed");//~1A65I~
                } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

                    if (manager == null) {
                        return;
                    }
                    if (Dump.Y)
                        Dump.println("WiFiDirectBroadCastReceiver:onReceive:P2P connection changed");//~1A65I~
                    //~va40I~
                    boolean swPaired;                                        //~va40I~
                    WifiP2pInfo p2pinfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_INFO);//~va40I~
                    swPaired = p2pinfo.groupFormed;                        //~va40I~
                    if (Dump.Y)
                        Dump.println("WiFiDirectBroadcastReceiver.onReceive swPaired=" + swPaired + ",WifiP2pInfo=" + Utils.toString(p2pinfo));//~va40I~

//          NetworkInfo networkInfo = (NetworkInfo) intent         //~va40R~
//                  .getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);//~va40R~
//          if (networkInfo.isConnected()) {                       //~va40R~
                    if (swPaired)                                          //~va40I~
                    {                                                      //~va40I~

                        // we are connected with the other device, request connection
                        // info to find group owner IP

//              DeviceDetailFragment fragment = (DeviceDetailFragment) activity//~1A65R~
//                      .getFragmentManager().findFragmentById(R.id.frag_detail);//~1A65R~
                        DeviceDetailFragment fragment = WDA.getDeviceDetailFragment();//~1A65I~
                        if (Dump.Y)
                            Dump.println("WiFiDirectBroadCastReceiver:onReceive:requestConnectionInfo");//~1A65I~
                        manager.requestConnectionInfo(channel, fragment);
//                      if (ActivityCompat.checkSelfPermission(AG.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)//~va40R~
                        if (!UView.isPermissionGrantedLocation())  //+va40R~
                        {                                          //~va40I~
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        manager.requestGroupInfo(channel, fragment/*GroupInfoListener*/);//~1A65I~
            } else {
                // It's a disconnect
                activity.resetData();
            }
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            if (Dump.Y) Dump.println("WiFiDirectBroadCastReceiver:onReceive:P2P this device changed");//~1A65I~
//          DeviceListFragment fragment = (DeviceListFragment) activity.getFragmentManager()//~1A65R~
//                  .findFragmentById(R.id.frag_list);             //~1A65R~
            DeviceListFragment fragment = WDA.getDeviceListFragment();//~1A65R~
            fragment.updateThisDevice((WifiP2pDevice) intent.getParcelableExtra(
                    WifiP2pManager.EXTRA_WIFI_P2P_DEVICE));

        }
      }                                                            //~1A65I~
      catch(Exception e)                                            //~1A65I~
      {                                                            //~1A65I~
      	Dump.println(e,"WiFiDirectBroadcastReceiver:onReceive");   //~1A65R~
      }                                                            //~1A65I~
    }
}
