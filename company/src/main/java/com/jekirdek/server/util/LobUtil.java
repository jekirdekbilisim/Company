package com.jekirdek.server.util;

import java.sql.Blob;
import java.sql.SQLException;

public class LobUtil {

	public static byte[] blobToByte(Blob blob) {
		if (blob == null)
			return null;
		int blobLength;
		try {
			blobLength = (int) blob.length();
			return blob.getBytes(1, blobLength);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
