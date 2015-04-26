package com.jekirdek.server.util;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.fileupload.FileItem;

import com.jekirdek.client.util.MthsException;

public class ServerConvertUtil {

	public static Clob fileItem2Clob(FileItem fileItem) {
		if (fileItem == null || fileItem.get() == null)
			return null;
		String fileContent = null;
		SerialClob clob = null;
		try {
			fileContent = new String(fileItem.get(), "UTF-8");
			clob = new SerialClob(fileContent.toCharArray());
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return clob;
	}

	public static String fileItem2String(FileItem fileItem) {
		if (fileItem == null || fileItem.get() == null)
			return null;
		String fileContent = null;
		try {
			fileContent = new String(fileItem.get(), "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return fileContent;
	}

	public static String formattedByte(long bytes) {
		int unit = 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = ("KMGTPE").charAt(exp - 1) + ("i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	public static Blob fileItem2Blob(FileItem fileItem) throws MthsException {
		if (fileItem == null || fileItem.get() == null)
			throw new MthsException("Dosya boş veya hatalı, tekrar dener misiniz?");
		Blob blob = null;
		try {
			blob = new SerialBlob(fileItem.get());
		}
		catch (SerialException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return blob;
	}
}
