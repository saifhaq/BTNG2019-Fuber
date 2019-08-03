package com.fuber.restbackend.bdo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    //int id;
    int resource_id;
    //int user_id;
    DateTime from;
    DateTime to;
    //float price_per_day;

//    public RawBasicRental(int resource_id, String from, String to) {
//        this.id = Integer.MAX_VALUE;
//        this.resource_id = resource_id;
//        this.user_id = Integer.MAX_VALUE;
//        this.from = new DateTime(from);
//        this.to = new DateTime(to);
//        this.price_per_day = (float) (Math.random() * Math.floor(Double.MAX_VALUE));
//    }
//
//    public RawBasicRental(int id, int resource_id, DateTime from, DateTime to){
//        this.id = id;
//        this.resource_id = resource_id;
//        this.user_id = Integer.MAX_VALUE;
//        this.from = from;
//        this.to = to;
//        this.price_per_day = (float) (Math.random() * Math.floor(Double.MAX_VALUE));
//    }
//
//    public void setId(int id){
//        this.id = id;
//    }

}
