package com.crochet.spring.jpa.demo.dto;

import com.crochet.spring.jpa.demo.dto.paypal.MoneyDTO;
import com.crochet.spring.jpa.demo.dto.paypal.OrderDTO;
import com.crochet.spring.jpa.demo.dto.paypal.PayPalAppContextDTO;
import com.crochet.spring.jpa.demo.dto.paypal.PurchaseUnit;
import com.crochet.spring.jpa.demo.type.paypal.OrderIntent;
import com.crochet.spring.jpa.demo.type.paypal.PaymentLandingPage;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(fluent = true)
public class OrderDTOBuilder {
  private OrderIntent intent;
  private List<PurchaseUnit> purchaseUnits;
  private PayPalAppContextDTO applicationContext;

  public OrderDTO build() {
    return new OrderDTO(intent, purchaseUnits, applicationContext);
  }

  public OrderDTOBuilder createPayPalOrderDTO(String currencyCode,
                                              String value,
                                              String returnUrl) {
    String baseUri = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
    var appContext = PayPalAppContextDTO.builder()
        .returnUrl(baseUri + returnUrl)
        .brandName("Little Crochet")
        .landingPage(PaymentLandingPage.BILLING)
        .build();
    MoneyDTO moneyDTO = MoneyDTO.builder()
        .currencyCode(currencyCode)
        .value(String.valueOf(value))
        .build();
    PurchaseUnit purchaseUnit = PurchaseUnit.builder()
        .amount(moneyDTO)
        .build();
    List<PurchaseUnit> purchaseUnits = new ArrayList<>();
    purchaseUnits.add(purchaseUnit);
    intent(OrderIntent.CAPTURE)
        .applicationContext(appContext)
        .purchaseUnits(purchaseUnits)
        .build();
    return this;
  }
}
