package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.restclient;

import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.Client;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.CalculatePriceResult;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.PricingOperations;

@Client(id = "pricing-service")
public interface PricingClient extends PricingOperations {
    @Override
    @Post("/pricing/calculate")
    CalculatePriceResult calculatePrice(CalculatePriceCommand cmd);
}