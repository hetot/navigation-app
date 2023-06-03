package com.dsr.navigationapp.controllers;

import com.dsr.navigationapp.genarators.BodyGenerator;
import com.dsr.navigationapp.models.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
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
    public DeleteResponse deleteFile(@RequestParam("path") String path) {
        return BodyGenerator.removeFile(path);
    }

    @PostMapping(value = "/rename")
    @ResponseBody
    public BooleanResponse renameFile(@RequestBody RenameRequest request) {
        return BodyGenerator.rename(request.getPath(), request.getNewName());
    }

    @PostMapping(value = "/copy")
    @ResponseBody
    public BooleanResponse copyFile(@RequestBody RedirectFileRequest request) {
        return BodyGenerator.copy(request.getOrigin(), request.getDestination());
    }

    @PostMapping(value = "/move")
    @ResponseBody
    public BooleanResponse moveFile(@RequestBody RedirectFileRequest request) {
        return BodyGenerator.move(request.getOrigin(), request.getDestination());
    }
}
