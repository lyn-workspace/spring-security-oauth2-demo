package com.spring.security.uaa;

import cn.hutool.core.io.FileUtil;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

class UaaApplicationTests {

    public static void main(String[] args) {
        String file = FileUtil.readString("D:/工程.txt", "utf-8");
        System.out.println(file);
        System.out.println(Jsoup.parse(file).text());
    }
}
