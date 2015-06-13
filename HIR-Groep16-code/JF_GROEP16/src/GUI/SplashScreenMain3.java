package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;
import javax.swing.UnsupportedLookAndFeelException;

public class SplashScreenMain3 {

  SplashScreen screen;
  String email = "";
  String naamTA = "";
  
  public SplashScreenMain3(String email ,String naamTA) {
        this.email = email;
        this.naamTA = naamTA;
        
       EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//                    ex.printStackTrace();
//                }

                splashScreenInit();
            }
        });

  }

  private void splashScreenDestruct() {
    screen.setScreenVisible(false);
  }

  private void splashScreenInit() {
    ImageIcon myImage = new ImageIcon(getClass().getResource("emailsplash.gif"));
    screen = new SplashScreen(myImage);
    screen.setLocationRelativeTo(null);
    screen.setProgressMax(100);
    screen.setScreenVisible(true);
    
            
    
    new ResourceLoader().execute();

  }

  public static void main(String[] args)
  {
//    try {
//      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    }
    new SplashScreenMain();
  }
  
  public class ResourceLoader extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {

            // Wait a little while, maybe while loading resources
                UIManager.put("ProgressBar.foreground", Color.BLUE);
                screen.getProgressBar().setIndeterminate(true);

                
                SendMail stuurmail = new SendMail();
                stuurmail.stuurTakeAwayRegistratieMail(email, naamTA);
                GUI_Inloggen guiWindow = new GUI_Inloggen() ;
                guiWindow.setVisible(true);
                guiWindow.setLocationRelativeTo(null);
                guiWindow.setResizable(false); 

            return null;

        }

        @Override
        protected void done() {
            screen.getProgressBar().setIndeterminate(false);
            splashScreenDestruct();
        }


    }

}


