package com.adms.test;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class TestProject {

	public interface TeleCrypt2 extends Library {

	}

	public interface Kernel32 extends Library {
		public boolean Beep(int FREQUENCY, int DURATION);
		public void Sleep(int DURATION);
	}

	public static void main(String[] args) {
		try {
			System.out.println("Start");
			Kernel32 lib = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);
			lib.Beep(698, 500);
			lib.Sleep(500);
			lib.Beep(698, 500);
			lib.Beep(698, 500);
			lib.Sleep(500);
			lib.Beep(698, 500);
			lib.Beep(698, 500);
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
