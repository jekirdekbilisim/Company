package com.jekirdek.client.util;

import java.io.Serializable;
import java.util.Arrays;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FileDownloadItem implements Serializable, IsSerializable {

	private static final long	serialVersionUID	= 8556454785908422747L;

	private byte[]				fileContent;
	private String				fileContentType;
	private String				fileName;

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(fileContent);
		result = prime * result + ((fileContentType == null) ? 0 : fileContentType.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileDownloadItem other = (FileDownloadItem) obj;
		if (!Arrays.equals(fileContent, other.fileContent))
			return false;
		if (fileContentType == null) {
			if (other.fileContentType != null)
				return false;
		} else if (!fileContentType.equals(other.fileContentType))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FileDownloadItem [fileContentType=" + fileContentType + ", fileName=" + fileName + "]";
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
