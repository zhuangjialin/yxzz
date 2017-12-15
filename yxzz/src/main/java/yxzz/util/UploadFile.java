package yxzz.util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class UploadFile {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String SourceLocation = sc.nextLine();
        String [] sources = SourceLocation.split("\\\\");
        int size = sources.length;
        String GoalLocation = new String("F:\\测试\\");
        String GoalFileName = sources[size-1];
        GoalLocation = GoalLocation + GoalFileName;
        File sourcefile = new File(SourceLocation);
        if(!checkUploadfileType(GoalFileName,new String[]{"png"}))
        {
        System.out.println("文件格式不对");
        return;
        }
        if(!checkUploadfileSize(sourcefile, 1024*1024))
            {
            System.out.println("文件太大");
            return;
            }
        
        try{
            File locationfile = new File(GoalLocation);
            FileUtils.copyFile(sourcefile, locationfile);
        }catch(Exception e){
            System.out.println("上传失败");
        }
    }
    public static boolean checkUploadfileSize(File file, int size){
        Boolean flag = true;
        if(file.length()>=size)
            flag = false;
        return flag;
    }
    public static boolean checkUploadfileType(String Filename,String[] types){
        Boolean flag = false;
        for(String type:types){
            if(Filename.toLowerCase().lastIndexOf(type)+type.length() == Filename.length()){
                flag = true;
            }
        }
        return flag;
    }
    
    

    
    
}