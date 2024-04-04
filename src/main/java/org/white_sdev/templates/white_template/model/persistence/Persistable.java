package org.white_sdev.templates.white_template.model.persistence;

import jakarta.persistence.Entity;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;

/**
 * Interface that represents all the persistable instances in the app and standardizes some common methods they have.
 */
public interface Persistable extends Serializable {

    /**
     * The <a href="https://docs.oracle.com/cd/E19798-01/821-1841/6nmq2cpak/index.html#:~:text=If%20an%20entity%20instance%20is,classes%20may%20extend%20entity%20classes">standard</a>
     * recommends this in case the {@link Serializable} object could be deserialized.
     */
    long serialVersionUID = 1L;

    /**
     * Compares both {@link Persistable Persistables} {@link Entity Entities} properties and returns whether they have the exact same elements or not.
     * Compares the actual object [<code>this</code>] with the one provided.
     *
     * @param persistable The second {@link Persistable} {@link Object} to compare with <code>this</code>.
     * @return <code>true</code> in case both objects are clones, <code>false</code> in case the provided parameter is <code>null</code> or is not a clone of <code>this</code>.
     */
    @SuppressWarnings("unused")
    default boolean isClone(Persistable persistable) {
        try {
            if (this.getClass() != persistable.getClass()) return false;

            BeanInfo entityInfo = Introspector.getBeanInfo(persistable.getClass());
            PropertyDescriptor[] propertyDescriptors = entityInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                Object persistablePropertyValue = propertyDescriptor.getReadMethod().invoke(persistable);
                Object thisPropertyValue = propertyDescriptor.getReadMethod().invoke(this);
                if (persistablePropertyValue != null) {
                    if (thisPropertyValue != null) {
                        if (persistablePropertyValue != thisPropertyValue) return false;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            throw new RuntimeException("Impossible to validate if the object is a clone due to an unknown internal error.", ex);
        }
    }

    /**
     * Compares both {@link Persistable Persistables} {@link Entity Entities} properties and returns whether
     * they have the exact same elements or not.Compares the actual object [<code>this</code>] with the one provided.
     *
     * @param original  The first {@link Persistable} {@link Entity} to compare whether is clone of {@code candidate} or not.
     * @param candidate The second {@link Persistable} {@link Object} to compare with {@code original}.
     * @return <code>true</code> in case both objects are clones, <code>false</code> in case any of the parameters are <code>null</code> or not clones.
     */
    @SuppressWarnings("unused")
    default boolean areClones(Persistable original, Persistable candidate) {
        try {
            if (original.getClass() != candidate.getClass()) return false;

            BeanInfo entityInfo = Introspector.getBeanInfo(candidate.getClass());
            PropertyDescriptor[] propertyDescriptors = entityInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                Object persistablePropertyValue = propertyDescriptor.getReadMethod().invoke(candidate);
                Object thisPropertyValue = propertyDescriptor.getReadMethod().invoke(original);
                if (persistablePropertyValue != null) {
                    if (thisPropertyValue != null) {
                        if (persistablePropertyValue != thisPropertyValue) return false;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            throw new RuntimeException("Impossible to complete the operation due to an unknown internal error.", ex);
        }
    }
}
