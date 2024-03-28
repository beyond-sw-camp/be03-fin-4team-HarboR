package com.example.harbor_login.Login.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 3000)
    private String contents;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;


    public void updateNotice(String title, String contents, String fileName, String filePath){

        this.title = title;
        this.contents = contents;
    }
    public void deleteNotice(){ // notice 삭제 시 호출
        this.delYn = "Y";
    }
}