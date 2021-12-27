package delphitree.exceptions;

import delphitree.IncludeType;

public class UnhandledIncludeType extends RuntimeException {
    public UnhandledIncludeType(IncludeType includeType) {
        super(String.format("Неизвестный тип включения: %s", includeType));
    }

    public UnhandledIncludeType(String includeType) {
        super(String.format("Неизвестный тип включения: %s", includeType));
    }
}
