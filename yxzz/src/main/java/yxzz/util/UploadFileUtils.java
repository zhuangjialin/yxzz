package yxzz.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import yxzz.enums.UploadState;

/***
 * <b>function:</b> �ļ��ϴ�������
 * @author hoojo
 * @createDate Oct 9, 2010 11:12:47 PM
 * @file UploadFileUtils.java
 * @package com.hoo.util
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public abstract class UploadFileUtils {
    
    //�ϴ��ļ�����·��
    public static String path = "/upload/";
    //��������ϴ��ļ��ĺ�׺����,Ĭ��"*"����������
    public static String[] filePostfixs = { "*" };
    public static String[] typeImages = { "gif", "jpeg", "png", "jpg", "tif", "bmp" };
    public static String[] typeOthers = { "html", "htm", "doc", "xls", "txt", "zip", "rar", "pdf", "cll" };
    
    //�ϴ��ļ�����󳤶�
    public static long maxFileSize = 1024 * 1024 * 1024 * 2L;//2G
    //һ�ζ�ȡ�����ֽ�
    public static int bufferSize = 1024 * 8;
    
    private final static void init() {
        if (bufferSize > Integer.MAX_VALUE) {
            bufferSize = 1024 * 8;
        } else if (bufferSize < 8) {
            bufferSize = 8;
        }
        if (maxFileSize < 1) {
            maxFileSize = 1024 * 1024 * 1024 * 2L;
        } else if (maxFileSize > Long.MAX_VALUE) {
            maxFileSize = 1024 * 1024 * 1024 * 2L;
        }
    }
    
    /**
     * <b>function:</b>ͨ�������������ϴ��ļ�
     * @author hoojo
     * @createDate Oct 9, 2010 11:22:47 PM
     * @param uploadFileName �ļ�����
     * @param savePath ����·��
     * @param InputStream �ϴ����ļ���������
     * @return �Ƿ��ϴ��ɹ�
     * @throws Exception
     */
    public static UploadState upload4Stream(String fileName, String path, InputStream is) throws Exception {
        init();
        UploadState state = UploadState.UPLOAD_FAILURE;
        FileOutputStream fos = null;
       
        try {
            path = getDoPath(path);
            mkDir(path);
            fos = new FileOutputStream(path + fileName);   
              
            byte[] buffer = new byte[bufferSize];   
            int len = 0;   
            while ((len = is.read(buffer)) > 0) {   
                fos.write(buffer, 0, len);   
            }
            state = UploadState.UPLOAD_SUCCSSS;
        } catch (FileNotFoundException e) {   
            state = UploadState.UPLOAD_NOTFOUND;
               throw e;
        } catch (IOException e) {   
            state = UploadState.UPLOAD_FAILURE;
            throw e; 
        } finally {
            if (is != null) {
                is.close();
            }
            if (fos != null) {
                fos.flush();
                fos.close();
            }
        }
        return state;   
    }   
    
    /**
     * <b>function:</b>�ϴ��ļ�
     * @author hoojo
     * @createDate Oct 9, 2010 11:33:27 PM
     * @param uploadFileName �ļ�����
     * @param savePath ����·��
     * @param uploadFile �ϴ����ļ�
     * @return �Ƿ��ϴ��ɹ�
     * @throws Exception
     */
    public static UploadState upload4Stream(String fileName, String path, File file) throws Exception {
        init();
        UploadState state = UploadState.UPLOAD_FAILURE;
        FileInputStream fis = null;
        try {
            long size = file.length();
            if (size <= 0) {
                state = UploadState.UPLOAD_ZEROSIZE;
            } else {
                if (size <= maxFileSize) {
                    fis = new FileInputStream(file); 
                    state = upload4Stream(fileName, path, fis);
                } else {
                    state = UploadState.UPLOAD_OVERSIZE;
                }
            }
        } catch (FileNotFoundException e) {   
            state = UploadState.UPLOAD_NOTFOUND;
               throw e;
        } catch (IOException e) {   
            state = UploadState.UPLOAD_FAILURE;
            throw e; 
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return state;   
    }   
    
    /**
     * <b>function:</b>ͨ�����������֤�ļ������ϴ�
     * @author hoojo
     * @createDate Oct 10, 2010 3:39:34 PM
     * @param fileName �ļ�����
     * @param path �ļ�·��
     * @param file �ļ�
     * @param allowTypes �ļ���׺����������
     * @return �����Ƿ��ϴ��ɹ�
     * @throws Exception
     */
    public static UploadState upload4Stream(String fileName, String path, File file, String[] allowTypes) throws Exception {
        UploadState state = UploadState.UPLOAD_FAILURE;
        if (validTypeByName(fileName, allowTypes)) {
            state = upload4Stream(fileName, path, file);
        } else {
            state = UploadState.UPLOAD_TYPE_ERROR;
        }
        return state; 
    }
    
    /**
     * <b>function:</b>ͨ�����������֤�ļ������ϴ�
     * @author hoojo
     * @createDate Oct 10, 2010 3:43:30 PM
     * @param fileName �ļ�����
     * @param path �ļ�·��
     * @param InputStream �ļ�������
     * @param allowTypes �ļ���׺����������
     * @return �����Ƿ��ϴ��ɹ�
     * @throws Exception
     */
    public static UploadState upload4Stream(String fileName, String path, InputStream fs, String[] allowTypes) throws Exception {
        UploadState state = UploadState.UPLOAD_FAILURE;
        if (validTypeByName(fileName, allowTypes)) {
            state = upload4Stream(fileName, path, fs);
        } else {
            state = UploadState.UPLOAD_TYPE_ERROR;
        }
        return state; 
    }
    
    /**
     * <b>function:</b> ����FileUtils�ϴ��ļ�������maxFileSize�������ϴ��ļ��Ĵ�С
     * @author hoojo
     * @createDate Oct 9, 2010 11:49:15 PM
     * @param fileName �ļ�����
     * @param path ����·��
     * @param file �ļ�
     * @return �Ƿ��ϴ��ɹ�
     * @throws Exception
     */
    public static boolean upload4CopyFile(String fileName, String path, File file) throws Exception {
        init();
        boolean success = false;
        if (file.length() <= maxFileSize) {
            path = getDoPath(path);
            mkDir(path);
            File destFile = new File(path, fileName);
            FileUtils.copyFile(file, destFile);
            success = true;
        } 
        return success;   
    }  
    
    /**
     * <b>function:</b>  
     * @author hoojo
     * @createDate Oct 10, 2010 12:30:09 PM
     * @param fileName �ļ���
     * @param path ·��
     * @param file �ļ�
     * @param allowTypes ���͡���׺����
     * @return �ɹ��ϴ����ļ���
     * @throws Exception
     */
    public static boolean upload4CopyFile(String fileName, String path, File file, String[] allowTypes) throws Exception {   
        boolean success = false;
        if (validTypeByName(fileName, allowTypes)) {
            success = upload4CopyFile(fileName, path, file);
        }
        return success;   
    }
    
    /**
     * <b>function:</b> �����ļ���������������֤�ļ������Ƿ�Ϸ���flag�Ƿ���Դ�Сд
     * @author hoojo
     * @createDate Oct 10, 2010 11:54:54 AM
     * @param fileName �ļ���
     * @param allowTypes ��������
     * @param flag �Ƿ��ô�Сд
     * @return �Ƿ���֤ͨ��
     */
    public static boolean validTypeByName(String fileName, String[] allowTypes, boolean flag) {
        String suffix = getType(fileName);
        boolean valid = false;
        if (allowTypes.length > 0 && "*".equals(allowTypes[0])) {
            valid = true;
        } else {
            for (String type : allowTypes) {
                if (flag) {//�����ִ�Сд��׺
                    if (suffix != null && suffix.equalsIgnoreCase(type)) {
                        valid = true;
                        break;
                    }
                } else {//�ϸ����ִ�Сд
                    if (suffix != null && suffix.equals(type)) {
                        valid = true;
                        break;
                    }
                }
            }
        }
        return valid;
    }
    
    /**
     * <b>function:</b>�����ļ����ƺ�����������֤�ļ������Ƿ�Ϸ�
     * @author hoojo
     * @createDate Oct 10, 2010 10:27:17 AM
     * @param fileName �ļ���
     * @param allowTypes �ļ���������
     * @return �Ƿ�Ϸ�
     */
    public static boolean validTypeByName(String fileName, String[] allowTypes) {
        return validTypeByName(fileName, allowTypes, true);
    }
    
    /**
     * <b>function:</b> ���ݺ�׺������������֤�ļ������Ƿ�Ϸ���flag�Ƿ����ֺ�׺��Сд��true�ϸ��Сд
     * @author hoojo
     * @createDate Oct 10, 2010 12:00:10 PM
     * @param suffix ��׺��
     * @param allowTypes �ļ���������
     * @param flag �Ƿ����ִ�Сд
     * @return �Ƿ�Ϸ�
     */
    public static boolean validTypeByPostfix(String suffix, String[] allowTypes, boolean flag) {
        boolean valid = false;
        if (allowTypes.length > 0 && "*".equals(allowTypes[0])) {
            valid = true;
        } else {
            for (String type : allowTypes) {
                if (flag) {//�����ִ�Сд��׺
                    if (suffix != null && suffix.equalsIgnoreCase(type)) {
                        valid = true;
                        break;
                    }
                } else {//�ϸ����ִ�Сд
                    if (suffix != null && suffix.equals(type)) {
                        valid = true;
                        break;
                    }
                }
            }
        }
        return valid;
    }
    
    /**
     * <b>function:</b>�����ļ���׺�����������飬��֤�ļ������Ƿ�Ϸ�
     * @author hoojo
     * @createDate Oct 10, 2010 10:25:32 AM
     * @param suffix ��׺��
     * @param allowTypes ��������
     * @return �Ƿ�Ϸ�
     */
    public static boolean validTypeByPostfix(String suffix, String[] allowTypes) {
        return validTypeByPostfix(suffix, allowTypes, true);
    }
    
    /**
     * <b>function:</b>��֤��ǰ��׺���ļ������Ƿ���ͼƬ����
     * typeImages ��������ͼƬ����
     * @author hoojo
     * @createDate Oct 10, 2010 12:17:18 PM
     * @param suffix ��֤�ļ��ĺ�׺
     * @return �Ƿ�Ϸ�
     */
    public static boolean validTypeByPostfix4Images(String suffix) {
        return validTypeByPostfix(suffix, typeImages);
    }
    
    /**
     * <b>function:</b>��֤��ǰ��׺���ļ������Ƿ��Ƿ�ͼƬ���ͣ����ð칫�ļ����ͣ�
     * typeOthers ���������ļ�����
     * @author hoojo
     * @createDate Oct 10, 2010 12:18:18 PM
     * @param suffix ��֤�ļ��ĺ�׺
     * @return �Ƿ�Ϸ�
     */
    public static boolean validTypeByPostfix4Others(String suffix) {
        return validTypeByPostfix(suffix, typeOthers);
    }
    
    /**
     * <b>function:</b>��֤��ǰ�ļ������ļ������Ƿ���ͼƬ����
     * typeImages ��������ͼƬ����
     * @author hoojo
     * @createDate Oct 10, 2010 12:19:18 PM
     * @param fileName ��֤�ļ�������
     * @return �Ƿ�Ϸ�
     */
    public static boolean validTypeByName4Images(String fileName) {
        return validTypeByName(fileName, typeImages);
    }
    
    /**
     * <b>function:</b>��֤��ǰ�ļ����ơ��ļ������Ƿ��Ƿ�ͼƬ���ͣ����ð칫�ļ����ͣ�
     * typeOthers ���������ļ�����
     * @author hoojo
     * @createDate Oct 10, 2010 12:21:22 PM
     * @param fileName ��֤�ļ�������
     * @return �Ƿ�Ϸ�
     */
    public static boolean validTypeByName4Others(String fileName) {
        return validTypeByName(fileName, typeOthers);
    }
    
    /**
     * <b>function:</b>����һ��·�����ļ����ƣ�ɾ�����ļ�
     * @author hoojo
     * @createDate Oct 10, 2010 10:47:57 AM
     * @param fileName �ļ�����
     * @param path ·��
     * @return �Ƿ�ɾ���ɹ�
     */
    public static boolean removeFile(String fileName, String path) {
        boolean flag = false;
        if (isFileExist(fileName, path)) {
            File file = new File(getDoPath(path) + fileName);
            flag = file.delete();
        }
        return flag;
    }
    
    /**
     * <b>function:</b>ɾ����ǰ�ļ�
     * @author hoojo
     * @createDate Oct 10, 2010 10:49:54 AM
     * @param file Ҫɾ�����ļ�
     * @return �Ƿ�ɾ���ɹ�
     */
    public static boolean removeFile(File file) {
        boolean flag = false;
        if (file != null && file.exists()) {
            flag = file.delete();
        }
        return flag;
    }
    
    /**
     * <b>function:</b>ɾ��ĳ���ļ�
     * @author hoojo
     * @createDate Oct 12, 2010 9:33:06 PM
     * @param path ���ݸ��ļ�·��
     * @return ɾ���Ƿ�ɹ�
     */
    public static boolean removeFile(String path) {
        return removeFile(new File(path));
    }
    
    /**
     * <b>function:</b>ɾ����ǰ�ļ����������ļ�
     * @author hoojo
     * @createDate Oct 12, 2010 9:27:33 PM
     * @param file File Ҫɾ�����ļ��������ļ����ļ�����
     * @return �Ƿ�ɾ���ɹ��������һ���ļ�ɾ��ʧ�ܣ�������false
     */
    public static boolean removeFile4Dir(File file) {
        boolean flag = false;
        if (file != null && file.exists() && file.isDirectory()) {
            File[] allFile = file.listFiles();
            for (File f : allFile) {
                flag = f.delete();
                if (!flag) {
                    System.err.println("ɾ���ļ�" + f.getAbsolutePath() + "�����ˣ�");
                    break;
                }
            }
        }
        return flag;
    }
    
    /**
     * <b>function:</b>ɾ����ǰĿ¼�������ļ�
     * @author hoojo
     * @createDate Oct 12, 2010 9:34:41 PM
     * @param path Ŀ¼��·��
     * @return �Ƿ�ɹ�
     */
    public static boolean removeFile4Dir(String path) {
        return removeFile4Dir(new File(path));
    }
    
    /**
     * <b>function:</b>ɾ��ĳ���ļ����µ������ļ�����Ŀ¼�����������ļ��е��ļ�
     * @author hoojo
     * @createDate Oct 12, 2010 9:30:01 PM
     * @param file ����ɾ���ļ��ж���
     * @return �Ƿ�ɾ���ɹ�
     */
    public static boolean removeAllFile4Dir(File file) {
        boolean flag = false;
        if (file != null && file.exists() && file.isDirectory()) {
            File[] allFile = file.listFiles();
            for (File f : allFile) {
                if (!f.isDirectory()) {
                    flag = f.delete();
                } else {
                    flag = removeAllFile4Dir(f);
                }
                if (!flag) {
                    System.err.println("ɾ���ļ�" + f.getAbsolutePath() + "�����ˣ�");
                    break;
                }
            }
        }
        return flag;
    }
    
    /**
     * <b>function:</b>ɾ��ĳ��Ŀ¼�������ļ����������ļ��У������ļ����µ��ļ���
     * @author hoojo
     * @createDate Oct 12, 2010 9:36:17 PM
     * @param path
     * @return
     */
    public static boolean removeAllFile4Dir(String path) {
        return removeAllFile4Dir(new File(path));
    }
    
    /**
     * <b>function:</b> ����һ���ļ������õ�����ļ����Ƶĺ�׺
     * @author hoojo
     * @createDate Oct 9, 2010 11:30:46 PM
     * @param fileName �ļ���
     * @return ��׺��
     */
    public static String getSuffix(String fileName) {
         int index = fileName.lastIndexOf(".");
         if (index != -1) {
             String suffix = fileName.substring(index);//��׺
             return suffix; 
         } else {
             return null;
         }
    }
    
    /**
     * <b>function:</b>���ļ���׺һ������ͬ����û�С�.��
     * @author hoojo
     * @createDate Oct 10, 2010 2:42:43 PM
     * @param fileName �ļ�����
     * @return
     */
    public static String getType(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            String suffix = fileName.substring(index + 1);//��׺
            return suffix; 
        } else {
            return null;
        }
   }
    
    /**
     * <b>function:</b> ����һ���ļ����ƺ�һ�������ƣ���ϳ�һ���µĴ���׺�ļ���
     * �����ݵ��ļ���û�к�׺�������Ĭ�ϵĺ�׺
     * @author hoojo
     * @createDate Oct 9, 2010 10:53:06 PM
     * @param fileName �ļ�����
     * @param newName ���ļ�����
     * @param nullSuffix Ϊû�к�׺���ļ�����ӵĺ�׺;eg:txt
     * @return String �ļ�����
     */
    public static String getNewFileName(String fileName, String newName, String nullSuffix) {
        String suffix = getSuffix(fileName);
        if (suffix != null) {
            newName += suffix; 
        } else {
            newName = newName.concat(".").concat(nullSuffix);
        }
        return newName;   
    } 
    
    /**
     * <b>function:</b> ����uuid����һ�������name
     * @author hoojo
     * @createDate Oct 9, 2010 10:45:27 PM
     * @param fileName ����׺���ļ�����
     * @return String ������ɵ�name
     */
    public static String getRandomName(String fileName) {
        String randomName = UUID.randomUUID().toString();
        return getNewFileName(fileName, randomName, "txt");
    } 
    
    /**
     * <b>function:</b> �õ�ǰ���ڡ�ʱ���1000���ڵ��������ϳɵ��ļ�����
     * @author hoojo
     * @createDate Oct 9, 2010 11:01:47 PM
     * @param fileName �ļ�����
     * @return ���ļ�����
     */
    public static String getNumberName(String fileName) {
        SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmss");
        int rand = new Random().nextInt(1000);
        String numberName = format.format(new Date()) + rand;
        return getNewFileName(fileName, numberName, "txt");
    }
    
    /**
     * <b>function:</b>�жϸ��ļ��Ƿ����
     * @author hoojo
     * @createDate Oct 10, 2010 12:00:44 AM
     * @param fileName �ļ�����
     * @param path Ŀ¼
     * @return �Ƿ����
     */
    public static boolean isFileExist(String fileName, String path) {
        File file = new File(getDoPath(path) + fileName);
        return file.exists();
    }
    
    /**
     * <b>function:</b>���ؿ��õ��ļ���
     * @author hoojo
     * @createDate Oct 10, 2010 1:02:45 AM
     * @param fileName �ļ���
     * @param path ·��
     * @return �����ļ���
     */
    public static String getBracketFileName(String fileName, String path) {
        return getBracketFileName(fileName, fileName, path, 1);
    }
    
    
    /**
     * <b>function:</b>�ݹ鴦���ļ����ƣ�ֱ�����Ʋ��ظ������ļ�����Ŀ¼�ļ��ж����ã�
     * eg: a.txt --> a(1).txt
     * �ļ���upload--> �ļ���upload(1)
     * @author hoojo
     * @createDate Oct 10, 2010 12:56:27 AM
     * @param fileName �ļ�����
     * @param path �ļ�·��
     * @param num �ۼ����֣�����
     * @return ����û���ظ�������
     */
    public static String getBracketFileName(String fileName, String bracketName, String path, int num) {
        boolean exist = isFileExist(bracketName, path);
        if (exist) {
            int index = fileName.lastIndexOf(".");
            String suffix = "";
            bracketName = fileName;
            if (index != -1) {
                suffix = fileName.substring(index);
                bracketName = fileName.substring(0, index);
            }
            bracketName += "(" + num + ")" + suffix;
            num++;
            bracketName = getBracketFileName(fileName, bracketName, path, num);
        } 
        return bracketName;
    }
    
    /**
     * <b>function:</b>������ϵͳ�ļ�·��
     * @author hoojo
     * @createDate Oct 10, 2010 12:49:31 AM
     * @param path �ļ�·��
     * @return ���ش�����·��
     */
    public static String getDoPath(String path) {
        path = path.replace("\\", "/");
        String lastChar = path.substring(path.length() - 1);
        if (!"/".equals(lastChar)) {
            path += "/";
        }
        return path;
    }
    
    /**
     * <b>function:</b> ����ָ����path·��Ŀ¼
     * @author hoojo
     * @createDate Oct 9, 2010 11:03:49 PM
     * @param path Ŀ¼��·��
     * @return �Ƿ񴴽��ɹ�
     * @throws Exception
     */
    public static boolean mkDir(String path) throws Exception {   
        File file = null;   
        try {   
            file = new File(path);   
            if (!file.exists()) {   
                return file.mkdirs();   
            }   
        } catch (RuntimeException e) {   
            throw e;
        } finally {   
            file = null;   
        }   
        return false;   
    }   
    
    /**
     * ��������ͼ
     * @param file �ϴ����ļ���
     * @param height ��С�ĳߴ�
     * @throws IOException
     */
    /*public static void writeBrevityPic(File file, float width, float height) throws IOException {
        Image src = javax.imageio.ImageIO.read(file); // ����Image����
        int old_w = src.getWidth(null); // �õ�Դͼ��
        int old_h = src.getHeight(null);
        int new_w = 0;
        int new_h = 0; // �õ�Դͼ��
        float tempdouble;
        if (old_w >= old_h) {
            tempdouble = old_w / width;
        } else {
            tempdouble = old_h / height;
        }

        if (old_w >= width || old_h >= height) { // ����ļ�С������ͼ�ĳߴ����Ƽ���
            new_w = Math.round(old_w / tempdouble);
            new_h = Math.round(old_h / tempdouble);// ������ͼ����
            while (new_w > width && new_h > height) {
                if (new_w > width) {
                    tempdouble = new_w / width;
                    new_w = Math.round(new_w / tempdouble);
                    new_h = Math.round(new_h / tempdouble);
                }
                if (new_h > height) {
                    tempdouble = new_h / height;
                    new_w = Math.round(new_w / tempdouble);
                    new_h = Math.round(new_h / tempdouble);
                }
            }
            BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null); // ������С���ͼ
            FileOutputStream newimage = new FileOutputStream(file); // ������ļ���
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
            param.setQuality((float) (100 / 100.0), true);// ����ͼƬ����,100���,Ĭ��70
            encoder.encode(tag, param);
            encoder.encode(tag); // ��JPEG����
            newimage.close();
        }
    }*/
    
    public static void main(String[] args) throws Exception {
        String path = "E:"+File.separator+"���·緶��"+File.separator+"pictures"+File.separator+"b5.jpg";
/*        //System.out.println(mkDir(path));
        System.out.println(getDoPath(path));
        System.out.println(getBracketFileName("a.txt", getDoPath(path)));
        System.out.println(getNumberName("a.jpg"));
        System.out.println(getNumberName("a.jpg"));
        System.out.println(getNewFileName("a", "bbb", "txt"));
        System.out.println(getRandomName("a.htm"));
        System.out.println(getSuffix("a.jpg"));
        System.out.println(getType("a.jpg"));
        //List<File> list = getFiles(path);
        //List<File> list = getFiles(path, "xml");
        //List<File> list = getFiles(path, typeImages);
        //List<File> list = getFiles(path, typeOthers);
        //List<File> list = getFiles(path, typeImages, false);
        List<File> list = getFiles(path, "GIF", true);
        for (File f : list) {
            System.out.println("Name:" + f.getName());
            System.out.println(f.getAbsolutePath() + "#" + f.getPath());
        }
        System.out.println(removeFile("a.txt", path));
        System.out.println("#############################################");
        System.out.println("###" + validTypeByName("a", new String[]{"*"}));
        System.out.println("###" + validTypeByName("a.JPG", typeImages));
        System.out.println("###" + validTypeByName("a.JPG", typeImages, false));
        System.out.println(validTypeByPostfix("cals", new String[]{"*", "b"}));
        System.out.println(validTypeByPostfix("b", new String[]{"cal", "B"}, false));
        */
        
        String[] types = {"jpg","png"};
        
        
        
        System.out.println(upload4CopyFile("201712140001", path, new File(path), types));
        
        
    }
}