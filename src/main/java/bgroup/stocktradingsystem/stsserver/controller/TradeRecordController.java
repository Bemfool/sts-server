package bgroup.stocktradingsystem.stsserver.controller;

import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import bgroup.stocktradingsystem.stsserver.service.TradeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class TradeRecordController {
    final private TradeRecordService tradeRecordService;

    @Autowired
    public TradeRecordController(TradeRecordService tradeRecordService) {
        this.tradeRecordService = tradeRecordService;
    }

    @RequestMapping(value = "/trade_record/{fundId}", method = GET)
    @ResponseBody
    public String fetchAllStock(@PathVariable String fundId) {
        return new CustomResponse(new Result(true),
                tradeRecordService.fetchRecord(Integer.valueOf(fundId))).toString();
    }
}
