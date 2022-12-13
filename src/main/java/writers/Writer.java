package writers;

import java.util.List;

public interface Writer {
    void writeToFile(final String fileName, final List<?> districts);
}
