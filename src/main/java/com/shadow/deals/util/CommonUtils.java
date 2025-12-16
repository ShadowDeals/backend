package com.shadow.deals.util;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.shadow.deals.base.enums.CommonEnum;
import com.shadow.deals.exception.APIException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.ParameterizedType;
import java.text.ParseException;

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

    public static String getUserEmailFromJWTToken(@NotNull HttpRequest<?> request){
        String authorization = request.getHeaders().get("Authorization").replace("Bearer ", "");

        String userEmail;
        try {
            JWT jwt = JWTParser.parse(authorization);
            userEmail = jwt.getJWTClaimsSet().getSubject();
        } catch (ParseException e) {
            throw new APIException(
                    "Во время парсинга JWT токена произошла ошибка",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return userEmail;
    }

    /**
     * This method allows to generate a string representation of UUID from a string.
     *
     * @param str the string to use to generate the UUID.
     * @return The string representation of UUID.
     */
    public static String generateUUIDFromString(@NotNull String str) {
        return UUID.nameUUIDFromBytes(str.getBytes()).toString();
    }
}