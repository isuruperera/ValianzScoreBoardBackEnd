package com.uom.cse.ValianzScoreBoardBackEnd.Controller;


import com.uom.cse.ValianzScoreBoardBackEnd.Beans.BaseResponse;
import com.uom.cse.ValianzScoreBoardBackEnd.Beans.ScoreBoardRequestBean;
import com.uom.cse.ValianzScoreBoardBackEnd.Beans.ScoreUpdateRequestBean;
import com.uom.cse.ValianzScoreBoardBackEnd.Services.ScoreRequestService;
import com.uom.cse.ValianzScoreBoardBackEnd.Services.ScoreUpdaterService;
import com.uom.cse.ValianzScoreBoardBackEnd.Util.JSONHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RestController
@RequestMapping(value="/api")
public class BackEndRestController {

    @Autowired
    private ScoreRequestService scoreRequestService;

    @Autowired
    private ScoreUpdaterService scoreUpdaterService;

    @CrossOrigin()
    @RequestMapping(value="/requestData",method= RequestMethod.POST)
    public String requestData(@RequestBody String data) throws Exception {
        ScoreBoardRequestBean scoreBoardRequest = JSONHandler.parseFromJSON(data,ScoreBoardRequestBean.class);
        BaseResponse response = scoreRequestService.requestData(scoreBoardRequest);
        return JSONHandler.parseToJSON(response);
    }

    @CrossOrigin()
    @RequestMapping(value="/auth/user",method= RequestMethod.GET)
    public String version(){
        return "{SUCCESS}";
    }

    @CrossOrigin()
    @RequestMapping(value="/options",method= RequestMethod.OPTIONS)
    public String options(){
        return "{SUCCESS}";
    }

    @CrossOrigin()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value="/auth/admin",method= RequestMethod.GET)
    public String versionAdmin(){
        return "{SUCCESS}";
    }

    @CrossOrigin()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value="/updateData",method= RequestMethod.POST)
    public String updateData(@RequestBody String data) throws Exception {
        ScoreUpdateRequestBean scoreUpdateRequest = JSONHandler.parseFromJSON(data,ScoreUpdateRequestBean.class);
        BaseResponse response = scoreUpdaterService.updateData(scoreUpdateRequest);
        return JSONHandler.parseToJSON(response);
    }




}
