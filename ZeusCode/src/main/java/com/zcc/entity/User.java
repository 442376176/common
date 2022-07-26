package com.zcc.entity;

import com.zcc.utils.AnnotationExport;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.persistence.Table;

@Data
@Table(name = "t_user")
public class User {
    @AnnotationExport(columnName = "id")
    private Integer id;
    @AnnotationExport(columnName = "姓名")
    private String name;
    @Transient
    private String sex;
    private String phone;

    private String password;
    private static int count;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
        this.id = count++;
    }
}
