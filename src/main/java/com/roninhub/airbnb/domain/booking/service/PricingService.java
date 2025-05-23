package com.roninhub.airbnb.domain.booking.service;

import com.roninhub.airbnb.domain.booking.constant.Currency;
import com.roninhub.airbnb.domain.booking.dto.BookingPrice;
import com.roninhub.airbnb.domain.booking.entity.HomestayAvailability;
import com.roninhub.airbnb.domain.homestay.entity.Homestay;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PricingService {

    private final DiscountService discountService;

    public BookingPrice calculate(final List<HomestayAvailability> aDays) {
        final var nights = aDays.size();
        var subtotal = BigDecimal.ZERO;

        for (var aDay: aDays) {
            subtotal = subtotal.add(aDay.getPrice());
        }

        final var discount = discountService.getDiscountAmount(subtotal, nights);

        return BookingPrice.builder()
                .subtotal(subtotal)
                .discount(discount)
                .totalAmount(subtotal.add(discount))
                .currency(Currency.USD.getValue())
                .build();
    }

    public BookingPrice calculate(Homestay homestay, List<HomestayAvailability> aDays) {
        final var nights = aDays.size();
        var subtotal = BigDecimal.ZERO;

        for (var aDay: aDays) {
            subtotal = subtotal.add(aDay.getPrice());
        }

        final var discount = discountService.getDiscountAmount(subtotal, nights);

        return BookingPrice.builder()
                .subtotal(subtotal)
                .discount(discount)
                .totalAmount(subtotal.add(discount))
                .currency(Currency.USD.getValue())
                .build();
    }
}
