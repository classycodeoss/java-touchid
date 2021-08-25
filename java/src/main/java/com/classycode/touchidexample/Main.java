package com.classycode.touchidexample;

import com.sun.jna.Native;

import javax.swing.*;
import java.awt.*;

public class Main {

    private JButton button;
    private JLabel label;

    private JTouchID.AuthCallback touchIDCallback = (result, laError) -> {
        if (result == 1) {
            label.setText("Authentication successful");
        } else {
            label.setText("Authentication failed, LAError is: " + laError);
        }
        button.setEnabled(true);
    };

    private void run() {
        JTouchID nativeLib = Native.load("JTouchID", JTouchID.class);
        final JFrame frame = new JFrame("Touch ID Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 100);

        button = new JButton("Authenticate");
        button.addActionListener(e -> {
            button.setEnabled(false);
            label.setText("");
            nativeLib.touchid_authenticate("Hello from Java", touchIDCallback);
        });
        label = new JLabel();

        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button);
        panel.add(label);

        if (nativeLib.touchid_supported() == 1) {
            button.setEnabled(true);
            label.setText("Touch ID is supported");
        } else {
            button.setEnabled(false);
            label.setText("Touch ID is not supported on this device");
        }

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
