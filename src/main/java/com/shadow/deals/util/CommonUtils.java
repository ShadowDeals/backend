package com.shadow.deals.util;

import com.shadow.deals.base.enums.CommonEnum;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.ParameterizedType;

/**
 * This class contains methods used by various classes that are not united by a specific topic.
 *
 * @author Kirill "Tamada" Simovin
 */
public class CommonUtils {

    /**
     * This method allows to find the corresponding Enum element by title.
     *
     * @param title element's title.
     * @return Enum element that corresponding to the given title. If the corresponding title is not found, {@code null}
     * is returned.
     * @throws IllegalStateException in case class is not parametrized with generic type.
     * @see CommonEnum#fromTitle(String)
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static <T extends CommonEnum<?>> T enumElementFromTitle(Class<? extends CommonEnum<?>> enumClazz, String title) {
        if (enumClazz == null) {
            return null;
        }

        CommonEnum<?>[] enumObjects = enumClazz.getEnumConstants();
        if (enumObjects == null || enumObjects.length == 0) {
            return null;
        }
        return (T) enumObjects[0].fromTitle(title);
    }

    /**
     * This method allows to get a generic class.
     *
     * @return Generic class.
     * @throws IllegalStateException in case class is not parametrized with generic type.
     * @see <a href="https://stackoverflow.com/a/33997207">Source</a>.
     */
    @NotNull
    public static Class<?> reflectClassType(@NotNull Class<?> clazz) throws IllegalStateException {
        try {
            ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
            String className = parameterizedType.getActualTypeArguments()[0].getTypeName();
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class is not parametrized with generic type! Please use extends <>!");
        }
    }
}