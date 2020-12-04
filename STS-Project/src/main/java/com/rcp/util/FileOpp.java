package com.rcp.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOpp {
	public static byte[] fileToByte(File file) {
		try (FileInputStream fis = new FileInputStream(file)){
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for(int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf,0,readNum);
			}
	
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			
			return new byte[1];
		}
		
		
		
		
	}
	public static File byteToFile(byte[] bytes, String name) {
		try(FileOutputStream fos = new FileOutputStream("files/"+name)){
			fos.write(bytes);
			return new File("files/"+name);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			return new File("files/error.file");
		} catch (IOException e) {
		
			e.printStackTrace();
			return new File("files/error.file");
		}
	}
}
