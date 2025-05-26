package com.shadow.deals.base.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shadow.deals.util.CommonUtils;
import org.jetbrains.annotations.Nullable;

/**
 * This interface contains methods that need to be override in the Enum.
 *
 * @param <E> The enum that implements this interface.
 * @author Kirill "Tamada" Simovin
 */
public interface CommonEnum<E extends CommonEnum<?>> {
    /**
     * This method allows to get the title of the Enum element.
     *
     * @return Element's title.
     */
    String getTitle();

    /**
     * This method allows to find the corresponding Enum element by title.
     *
     * @param title element's title.
     * @return Enum element that corresponding to the given title. If the corresponding title is not found, {@code null}
     * is returned.
     * @throws IllegalStateException in case class is not parametrized with generic type.
     */
    @JsonCreator
    @Nullable
    @SuppressWarnings("unchecked")
    default E fromTitle(String title) throws IllegalStateException {
        Class<E> clazz = (Class<E>) CommonUtils.reflectClassType(this.getClass());
        for (E e : clazz.getEnumConstants()) {
            if (!(e instanceof CommonEnum<?> commonEnum)) {
                continue;
            }

            if (commonEnum.getTitle().equals(title)) {
                return e;
            }
        }

        return null;
    }
}