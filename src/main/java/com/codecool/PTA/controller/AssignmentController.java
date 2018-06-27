package com.codecool.PTA.controller;

import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.FillInTheBlank;
import com.codecool.PTA.model.quest.Kata;
import com.codecool.PTA.model.quest.PA;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.service.FillInTheBlankService;
import com.codecool.PTA.service.KataService;
import com.codecool.PTA.service.PaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//TODO
@Controller
public class AssignmentController extends AbstractController {

    @Autowired
    private KataService kataService;

    @Autowired
    private PaService paService;

    @Autowired
    private FillInTheBlankService fillInTheBlankService;

    @GetMapping("/assignments")
    public String listAssignments(Model model) {
        Student student = getLoggedInUser();
        CourseType courseName = student.getCourse().getName();
        Level levelName = student.getLevel();
        isNewFriendRequest();

        model.addAttribute("kataList", kataService.findKataTemplatesByCourseNameAndLevelName(courseName, levelName));
        model.addAttribute("paList", paService.findPaTemplatesByCourseNameAndLevelName(courseName, levelName));
        model.addAttribute("student", student);

        return "assignments/assignments";
    }

    @GetMapping("/fill/{id}")
    public String displayFillAssignment(@PathVariable Long id, @PathVariable String left, Model model) {
        Student student = getLoggedInUser();
        isNewFriendRequest();
        model.addAttribute("stundent", student);
        model.addAttribute("fill", fillInTheBlankService.findById(id));
        model.addAttribute("left", left);
        return "fillInTheBlank/fillInTheBlank";
    }

    @GetMapping("/kata")
    public String displayKataAssignment(Model model) {
        return "kata/katas";
    }

    @PostMapping("/kata")
    public String submitKataAssignment(@ModelAttribute Kata kata) {
        return "redirect:assignments/assignments";
    }

    @GetMapping("/pa")
    public String displayPAAssignment(Model model) {
        return "pa/pas";
    }

    @PostMapping("/pa")
    public String submitPAAssignment(@ModelAttribute PA pa) {
        return "redirect:assignments/assignments";
    }

    //TODO rewrite 5-question quiz
    //@GetMapping("/question")

    @GetMapping("/quiz")
    public String displayQuizAssignment(Model model) {
        return "quiz/quizzes";
    }

    //TODO is this an API?
//    @PostMapping("/quiz")


    // TODO: assignment GET
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if(checkLogin(req)) {
//            Student student = (Student) getLoggedInUser(req);
//            CourseType courseName = student.getCourse().getName();
//            Level levelName = student.getLevel();
//            isNewFriendRequest(req);
//            WebContext context = new WebContext(req, resp, req.getServletContext());
//            try{
//                List<Kata> tempKataList = persistenceImplementation.findAllKatas(courseName, levelName);
//                List<Kata> kataList = new ArrayList<>();
//                for (Kata kata:tempKataList) {
//                    if (kata.isTemplate){
//                        kataList.add(kata);
//                    }
//                }
//                context.setVariable("kataList", kataList);
//
//            } catch (IOException ex){
//                context.setVariable("kataList", null);
//            }
//
//            try {
//                List<PA> tempPaList = persistenceImplementation.findAllPaAssignments(courseName, levelName);
//                List<PA> paList = new ArrayList();
//                for (PA pa:tempPaList) {
//                    if (pa.isTemplate){
//                        paList.add(pa);
//                    }
//                }
//                context.setVariable("paList", paList);
//            } catch (IOException ex){
//                context.setVariable("paList", null);
//            }
//
//            context.setVariable("student", student);
//
//            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//            engine.process("assignments/assignments.html", context, resp.getWriter());
//        } else {
//            resp.sendRedirect("/login");
//        }
//    }
}
