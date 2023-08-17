package projectpassweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import projectpassweb.service.packaze.PackageService;
import projectpassweb.service.pass.BulkPassService;
import projectpassweb.service.statistics.StatisticsService;
import projectpassweb.service.user.UserGroupMappingService;
import projectpassweb.util.LocalDateTimeUtils;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/admin")
public class AdminViewController {

    private final BulkPassService bulkPassService;
    private final PackageService packageService;
    private final UserGroupMappingService userGroupMappingService;
    private final StatisticsService statisticsService;

    public AdminViewController(BulkPassService bulkPassService, PackageService packageService, UserGroupMappingService userGroupMappingService, StatisticsService statisticsService) {
        this.bulkPassService = bulkPassService;
        this.packageService = packageService;
        this.userGroupMappingService = userGroupMappingService;
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public ModelAndView home(ModelAndView modelAndView, @RequestParam("to") String toString) {
        LocalDateTime to = LocalDateTimeUtils.parseDate(toString);

//        charData 를 조회, (라벨, 출석횟수, 취소횟수)
        modelAndView.addObject("chartData", statisticsService.makeChartData(to));
        modelAndView.setViewName("admin/index");
        return modelAndView;
    }

    @GetMapping("/bulk-pass")
    public ModelAndView registerBulkPass(ModelAndView modelAndView){
//        bulk pass 목록 조회
        modelAndView.addObject("bulkPasses", bulkPassService.getAllBulkPasses());
//        bulk pass를 등록할 때 필요한 Package 값을 위해 모든 package를 조회
        modelAndView.addObject("packages",packageService.getAllPackages());
//        bulk pass를 등록할 때 필요한 user_GroupId 값을 위해 모든 UserGroupId 를 조회
        modelAndView.addObject("userGroupIds",userGroupMappingService.getAllUserGroupIds());
//        bulk pass request 를 제공
        modelAndView.addObject("request", new BulkPassRequest());
        modelAndView.setViewName("admin/bulk-pass");

        return modelAndView;
    }

    @PostMapping("/bulk-pass")
    public String addBulkPass(@ModelAttribute("request") BulkPassRequest request, Model model){
//        bulk pass를 생성
        bulkPassService.addBulkPass(request);
        return "redirect:/admin/bulk-pass";
    }

}
