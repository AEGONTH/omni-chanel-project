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
			int freq = 698;
			int shortDuration = 200;
			int longDuration = 500;
			int breakDuation = 200;
			
			System.out.println("Start");
			Kernel32 lib = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);
			lib.Beep(freq, shortDuration);
			lib.Beep(freq, longDuration);
			lib.Sleep(breakDuation);
			
//			lib.Beep(freq, longDuration);
//			lib.Beep(freq, longDuration);
//			lib.Sleep(breakDuation);
//			lib.Beep(freq, longDuration);
//			lib.Beep(freq, longDuration);
//			lib.Beep(freq, longDuration);
//			lib.Sleep(breakDuation);
//			lib.Beep(freq, longDuration);
//			lib.Sleep(breakDuation);
//			lib.Beep(freq, shortDuration);
//			lib.Beep(freq, shortDuration);
//			lib.Beep(freq, shortDuration);
//			lib.Beep(freq, shortDuration);
			System.out.println("Done");
			
			String txt = "Ivory Coast (C\u00F4te d\'Ivoire)";
			System.out.println(txt);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
