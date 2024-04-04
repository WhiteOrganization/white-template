package org.white_sdev.templates.white_template.model.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * Base class for all {@link Entity entities} in the app.
 */
@MappedSuperclass
@Getter
@Setter
@Slf4j
public abstract class WhiteBaseEntity implements Persistable {
    @Id
    @GeneratedValue
    public Long id;

    @Override
    public String toString() {
        return "(" +
                "id=" + id +
                ')';
    }

    @SuppressWarnings("unused")
    public String toFullString() {
        return "WhiteBaseEntity{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if(o == null) return false;
        if (this == o) return true;
        if (!(o instanceof final WhiteBaseEntity whiteEntity)) return false;
        return getId()!=null && Objects.equals(getId(), whiteEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
    @SuppressWarnings("unused")
    public boolean objectEquals(final Object o) {
        return super.equals(o);
    }
    @SuppressWarnings("unused")
    public int objectHashCode() {
        return super.hashCode();
    }
}