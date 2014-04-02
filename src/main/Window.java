package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Choice;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JTextField;

import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Window {

	private boolean debugWin = false;

	private JFrame frmPowerpointclicker;
	JTextPane textPane1;
	JTextPane textPane2;
	JTextPane textPane3;
	/**
	 * Launch the application.
	 */
	void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Window window = new Window();
					frmPowerpointclicker.setVisible(debugWin);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPowerpointclicker = new JFrame();
		frmPowerpointclicker.setTitle("PowerPointClicker");
		frmPowerpointclicker.setResizable(false);
		frmPowerpointclicker.setBounds(100, 100, 337, 142);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPowerpointclicker.getContentPane().setLayout(null);
		
		textPane1 = new JTextPane();
		textPane1.setEditable(false);
		textPane1.setBackground(SystemColor.menu);
		textPane1.setBounds(10, 11, 311, 20);
		frmPowerpointclicker.getContentPane().add(textPane1);
		
		textPane2 = new JTextPane();
		textPane2.setEditable(false);
		textPane2.setBackground(Color.WHITE);
		textPane2.setBounds(10, 42, 311, 20);
		frmPowerpointclicker.getContentPane().add(textPane2);
		
		textPane3 = new JTextPane();
		textPane3.setEditable(false);
		textPane3.setBackground(Color.WHITE);
		textPane3.setBounds(10, 73, 311, 20);
		frmPowerpointclicker.getContentPane().add(textPane3);
		
		frmPowerpointclicker.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				frmPowerpointclicker.setState(Frame.NORMAL);
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmPowerpointclicker.setVisible(false);

			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void setText1(String text){
		text = "Status: "+text;
		textPane1.setText(text);
	}
	public void setText2(String text){
		text = "String Sent: "+text;
		textPane2.setText(text);
	}
	public void setText3(String text){
		textPane3.setText(text);
	}

	public void showFrame() {
		// TODO Auto-generated method stub
		frmPowerpointclicker.setVisible(debugWin);
		frmPowerpointclicker.setState(Frame.NORMAL);
	}
	
}
