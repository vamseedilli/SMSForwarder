package com.vdill.smsforwarder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Switch;

/**
 * Created by vamse on 9/1/2018.
 */

public class SMSListener extends BroadcastReceiver {

    SmsManager smsManager = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_shared_preferences_key), Context.MODE_PRIVATE);
        String messageBody = null;
        String messageSender = null;
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                messageBody = smsMessage.getMessageBody();
                messageSender = smsMessage.getDisplayOriginatingAddress();
            }
            String targetNumber = sharedPreferences.getString(context.getString(R.string.target_phone_number), "0");
            Boolean messageSwitch = sharedPreferences.getBoolean(context.getString(R.string.send_toggle), true);
            if(targetNumber != null && !targetNumber.equals("0") && messageSwitch){
                smsManager.sendTextMessage(targetNumber, null, messageBody, null, null);
            }
        }

    }
}
