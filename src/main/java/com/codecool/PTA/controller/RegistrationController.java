package com.codecool.PTA.controller;


import com.codecool.PTA.model.user.GenderEnum;
import com.codecool.PTA.model.user.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class RegistrationController extends AbstractController {

    @GetMapping(value = "/registration")
    public String registrationView(Model model){
        model.addAttribute("student", new Student());
        return "registration/registration";
    }

    @PostMapping("/reg")
    public String registrationSave(@ModelAttribute Student student){
        return "/login";
    }

////TODO
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//
//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        engine.process("registration/registration.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        if (passwordsMatch(req)) {
//            Student student = createNewlyRegisteredStudent(req);
//            if(persistenceImplementation.persist(student)) {
//                session.setAttribute("student", student);
//                clearPasswordNotMatchFromSession(session);
//                if (session.getAttribute("used") != null) {
//                    session.removeAttribute("used");
//                }
//                resp.sendRedirect("");
//            } else {
//                session.setAttribute("used", "used username or password");
//                resp.sendRedirect("/registration");
//            }
//
//        } else {
//            addPasswordNotMatchToSession(session);
//            resp.sendRedirect("/registration");
//        }
//    }
//TODO create abstract class with these helper methods
    private void addPasswordNotMatchToSession(HttpSession session) {
        session.setAttribute("passwordNotMatch", "The passwords you entered are not matching!");
    }

    private void clearPasswordNotMatchFromSession(HttpSession session) {
        if (session.getAttribute("passwordNotMatch") != null) {
            session.removeAttribute("passwordNotMatch");
        }
    }

    private boolean passwordsMatch(HttpServletRequest req) {
        String password = req.getParameter("password");
        String passwordConfirm = req.getParameter("password_confirm");

        return password.equals(passwordConfirm);
    }

    GenderEnum translateGender(String genderChecked) {
        GenderEnum gender;
        if (genderChecked.equals("female")) {
            gender = GenderEnum.FEMALE;
        } else if (genderChecked.equals("male")) {
            gender = GenderEnum.MALE;
        } else {
            gender = GenderEnum.OTHER;
        }
        return gender;
    }

//
//    private Student createNewlyRegisteredStudent(HttpServletRequest req) {
//        String hashedPassword = passwordHashing.hashPassword(req.getParameter("password"));
//        Course course = persistenceImplementation.findCourseByName(CourseType.ORIENTATION);
//        GenderEnum gender = translateGender(req.getParameter("gender"));
//
//        return new Student(
//                req.getParameter("username"),
//                hashedPassword,
//                req.getParameter("first_name"),
//                req.getParameter("last_name"),
//                req.getParameter("email"),
//                course,
//                gender
//        );
//    }

}
