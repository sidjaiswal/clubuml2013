/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.kai.uml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
/**
 *
 * @author Kai
 */
public class EcoreParser {
    
    private static ArrayList<EObject> list = new ArrayList<EObject>();
    private static String pathToEcore;
    
    public EcoreParser(final String path) throws IOException
    {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
        pathToEcore = path;
        generate();
    }
    
    public static void generate() throws IOException
    { 
       ResourceSet resourceSet = new ResourceSetImpl();
       Resource res = new ResourceImpl();

       final URI uri = URI.createFileURI(pathToEcore);   
       res = resourceSet.createResource(uri);
       res.load(Collections.emptyMap());
       
       EList<EObject> objList = res.getContents();

       for (int i=0;i<objList.size();i++){
           EObject pkg = objList.get(i);
           list.add(pkg);
       }
    }
    
    public ArrayList<EObject> getList() {
        return list;
    }

}
