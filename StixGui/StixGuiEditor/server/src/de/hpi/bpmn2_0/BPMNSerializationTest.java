package de.hpi.bpmn2_0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.json.JSONException;
import org.oryxeditor.server.diagram.Diagram;
import org.oryxeditor.server.diagram.DiagramBuilder;
import org.oryxeditor.server.diagram.JSONBuilder;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import de.hpi.bpmn2_0.model.Definitions;
import de.hpi.bpmn2_0.transformation.BPMN2DiagramConverter;
import de.hpi.bpmn2_0.transformation.BPMNPrefixMapper;
import de.hpi.bpmn2_0.transformation.Diagram2BpmnConverter;

public class BPMNSerializationTest {

	final static String path = "C:\\Dokumente und Einstellungen\\Sven\\workspace\\oryx_bpmn2.0_2\\editor\\server\\src\\de\\hpi\\bpmn2_0\\";
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		toBpmn2_0();
//		fromXml();

	}
	
	public static void toBpmn2_0() throws Exception {
		File json = new File(path + "trivial_bpmn2.0_process.json");
		BufferedReader br = new BufferedReader(new FileReader(json));
		String bpmnJson = "";
		String line;
		while((line = br.readLine()) != null) {
			bpmnJson += line;
		}
		Diagram diagram = DiagramBuilder.parseJson(bpmnJson);
		
		Diagram2BpmnConverter converter = new Diagram2BpmnConverter(diagram);
		Definitions def = converter.getDefinitionsFromDiagram();
		
//		final XMLStreamWriter xmlStreamWriter = XMLOutputFactory
//		.newInstance().createXMLStreamWriter(System.out);
//		
//		xmlStreamWriter.setPrefix("bpmndi","http://bpmndi.org");
		
		JAXBContext context = JAXBContext.newInstance(Definitions.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		NamespacePrefixMapper nsp = new BPMNPrefixMapper();
		m.setProperty("com.sun.xml.bind.namespacePrefixMapper", nsp);
		m.marshal(def, System.out);
		
//		SyntaxChecker checker = def.getSyntaxChecker();
//		checker.checkSyntax();
//		
//		System.out.println(checker.getErrorsAsJson().toString());
	}
	
	public static void fromXml() throws JAXBException, JSONException {
		File xml = new File(path + "bpmntest.xml");
		JAXBContext context = JAXBContext.newInstance(Definitions.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Definitions def = (Definitions) unmarshaller.unmarshal(xml);
		
		BPMN2DiagramConverter converter = new BPMN2DiagramConverter("/StixGuiEditor/");
		List<Diagram> dia = converter.getDiagramFromBpmn20(def);
		
		System.out.println(JSONBuilder.parseModeltoString(dia.get(0)));
	}
}
