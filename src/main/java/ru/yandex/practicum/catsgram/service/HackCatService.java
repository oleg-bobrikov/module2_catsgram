package ru.yandex.practicum.catsgram.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HackCatService {

    public static final String JDBC_URL = "jdbc:postgresql://127.0.0.1:5432/cats";
    public static final String JDBC_USERNAME = "kitty";
    public static final String JDBC_DRIVER = "org.postgresql.Driver";


    public void tryPassword(String jdbcPassword) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(JDBC_URL,JDBC_USERNAME,jdbcPassword);
        driverManagerDataSource.setDriverClassName(JDBC_DRIVER);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(driverManagerDataSource);
        jdbcTemplate.execute("SELECT 1;");
    }

    public String doHackNow() {
        List<String> catWordList = Arrays.asList("meow", "purr", "purrrrrr", "zzz");
        for (String word : catWordList) {
            try {
                tryPassword(word);
                return word;
            }
                catch (RuntimeException ex){
                    System.out.println(ex.getMessage());
            }
        }
        return "Sorry! We cannot hack!";
    }
}