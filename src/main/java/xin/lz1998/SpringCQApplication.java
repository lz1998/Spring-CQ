package xin.lz1998;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

@SpringBootApplication
public class SpringCQApplication {

    public static void main(String[] args) {
        // 托盘图标，方便Windows退出
        if(SystemTray.isSupported()){
            initTray();
        }
        SpringApplication.run(SpringCQApplication.class, args);
    }
    public static void initTray(){
        // 退出菜单
        MenuItem exitItem =new MenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        PopupMenu popupMenu=new PopupMenu();
        popupMenu.add(exitItem);

        // 创建图标
        URL url=SpringCQApplication.class.getResource("/icon.jpg");
        ImageIcon icon=new ImageIcon(url);
        TrayIcon trayIcon=new TrayIcon(icon.getImage(),"WCA-Data-Service",popupMenu);
        trayIcon.setImageAutoSize(true);
        SystemTray systemTray=SystemTray.getSystemTray();
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
