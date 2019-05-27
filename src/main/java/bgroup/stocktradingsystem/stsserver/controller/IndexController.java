package bgroup.stocktradingsystem.stsserver.controller;

import bgroup.stocktradingsystem.stsserver.domain.Index;
import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import bgroup.stocktradingsystem.stsserver.service.IndexService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class IndexController {
    @Autowired
    IndexService indexService;

    private Gson gson = new Gson();

    /**
     * @return Index类型的List, 包含所有指数信息
     */
    @RequestMapping(value = "/index/all", method = GET)
    @ResponseBody
    public String fetchAllIndex() {
        return new CustomResponse(new Result(true),
                indexService.fetchAllIndex()).toString();
        // TODO 失败判断
    }

    /**
     * @param data 指定指数代码
     * @return 指定代码的单只指数信息
     */
    @RequestMapping(value = "/index/one", method = GET)
    @ResponseBody
    public String fetchOneIndex(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        String code = gson.fromJson(data, String.class);
        return new CustomResponse(new Result(true),
                indexService.fetchCertainIndex(code)).toString();
        // TODO 失败判断
    }


    /**
     * @param data 更新后的单只指数信息
     * @return 成功或失败原因
     */
    @RequestMapping(value = "/index/update", method = POST)
    @ResponseBody
    public String updateIndex(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        Index index = gson.fromJson(data, Index.class);
        indexService.updateIndex(index);
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }


    /**
     * @param data 更新后的一系列指数信息
     * @return 成功或失败原因
     */
    @RequestMapping(value = "/index/update_list", method = POST)
    @ResponseBody
    public String updateIndexList(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        Type listType = new TypeToken<ArrayList<Index>>(){}.getType();
        List<Index> indices = new Gson().fromJson(data, listType);
        indexService.updateIndexList(indices);
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }
}
