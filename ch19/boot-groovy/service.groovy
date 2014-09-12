@Service
class MyDocumentsService {

    @Autowired
    MyDocumentsRepo repo

    def getDocs() {
        repo.allDocuments
    }
    
}