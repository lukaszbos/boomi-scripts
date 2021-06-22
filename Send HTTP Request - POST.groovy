//https://community.boomi.com/s/article/Retrieving-a-Set-Cookie-property-from-a-server-response

import java.util.Properties;
import java.io.InputStream;
import java.io.OutputStream;
import com.boomi.execution.ExecutionUtil;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.nio.charset.StandardCharsets;


for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
	
    String data = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:a3w="http://serverName/A3WebServices/"> <soapenv:Header/> <soapenv:Body> <a3w:Conectar> <!--Optional:--> <a3w:AppID>39CF6774-0246-49E2-8CD5-3819BFF6483E</a3w:AppID> <!--Optional:--> <a3w:ClientCode>0</a3w:ClientCode> <!--Optional:--> <a3w:Usuario>Oscar</a3w:Usuario> <!--Optional:--> <a3w:Password>a3</a3w:Password> </a3w:Conectar> </soapenv:Body> </soapenv:Envelope>'

    URL url = new URL("http://10.28.196.190/A3Equipo/A3WebServices/Seguridad.asmx");

    HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
    
    urlConnection.setRequestMethod("POST");
    //urlConnection.setRequestProperty("Set-Cookie", props.getProperty("document.dynamic.userdefined.ciastko")); 
    //urlConnection.setRequestProperty("Authorization", props.getProperty("document.dynamic.userdefined.Authorization"));
  
  //  urlConnection.setRequestProperty("Content-Length", "0");
    urlConnection.setRequestProperty("SOAPAction", "http://serverName/A3WebServices/Conectar");
	urlConnection.setRequestProperty("Connection", "Keep-Alive");
	urlConnection.setRequestProperty("Content-Type", "text/xml");
	
	urlConnection.setInstanceFollowRedirects(false); 
    
    urlConnection.setUseCaches(false);
	urlConnection.setDoInput(true);
	urlConnection.setDoOutput(true);

	logger = ExecutionUtil.getBaseLogger();
	
	
	
	byte[] out = data.getBytes(StandardCharsets.UTF_8);

	OutputStream stream = urlConnection.getOutputStream();
	stream.write(out);
	logger.info("Log connection response code and response message: " + urlConnection.getResponseCode() + " " + urlConnection.getResponseMessage());
	
	
	//https://crunchify.com/simple-way-to-get-http-response-header-in-java/

	Map<String, List<String>> headerFieldMap = urlConnection.getHeaderFields().get("Set-Cookie");

	for (Map.Entry<String, List<String>> entry : headerFieldMap.entrySet()) {
		logger.info("Headers: " + entry.getKey() + " : " + entry.getValue());
	}
	
	//List<String> cookies = headerFieldMap.get("Set-Cookie");
	List<String> cookies = urlConnection.getHeaderFields().get("Set-Cookie");
	
    for (String cookie: cookies)
    {
        System.out.println(cookie);
        logger.info("Set-Cookie: " + cookie);
    }
	

	//https://stackoverflow.com/questions/8735792/how-to-parse-link-header-from-github-api/46328155#46328155

	String relationExpression = "(?<=rel=\").+?(?=\")";
	String hrefExpression = "(?<=<).+?(?=>)";

	Pattern relationPattern = Pattern.compile(relationExpression, Pattern.CASE_INSENSITIVE);
	Pattern hrefPattern = Pattern.compile(hrefExpression, Pattern.CASE_INSENSITIVE);


	//If there are multiple message-header fields with the same field-name, all the field values
	//will be added to the List corresponding to the same field name, with the field name functioning
	//as the key part in the map entry, and the List of values the value part of the map entry.
	//So the correct way to get all the field values corresponding to the same field name is to
	//get the map entry for it, iterating over the List value.

	List<String> linkHeader = headerFieldMap.get("Link");

	for (String link : linkHeader) {
		Matcher relationMatcher = relationPattern.matcher(link);
		Matcher hrefMatcher = hrefPattern.matcher(link);
		logger.info("RelationMatcher: " + relationMatcher)
		logger.info("HrefMatcher: " + hrefMatcher)

		if (relationMatcher.find() && hrefMatcher.find()){
			props.setProperty("document.dynamic.userdefined.ciastko" + relationMatcher[0].toString(), hrefMatcher[0].toString());
		}

	}
	
	
	dataContext.storeStream(is, props);
}
