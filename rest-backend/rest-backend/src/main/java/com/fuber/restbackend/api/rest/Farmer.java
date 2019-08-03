package com.fuber.restbackend.api.rest;

import com.fuber.restbackend.api.rest.dto.RawBasicRental;
import com.fuber.restbackend.api.rest.dto.RawBasicRentalWrapper;
import com.fuber.restbackend.bod.Resource;
import com.fuber.restbackend.service.DataService;
import com.fuber.restbackend.utils.Filters;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class Farmer {

    private final DataService dataService;
    private final DateTimeFormatter formatter;

    public Farmer(DataService dataService, DateTimeFormatter formatter) {
        this.dataService = dataService;
        this.formatter = formatter;
    }

    @RequestMapping("/available_types_to_rent")
    public List<Resource> getAvailableResource(@RequestParam(value = "from", required = false) String startDateStr,
                                               @RequestParam(value = "to", required = false) String endDateStr) {
        DateTime startDate = DateTime.parse(startDateStr, formatter);
        DateTime endDate = DateTime.parse(endDateStr, formatter);
        return Filters.resourcesAvailableByDate(dataService.getResourceList().values(), startDate, endDate);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void checkoutRental(@RequestBody RawBasicRentalWrapper items){
        for(RawBasicRental rental : items.getItems()) {
            dataService.newRental(rental);
        }
    }

}
