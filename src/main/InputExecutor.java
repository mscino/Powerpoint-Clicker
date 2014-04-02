package main;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputExecutor {

	private Robot robot;
	private Window appWin;

	public void setWindow(Window t) {
		this.appWin = t;
	}
	
	public InputExecutor() {
	}

//	public String getKey() {
//		boolean done = false;
//		String[] theWords = { "up", "down", "left", "right", "page up",
//				"page down", "delete", "enter", "spacebar" };
//
//		List<String> listOfCommands = Arrays.asList(theWords);
//		String controlText = "";
//		do {
//			System.out.print("Please enter a command(type exit to quit): ");
//			Scanner textInput = new Scanner(System.in);
//			controlText = textInput.nextLine().toLowerCase();
//			if (listOfCommands.contains(controlText)
//					|| controlText.equals("exit") || controlText.equals("quit")) {
//				System.out.println("Command accepted");
//				done = true;
//				if (controlText.equals("exit") || controlText.equals("quit")) {
//					return "quit";
//				}
//			} else {
//				System.out.println("Please input a proper Command");
//			}
//		} while (done != true);
//		done = false;
//		// System.out.println(controlText);
//		return controlText;
//	}

	public void sendKey(String key) throws AWTException, InterruptedException {
		robot = new Robot();
		//Thread.sleep(3000);

		String[] theWords = { "up", "down", "left", "right", "page up",
				"page down", "delete", "enter", "spacebar" };
		List<String> listOfCommands = Arrays.asList(theWords);
		if (listOfCommands.contains(key)) {
//			System.out.println("Executed: " + key);
			appWin.setText3("Executed: " + key);
			
			switch (key) {
			case "quit":
				// do nothing
				break;
			case "up":
				robot.keyPress(KeyEvent.VK_UP);
				robot.keyRelease(KeyEvent.VK_UP);
				break;
			case "down":
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				break;
			case "left":
				robot.keyPress(KeyEvent.VK_LEFT);
				robot.keyRelease(KeyEvent.VK_LEFT);
				break;
			case "right":
				robot.keyPress(KeyEvent.VK_RIGHT);
				robot.keyRelease(KeyEvent.VK_RIGHT);
				break;
			case "page up":
				robot.keyPress(KeyEvent.VK_PAGE_UP);
				robot.keyRelease(KeyEvent.VK_PAGE_UP);
				break;
			case "page down":
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				break;
			case "delete":
				robot.keyPress(KeyEvent.VK_DELETE);
				robot.keyRelease(KeyEvent.VK_DELETE);
				break;
			case "enter":
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				break;
			case "spacebar":
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				break;
			default:
//				System.out.println("Something Bad has occured: SwitchCase");
				appWin.setText3("Something bad has occured-SwitchCase-InputExecutor");
				break;
			}
		}
	}

//	public void run() throws AWTException, InterruptedException {
//		while (true) {
//			String command = getKey();
//			if (!(command.equals("quit"))) {
//				sendKey(command);
//			} else {
//				break;
//			}
//		}
//		System.out.println("You have exited the Program");
//	}
}
