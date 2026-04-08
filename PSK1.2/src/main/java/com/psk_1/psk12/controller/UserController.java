package com.psk_1.psk12.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // React dev server
public class UserController {
    @GetMapping("/hello")
    public String hello(){
        return "ようこそわたしのソウル－";
    }
}