package com.hfl.util;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class JSONUtil {

    //调用反射机制封装json工具包
    //作用：将任意类型对象内容转换为JSON格式字符串返回  {key1:value1,key2:value2,key3:value3,...}
    //参数：一个高级引用类型对象 Student对象  ，Dept对象，。。。。。
    public static String jsonObject(Object obj){
        StringBuffer str=new StringBuffer();
        str.append("{");
        //1.获取类
        Class objClass=obj.getClass();
        //2.获取类中属性
        Field[] fields =objClass.getDeclaredFields();
        for (int i=0;i<fields.length;i++) {
            Field f=fields[i];
            f.setAccessible(true);//打破封装
            String key=f.getName();
            Object value= null;
            try {
                value = f.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            str.append("\"");
            str.append(key);
            str.append("\"");
            str.append(":");
            str.append("\"");
            str.append(value);
            str.append("\"");
            if(i<fields.length-1){
                str.append(",");
            }


        }
        str.append("}");

        return str.toString();
    }
    //作用：将任意类型对象集合内容转换为JSON格式字符串返回  {key1:value1,key2:value2,key3:value3,...}
    public static String jsonArray(List list){
        StringBuffer str=new StringBuffer();
        str.append("[");

        Iterator it=list.iterator();
        while (it.hasNext()){
            Object obj=it.next();
            str.append("{");
            //1.获取类
            Class objClass=obj.getClass();
            //2.获取类中属性
            Field[] fields =objClass.getDeclaredFields();
            for (int i=0;i<fields.length;i++) {
                Field f=fields[i];
                f.setAccessible(true);//打破封装
                String key=f.getName();
                Object value= null;
                try {
                    value = f.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                str.append("\"");
                str.append(key);
                str.append("\"");
                str.append(":");
                str.append("\"");
                str.append(value);
                str.append("\"");
                if(i<fields.length-1){
                    str.append(",");
                }
            }
            str.append("}");
            if(it.hasNext()){
                str.append(",");
            }
        }
        str.append("]");

        return str.toString();
    }
}
