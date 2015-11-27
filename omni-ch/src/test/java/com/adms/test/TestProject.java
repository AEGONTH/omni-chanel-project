package com.adms.test;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class TestProject {

	public interface TeleCrypt2 extends Library {
		public String EncryptField(String val);
	}

	public interface Kernel32 extends Library {
		public boolean Beep(int FREQUENCY, int DURATION);
		public void Sleep(int DURATION);
	}

	public static void main(String[] args) {
		try {
			String a = "a";
			String b = "a";
			
			System.out.println(a == b);
			System.out.println(a.compareTo(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
