package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.common.MessageConstant;
import com.crochet.spring.jpa.demo.dto.ghn.store.GHNShopCreationRequest;
import com.crochet.spring.jpa.demo.model.CartDetail;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.model.Product;
import com.crochet.spring.jpa.demo.model.Shop;
import com.crochet.spring.jpa.demo.repository.ShopRepo;
import com.crochet.spring.jpa.demo.service.CustomerService;
import com.crochet.spring.jpa.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShopServiceImpl implements ShopService {
  @Autowired
  private ShopRepo shopRepo;
  @Autowired
  private CustomerService customerService;

  @Override
  public String createOrUpdate(GHNShopCreationRequest request) {
    Shop shop;
    if (request.getId() == null) {
      shop = new Shop();
      var customer = customerService.getCustomerById(request.getCustomerId());
      shop.setCustomer(customer);
    } else {
      shop = this.getById(request.getId());
    }
    shop.setShopName(request.getShopName());
    shop.setPhone(request.getPhone());
    shop.setAddress(request.getAddress());
    shop.setWardCode(request.getWardCode());
    shop.setWardName(request.getWardName());
    shop.setDistrictID(request.getDistrictId());
    shop.setDistrictName(request.getDistrictName());
    shop.setProvinceName(request.getProvinceName());
    shopRepo.save(shop);
    return MessageConstant.SHOP_SUCCESSFUL_CREATION;
  }

  @Override
  public List<Shop> getAll() {
    return shopRepo.findAll();
  }

  @Override
  public Shop getById(UUID id) {
    return shopRepo.findById(id)
        .orElseThrow(() -> new RuntimeException(MessageConstant.SHOP_NOTFOUND));
  }

  @Override
  public Shop findShopForProducts(Customer customer) {
    return customer.getCart()
        .getCartDetails()
        .stream()
        .map(CartDetail::getProduct)
        .map(Product::getShop)
        .findFirst()
        .orElseThrow(() -> new RuntimeException(MessageConstant.CART_EMPTY));
  }
}
