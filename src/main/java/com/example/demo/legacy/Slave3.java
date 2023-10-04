package com.example.demo.legacy;

import org.springframework.stereotype.Service;

@Service
public class Slave3 {

    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }
}
