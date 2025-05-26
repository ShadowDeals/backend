package com.shadow.deals.base.enums;

import com.shadow.deals.util.CommonUtils;
import jakarta.persistence.AttributeConverter;
import org.jetbrains.annotations.NotNull;

/**
 * This class defines the basic logic for converting Enum elements to a string and back.
 *
 * @param <E> The enum for which the converter needs to be implemented.
 * @author Kirill "Tamada" Simovin
 */
public abstract class CommonEnumAttributeConverter<E extends CommonEnum<E>> implements AttributeConverter<E, String> {
    /**
     * This method allows to convert an Enum element to a string.
     *
     * @param e Enum element that needs to convert.
     * @return Element's string representation.
     */
    @Override
    public String convertToDatabaseColumn(@NotNull E e) {
        return e.getTitle();
    }

    /**
     * This method allows to convert a string to the corresponding Enum element.
     *
     * @param s the string to be converted to the Enum element.
     * @return Enum element that corresponding to the given title. If the corresponding title is not found, {@code null}
     * is returned.
     * @throws IllegalStateException in case class is not parametrized with generic type.
     */
    @Override
    @SuppressWarnings("unchecked")
    public E convertToEntityAttribute(String s) {
        Class<E> clazz = (Class<E>) CommonUtils.reflectClassType(this.getClass());
        return CommonUtils.enumElementFromTitle(clazz, s);
    }
}