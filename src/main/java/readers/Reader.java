package readers;

import java.util.List;

public interface Reader {
    List<?> readFromFile(final String fileName);
}

