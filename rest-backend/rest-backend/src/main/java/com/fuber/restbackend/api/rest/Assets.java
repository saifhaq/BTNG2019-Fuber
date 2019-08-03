package com.fuber.restbackend.api.rest;

import com.fuber.restbackend.data.DataService;
import com.fuber.restbackend.dto.Rental;
import com.fuber.restbackend.dto.Resource;
import com.fuber.restbackend.utils.Filters;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Filter;

@RestController
@RequestMapping("/assets")
public class Assets {

    private final DataService dataService;

    public Assets(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping("/available_to_purchase")
    public List<Resource> getResources(@RequestParam(value="type", defaultValue="") String type) {
        //TODO Filter by type
        return dataService.getResourceList();
    }

    @RequestMapping("/purchase")
    public void purchaseResource(@RequestParam(value = "resource_id") int resourceId) {
        //TODO
    }

    @RequestMapping("/available_types_to_rent")
    public List<Resource> getAvailableResource(@RequestParam(value = "from", required = false) String start_date,
                                             @RequestParam(value = "to", required = false) String end_date) {
        DateTime startDate = new DateTime(start_date);
        DateTime endDate = new DateTime(end_date);
        return Filters.resourcesAvailableByDate(dataService.getResourceList(), startDate, endDate);
    }

    @RequestMapping("/checkout")
    public void checkoutRental(@RequestParam(name = "items")List<Rental> items){
        //TODO
    }

}
