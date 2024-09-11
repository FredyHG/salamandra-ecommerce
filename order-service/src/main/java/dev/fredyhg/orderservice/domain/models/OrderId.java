package dev.fredyhg.orderservice.domain.models;

import dev.fredyhg.orderservice.common.domain.Identifier;

import java.util.UUID;

public class OrderId extends Identifier<UUID> {
    protected OrderId() {
        super(UUID.randomUUID());
    }
}
