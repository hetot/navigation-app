package com.dsr.navigationapp;

import com.dsr.navigationapp.directories.DirectoryInfo;
import com.dsr.navigationapp.directories.DirectoryNavigation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

@SpringBootApplication
public class NavigationAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(NavigationAppApplication.class, args);
    }
}
