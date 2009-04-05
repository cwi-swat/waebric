
import java.io.*;
import java.net.URLEncoder;
import java.net.URI;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.Writer;

import org.w3c.tidy.*;
import org.w3c.dom.*;



public class REPLServlet extends HttpServlet {
    private static final REPL repl = new REPL();

    private Process eval(String source) throws IOException {
	File tempFile = File.createTempFile("source", ".tmp" );
	FileWriter writer = new FileWriter(tempFile);
	writer.write(source);
	writer.close();

	Process waebric = Runtime.getRuntime().exec("waebric " + tempFile.getAbsolutePath());

	try {
	    waebric.waitFor();
	}
	catch (InterruptedException e) {
	}
	return waebric;
	//return read(waebric.getInputStream());
    }


    private String ast(String source) throws IOException {
	File tempFile = File.createTempFile("source", ".tmp" );
	FileWriter writer = new FileWriter(tempFile);
	writer.write(source);
	writer.close();

	Process waebric = Runtime.getRuntime().exec("waebric-ast " + tempFile.getAbsolutePath());

	try {
	    waebric.waitFor();
	}
	catch (InterruptedException e) {
	}
	return read(waebric.getInputStream()).replaceAll(",", ", ");
    }

    private String read(InputStream input) throws IOException {
	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	StringBuilder string = new StringBuilder();
	String line = "";
	while ((line = reader.readLine()) != null) {
	    string.append(line);
	}
	return string.toString();
    }

    private String escape(String string) {
	return string.replaceAll("&","&amp;").replaceAll("<","&lt;").replaceAll(">", "&gt;");
    }

    private String tidy(String string) {
	Tidy tidy = new Tidy();
	tidy.setQuiet(true);
	tidy.setXHTML(true);
	tidy.setTidyMark(false);
	tidy.setMakeClean(false);
	tidy.setIndentContent(true);
	tidy.setIndentAttributes(false);
	//tidy.setSmartIndent(true);
	tidy.setDocType("omit");

	ByteArrayOutputStream output = new ByteArrayOutputStream();
	ByteArrayInputStream input = new ByteArrayInputStream(string.getBytes());
	Document doc = tidy.parseDOM(input, output);
	ByteArrayOutputStream pp = new ByteArrayOutputStream();
	tidy.pprint(doc, pp);
	return pp.toString();
    } 

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException {
	String source = request.getParameter("source");
	Process eval = eval(source);
	String result = read(eval.getInputStream());
	if (!result.equals("")) {
	    String escapedResult = escape(tidy(result));
	    // ;charset=utf-8
	    URI uri = null;
	    try {
		uri = new URI("data", "text/html;charset=utf-8," +
			      result, null);

	    }
	    catch (URISyntaxException e) {
		System.err.println(e.getMessage());
	    }
	    
	    String ast = ast(source);
	    try {
		repl.resultPage(response.getWriter(), source, escapedResult, 
				uri.toASCIIString(), ast);
	    }
	    catch (java.sql.SQLException e) {
	    }
	}
	else {
	    String error = read(eval.getErrorStream());
	    try {
		repl.errorPage(response.getWriter(), source, escape(error));
	    }
	    catch (Exception e) {
	    }
	}
    }
    

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException {
	
	String source = "module test\n\n def main \n  html { \n   head title \"Hello\";\n    body p \"Hello\";\n  }\nend";
	response.setContentType("text/html");
	try {
	    repl.homePage(response.getWriter(), source);
	}
	catch (java.sql.SQLException e) {
	}
    }
	    
 
    String getSource(HttpServletRequest request) {
	return request.getParameter("source");
    }
 
}