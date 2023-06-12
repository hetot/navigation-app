package com.dsr.navigationapp.controllers;

import com.dsr.navigationapp.filesystem.DirectoryInfo;
import com.dsr.navigationapp.filesystem.DirectoryNavigation;
import com.dsr.navigationapp.models.FileInfo;
import com.dsr.navigationapp.models.requests.rest.RenameRequestBody;
import com.dsr.navigationapp.services.NavigationService;
import com.dsr.navigationapp.models.responses.rest.BooleanResponseBody;
import com.dsr.navigationapp.models.responses.rest.DeleteResponseBody;
import com.dsr.navigationapp.models.responses.rest.PathContentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/web/")
public class DirectoryController {
    @GetMapping(value = "/directories")
    public ResponseEntity<PathContentResponse> get(
            @RequestParam(value = "path") String path,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        path = DirectoryNavigation.getInstance().getFullPath(path);
        return ResponseEntity.ok(NavigationService.getPathContent(path, page, size));
    }

    @GetMapping(value = "/directories/info")
    public ResponseEntity<FileInfo> getInfo(
            @RequestParam(value = "path") String path
    ) {
        path = DirectoryNavigation.getInstance().getFullPath(path);
        return ResponseEntity.ok(DirectoryInfo.getFileInfo(path));
    }

    @DeleteMapping(value = "/directories")
    public ResponseEntity<DeleteResponseBody> delete(
            @RequestParam(value = "path") String path
    ) {
        path = DirectoryNavigation.getInstance().getFullPath(path);
        return ResponseEntity.ok(NavigationService.removeFile(path));
    }

    @PutMapping(value = "/directories")
    public ResponseEntity<BooleanResponseBody> rename(
            @RequestBody RenameRequestBody requestBody
    ) {
        var path = DirectoryNavigation.getInstance().getFullPath(requestBody.getPath());
        return ResponseEntity.ok(NavigationService.rename(path, requestBody.getNewName()));
    }

    @PutMapping(value = "/directories/copy")
    public ResponseEntity<BooleanResponseBody> copy(
            @RequestParam(value = "path") String path,
            @RequestParam(value = "destination") String destination
    ) {
        path = DirectoryNavigation.getInstance().getFullPath(path);
        destination = DirectoryNavigation.getInstance().getFullPath(destination);
        return ResponseEntity.ok(NavigationService.copy(path, destination));
    }

    @PutMapping(value = "/directories/move")
    public ResponseEntity<BooleanResponseBody> move(
            @RequestParam(value = "path") String path,
            @RequestParam(value = "destination") String destination
    ) {
        path = DirectoryNavigation.getInstance().getFullPath(path);
        destination = DirectoryNavigation.getInstance().getFullPath(destination);
        return ResponseEntity.ok(NavigationService.move(path, destination));
    }
}
