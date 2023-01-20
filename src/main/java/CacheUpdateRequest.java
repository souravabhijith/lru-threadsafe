import java.util.Map;

public class CacheUpdateRequest {

    RequestType type;
    String key;
    String value;

    public CacheUpdateRequest(RequestType type, String key, String value) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return "CacheUpdateRequest {" +
                "type=" + type +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
