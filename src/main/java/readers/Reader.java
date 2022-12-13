package readers;

import java.util.List;

public interface Reader {
    public List<?> readFromFile(final String fileName);
}
