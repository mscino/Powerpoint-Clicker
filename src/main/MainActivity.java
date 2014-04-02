package main;

import java.awt.AWTException;

import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class MainActivity{
	
	static Server theServer;

	public static void main(String[] args) throws AWTException,
			InterruptedException {
		
		
//		Window appWin = new Window(new Window.onClickListener() {
//			
//			@Override
//			public void onClick(String mInputString) {
//				// TODO Auto-generated method stub
//				System.out.println(mInputString);
//			}
//		});
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		

	    final Window appWin = new Window();
		appWin.main(args);
		//appWin.setText1("Loading...");
		final SysTray trayIconGUI = new SysTray(new SysTray.OnResultListener() {

			@Override
			public void pressedDisconnectByServer() {
				// TODO Auto-generated method stub
				//how to call theServer.disconnect();
				theServer.pressedDisconnect();
			}
		});
		trayIconGUI.setWindow(appWin);
		trayIconGUI.main(args);
		
		
		theServer = new Server(new Server.onResultListener() {

			@Override
			public void showNotificationByServer(String aText) {
				// TODO Auto-generated method stub
				trayIconGUI.showNotification(aText);

			}

			@Override
			public void updateTrayMenuByServer(String aText, boolean aBool) {
				// TODO Auto-generated method stub
				trayIconGUI.updateTrayMenu(aText,aBool);
			}
		});
		
		theServer.setTrayIcon(trayIconGUI);
		theServer.setWindow(appWin);
		theServer.startRunning();
//		InputExecutor mInputExecutor = new InputExecutor();
//		mInputExecutor.setWindow(appWin);
//		//mInputExecutor.run();
	}

}
