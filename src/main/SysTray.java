package main;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class SysTray {

	public String appName = "Powerpoint Clicker";
	
	private Window appWin;
	private boolean isShowingPopup = false;
	private boolean isShowingMessageDialog = false;
	final TrayIcon trayIcon = new TrayIcon(createImage("images/iconSys.png",
			"tray icon"));
	final JPopupMenu popup = new JPopupMenu();
	final SystemTray tray = SystemTray.getSystemTray();
	JMenuItem aboutItem = new JMenuItem("About "+ appName);
	JMenuItem exitItem = new JMenuItem("Exit");
	JMenuItem status = new JMenuItem("Status: Disconnected");
	JMenuItem disconnect = new JMenuItem("Disconnect");
	//boolean disconnectPressed;
	private OnResultListener mOnResultListener;
	
	public SysTray(OnResultListener onResultListener)
	{
		mOnResultListener = onResultListener;
	}
	
	public void setWindow(Window t) {
		this.appWin = t;
	}
	
	public void showNotification(String text){
		trayIcon.displayMessage(appName,
				text, TrayIcon.MessageType.INFO);
		}
	
	public void updateTrayMenu(String sText, Boolean dText){
	status.setText("Status: " + sText);
	disconnect.setEnabled(dText);
	popup.add(status,0);
	}
	
	void main(String[] args) {
		/* Use an appropriate Look and Feel */
		try {
			UIManager
					.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		// Schedule a job for the event-dispatching thread:
		// adding TrayIcon.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private void createAndShowGUI() {
		// Check the SystemTray support
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}

		// Create a popup menu components

		// Add components to popup menu
		popup.add(status);
		popup.add(disconnect);
		popup.addSeparator();
		popup.add(aboutItem);
		popup.addSeparator();
		popup.add(exitItem);
		
		disconnect.setEnabled(false);
		status.setEnabled(false);


		trayIcon.setImageAutoSize(true);
		//trayIcon.setPopupMenu(popup);

		//Setup HiddenDialog for systemTray
		final JDialog hiddenDialog = new JDialog();
		hiddenDialog.setSize(10, 10);
        hiddenDialog.setUndecorated(true);
        hiddenDialog.setAlwaysOnTop(true);
        hiddenDialog.setOpacity(0);
		
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
			return;
		}

		
		trayIcon.displayMessage(appName,
				"Server has started", TrayIcon.MessageType.INFO);
		
		disconnect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mOnResultListener.pressedDisconnectByServer();
				//disconnectPressed = true;
			}
		});

		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isShowingMessageDialog = true;
				JOptionPane.showMessageDialog(null, "mScino", "About "+appName,
						JOptionPane.INFORMATION_MESSAGE);
				isShowingMessageDialog = false;
			}
		});
				
		
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				System.exit(0);
			}
		});

		trayIcon.setToolTip(appName);

		trayIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isShowingMessageDialog){
				appWin.showFrame();
				}else{
                    hiddenDialog.setVisible(true);
				}
			}
		});
		
	    /* Add the window focus listener to the hidden dialog */
	    hiddenDialog.addWindowFocusListener(new WindowFocusListener () {
	        @Override
	        public void windowLostFocus (WindowEvent we ) {
	        	  //System.out.println("LOST FOCUS");
	            popup.setVisible(false);
	        }
	        @Override
	        public void windowGainedFocus (WindowEvent we) {}
	    });
	    
		trayIcon.addMouseListener(new MouseAdapter() {
	        public void mouseReleased(MouseEvent e) {
	            if (e.isPopupTrigger()) {
	            	if(!isShowingMessageDialog){
	            	hiddenDialog.setLocation(e.getX(), e.getY());
                    // Now the popup menu's invoker is the hidden dialog
                    popup.setInvoker(hiddenDialog);
                    hiddenDialog.setVisible(true);

	                popup.setLocation(e.getX(), e.getY()-115);
	                popup.setInvoker(popup);
	                popup.setVisible(true);
	                isShowingPopup = true;
	            	}else
	            	{
	                    hiddenDialog.setVisible(true);
	            	}
	            }
	        }
	    });
		
	}

	// Obtain the image URL
	protected static Image createImage(String path, String description) {
		URL imageURL = SysTray.class.getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}
	
	interface OnResultListener{
		void pressedDisconnectByServer();
	}
	
}
