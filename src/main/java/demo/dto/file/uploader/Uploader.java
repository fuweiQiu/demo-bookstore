package demo.dto.file.uploader;

import org.springframework.lang.NonNull;
import demo.dto.file.directory.Directory;
import demo.exception.file.FileException;

@FunctionalInterface
public interface Uploader {
    @NonNull
    UploadResult upload(@NonNull Directory uploadTo) throws FileException;
}
