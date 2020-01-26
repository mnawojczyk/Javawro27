package pl.sda.rafal.zientara.swing;

import pl.sda.rafal.zientara.swing.login.LoginWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow {
private JButton button;
private JLabel counter;
private int clickCount = 0;

    public static void main(String[] args) {
        new FirstWindow();
    }

    public FirstWindow(){
        JFrame frame = new JFrame("Siema");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(300,350);

        button = new JButton("Wincyj!");
        button.setBounds(50,50,200,100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("clik!");
                clickCount++;
                String text = Integer.toString(clickCount);
                counter.setText(text);
            }
        });
        frame.add(button);

        counter = new JLabel("0", SwingConstants.CENTER);
        counter.setBounds(50,200,200,100);
        frame.add(counter);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static class Main {
        public static void main(String[] args) {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setListener(new LoginWindow.LoginListener() {
                @Override
                public void onLoginClicked(String password) {
                    System.out.println(password);
                }
            });
        }
    }
}