package travel_web.JDBCTest;

import com.travelInfo.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;

public class Jtest {
    @Test
    public void test1(){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        System.out.println(template);
    }

    @Test
    public void test2(){
        //1. 获取当前时间
        Date now = new Date();
        System.out.println("当前时间:" + now);
        //2, 转换为毫秒数
        Long nowTime = now.getTime();
        //3, 加入下一天的毫秒数
        long nextDay = 1; // 表示一天
        nextDay *= 24; // 表示24小时
        nextDay *= 60; // 表示60分
        nextDay *= 60;  // 表示60秒
        nextDay *= 1000;  // 表示1000毫秒
        //4，显示
        System.out.println("下一天的时间：" + new Date(nextDay + nowTime));
    }
}
