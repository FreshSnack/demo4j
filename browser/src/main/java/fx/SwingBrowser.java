package fx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: mxding
 * @Date: 2018-12-27 15:51
 */
public class SwingBrowser {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame frame = new JFrame("JavaFX in Swing");
            JFXPanel jfxPanel = new JFXPanel();
            frame.setLayout(new BorderLayout());
            frame.add(jfxPanel, BorderLayout.CENTER);
            Platform.runLater(()->{
                WebView webView = new WebView();
                Scene scene = new Scene(webView);
                WebEngine webEngine = webView.getEngine();
                webEngine.load("http://www.baidu.com");
                jfxPanel.setScene(scene);
            });
            frame.setSize(1000,800);
            frame.setVisible(true);
        });
    }
}
