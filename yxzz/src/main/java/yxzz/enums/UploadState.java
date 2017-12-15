package yxzz.enums;

/** 
 * <b>function:</b> �ļ��ϴ�״̬ 
 * @package com.hoo.enums 
 * @fileName UploadState.java 
 * @createDate 2010-10-11 ����12:18:14 
 * @author hoojo 
 */  
public enum UploadState {  
    UPLOAD_SUCCSSS(0, "�ϴ��ļ��ɹ���"),   
    UPLOAD_FAILURE(1, "�ϴ��ļ�ʧ�ܣ�"),   
    UPLOAD_TYPE_ERROR(2, "�ϴ��ļ����ʹ���"),   
    UPLOAD_OVERSIZE(3, "�ϴ��ļ�����"),  
    UPLOAD_ZEROSIZE(4, "�ϴ��ļ�Ϊ�գ�"),  
    UPLOAD_NOTFOUND(5, "�ϴ��ļ�·������");  
      
    private String state;  
    private int flag;  
    public String getState() {  
        return this.state;  
    }  
      
    public int getFlag() {  
        return this.flag;  
    }  
    UploadState(int flag, String state) {  
        this.state = state;  
        this.flag = flag;  
    }  
}  