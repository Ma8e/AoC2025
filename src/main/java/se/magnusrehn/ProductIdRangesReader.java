package se.magnusrehn;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class ProductIdRangesReader {

    static public List<ProductIdRange> read(String resourceName) throws IOException {
        try (var reader = Reader.reader(resourceName)) {
            return Arrays.stream(reader.readLine().split(",")).map(r -> {
                var v = r.split("-");
                return new ProductIdRange(Long.parseLong(v[0]), Long.parseLong(v[1]));
            }).toList();
        }
    }
}
