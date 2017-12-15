package yxzz.util;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringUtil {

	public static int page = 0;
	
	public static int rows = 10;
	
	private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyyMMddHHmmss"); // 标准日期时间格式

	private static SimpleDateFormat DATE_TEXT_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 标准日期时间显示格式

	private static int RESERVESNUM;

	private static char reserve[];
	
	private static final String FORMAT_0 = "yyyy-MM-dd HH:mm:ss";
	
	private static final String FORMAT_1 = "yyyy/MM/dd HH:mm:ss";

	private static final char QUOTE_ENCODE[] = "&quot;".toCharArray();

	private static final char AMP_ENCODE[] = "&amp;".toCharArray();

	private static final char LT_ENCODE[] = "&lt;".toCharArray();

	private static final char GT_ENCODE[] = "&gt;".toCharArray();

	public static final long SECOND = 1000L;

	public static final long MINUTE = 60000L;

	public static final long HOUR = 0x36ee80L;

	public static final long DAY = 0x5265c00L;

	public static final long WEEK = 0x240c8400L;

	public static final long KBYTES = 1024L;

	private static MessageDigest digest = null;

	/**
	 * 构�?�函�?
	 */
	public StringUtil() {
	}

	/**
	 * 测试方法
	 */
	public static void main(String[] args) {
	}

	/**
	 * 把布尔型的字符串转换成整�?,true->1,false->0,不区分大小写 不是布尔型的字符串全部转�?0
	 * 
	 * @param booleanStr
	 * @return int
	 */
	public static int booleanStr2int(String booleanStr) {
		return new Boolean(booleanStr).booleanValue() ? 1 : 0;
	}

	/**
	 * 去除字符串头尾的空白,如果是null,则返回空�?
	 * 
	 * @param str
	 * @return String
	 */
	public static String trim(String str) {
		return (str == null) ? "" : str.trim();
	}

	/**
	 * 只�?�用网页上进行字符串null或空白转换成空白 返回&nbsp;,如果有�?�则返回当前�?
	 * 
	 * @param str
	 * @return str
	 */
	public static String webTrim(String str) {
		str = trim(str);
		return str.equals("") ? "&nbsp;" : str;
	}

	/**
	 * 只�?�用网页上进行对象null或空白转换成空白 返回&nbsp;,如果有�?�则返回当前�?
	 * 
	 * @param obj
	 * @return str
	 */
	public static String webTrim(Object obj) {
		String str = trim(obj);
		return str.equals("") ? "&nbsp;" : str;
	}

	/**
	 * 替换字符串，如果当str没�?�，则用defult来替换，否则返回当前的str
	 * 
	 * @param str
	 * @param defult
	 * @return str
	 */
	public static String trim(String str, String defult) {
		return (str == null) ? trim(defult) : str.trim();
	}

	/**
	 * 替换对象，如果当obj==null，则用defult来替换，否则返回当前的obj字符�?
	 * 
	 * @param str
	 * @param defult
	 * @return
	 */
	public static String trim(Object obj, String defult) {
		return (obj == null) ? trim(defult) : obj.toString().trim();
	}

	/**
	 * 格式化Object对象为字符串，如果为null则返回空�?.
	 * 
	 * @param str
	 * @return str
	 */
	public static String trim(Object str) {
		return (str == null) ? "" : str.toString().trim();
	}

	/**
	 * @deprecated
	 * @param str
	 * @see webTrim(String str)
	 * @return
	 */
	public static String trim_web(String str) {
		String tmp = trim(str);
		return (tmp.equals("")) ? "&nbsp;" : tmp;
	}

	/**
	 * 把为null的字符串转换成空白�??
	 * 
	 * @param str
	 * @return str
	 */
	public static String nonNull(String str) {
		return (str == null) ? "" : str;
	}

	/**
	 * 从输入流中转换成字符串流
	 * 
	 * @param in
	 * @return stringbuffer
	 * @throws IOException
	 */
	public static StringBuffer getStringBuffer(InputStream in) throws IOException {
		if (in == null) {
			return new StringBuffer("");
		}
		StringBuffer result = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		char buf[] = new char[1024];
		for (boolean quit = false; !quit;) {
			int len = reader.read(buf);
			if (len < 0) {
				quit = true;
			} else {
				result.append(buf, 0, len);
			}
		}
		reader.close();
		return result;
	}

	/**
	 * 从输入流中转换成字符�?
	 * 
	 * @param in
	 * @return str
	 * @throws IOException
	 */
	public static String getText(InputStream in) throws IOException {
		return getStringBuffer(in).toString();
	}

	/**
	 * 从Reader中获取字符串
	 * 
	 * @param reader
	 * @return str
	 * @throws IOException
	 */
	public static String getText(Reader reader) throws IOException {
		if (reader == null) {
			return "";
		}
		StringBuffer dat = new StringBuffer();
		char buf[] = new char[1024];
		do {
			int l = reader.read(buf);
			if (l >= 0) {
				dat.append(buf, 0, l);
			} else {
				return dat.toString();
			}
		} while (true);
	}

	/**
	 * 字符串转换成数字，转换出错或者为空则返回-1
	 * 
	 * @param str
	 * @return int
	 */
	public static int getAsInt(String str) {
		return getAsInt(str, -1);
	}

	/**
	 * 字符串转换成数字，转换出错或者为空则返回defaultv
	 * 
	 * @param str
	 * @param defaultv
	 * @return
	 */
	public static int getAsInt(String str, int defaultv) {
		if (str == null || "".equals(str)) {
			return defaultv;
		}
		try {
			return Integer.parseInt(str, 10);
		} catch (Exception e) {
			return defaultv;
		}
	}

	/**
	 * 字符串转换成long，转换出错或者为空则返回-1L
	 * 
	 * @param str
	 * @return long
	 */
	public static long getAsLong(String str) {
		return getAsLong(str, -1L);
	}

	/**
	 * 字符串转换成long，转换出错或者为空则返回defaultv
	 * 
	 * @param str
	 * @param defaultv
	 * @return long
	 */
	public static long getAsLong(String str, long defaultv) {
		if (str == null || "".equals(str)) {
			return defaultv;
		}
		try {
			return Long.parseLong(str, 10);
		} catch (Exception e) {
			return defaultv;
		}
	}

	/**
	 * 分离字符串中特定的字符串返回数组 (可以分离特殊的字符串�?"|$")
	 * 
	 * @param str
	 * @param seperator
	 * @return
	 */
	public static String[] split(String str, String seperator) {
		return split(str, seperator, false);
	}

	/**
	 * 分离字符串中特定的字符串返回数组 (可以分离特殊的字符串�?"|$"),并且限定是否继承。默认为不限�?
	 * 
	 * @param str
	 * @param seperator
	 * @param tail
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String[] split(String str, String seperator, boolean tail) {
		if (str == null || str.equals("") || trim(seperator).equals("")) {
			return (tail) ? (new String[] { "" }) : new String[0];
		}
		if (!tail && str.endsWith(seperator)) {
			str = str.substring(0, str.length() - seperator.length());
		}
		ArrayList temp = new ArrayList();
		int oldPos = 0;
		int newPos = str.indexOf(seperator);
		int parentLength = str.length();
		int subStrLength = seperator.length();
		if (newPos != -1) {
			newPos += subStrLength;
		}
		while (newPos <= parentLength && newPos != -1) {
			temp.add(str.substring(oldPos, newPos - subStrLength));
			oldPos = newPos;
			newPos = str.indexOf(seperator, oldPos);
			if (newPos != -1) {
				newPos += seperator.length();
			}
		}
		if (oldPos <= parentLength) {
			temp.add(str.substring(oldPos));
		}
		return (String[]) temp.toArray(new String[temp.size()]);
	}

	/**
	 * �?查特定的字符串在指定的字符串中出现的次数，如果没有则返回0
	 * 
	 * @param src
	 * @param key
	 * @return
	 */
	public static int count(String src, String key) {
		int count = 0;
		for (String temp = src; temp.indexOf(key) >= 0; temp = temp.substring(temp.indexOf(key) + key.length())) {
			count++;
		}
		return count;
	}

	/**
	 * 把指定的字符串src中特定的某些字符oldStr替换成其他的字符newStr
	 * 
	 * @param src
	 * @param oldStr
	 * @param newStr
	 * @return
	 */
	public static String replace(String src, String oldStr, String newStr) {
		StringBuffer result = new StringBuffer();
		boolean found = false;
		if (src == null || oldStr == null || newStr == null) {
			throw new NullPointerException("parameter is null");
		}
		if (oldStr.length() == 0) {
			throw new IllegalArgumentException("illegal parameter");
		}
		int pos = 0;
		int pos1 = 0;
		while (pos < src.length()) {
			pos1 = src.indexOf(oldStr, pos);
			if (pos1 < 0) {
				pos1 = src.length();
				found = false;
			} else {
				found = true;
			}
			result.append(src.substring(pos, pos1));
			if (found) {
				result.append(newStr);
				pos = pos1 + oldStr.length();
			} else {
				pos = pos1;
			}
		}
		return result.toString();
	}

	/**
	 * 指定的字符串中替换一些成特定的编码格式的字符�? �? ? 变成UTF-8�?%3F
	 * 
	 * @param url
	 * @param encode
	 * @return
	 */
	public static String urlEncode(String url, String encode) {
		if (url == null) {
			return "";
		}
		byte dat[];
		if (encode != null) {
			try {
				dat = url.getBytes(encode);
			} catch (UnsupportedEncodingException ex) {
				return "";
			}
		} else {
			dat = url.getBytes();
		}
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < dat.length; i++) {
			int ch = dat[i] & 0xff;
			if (isReserve(ch)) {
				result.append("%");
				result.append(Integer.toHexString(ch).toUpperCase());
			} else {
				result.append((char) ch);
			}
		}
		return result.toString();
	}

	/**
	 * 判断是否存储有指定的字符
	 * 
	 * @param ch
	 * @return
	 */
	private static boolean isReserve(int ch) {
		if (ch > 126 || ch < 32) {
			return true;
		}
		for (int i = 0; i < RESERVESNUM; i++) {
			if (ch == reserve[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 特定的字符串中把<>符号转换成HTML�?&lt;&gt;
	 * 
	 * @param in
	 * @return str
	 */
	public static final String escapeHTMLTags(String in) {
		if (in == null) {
			return null;
		}
		int i = 0;
		int last = 0;
		char input[] = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) ((double) len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>')
				if (ch == '<') {
					if (i > last) {
						out.append(input, last, i - last);
					}
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '>') {
					if (i > last) {
						out.append(input, last, i - last);
					}
					last = i + 1;
					out.append(GT_ENCODE);
				}
		}
		if (last == 0) {
			return in;
		}
		if (i > last) {
			out.append(input, last, i - last);
		}
		return out.toString();
	}

	/**
		 * 指定的字符串转换成Html �? < " & 符号的转�?
		 * 
		 * @param in
		 * @return str
		 */
	public static final String escapeForXML(String in) {
		if (in == null) {
			return null;
		}
		int i = 0;
		int last = 0;
		char input[] = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) ((double) len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>')
				if (ch == '<') {
					if (i > last) {
						out.append(input, last, i - last);
					}
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '&') {
					if (i > last) {
						out.append(input, last, i - last);
					}
					last = i + 1;
					out.append(AMP_ENCODE);
				} else if (ch == '"') {
					if (i > last) {
						out.append(input, last, i - last);
					}
					last = i + 1;
					out.append(QUOTE_ENCODE);
				}
		}

		if (last == 0) {
			return in;
		}
		if (i > last) {
			out.append(input, last, i - last);
		}
		return out.toString();
	}

	/**
	 * 还原已被转化成的Html字符成String字符串�??
	 * 
	 * @param string
	 * @return str
	 */
	public static final String unescapeFromXML(String string) {
		string = replace(string, "&lt;", "<");
		string = replace(string, "&gt;", ">");
		string = replace(string, "&quot;", "\"");
		return replace(string, "&amp;", "&");
	}

	/**
	 * 字符串转换成日历Calendar，但必须str�?14位的字符串数字，�?: 20120217140014
	 * 
	 * @param str
	 * @return Calendar
	 */
	public static Calendar toTime(String str) {
		if (str == null) {
			return null;
		}
		if (str.length() != 14) {
			return null;
		}
		Calendar time = Calendar.getInstance();
		time.clear();
		try {
			String tmp = str.substring(0, 4);
			int y = Integer.parseInt(tmp);
			tmp = str.substring(4, 6);
			int m = Integer.parseInt(tmp) - 1;
			tmp = str.substring(6, 8);
			int d = Integer.parseInt(tmp);
			tmp = str.substring(8, 10);
			int h = Integer.parseInt(tmp);
			tmp = str.substring(10, 12);
			int min = Integer.parseInt(tmp);
			tmp = str.substring(12, 14);
			int s = Integer.parseInt(tmp);
			time.set(y, m, d, h, min, s);
		} catch (NumberFormatException ex) {
			return null;
		}
		return time;
	}

	/**
	 * 时间转换成字符串 采用的是默认的yyyyMMddHHmmss格式
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToTimeStr(Date date) {
		return (date == null) ? "" : DATE_FORMATTER.format(date);
	}

	/**
	 * 时间转换成字符串 采用的是默认的yyyy-MM-dd HH:mm格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateText(Date date) {
		return (date == null) ? "" : DATE_TEXT_FORMATTER.format(date);
	}

	/**
	 * 字符串转换成日历日期，但必须str�?14位或8位的字符串数字，�?: 20120217140014�? 20120214
	 * 
	 * @param str
	 * @return Date
	 */
	public static Date TimeStrToDate(String str) {
		if (str == null) {
			return null;
		}
		str = str.trim();
		if (str.length() != 14 && str.length() != 8) {
			return null;
		}
		Calendar time = Calendar.getInstance();
		time.clear();
		try {
			String tmp = str.substring(0, 4);
			int y = Integer.parseInt(tmp);
			tmp = str.substring(4, 6);
			int m = Integer.parseInt(tmp) - 1;
			tmp = str.substring(6, 8);
			int d = Integer.parseInt(tmp);
			if (str.length() > 8) {
				tmp = str.substring(8, 10);
				int h = Integer.parseInt(tmp);
				tmp = str.substring(10, 12);
				int min = Integer.parseInt(tmp);
				tmp = str.substring(12, 14);
				int s = Integer.parseInt(tmp);
				time.set(y, m, d, h, min, s);
			} else {
				time.set(y, m, d);
			}
		} catch (NumberFormatException ex) {
			return null;
		}
		return time.getTime();
	}

	/**
	 * 把指定的系统属�?�替换成属�?��?�，如果${java.version} ${java.home} ${os.name} ${user.home} ...
	 * 
	 * @param s
	 * @return
	 */
	public static String replaceToken(String s) {
		int startToken = s.indexOf("${");
		int endToken = s.indexOf("}", startToken);
		String token = s.substring(startToken + 2, endToken);
		StringBuffer value = new StringBuffer();
		value.append(s.substring(0, startToken));
		value.append(System.getProperty(token));
		value.append(s.substring(endToken + 1));
		return value.toString();
	}

	/**
	 * 把文件大小B转换成KB�?1024B = 1KB 注意四舍五入
	 * 
	 * @param size
	 *            long
	 * @return str
	 */
	public static String getFileSizeText(long size) {
		long num = size / 1024L + (long) (size % 1024L <= 0L ? 0 : 1);
		return num + "KB";
	}

	public static final synchronized String hash(String data) {
		if (digest == null)
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println("Failed to load the MD5 MessageDigest. System will be unable to function normally.");
				nsae.printStackTrace();
			}
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}

	public static final String encodeHex(byte bytes[]) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 16)
				buf.append("0");
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public static final byte[] decodeHex(String hex) {
		char chars[] = hex.toCharArray();
		byte bytes[] = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			byte newByte = 0;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = newByte;
			byteCount++;
		}
		return bytes;
	}

	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case 48: // '0'
			return 0;

		case 49: // '1'
			return 1;

		case 50: // '2'
			return 2;

		case 51: // '3'
			return 3;

		case 52: // '4'
			return 4;

		case 53: // '5'
			return 5;

		case 54: // '6'
			return 6;

		case 55: // '7'
			return 7;

		case 56: // '8'
			return 8;

		case 57: // '9'
			return 9;

		case 97: // 'a'
			return 10;

		case 98: // 'b'
			return 11;

		case 99: // 'c'
			return 12;

		case 100: // 'd'
			return 13;

		case 101: // 'e'
			return 14;

		case 102: // 'f'
			return 15;

		case 65: // 'A'
			return 10;

		case 66: // 'B'
			return 11;

		case 67: // 'C'
			return 12;

		case 68: // 'D'
			return 13;

		case 69: // 'E'
			return 14;

		case 70: // 'F'
			return 15;

		case 58: // ':'
		case 59: // ';'
		case 60: // '<'
		case 61: // '='
		case 62: // '>'
		case 63: // '?'
		case 64: // '@'
		case 71: // 'G'
		case 72: // 'H'
		case 73: // 'I'
		case 74: // 'J'
		case 75: // 'K'
		case 76: // 'L'
		case 77: // 'M'
		case 78: // 'N'
		case 79: // 'O'
		case 80: // 'P'
		case 81: // 'Q'
		case 82: // 'R'
		case 83: // 'S'
		case 84: // 'T'
		case 85: // 'U'
		case 86: // 'V'
		case 87: // 'W'
		case 88: // 'X'
		case 89: // 'Y'
		case 90: // 'Z'
		case 91: // '['
		case 92: // '\\'
		case 93: // ']'
		case 94: // '^'
		case 95: // '_'
		case 96: // '`'
		default:
			return 0;
		}
	}

	/**
	 * 字符串用64位转�?
	 * 
	 * @param data
	 * @return
	 */
	public static String encodeBase64(String data) {
		return encodeBase64(trim(data).getBytes());
	}

	/**
	 * 字符数组�?64位转�?
	 * 
	 * @param data
	 * @return
	 */
	public static String encodeBase64(byte data[]) {
		int len = data.length;
		StringBuffer ret = new StringBuffer((len / 3 + 1) * 4);
		for (int i = 0; i < len; i++) {
			int c = data[i] >> 2 & 0x3f;
			ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			c = data[i] << 4 & 0x3f;
			if (++i < len) {
				c |= data[i] >> 4 & 0xf;
			}
			ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			if (i < len) {
				c = data[i] << 2 & 0x3f;
				if (++i < len) {
					c |= data[i] >> 6 & 0x3;
				}
				ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			} else {
				i++;
				ret.append('=');
			}
			if (i < len) {
				c = data[i] & 0x3f;
				ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			} else {
				ret.append('=');
			}
		}
		return ret.toString();
	}

	/**
	 * 还原64位的字符�?
	 * 
	 * @param data
	 * @return
	 */
	public static String decodeBase64(String data) {
		return decodeBase64(data.getBytes());
	}

	/**
	 * 还原64位的字符数组
	 * 
	 * @param data
	 * @return
	 */
	public static String decodeBase64(byte data[]) {
		int len = data.length;
		StringBuffer ret = new StringBuffer((len * 3) / 4);
		for (int i = 0; i < len; i++) {
			int c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(data[i]);
			i++;
			int c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(data[i]);
			c = c << 2 | c1 >> 4 & 0x3;
			ret.append((char) c);
			if (++i < len) {
				c = data[i];
				if (61 == c) {
					break;
				}
				c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char) c);
				c1 = c1 << 4 & 0xf0 | c >> 2 & 0xf;
				ret.append((char) c1);
			}
			if (++i >= len) {
				continue;
			}
			c1 = data[i];
			if (61 == c1) {
				break;
			}
			c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char) c1);
			c = c << 6 & 0xc0 | c1;
			ret.append((char) c);
		}
		return ret.toString();
	}

	/**
	 * 字符串数组用特定的字符分割链接成字符串返�?
	 * 
	 * @param list
	 * @param seperator
	 * @return
	 */
	public static String join(String list[], String seperator) {
		StringBuffer result = new StringBuffer();
		if (list == null || list.length <= 0) {
			return "";
		}
		for (int i = 0; i < list.length; i++) {
			if (list[i] != null) {
				result.append(list[i].toString());
			}
			if (i != list.length - 1) {
				result.append(seperator);
			}
		}
		return result.toString();
	}

	/**
	 * long数组用特定的字符分割链接成字符串返回
	 * 
	 * @param values
	 * @param seperator
	 * @return
	 */
	public static String join(long[] values, String seperator) {
		StringBuffer result = new StringBuffer();
		if (values == null || values.length <= 0) {
			return "";
		}
		for (int i = 0; i < values.length; i++) {
			result.append(trim(values[i]));
			if (i != values.length - 1) {
				result.append(seperator);
			}
		}
		return result.toString();
	}

	/**
	 * 用gb格式 格式�? 特定的字符串
	 * 
	 * @param gbString
	 * @return
	 */
	public static String gbEncoding(String gbString) {
		char utfBytes[] = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
		return unicodeBytes;
	}

	/**
	 * 判断是否全是ascii的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isASCIIString(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		byte bytes[] = str.getBytes();
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 用source补全多少位count返回
	 * 
	 * @param source
	 * @param count
	 * @return
	 */
	public static String duplicate(String source, int count) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			sb.append(source);
		}
		return sb.toString();
	}

	public static String fillCharAtHeadForNumber(int source, String count, char fillchr) {
		int i = getAsInt(count);
		String str = (new StringBuffer(String.valueOf(source))).toString();
		if (str.length() < i) {
			for (int j = str.length(); j < i; j++) {
				str = fillchr + str;
			}
		}
		return str;
	}

	public static String fillCharAtHead(String source, String count, char fillchr) {
		int i = getAsInt(count);
		if (source.length() < i) {
			for (int j = source.length(); j < i; j++) {
				source = fillchr + source;
			}
		}
		return source;
	}

	public static String fillCharAtTail(String source, String count, char fillchr) {
		int i = getAsInt(count);
		if (source.length() < i) {
			for (int j = source.length(); j < i; j++) {
				source = source + fillchr;
			}
		}
		return source;
	}

	public static String pad(String words, String filling, int length) {
		for (int i = words.length(); i < length; i++) {
			words = filling + words;
		}
		return words;
	}

	public static String rpad(String words, String filling, int length) {
		for (int i = words.length(); i < length; i++) {
			words = words + filling;
		}
		return words;
	}

	public static boolean in(String str, String array[]) {
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				if (str == null && array[i] == null)
					return true;
				if (str != null && str.equals(array[i]))
					return true;
			}
		} else {
			return str == null;
		}

		return false;
	}

	public static String Base64ToString(String base64str, boolean isTrim) throws IOException {
		BASE64Decoder base64decode = new BASE64Decoder();
		if (isTrim)
			return trim(new String(base64decode.decodeBuffer(trim(base64str))));
		else
			return new String(base64decode.decodeBuffer(base64str));
	}

	public static String StringToBase64(String str, boolean isTrim) throws IOException {
		if (str == null)
			str = "";
		if (isTrim)
			str = trim(str);
		BASE64Encoder base64encode = new BASE64Encoder();
		return base64encode.encode(str.getBytes());
	}

	public static String Base64ToString(String base64str) throws IOException {
		BASE64Decoder base64decode = new BASE64Decoder();
		return new String(base64decode.decodeBuffer(base64str));
	}

	public static String StringToBase64(String str) {
		if (str == null)
			str = "";
		BASE64Encoder base64encode = new BASE64Encoder();
		return base64encode.encode(str.getBytes());
	}

	public static byte[] Base64ToByte(String base64str) throws IOException {
		BASE64Decoder base64decode = new BASE64Decoder();
		return base64decode.decodeBuffer(base64str);
	}

	public static String ByteToBase64(byte bt[]) throws IOException {
		BASE64Encoder base64encode = new BASE64Encoder();
		return base64encode.encode(bt);
	}

	public static int indexof(String strs[], String str) {
		if (str == null || strs == null)
			return -1;
		for (int i = 0; i < strs.length; i++)
			if (str.equals(strs[i]))
				return i;

		return -1;
	}

	public static boolean checkIntercross(String strs1[], String strs2[]) {
		if (strs1 == null || strs2 == null)
			return false;
		for (int i = 0; i < strs1.length; i++) {
			for (int k = 0; k < strs2.length; k++)
				if (strs1[i].equals(strs2[k]))
					return true;

		}

		return false;
	}

	public static String acfSubstring(String s, int j) throws IOException {
		if (s == null)
			return "";
		int k = 0;
		int l = 0;
		for (int i1 = 0; i1 < s.length() && j > l * 2 + k; i1++)
			if (s.charAt(i1) > '\200')
				l++;
			else
				k++;

		if (l + k >= s.length())
			return s;
		if (j >= l * 2 + k && l + k > 0)
			return s.substring(0, l + k) + "...";
		if (l + k > 0)
			return s.substring(0, (l + k) - 1) + "...";
		else
			return s;
	}

	static {
		RESERVESNUM = 12;
		reserve = new char[RESERVESNUM];
		reserve[0] = '!';
		reserve[1] = '#';
		reserve[2] = '%';
		reserve[3] = '*';
		reserve[4] = '/';
		reserve[5] = ':';
		reserve[6] = '?';
		reserve[7] = '&';
		reserve[8] = ';';
		reserve[9] = '=';
		reserve[10] = '@';
		reserve[11] = ' ';
	}

	public static boolean hasText(String str) {
		return !"".equals(nonNull(str));
	}

	/**
	 * 过滤数组中重复的数�?�只保留其中的一�?
	 * 
	 * @param arrays
	 * @return String[]
	 */
	public static String[] filterDuplicate(String[] arrays) {
		if (arrays == null || arrays.length == 0) {
			return null;
		}
		List<String> list = new LinkedList<String>();
		for (int i = 0; i < arrays.length; i++) {
			if (!list.contains(arrays[i])) {
				list.add(arrays[i]);
			}
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	/**
	 * 填充空白的str为newstr
	 * 
	 * @param str
	 * @param newstr
	 * @return
	 */
	public static String fillblankstr(String str, String newstr) {
		return StringUtil.hasText(str) ? str : newstr;
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @param arr
	 * @return
	 */
	public static boolean isEmptyArray(String[] arr) {
		if (arr == null || arr.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取两个数组之间的交�?
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String[] getArraysIntersection(String[] arr1, String[] arr2) {
		if (isEmptyArray(arr1)) {
			if (isEmptyArray(arr2)) {
				return null;
			} else {
				return arr2;
			}
		} else {
			if (isEmptyArray(arr2)) {
				return arr1;
			} else {
				List vallist = new ArrayList();
				for (int i = 0; i < arr1.length; i++) {
					String val = arr1[i];
					if (in(val, arr2) && !vallist.contains(val)) {
						vallist.add(val);
					}
				}
				return (String[]) vallist.toArray(new String[0]);
			}
		}
	}



	public static String firstCharToLowCase(String str) {
		String result = "";
		if (str != null && str.length() > 0) {
			String firstChar = str.substring(0, 1);
			firstChar = firstChar.toLowerCase();
			result = (new StringBuffer(String.valueOf(firstChar))).append(str.substring(1, str.length())).toString();
		}
		return result;
	}

	public static String firstCharToUpperCase(String str) {
		String result = "";
		if (str != null && str.length() > 0) {
			String firstChar = str.substring(0, 1);
			firstChar = firstChar.toUpperCase();
			result = (new StringBuffer(String.valueOf(firstChar))).append(str.substring(1, str.length())).toString();
		}
		return result;
	}

	/**
	 * 将一个null转化为空�?
	 * 
	 * @param obj
	 * @return
	 */
	public static String null2String(Object obj) {
		if (obj == null) {
			return "";
		} else
			return obj.toString();
	}

	/**
	 * 主要用来去掉来自字符串的空白
	 * 
	 * @param str
	 *            要检查的字符�?
	 * @return 去掉空格的字符串
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimLeadingWhitespace(String str) {
		if (str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	/**
	 * 将一个对象输出格式化已delim为分割的字符�?
	 * 
	 * @param arr
	 * @param delim
	 * @return
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (arr == null) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	/**
	 * 解析给定的字符串添加到StringTokenizer数组�? 省略空的令牌.
	 * 
	 * @param str
	 *            字符串令�?
	 * @param delimiters
	 *            分割�?
	 * @return 返回�?个数组令�?
	 */
	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	/**
	 * 解析给定的字符串添加到StringTokenizer数组�?
	 * 
	 * @param str
	 * @param delimiters
	 * @param trimTokens
	 * @param ignoreEmptyTokens
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

		StringTokenizer st = new StringTokenizer(str, delimiters);
		List tokens = new ArrayList();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() > 0) {
				tokens.add(token);
			}
		}
		return (String[]) tokens.toArray(new String[tokens.size()]);
	}

	@SuppressWarnings("unchecked")
	public static String join(List as, String s) {
		StringBuffer stringbuffer = new StringBuffer();
		if (null == as)
			return "";
		for (int j = 0, len = as.size(); j < len; j++) {
			if (null != as.get(j))
				stringbuffer.append(as.get(j).toString());
			if (j != len - 1)
				stringbuffer.append(s);
		}

		return stringbuffer.toString();
	}
	public static double getAsDouble(String str) {
		return getAsDouble(str,0d);
	}
	
	
	public static double getAsDouble(String str, double defaultv) {
		if (str == null || "".equals(str)) {
			return defaultv;
		}
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			return defaultv;
		}
	}
	
	
	/**
	 * 判断对象或对象数组中每一个对象是否为�?: 对象为null，字符序列长度为0，集合类、Map为empty
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null)
			return true;

		if (obj instanceof CharSequence)
			return ((CharSequence) obj).length() == 0;

		if (obj instanceof Collection)
			return ((Collection) obj).isEmpty();

		if (obj instanceof Map)
			return ((Map) obj).isEmpty();

		if (obj instanceof Object[]) {
			Object[] object = (Object[]) obj;
			if (object.length == 0) {
				return true;
			}
			boolean empty = true;
			for (int i = 0; i < object.length; i++) {
				if (!isNullOrEmpty(object[i])) {
					empty = false;
					break;
				}
			}
			return empty;
		}
		return false;
	}

	public static Map getBean2Map(Object beanObj) {
		HashMap map = new HashMap();
		if (null == beanObj) {
			return map;
		} else {
			try {
				Class e = beanObj.getClass();
				Field[] fields = e.getDeclaredFields();
				Field[] arr$ = fields;
				int len$ = fields.length;

				for (int i$ = 0; i$ < len$; ++i$) {
					Field field = arr$[i$];
					if (!field.getName().equals("serialVersionUID")) {
						try {
							PropertyDescriptor e1 = new PropertyDescriptor(field.getName(), beanObj.getClass());
							Method method = e1.getReadMethod();
							Object value = method.invoke(beanObj, new Object[0]);
							if (null != value) {
								map.put(field.getName(), value);
							} else if (!"Long".equals(field.getType().getSimpleName())
									&& !"Integer".equals(field.getType().getSimpleName())) {
								map.put(field.getName(), "");
							} else {
								map.put(field.getName(), Integer.valueOf(-1));
							}
						} catch (Exception arg10) {
							System.out.println(arg10);
						}
					}
				}
			} catch (Exception arg11) {
				System.out.println(arg11);
			}

			return map;
		}
	}
	
	
	public static final String NowStr() {
		return DatetimetoStr(new Date());
	}
	
	public static final String DatetimetoStr(Date date) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(FORMAT_1);
		return simpledateformat.format(date);
	}
	
	public static Object getMap2Bean(Map map, Class<?> beanClass) {
		Object inst = null;

		try {
			Field[] e = beanClass.getDeclaredFields();
			Constructor constructor1 = beanClass.getConstructor(new Class[0]);
			inst = constructor1.newInstance(new Object[0]);
			Field[] arr$ = e;
			int len$ = e.length;

			for (int i$ = 0; i$ < len$; ++i$) {
				Field field = arr$[i$];
				if (!field.getName().equals("serialVersionUID")) {
					try {
						PropertyDescriptor e1 = new PropertyDescriptor(field.getName(), inst.getClass());
						Method method = e1.getWriteMethod();
						if (null != map.get(field.getName())) {
							if ("Long".equals(field.getType().getSimpleName())) {
								method.invoke(inst,
										new Object[] { StringUtils.isEmpty(map.get(field.getName()).toString()) ? ""
												: Long.valueOf(Long.parseLong(map.get(field.getName()).toString())) });
							} else if ("Date".equals(field.getType().getSimpleName())) {
								String dateStr = "";
								if (!StringUtils.isEmpty(map.get(field.getName()).toString())) {
									dateStr = map.get(field.getName()).toString();
									if (dateStr.indexOf("T") != -1) {
										dateStr = dateStr.replace("T", " ");
									}
								}

								if (StringUtil.trim(field.getType()).indexOf("java.util.Date") >= 0) {
									method.invoke(inst,
											new Object[] { StringUtils.isEmpty(map.get(field.getName()).toString()) ? ""
													: reverse2Date(dateStr) });
								} else {
									method.invoke(inst,
											new Object[] { StringUtils.isEmpty(map.get(field.getName()).toString()) ? ""
													: reverse2SqlDate(dateStr) });
								}
							} else if (!"Integer".equals(field.getType().getSimpleName())
									&& !"int".equals(field.getType().getSimpleName())) {
								if ("List".equals(field.getType().getSimpleName())) {
									method.invoke(inst, new Object[] {
											null == map.get(field.getName()) ? "" : (List) map.get(field.getName()) });
								} else if ("Map".equals(field.getType().getSimpleName())) {
									method.invoke(inst, new Object[] {
											null == map.get(field.getName()) ? "" : (Map) map.get(field.getName()) });
								} else if (!"Double".equals(field.getType().getSimpleName())
										&& !"double".equals(field.getType().getSimpleName())) {
									if (!"Float".equals(field.getType().getSimpleName())
											&& !"float".equals(field.getType().getSimpleName())) {
										method.invoke(inst,
												new Object[] { StringUtils.isEmpty(map.get(field.getName()).toString())
														? "" : map.get(field.getName()).toString() });
									} else {
										method.invoke(inst, new Object[] {
												Float.valueOf(Float.parseFloat(map.get(field.getName()).toString())) });
									}
								} else {
									method.invoke(inst, new Object[] {
											Double.valueOf(Double.parseDouble(map.get(field.getName()).toString())) });
								}
							} else {
								method.invoke(inst,
										new Object[] { StringUtils.isEmpty(map.get(field.getName()).toString()) ? ""
												: Integer.valueOf(
														Integer.parseInt(map.get(field.getName()).toString())) });
							}
						}
					} catch (Exception arg11) {
						System.out.println(arg11);
					}
				}
			}
		} catch (Exception arg12) {
			System.out.println(arg12);
		}

		return inst;
	}
	
	
	public static java.sql.Date reverse2SqlDate(String date) {
		SimpleDateFormat simple = null;
		switch (date.trim().length()) {
		case 8:
			simple = new SimpleDateFormat("HH:mm:ss");
			break;
		case 10:
			simple = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case 19:
			simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}

		try {
			java.sql.Date e = new java.sql.Date(simple.parse(date.trim()).getTime());
			return e;
		} catch (ParseException arg2) {
			arg2.printStackTrace();
			return null;
		}
	}
	
	
	public static Date reverse2Date(String date) {
		SimpleDateFormat simple = null;
		switch (date.trim().length()) {
		case 8:
			simple = new SimpleDateFormat("HH:mm:ss");
			break;
		case 10:
			simple = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case 19:
			simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}

		try {
			return simple.parse(date.trim());
		} catch (ParseException arg2) {
			arg2.printStackTrace();
			return null;
		}
	}


}
