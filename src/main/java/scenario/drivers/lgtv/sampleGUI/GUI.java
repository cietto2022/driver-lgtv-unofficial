package scenario.drivers.lgtv.sampleGUI;

import jep.Interpreter;
import scenario.drivers.lgtv.driver.LGTV;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class GUI extends Thread{

    private Data data;
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    LGTV lgtv;

    public GUI(LGTV lgtv, Data data){
        this.data = data;
        this.lgtv = lgtv;
        prepareGUI();
        showJPanelDemo();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Remote Control");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("",JLabel.CENTER );
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
    public void showJPanelDemo(){
        headerLabel.setText("Control in action: Button");

        JButton okButton = new JButton("VOLUME_UP");
        JButton submitButton = new JButton("VOLUME_DOWN");
        JButton cancelButton = new JButton("Cancel");

        okButton.setActionCommand("VOLUME_UP");
        submitButton.setActionCommand("VOLUME_DOWN");
        cancelButton.setActionCommand("Cancel");

        okButton.addActionListener(new ButtonClickListener());
        submitButton.addActionListener(new ButtonClickListener());
        cancelButton.addActionListener(new ButtonClickListener());

        controlPanel.add(okButton);
        controlPanel.add(submitButton);
        controlPanel.add(cancelButton);

        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if( command.equals( "VOLUME_UP" ))  {
                statusLabel.setText("vol up Button clicked.");
                lgtv.notify("VOLUME_UP");
            } else if( command.equals( "VOLUME_DOWN" ) )  {
                statusLabel.setText("vol down Button clicked.");
                lgtv.notify("VOLUME_DOWN");
            } else {
                statusLabel.setText("Cancel Button clicked.");
            }
        }
    }

    public void run(){
        String packets[] = {
                "VOLUME_UP",
                "VOLUME_UP",
                "VOLUME_UP",
                "VOLUME_UP",
                "VOLUME_UP",
                "VOLUME_UP",
                "End"
        };

        for (String packet : packets) {
            data.send(packet);

            // Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
    }

}
