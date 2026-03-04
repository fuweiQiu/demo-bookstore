package demo.dto.file.directory;

import lombok.experimental.UtilityClass;
import demo.exception.file.NotDirectoryException;
import demo.util.file.FileUtils;

import java.nio.file.Path;

@UtilityClass
public class DirectoryFactory {
    public Directory create(Path path) {
        if (FileUtils.isNotDirectory(path)) {
            throw new NotDirectoryException(path);
        }
        return new DirectoryImpl(path);
    }
}
