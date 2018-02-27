package controller;

import client.GmailClient;

public class NotificationController {
	public boolean sendNotice() {

		GmailClient client = new GmailClient();
		try {
			client.sendMessage("time to see stock", "");
		} catch (Exception e) {
			System.err.println("Sending notification failed");
			return false;
		}
		return true;
	}
	
	
	
}
