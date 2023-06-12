package com.dsr.navigationapp.controllers;

import com.dsr.navigationapp.filesystem.DirectoryInfo;
import com.dsr.navigationapp.filesystem.DirectoryNavigation;
import com.dsr.navigationapp.models.FileInfo;
import com.dsr.navigationapp.models.requests.rest.MoveCopyRequest;
import com.dsr.navigationapp.models.requests.rest.RenameRequestBody;
import com.dsr.navigationapp.services.NavigationService;
import com.dsr.navigationapp.models.responses.rest.BooleanResponseBody;
import com.dsr.navigationapp.models.responses.rest.DeleteResponseBody;
import com.dsr.navigationapp.models.responses.rest.PathContentResponse;
import com.dsr.navigationapp.utils.ValidatorClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/web/")
public class DirectoryController {
    @GetMapping(value = "/directories")
    public ResponseEntity<?> get(
            @RequestParam(value = "path") String path,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        var isValid = ValidatorClass.validatePath(path);
        if (!isValid) {
            return ResponseEntity.badRequest().body("Not valid path");
        }
        path = DirectoryNavigation.getInstance().getFullPath(path);
        return ResponseEntity.ok(NavigationService.getPathContent(path, page, size));
    }

    @GetMapping(value = "/directories/info")
    public ResponseEntity<?> getInfo(
            @RequestParam(value = "path") String path
    ) {
        var isValid = ValidatorClass.validatePath(path);
        if (!isValid) {
            return ResponseEntity.badRequest().body("Not valid path");
        }
        path = DirectoryNavigation.getInstance().getFullPath(path);
        return ResponseEntity.ok(DirectoryInfo.getFileInfo(path));
    }

    @DeleteMapping(value = "/directories")
    public ResponseEntity<?> delete(
            @RequestParam(value = "path") String path
    ) {
        var isValid = ValidatorClass.validatePath(path);
        if (!isValid) {
            return ResponseEntity.badRequest().body("Not valid path");
        }
        path = DirectoryNavigation.getInstance().getFullPath(path);
        return ResponseEntity.ok(NavigationService.removeFile(path));
    }

    @PutMapping(value = "/directories")
    public ResponseEntity<?> rename(
            @RequestBody RenameRequestBody requestBody
    ) {
        var isValid = ValidatorClass.validatePath(requestBody.getPath());
        if (!isValid) {
            return ResponseEntity.badRequest().body("Not valid path");
        }
        var path = DirectoryNavigation.getInstance().getFullPath(requestBody.getPath());
        return ResponseEntity.ok(NavigationService.rename(path, requestBody.getNewName()));
    }

    @PutMapping(value = "/directories/copy")
    public ResponseEntity<?> copy(
            @RequestBody MoveCopyRequest body
    ) {
        var isValid = ValidatorClass.validatePath(body.getPath());
        if (!isValid) {
            return ResponseEntity.badRequest().body("Not valid path");
        }
        var path = DirectoryNavigation.getInstance().getFullPath(body.getPath());
        var destination = DirectoryNavigation.getInstance().getFullPath(body.getDestination());
        return ResponseEntity.ok(NavigationService.copy(path, destination));
    }

    @PutMapping(value = "/directories/move")
    public ResponseEntity<?> move(
            @RequestBody MoveCopyRequest body
    ) {
        var isValid = ValidatorClass.validatePath(body.getPath());
        if (!isValid) {
            return ResponseEntity.badRequest().body("Not valid path");
        }
        var path = DirectoryNavigation.getInstance().getFullPath(body.getPath());
        var destination = DirectoryNavigation.getInstance().getFullPath(body.getDestination());
        return ResponseEntity.ok(NavigationService.move(path, destination));
    }
}
