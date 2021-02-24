package com.hfl.listener;

import com.hfl.util.JDBCUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 模拟数据库连接池
 */
public class DBListener implements ServletContextListener {

    //创建20个数据库连接对象
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Map connMap=new HashMap();

        for(int i=0;i<20;i++){
            Connection conn=null;
            try {
                conn= JDBCUtil.getConnection();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("在Http服务器启动时，创建Connection "+conn);
            connMap.put(conn,true);//true代表通道空闲，false代表通道正在使用
            //为了在Http服务器运行期间，一直都可以使用20个Connection，将connection保存到全局作用域对象对象
            ServletContext application=sce.getServletContext();
            application.setAttribute("connection",connMap);

        }

    }

    //map被销毁
    //在Http服务器关闭时刻，将全局作用域对象20个Connection销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        Map map=(Map)application.getAttribute("connection");

        Iterator it=map.keySet().iterator();
        while (it.hasNext()){
            Connection conn=(Connection) it.next();
            if(conn!=null){
                try {
                    System.out.println("兄弟们，我"+conn+" 现行一步，20年后老子还是条好汉");
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
