package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.model.Shop;
import com.crochet.spring.jpa.demo.payload.dto.ghn.store.CreateOrUpdateShopRequest;
import com.crochet.spring.jpa.demo.repository.ShopRepo;
import com.crochet.spring.jpa.demo.service.CustomerService;
import com.crochet.spring.jpa.demo.service.ShopService;
import com.crochet.spring.jpa.demo.service.ghn.GHNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String createOrUpdate(CreateOrUpdateShopRequest request) {
        Shop shop;
        if (request.getId() == null) {
            shop = new Shop();
            var customer = customerService.getCustomerById(request.getCustomerId());
            shop.setCustomer(customer);
        } else {
            shop = this.getShopById(request.getId());
        }
        shop.setShopName(request.getShopName());
        shop.setPhone(request.getPhone());
        shop.setAddress(request.getAddress());
        shop.setWardName(request.getWardName());
        shop.setDistrictName(request.getDistrictName());
        shop.setProvinceName(request.getProvinceName());
        shopRepo.save(shop);
        return "Create shop success";
    }

    public Shop getShopById(UUID id) {
        return shopRepo.findById(id).orElseThrow(() -> new RuntimeException("Cannot found shop"));
    }
}
