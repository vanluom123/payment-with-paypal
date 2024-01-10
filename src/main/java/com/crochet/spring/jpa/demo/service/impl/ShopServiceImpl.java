package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.dto.ghn.store.GHNShopCreationRequest;
import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.model.Product;
import com.crochet.spring.jpa.demo.model.Shop;
import com.crochet.spring.jpa.demo.repository.ShopRepo;
import com.crochet.spring.jpa.demo.service.CustomerService;
import com.crochet.spring.jpa.demo.service.ShopService;
import com.crochet.spring.jpa.demo.service.ghn.GHNService;
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
    @Autowired
    private GHNService ghnService;

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
        return "Create shop success";
    }

    @Override
    public List<Shop> getAll() {
        return shopRepo.findAll();
    }

    @Override
    public Shop getById(UUID id) {
        return shopRepo.findById(id).orElseThrow(() -> new RuntimeException("Cannot found shop"));
    }

    @Override
    public Shop findShopForProducts(Customer customer) {
        return customer.getCarts()
                .stream()
                .map(Cart::getProduct)
                .map(Product::getShop)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Shop not found"));
    }
}
