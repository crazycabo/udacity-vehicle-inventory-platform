package com.udacity.pricing.domain;

import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<Price, Long> {
}
