package com.jekirdek.client.util;

public class SmartCardUtil {

	public static native String loginSmartCardWithApplet(String pin)/*-{
																	var cardApplet = $doc.smartCardApplet;
																	return cardApplet.loginSmartCard(pin);
																	}-*/;

	public static native String countCertificate() /*-{
													var cardApplet = $doc.smartCardApplet;
													return cardApplet.countCertificate();

													}-*/;

	public static native String[] smartCardInfoFromApplet() /*-{
																var cardApplet = $doc.smartCardApplet;
																return cardApplet.selectedCertInfo();
																}-*/;

	public static native String signDocument(String signableInput)/*-{
																	var cardApplet = $doc.smartCardApplet;
																	return cardApplet.signDocument(signableInput);
																	}-*/;

}
