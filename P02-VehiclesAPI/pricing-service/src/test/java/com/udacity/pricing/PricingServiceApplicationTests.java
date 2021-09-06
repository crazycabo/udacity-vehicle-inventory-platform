package com.udacity.pricing;

import com.udacity.pricing.domain.Price;
import com.udacity.pricing.domain.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricingServiceApplicationTests {

	@MockBean
	private PriceRepository priceRepository;

	@Test
	public void contextLoads() {
	}

	// Silly tests as we don't actually have services or logic that transform data
	@Test
	public void getVehiclePriceData() {
		Price p = Price.builder()
				.id(3L)
				.currency("JPY")
				.price(BigDecimal.valueOf(150000.00))
				.vehicleId(1234L)
				.build();

		when(priceRepository.findById(3L)).thenReturn(Optional.of(p));

		assertAll(
				() -> assertEquals("JPY", p.getCurrency()),
				() -> assertEquals(BigDecimal.valueOf(150000.00), p.getPrice()),
				() -> assertEquals(1234L, p.getVehicleId())
		);
	}

	@Test
	public void getVehicleReturnsNullIfNoneFound() {
		when(priceRepository.findById(99L)).thenReturn(Optional.empty());

		assertEquals(Optional.empty(), priceRepository.findById(99L));
	}

}
