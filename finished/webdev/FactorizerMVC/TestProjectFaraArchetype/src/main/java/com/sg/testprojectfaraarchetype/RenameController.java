package com.sg.testprojectfaraarchetype;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/controllerName"})
public class RenameController {

    public ControllerConstructor() {
    }

    @RequestMapping(value="/methodnameurl", method=RequestMethod.GET)
    public String methodName(Map<String, Object> model) {

        /*
        Get incoming parameters: request.getParameter("sameNameAsInIndex.jsp")
        Do some calculations: probably need a service layer called above, maybe DTO, DAO, etc
        Set values on the model Map: model.put("nameForResult.jsp", variableDefinedHere)
        Forward control to another JSP: return "result";
        */

    }
}
