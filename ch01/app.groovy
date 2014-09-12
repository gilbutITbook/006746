@Controller
class MyApp {

    @RequestMapping("/")
    @ResponseBody
    String message() {
        return "<h1>Hello World!</h1>"
    }

}