/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.groupf.timercomponent;

import java.awt.Font;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Baker
 */
public class TimerComponent extends JLabel implements Serializable {

    private String alarma = "00:00:00";
    private AlarmInterface alarmI;

    public TimerComponent() {
        super("00:00:00");
        setFont(new Font("Segoe UI", Font.BOLD, 24));
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);

        // Inicializa el Timer
        Timer timer = new Timer();

        // Crea una tarea que se ejecuta periódicamente
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Código a ejecutar cada segundo
                String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                setText(timeStamp);
                if (alarma.equals(timeStamp)) {
                    alarmI.timeReached();
                }
            }
        };

        // Programa la tarea para que se ejecute cada 1000 milisegundos (1 segundo)
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public String getAlarma() {
        return alarma;
    }

    public void setAlarma(String alarma) {
        //Regex 00:00:00
        String regex = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(alarma);

        //Matcher
        if (matcher.matches()) {
            this.alarma = alarma;
        } else {
            JOptionPane.showMessageDialog(null, "Formato no válido: '" + alarma + "'", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public AlarmInterface getAlarmI() {
        return alarmI;
    }

    public void setAlarmI(AlarmInterface alarmI) {
        this.alarmI = alarmI;
    }

}
