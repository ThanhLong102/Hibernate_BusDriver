package Util;

import java.util.Collection;

public class DataUtil {
    public static boolean isEmptyOrNull(Object obj) {
        return obj == null;
    }

    public static boolean isEmpty(Collection collection) {
        return collection!=null && collection.isEmpty();
    }
}
