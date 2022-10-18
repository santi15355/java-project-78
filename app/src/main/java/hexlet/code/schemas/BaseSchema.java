package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final Map<String, Predicate<Object>> predicates = new HashMap<>();
    private static String status;
    private boolean result;

    public final boolean isValid(Object obj) {

        if (predicates.isEmpty()) {
            result = true;
        } else if (status.equals("required") && obj == null || obj == "") {
            result = false;
        } else {
            for (var predicate : predicates.entrySet()) {
                return predicate.getValue().test(obj);
            }
        }
        return result;
    }

    public final void transferData(String flag, Predicate<Object> predicate) {
        status = flag;
        predicates.put(status, predicate);
    }
}
