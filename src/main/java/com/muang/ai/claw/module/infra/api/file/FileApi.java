package com.muang.ai.claw.module.infra.api.file;

import com.muang.ai.claw.module.infra.service.file.FileService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotEmpty;

/**
 * 文件 API 实现类
 *
 */
@Service
@Validated
public class FileApi {

    @Resource
    private FileService fileService;

    public String createFile(byte[] content, String name, String directory, String type) {
        return fileService.createFile(content, name, directory, type);
    }

    public String presignGetUrl(String url, Integer expirationSeconds) {
        return fileService.presignGetUrl(url, expirationSeconds);
    }

    public String createFile(byte[] content) {
        return createFile(content, null, null, null);
    }

    public String createFile(byte[] content, String name) {
        return createFile(content, name, null, null);
    }

}
