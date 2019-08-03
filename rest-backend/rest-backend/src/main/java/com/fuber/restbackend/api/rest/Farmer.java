package com.fuber.restbackend.api.rest;

import com.fuber.restbackend.api.rest.dto.RawBasicRental;
import com.fuber.restbackend.bdo.Resource;
import com.fuber.restbackend.data.DataService;
import com.fuber.restbackend.utils.Filters;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class Farmer {

    private final DataService dataService;

    public Farmer(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping("/available_types_to_rent")
    public List<Resource> getAvailableResource(@RequestParam(value = "start_date", required = false) String start_date,
                                               @RequestParam(value = "end_date", required = false) String end_date) {
        DateTime startDate = new DateTime(start_date);
        DateTime endDate = new DateTime(end_date);
        return Filters.resourcesAvailableByDate(dataService.getResourceList().values(), startDate, endDate);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void checkoutRental(@RequestBody List<RawBasicRental> items){
        for(RawBasicRental rental : items) {
            System.out.println(rental);
            //dataService.addRental(rental);
        }
    }

}
