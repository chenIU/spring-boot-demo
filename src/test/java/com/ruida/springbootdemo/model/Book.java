package com.ruida.springbootdemo.model;

import com.ruida.springbootdemo.utils.excel.ExcelAnnotation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 1.普通的属性注入(构造方法注入)
 * 2.类型安全的属性注入(set方法注入)
 * @author: chenjy
 * @create: 2020-10-29 11:05
 */
@Component
@PropertySource("classpath:config/book.properties")
@ConfigurationProperties(prefix = "book")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    //@Value("${book.id}")
    @ExcelAnnotation(title = "序号")
    private Long id;

    //@Value("${book.name}")
    @ExcelAnnotation(title = "书籍名称")
    private String name;

    //@Value("${book.author}")
    @ExcelAnnotation(title = "作者")
    private String author;

    private Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
