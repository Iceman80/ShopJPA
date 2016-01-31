import dbService.dao.ItemDAO;
import dbService.dao.UserDAO;
import dbService.dataSets.DBException;
import dbService.dataSets.UserDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Сергей on 29.01.2016.
 */
@Controller
public class UserController {
    @Autowired
    private UserDataSet userDataSet;

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = "application/json")
    public String getUser(@RequestParam(value = "name") String name, Model model) {
        try {
            userDataSet.addUser(name, "12323", 12, "test");
        } catch (DBException e) {
            e.printStackTrace();
        }

        model.addAttribute("name", name);

        return "";
    }
}
