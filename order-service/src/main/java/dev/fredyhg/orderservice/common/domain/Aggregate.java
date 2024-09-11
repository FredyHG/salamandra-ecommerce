package dev.fredyhg.orderservice.common.domain;

import lombok.Getter;

import java.util.Observable;

@Getter
public abstract class Aggregate<ID extends Identifier> extends Observable {

    private final ID id;

    protected Aggregate(ID id) {
        super();
        this.id = id;
    }
}
