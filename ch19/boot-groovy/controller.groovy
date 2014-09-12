@Controller
@RequestMapping("/documents")
class MyDocumentsController {

    @Autowired
    private MyDocumentsService myService;

    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public def documents() {
        myService.docs
    }

}
