package com.dsr.navigationapp.controllers;

import com.dsr.navigationapp.genarators.BodyGenerator;
import com.dsr.navigationapp.models.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DirectoryController {
    @GetMapping(value = "/get_info")
    @ResponseBody
    public FileInfo getInfo(@RequestParam("path") String path) {
        return BodyGenerator.getFileInfo(path);
    }

    @GetMapping(value = "/get_content")
    @ResponseBody
    public PathContent getFileContent(@RequestParam("path") String path) {
        return BodyGenerator.getFileContent(path);
    }

    @DeleteMapping(value = "/remove")
    @ResponseBody
    public DeleteResponse deleteFile(@RequestBody DeleteRequest delete) {
        return BodyGenerator.removeFile(delete.getPath());
    }

    @PostMapping(value = "/rename")
    @ResponseBody
    public RenameResponse renameFile(@RequestBody RenameRequest request) {
        return BodyGenerator.rename(request.getPath(), request.getNewName());
    }
}
