package dev.fredyhg.productservice.configs;

import dev.fredyhg.productservice.exceptions.BeanUtilsParserExceptionClass;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class BeanPropertyUtils {
    public static void copyNonNullProperties(Object source, Object target) {
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(source.getClass());
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            try {
                Method readMethod = propertyDescriptor.getReadMethod();
                Method writeMethod = propertyDescriptor.getWriteMethod();
                if (readMethod != null && writeMethod != null) {
                    Object value = readMethod.invoke(source);
                    if (value != null) {
                        writeMethod.invoke(target, value);
                    }
                }
            } catch (Exception e) {
                throw new BeanUtilsParserExceptionClass("Could not copy property");
            }
        }
    }

    private BeanPropertyUtils() {}
}
