package dev.fredyhg.orderservice.domain.models.product;

import dev.fredyhg.orderservice.common.domain.Identifier;

import java.util.UUID;

public class ProductId extends Identifier<UUID> {
    protected ProductId() {
        super(UUID.randomUUID());
    }
}
