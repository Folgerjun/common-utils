package com.tonglei.test;

/**
 * CRC16 modbus校验 注意高低位转换
 * 
 * @author ffj
 *
 */
public class CRC16 {
	static byte[] crc16_tab_h = { 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63,
			-127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1,
			-64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64,
			1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0,
			-63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65,
			1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64,
			Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1,
			-64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64,
			Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127,
			64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64,
			Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1,
			-64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63,
			-127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0,
			-63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64,
			Byte.MIN_VALUE, 65, 0, -63, -127, 64 };
	static byte[] crc16_tab_l = { 0, -64, -63, 1, -61, 3, 2, -62, -58, 6, 7, -57, 5, -59, -60, 4, -52, 12, 13, -51, 15,
			-49, -50, 14, 10, -54, -53, 11, -55, 9, 8, -56, -40, 24, 25, -39, 27, -37, -38, 26, 30, -34, -33, 31, -35,
			29, 28, -36, 20, -44, -43, 21, -41, 23, 22, -42, -46, 18, 19, -45, 17, -47, -48, 16, -16, 48, 49, -15, 51,
			-13, -14, 50, 54, -10, -9, 55, -11, 53, 52, -12, 60, -4, -3, 61, -1, 63, 62, -2, -6, 58, 59, -5, 57, -7, -8,
			56, 40, -24, -23, 41, -21, 43, 42, -22, -18, 46, 47, -17, 45, -19, -20, 44, -28, 36, 37, -27, 39, -25, -26,
			38, 34, -30, -29, 35, -31, 33, 32, -32, -96, 96, 97, -95, 99, -93, -94, 98, 102, -90, -89, 103, -91, 101,
			100, -92, 108, -84, -83, 109, -81, 111, 110, -82, -86, 106, 107, -85, 105, -87, -88, 104, 120, -72, -71,
			121, -69, 123, 122, -70, -66, 126, Byte.MAX_VALUE, -65, 125, -67, -68, 124, -76, 116, 117, -75, 119, -73,
			-74, 118, 114, -78, -77, 115, -79, 113, 112, -80, 80, -112, -111, 81, -109, 83, 82, -110, -106, 86, 87,
			-105, 85, -107, -108, 84, -100, 92, 93, -99, 95, -97, -98, 94, 90, -102, -101, 91, -103, 89, 88, -104, -120,
			72, 73, -119, 75, -117, -118, 74, 78, -114, -113, 79, -115, 77, 76, -116, 68, -124, -123, 69, -121, 71, 70,
			-122, -126, 66, 67, -125, 65, -127, Byte.MIN_VALUE, 64 };

	public static int calcCrc16(byte[] data) {
		return calcCrc16(data, 0, data.length);
	}

	public static int calcCrc16(byte[] data, int offset, int len) {
		return calcCrc16(data, offset, len, 65535);
	}

	public static int calcCrc16(byte[] data, int offset, int len, int preval) {
		int ucCRCHi = (preval & 0xFF00) >> 8;
		int ucCRCLo = preval & 0xFF;
		for (int i = 0; i < len; i++) {
			int iIndex = (ucCRCLo ^ data[(offset + i)]) & 0xFF;
			ucCRCLo = ucCRCHi ^ crc16_tab_h[iIndex];
			ucCRCHi = crc16_tab_l[iIndex];
		}
		return (ucCRCHi & 0xFF) << 8 | ucCRCLo & 0xFF & 0xFFFF;
	}

	/**
	 * test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// byte[] data = new byte[] { 0x01, 0x03, 0x3C, 0x01, 0x17, 0x01, 0x14, 0x01,
		// 0x14, 0x01, 0x17, 0x01, 0x16, 0x01,
		// 0x17, 0x01, 0x16, 0x01, 0x16, 0x01, 0x19, 0x01, 0x17, 0x01, 0x15, 0x01, 0x17,
		// 0x01, 0x16, 0x01, 0x18,
		// 0x01, 0x14, 0x01, 0x15, 0x01, 0x12, 0x01, 0x12, 0x01, 0x19, 0x01, 0x17, 0x01,
		// 0x16, 0x01, 0x18, 0x01,
		// 0x12, 0x01, 0x18, 0x01, 0x16, 0x01, 0x17, 0x0B, 0x0B, 0x0B, 0x0B, 0x0B, 0x0B,
		// 0x0B, 0x0B };
		// 01 03 02 58 00 04
		// byte[] data = new byte[] { 0x02, 0x03, 0x02, 0x58, 0x00, 0x04 };
		// System.out.println(calcCrc16(data));
		System.out.println(Float.intBitsToFloat(Integer.parseInt("458B1C89", 16)));
	}
}
