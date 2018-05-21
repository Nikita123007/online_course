package Documents;

import java.util.HashMap;
import java.util.Map;

public class DocumentServiceFactory {

    private static final DocumentServiceFactory factory = new DocumentServiceFactory();

    private Map<String, DocumentService> services = new HashMap<>();

    private DocumentServiceFactory() {
        services.put("Course", new CourseDocumentService());
        services.put("User", new UserDocumentService());
        services.put("Test", new TestDocumentService());
    }

    public static DocumentServiceFactory getInstance() {
        return factory;
    }

    public DocumentService getDocumentService(String name){
        if(services.containsKey(name))
            return services.get(name);

        return null;
    }


}
