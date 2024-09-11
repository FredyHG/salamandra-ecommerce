package dev.fredyhg.orderservice.common.domain;

public abstract class Identifier<T> {
    private final T value;

    protected Identifier(final T value) {
        this.value = value;
    }

    public String fromValue() {
        return this.value.toString();
    }
}
