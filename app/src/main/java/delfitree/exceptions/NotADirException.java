package delfitree.exceptions;

public class NotADirException extends RuntimeException {
    public NotADirException(String path) {
        super(String.format("Путь %s не является путём к папке", path));
    }
}
