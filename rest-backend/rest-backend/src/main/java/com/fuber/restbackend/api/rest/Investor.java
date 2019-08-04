package com.fuber.restbackend.api.rest;

import com.fuber.restbackend.api.rest.dto.RawBasicPurchase;
import com.fuber.restbackend.bod.Resource;
import com.fuber.restbackend.service.DataService;
import com.fuber.restbackend.utils.Filters;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class Investor {

    private final DataService dataService;

    public Investor(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping("/available_to_purchase")
    public List<Resource> getResources(@RequestParam(value="type", required = false) String type) {
        List<Resource> availableResource = Filters.getResourcesAvailableToPurchase(dataService.getResourceList().values());
        if(type == null) {
            return availableResource;
        }
        return Filters.resourcesByType(availableResource, type);
    }

    @RequestMapping("/purchase")
    public void purchaseResource(@RequestBody RawBasicPurchase purchase) {
        dataService.purchaseResource(purchase.getResource_id(), purchase.getPercentage_share());
    }

}
