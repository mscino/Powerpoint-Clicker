package main;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server {

	// private ServerSocket server;
	// private Socket connection;
	// private ObjectOutputStream output;
	// private ObjectInputStream input;
	private int portNumber = 53549;
	private boolean listening;
	private Window appWin;
	private SysTray trayIconGUI;
	private boolean mobileConnected;
	private onResultListener mOnResultListener;
	public boolean disconnectPressed = false;
	private Socket connectedSocket;

	public void pressedDisconnect() {
		disconnectPressed = true;
		try {
			connectedSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setWindow(Window t) {
		this.appWin = t;
	}

	public void setTrayIcon(SysTray t) {
		this.trayIconGUI = t;
	}

	public Server(onResultListener onResultListener) {
		// Keep MainActivity's listener function in mOnResultListener
		mOnResultListener = onResultListener;
	}

	public void startRunning() {

		listening = true;
		ServerSocket serverSocket = null;
		try {

			serverSocket = new ServerSocket(portNumber);
			appWin.setText1("Wating for people to connect");
			while (listening) {
				// System.out.println("Waiting for someone to connect...");
				new MultiServerThread(serverSocket.accept()).start();
			}
			serverSocket.close();

		} catch (IOException e) {
			System.err.println("Could not listen on port: "
					+ Integer.toString(6789));
			System.exit(-1);
		}
	}

	private class MultiServerThread extends Thread {
		private Socket socket = null;
		String mData = "";

		public MultiServerThread(Socket socket) {
			this.socket = socket;

		}

		public void run() {

			try {
				// System.out.println("Now connected"
				// + socket.getInetAddress().getHostName());
				// appWin.setText1("Now connected: " +
				// socket.getInetAddress().getHostName());

				// Below line will connect to mobile, takes awhile
				// appWin.setText1("Found Mobile: "
				// + socket.getInetAddress().getHostName());
				appWin.setText1("Someone Scanning");
				// Thread.sleep(2000);
				final BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				// trayIconGUI.showNotification("Connected");
				String tInputLine = null;
				// while loop becomes false when app is forced closed
				while ((tInputLine = inFromClient.readLine()) != null) {
					if (!mobileConnected) {
						connectedSocket = socket;
						mOnResultListener.showNotificationByServer("Connected");
						mOnResultListener.updateTrayMenuByServer(
								"Connected   ", true);
						appWin.setText1("Found Mobile");
						mobileConnected = true;
					}
					InputExecutor execute = new InputExecutor();
					execute.setWindow(appWin);
					// System.out.println(tInputLine);
					appWin.setText2(tInputLine);
					execute.sendKey(tInputLine);
				}
				if (mobileConnected) {
					appWin.setText1("Disconnected,Waiting for new connection");
					trayIconGUI.showNotification("Disconnected");
					mOnResultListener.updateTrayMenuByServer("Disconnected",
							false);
				}
				mobileConnected = false;

				// System.out.println("Closing Socket");
				// if (mobileFound) {
				// appWin.setText1("Closing Socket: Refreshing");
				// } else {
				// appWin.setText1("Waiting for connection");
				// }
				socket.close();
				inFromClient.close();
				// Thread.sleep(1000);

			} catch (Exception e) {
				if ((disconnectPressed == true)
						&& e.toString().equals(
								"java.net.SocketException: socket closed")) {
					appWin.setText1("Disconnected,Waiting for new connection");
					trayIconGUI.showNotification("Disconnected");
					mOnResultListener.updateTrayMenuByServer("Disconnected",
							false);
					mobileConnected = false;
					disconnectPressed = false;
				} else {
					System.out.println(e.toString());
					appWin.setText1("Error");
				}
			}
		}
	}

	// close Streams and Sockets
	// private void closeServer() {
	// // System.out.println("Closing Connections... ");
	// appWin.showMessage("Closing Connections...");
	// try {
	// output.close();
	// if (input != null) {
	// input.close();
	// }
	// connection.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	interface onResultListener {
		void showNotificationByServer(String aText);

		void updateTrayMenuByServer(String aText, boolean aBool);
	}
}
