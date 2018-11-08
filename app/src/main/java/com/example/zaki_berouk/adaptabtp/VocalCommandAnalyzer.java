package com.example.zaki_berouk.adaptabtp;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.example.zaki_berouk.adaptabtp.VocalCommand.CommandProccessed;
import com.example.zaki_berouk.adaptabtp.mocked_data.StaffSetter;
import com.example.zaki_berouk.adaptabtp.model.Staff;

import java.util.ArrayList;
import java.util.List;

public class VocalCommandAnalyzer {

    public VocalCommandAnalyzer() {
    }

    public CommandProccessed analyzeCommand(String userInput, Context context, Boolean isArchitect) {
        StaffSetter staffSetter = new StaffSetter();
        List<Staff> whole_staff = staffSetter.staffListRetrieve();
        String[] splitted_input = userInput.split(" ");
        String command;
        String content = "";
        List<Staff> targets = new ArrayList<>();

        if (splitted_input[0].contains("alert") || splitted_input[0].contains("probl")) {
            command = "Alerte";
            if (isArchitect) {
                String message = "AdaptaBTP !!" + userInput
                        + "!! from: Zaki, artiste faience de toilette";
                return new CommandProccessed(command, whole_staff, message);
            } else {
                for (Staff s : whole_staff) {
                    if (s.getRole().contains("Architect")) {
                        targets.add(s);
                    }
                }
                String message = "AdaptaBTP !!" + userInput
                        + "!! from: Zaki, artiste faience de toilette";
                return new CommandProccessed(command, targets, message);
            }
        } else if (splitted_input[0].contains("message") || splitted_input[0].contains("text")) {
            command = "Message";
            String target_splitter = "";
            for (Staff s : whole_staff) {
                if (s.getName().equals(splitted_input[1]) || s.getRole().contains(splitted_input[1])) {
                    targets.add(s);
                    target_splitter = splitted_input[1];
                } else if (splitted_input.length > 2) {
                    if (splitted_input[1].equals("et") || splitted_input[1].equals("à") || splitted_input.equals("au")) {
                        if (s.getName().equals(splitted_input[2]) || s.getRole().equals(splitted_input[2])) {
                            targets.add(s);
                            target_splitter = splitted_input[2];
                        }
                    }
                }
            }

            if (splitted_input.length > 1) {
                String[] content_splitter = userInput.split(target_splitter);
                content = content_splitter[1];
            }

            if (targets.size() == 0) {
                Toast.makeText(context, "Après la commande, donnez jusqu'à 3 destinataires, puis le contenu du message.", Toast.LENGTH_LONG).show();
                return null;
            }

            if (splitted_input.length > 1) {
                String[] content_splitter = userInput.split(target_splitter);
                content = content_splitter[1];
            }
            String message = "AdaptaBTP - " + content
                    + " from Zaki";
            return new CommandProccessed(command, targets, message);

        } else if (splitted_input[0].contains("appel") || splitted_input[0].contains("call")) {
            command = "Appel";
            for (Staff s : whole_staff) {
                if (s.getName().equals(splitted_input[1]) || s.getRole().contains(splitted_input[1])) {
                    targets.add(s);
                }
            }
            return new CommandProccessed(command, targets, "");

        } else {
            //Display an info
            Toast.makeText(context, "Commande invalide, dites Alerte, Appel ou Message. Puis le destinataire et enfin le contenu.", Toast.LENGTH_LONG).show();
            return null;
        }
    }

}
